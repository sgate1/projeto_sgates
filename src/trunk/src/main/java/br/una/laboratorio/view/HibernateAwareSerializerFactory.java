package br.una.laboratorio.view;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Transient;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;
import org.codehaus.jackson.map.ser.BeanSerializerFactory;
import org.codehaus.jackson.type.JavaType;
import org.hibernate.bytecode.javassist.FieldHandled;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.collection.PersistentMap;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * This is the key class in enabling graceful handling of Hibernate managed
 * entities when serializing them to JSON.
 * <p/>
 * The key features are: 1) Non-initialized properties will be rendered as
 * {@code null} in JSON to prevent "lazy-loaded" exceptions when the Hibernate
 * session is closed. 2) {@link Transient} properties not be rendered at all as
 * they often present back door references to non-initialized properties.
 * 
 * @author Kyrill Alyoshin
 */
public class HibernateAwareSerializerFactory extends BeanSerializerFactory {
	/**
	 * Name of the property added during build-time byte-code instrumentation by
	 * Hibernate. It must be filtered out.
	 */
	private static final String FIELD_HANDLER_PROPERTY_NAME = "fieldHandler";

	@Override
	public JsonSerializer<Object> createSerializer(JavaType type,
			SerializationConfig config) {
		Class<?> clazz = type.getRawClass();

		// check for all Hibernate proxy invariants and build custom serializers
		// for them
		if (PersistentCollection.class.isAssignableFrom(clazz)) {
			return new PersistentCollectionSerializer(type, config);
		}

		if (HibernateProxy.class.isAssignableFrom(clazz)) {
			return new HibernateProxySerializer(type, config);
		}

		// Well, then it is not a Hibernate proxy
		return super.createSerializer(type, config);
	}

	/**
	 * The purpose of this method is to filter out {@link Transient} properties
	 * of the bean from JSON rendering.
	 */
	@Override
	protected List<BeanPropertyWriter> filterBeanProperties(
			SerializationConfig config, BasicBeanDescription beanDesc,
			List<BeanPropertyWriter> props) {

		// filter out standard properties (e.g. those marked with @JsonIgnore)
		props = super.filterBeanProperties(config, beanDesc, props);

		filterInstrumentedBeanProperties(beanDesc, props);

		// now filter out the @Transient ones as they may trigger "lazy"
		// exceptions by
		// referencing non-initialized properties
		List<String> transientOnes = new ArrayList<String>();
		// BeanUtils and AnnotationUtils are utility methods that come from
		// the Spring Framework
		for (PropertyDescriptor pd : BeanUtils.getPropertyDescriptors(beanDesc
				.getBeanClass())) {
			Method getter = pd.getReadMethod();
			if (getter != null
					&& AnnotationUtils.findAnnotation(getter, Transient.class) != null) {
				transientOnes.add(pd.getName());
			}
		}

		// remove transient
		for (Iterator<BeanPropertyWriter> iter = props.iterator(); iter
				.hasNext();) {
			if (transientOnes.contains(iter.next().getName())) {
				iter.remove();
			}
		}

		return props;
	}

	private void filterInstrumentedBeanProperties(
			BasicBeanDescription beanDesc, List<BeanPropertyWriter> props) {

		// all beans that have build-time instrumented lazy-loaded properties
		// will implement FieldHandled interface.
		if (!FieldHandled.class.isAssignableFrom(beanDesc.getBeanClass())) {
			return;
		}

		// remove fieldHandler bean property from JSON serialization as it
		// causes
		// infinite recursion
		for (Iterator<BeanPropertyWriter> iter = props.iterator(); iter
				.hasNext();) {
			if (iter.next().getName().equals(FIELD_HANDLER_PROPERTY_NAME)) {
				iter.remove();
			}
		}
	}

	/**
	 * The purpose of this class is to perform graceful handling of custom
	 * Hibernate collections.
	 */
	private class PersistentCollectionSerializer extends JsonSerializer<Object> {
		private final JavaType type;
		private final SerializationConfig config;

		private PersistentCollectionSerializer(JavaType type,
				SerializationConfig config) {
			this.type = type;
			this.config = config;
		}

		@Override
		@SuppressWarnings("unchecked")
		public void serialize(Object value, JsonGenerator jgen,
				SerializerProvider provider) throws IOException {
			// avoid lazy initialization exceptions
			if (!((PersistentCollection) value).wasInitialized()) {
				jgen.writeNull();
				return;
			}

			// construct an actual serializer from the built-in ones
			
			// Use this on 1.5.x version of Jackson
			//BasicBeanDescription beanDesc = config.introspect(type.getRawClass());
			// Use this on 1.6.0 version of Jackson
			BasicBeanDescription beanDesc = config.introspect(type);
			
			Class<?> clazz = type.getRawClass();

			JsonSerializer<Object> serializer;
			if (PersistentMap.class.isAssignableFrom(clazz)) {
				serializer = (JsonSerializer<Object>) buildMapSerializer(type,
						config, beanDesc);
			} else {
				serializer = (JsonSerializer<Object>) buildCollectionSerializer(
						type, config, beanDesc);
			}

			// delegate serialization to a built-in serializer
			serializer.serialize(value, jgen, provider);
		}
	}

	/**
	 * The purpose of this class is to perform graceful handling of
	 * HibernateProxy objects.
	 */
	private class HibernateProxySerializer extends JsonSerializer<Object> {
		private final JavaType type;
		private final SerializationConfig config;

		private HibernateProxySerializer(JavaType type,
				SerializationConfig config) {
			this.type = type;
			this.config = config;
		}

		@Override
		public void serialize(Object value, JsonGenerator jgen,
				SerializerProvider provider) throws IOException {
			if (((HibernateProxy) value).getHibernateLazyInitializer()
					.isUninitialized()) {
				jgen.writeNull();
				return;
			}

			// Use this on 1.5.x version of Jackson
			//BasicBeanDescription beanDesc = config.introspect(type.getRawClass());
			// Use this on 1.6.0 version of Jackson
			BasicBeanDescription beanDesc = config.introspect(type);
			
			JsonSerializer<Object> serializer = findBeanSerializer(type,
					config, beanDesc);

			// delegate serialization to a build-in serializer
			serializer.serialize(value, jgen, provider);
		}
	}
}

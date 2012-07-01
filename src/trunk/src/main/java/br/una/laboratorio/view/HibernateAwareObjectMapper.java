package br.una.laboratorio.view;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

/**
 * This class extends {@code ObjectMapper} class of the Jackson framework to
 * provide minor customizations:
 * <ul>
 * <li>To set a custom {@link HibernateAwareSerializerFactory}</li>
 * <li>To relax Jackson handling of unknown class types</li>
 * </ul>
 * <p/>
 * <em>Note:</em> Due to the nature {@code ObjectMapper} class those
 * customization could not be done through the Spring Framework.
 * 
 * @author Kyrill Alyoshin
 * @see HibernateAwareSerializerFactory
 */
public class HibernateAwareObjectMapper extends ObjectMapper {

	public HibernateAwareObjectMapper() {
		setSerializerFactory(new HibernateAwareSerializerFactory());
		configure(Feature.FAIL_ON_EMPTY_BEANS, false);
	}

	public void setPrettyPrint(boolean prettyPrint) {
		configure(Feature.INDENT_OUTPUT, prettyPrint);
	}
	
}

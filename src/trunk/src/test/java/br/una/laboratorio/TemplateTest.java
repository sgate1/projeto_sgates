package br.una.laboratorio;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/infrastructure-config.xml" })
public class TemplateTest {

	@Test
	@Ignore
	public void teste() {
		System.out.println("iha");
	}

}
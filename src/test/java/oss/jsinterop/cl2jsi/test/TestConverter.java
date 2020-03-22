package oss.jsinterop.cl2jsi.test;

import org.junit.jupiter.api.Test;

import oss.jsinterop.cl2jsi.Converter;

public class TestConverter {
	
	@Test
	public void testCheckJar() throws Exception {
		Converter converter = new Converter();
		converter.check();
	}

}

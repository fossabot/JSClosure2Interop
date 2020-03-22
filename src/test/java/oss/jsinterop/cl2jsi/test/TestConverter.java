package oss.jsinterop.cl2jsi.test;

import org.junit.jupiter.api.Test;

import oss.jsinterop.cl2jsi.Converter;

public class TestConverter {
	
	@Test
	public void testCheckJar() throws Exception {
		Converter converter = new Converter();
		converter.check();
	}
	
	@Test
	public void testConvert() throws Exception  {
		Converter converter = new Converter();
		String jsFile = "async-2.0.js";
		String jsFilePath = Converter.JS_CODE_DIR + jsFile;
		String dependenciesFilePath = "foo.dep";
		String generatedSourceJarPath = Converter.GEN_CODE_DIR + jsFile +".jar";
		String packageName = "oss.jsinterop.java";
		String className = "HeadClass";
		converter.convert(jsFilePath,dependenciesFilePath, generatedSourceJarPath, packageName, className);
	}

}

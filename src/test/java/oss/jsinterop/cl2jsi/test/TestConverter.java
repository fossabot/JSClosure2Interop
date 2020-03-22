package oss.jsinterop.cl2jsi.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

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
		String jsFilePath = Converter.JS_CODE_DIR + File.separator + jsFile;
		String dependenciesFilePath = Converter.DEP_DIR + File.separator + jsFile + ".dep";
		String generatedSourceJarPath = Converter.JARS_DIR + File.separator + jsFile +".jar";
		String packageName = "oss.jsinterop.java";
		String className = "Async_2_0";
		int returnCode = converter.convert(jsFilePath,dependenciesFilePath, generatedSourceJarPath, packageName, className);
		System.out.println("stdout:\n" + converter.getStdOut());
		System.out.println("stderr:\n" + converter.getStdErr());
		assertEquals(0,returnCode);
		converter.unzipJar(generatedSourceJarPath,Converter.GEN_CODE_DIR);
	}

}

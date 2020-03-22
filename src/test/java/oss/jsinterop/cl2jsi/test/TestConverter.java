package oss.jsinterop.cl2jsi.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.google.googlejavaformat.java.FormatterException;

import oss.jsinterop.cl2jsi.Converter;

public class TestConverter extends TestClass {
	public static final String CONTRIB = "google-closure-compiler" + File.separator + "contrib";
	public static final String NODEJS = CONTRIB  + File.separator + "nodejs";
	public static final String EXTERNS = CONTRIB  + File.separator + "externs";
	public static final String MAPS = EXTERNS + File.separator + "maps";
	
	@Test
	public void testCheckJar() throws Exception {
		Converter converter = new Converter();
		converter.check();
	}
	
	@Test
	public void testConvertAsync_2_0() throws IOException, InterruptedException, FormatterException {
		String jsFile = EXTERNS + File.separator + "async-2.0.js";
		String className = "Async_2_0";
		convert(jsFile,className);
	}
	
	
	@Test
	public void testConvertNodejsPath() throws IOException, InterruptedException, FormatterException {
		String jsFile = NODEJS + File.separator + "path.js";
		String className = "NodejsPath";
		convert(jsFile,className);
	}
	
	
	@Disabled("failing")
	@Test
	public void testConvertNodejsOs() throws IOException, InterruptedException, FormatterException {
		String jsFile = NODEJS + File.separator + "os.js";
		String className = "NodejsOs";
		convert(jsFile,className);
	}
	
	@Disabled("type Object missing")
	@Test
	public void testConvertGeoJson() throws IOException, InterruptedException, FormatterException {
		String jsFile = EXTERNS + File.separator + "geojson.js";
		String className = "GeoJson";
		convert(jsFile,className);
	}
	
	public void convert(String jsFile, String className) throws IOException, InterruptedException, FormatterException {
		Converter converter = new Converter();
		String jsFilePath = Converter.JS_CODE_DIR + File.separator + jsFile;
		String dependenciesFilePath = Converter.DEP_DIR + File.separator + jsFile + ".dep";
		File depFile = new File(dependenciesFilePath);
		if (!depFile.getParentFile().exists()) {
			boolean created = depFile.getParentFile().mkdirs();
			if (!created) {
				throw new IOException("dep file dir not created: " + depFile.getParentFile().getAbsolutePath());
			}
		}
		String generatedSourceJarPath = Converter.JARS_DIR + File.separator + jsFile +".jar";
		File jarFile = new File(generatedSourceJarPath);
		if (!jarFile.getParentFile().exists()) {
			boolean created = jarFile.getParentFile().mkdirs();
			if (!created) {
				throw new IOException("jar dir not created: " + jarFile.getParentFile().getAbsolutePath());
			}
		}
		String packageName = "oss.jsinterop.java";
		int returnCode = converter.convert(jsFilePath,dependenciesFilePath, generatedSourceJarPath, packageName, className);
		System.out.println("stdout:\n" + converter.getStdOut());
		System.out.println("stderr:\n" + converter.getStdErr());
		assertEquals(0,returnCode);
		converter.unzipJar(generatedSourceJarPath,Converter.GEN_CODE_DIR);
	}

}

package oss.jsinterop.cl2jsi.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.google.googlejavaformat.java.FormatterException;

import oss.jsinterop.cl2jsi.Converter;

public class TestConverter extends TestClass {
	public static final String GOOGLE_CLOSURE_COMPILER = "google-closure-compiler";
	//
	public static final String CONTRIB = GOOGLE_CLOSURE_COMPILER  + File.separator + "contrib";
	public static final String NODEJS = CONTRIB  + File.separator + "nodejs";
	public static final String CONTRIB_EXTERNS = CONTRIB  + File.separator + "externs";
	public static final String MAPS = CONTRIB_EXTERNS + File.separator + "maps";
	//
	public static final String EXTERNS = GOOGLE_CLOSURE_COMPILER + File.separator + "externs";
	public static final String BROWSER = EXTERNS + File.separator + "browser";
	
	@Test
	public void testCheckJar() throws Exception {
		Converter converter = new Converter();
		converter.check();
	}
	
	@Test
	public void testConvertAsync_2_0() throws IOException, InterruptedException, FormatterException {
		String jsFile = CONTRIB_EXTERNS + File.separator + "async-2.0.js";
		String className = "Async_2_0";
		convert(jsFile,className);
	}
	
	@Test
	public void testConvertNodejsPath() throws IOException, InterruptedException, FormatterException {
		String jsFile = NODEJS + File.separator + "path.js";
		String className = "NodejsPath";
		convert(jsFile,className);
	}
	
	//failing tests
	
	@Test
	public void testConvertNodejsOs() throws IOException, InterruptedException, FormatterException {
		String jsFile = NODEJS + File.separator + "os.js";
		String className = "NodejsOs";
		convert(jsFile,className);
	}
	
	@Test
	public void testConvertW3cTrustedTypes() throws IOException, InterruptedException, FormatterException {
		String jsFile = BROWSER + File.separator + "w3c_trusted_types.js";
		String className = "W3cTrustedTypes";
		convert(jsFile,className);
	}
	
	@Test
	public void testConvertW3cDom1() throws IOException, InterruptedException, FormatterException {
		String jsFile = BROWSER + File.separator + "w3c_dom1.js";
		List<String> dependencyFiles = Arrays.asList(
				//BROWSER + File.separator + "window.js"
                //,
				//BROWSER + File.separator + "gecko_dom.js"
				//,
				//BROWSER + File.separator + "ie_dom.js"
				//,
				//BROWSER + File.separator + "ie_event.js"
				//,
				//BROWSER + File.separator + "w3c_fileapi.js"
				//,
				//BROWSER + File.separator + "nonstandard_fileapi.js"
				//,
				//BROWSER + File.separator + "w3c_clipboardevent.js"
				//,
				BROWSER + File.separator + "w3c_event.js"
                ,
                BROWSER + File.separator + "w3c_trusted_types.js"
                //,
                //BROWSER + File.separator + "w3c_geometry1.js"
                //,
				//BROWSER + File.separator + "w3c_css.js"
			);
		String className = "W3cDom1";
		convert(jsFile,dependencyFiles,className);
	}
	
	@Test
	public void testConvertW3cDom2() throws IOException, InterruptedException, FormatterException {
		String jsFile = BROWSER + File.separator + "w3c_dom2.js";
		List<String> dependencyFiles = Arrays.asList(
				BROWSER + File.separator + "window.js"
                ,
				BROWSER + File.separator + "gecko_dom.js"
				,
				BROWSER + File.separator + "ie_dom.js"
				,
				BROWSER + File.separator + "ie_event.js"
				,
				BROWSER + File.separator + "w3c_fileapi.js"
				,
				BROWSER + File.separator + "nonstandard_fileapi.js"
				,
				BROWSER + File.separator + "w3c_clipboardevent.js"
				,
				BROWSER + File.separator + "w3c_event.js"
                ,
                BROWSER + File.separator + "w3c_trusted_types.js"
                ,
                BROWSER + File.separator + "w3c_geometry1.js"
                ,
				BROWSER + File.separator + "w3c_dom1.js"
				, 
				BROWSER + File.separator + "w3c_css.js"
			);
		String className = "W3cDom2";
		convert(jsFile,dependencyFiles,className);
	}
	
	@Test
	public void testConvertNodejsMaps_v3_40() throws IOException, InterruptedException, FormatterException {
		String jsFile = MAPS + File.separator + "google_maps_api_v3_40.js";
		List<String> dependencyFiles = Arrays.asList(
				                            //CONTRIB_EXTERNS + File.separator + "chrome_extensions.js"
				                            //,
				                            //BROWSER + File.separator + "gecko_dom.js"
				                            //,
				                            //BROWSER + File.separator + "ie_event.js"
				                            //,
				                            BROWSER + File.separator + "w3c_event.js"
				                            ,
				                            BROWSER + File.separator + "w3c_trusted_types.js"
				                            ,
				                            BROWSER + File.separator + "w3c_geometry1.js"
				                            ,
											BROWSER + File.separator + "w3c_dom1.js"
											, 
				                            BROWSER + File.separator + "w3c_dom2.js"
				                            , 
											BROWSER + File.separator + "w3c_css.js"
											//, 
											//BROWSER + File.separator + "w3c_clipboardevent.js"
											//,
											//BROWSER + File.separator + "nonstandard_fileapi.js"
										);
		String className = "Google_Maps_v3_40";
		convert(jsFile,dependencyFiles,className);
	}
	
	@Disabled("Failing")
	@Test
	public void testConvertNodejsEvents() throws IOException, InterruptedException, FormatterException {
		String jsFile = NODEJS + File.separator + "events.js";
		String className = "NodejsEvents";
		convert(jsFile,className);
	}
	
	@Test
	public void testConvertNodejsQueryString() throws IOException, InterruptedException, FormatterException {
		String jsFile = NODEJS + File.separator + "querystring.js";
		String className = "NodejsQueryString";
		convert(jsFile,className);
	}
	
	@Test
	public void testConvertGeoJson() throws IOException, InterruptedException, FormatterException {
		String jsFile = CONTRIB_EXTERNS + File.separator + "geojson.js";
		String className = "GeoJson";
		convert(jsFile,className);
	}
	
	public void convert(String jsFile, String className) throws IOException, InterruptedException, FormatterException {
		convert(jsFile,null,className);
	}
	
	public void convert(String jsFile, List<String> dependencyFiles, String className) throws IOException, InterruptedException, FormatterException {
		Converter converter = new Converter();
		String jsFilePath = Converter.JS_CODE_DIR + File.separator + jsFile;
		String dependencyFilesList = null;
		if ((dependencyFiles != null) 
				&& (dependencyFiles.size() > 0)) {
			dependencyFilesList = "";
			for (String dependencyFile : dependencyFiles) {
				dependencyFilesList += Converter.JS_CODE_DIR + File.separator + dependencyFile + " ";
			}
		}
		String outputDependencyFilePath = Converter.DEP_DIR + File.separator + jsFile + ".dep";
		String dependencyMappingFilePath = Converter.DEP_DIR + File.separator + jsFile + ".map";
		File depFile = new File(outputDependencyFilePath);
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
		int returnCode = converter.convert(jsFilePath, dependencyFilesList, dependencyMappingFilePath, outputDependencyFilePath, generatedSourceJarPath, packageName, className);
		System.out.println("stdout:\n" + converter.getStdOut());
		System.out.println("stderr:\n" + converter.getStdErr());
		assertEquals(0,returnCode);
		converter.unzipJar(generatedSourceJarPath,Converter.GEN_CODE_DIR);
	}

}

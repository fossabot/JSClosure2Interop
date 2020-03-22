package oss.jsinterop.cl2jsi;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Converter {
	
	public static final String GENERATOR_JAR = "ClosureJsinteropGenerator_deploy.jar";
	public static final String GENERATOR_DIR = "jsinterop-generator-master-2020-03-21";
	public static final String JAR_SUBPATH = "bazel-bin/java/jsinterop/generator/closure";
	
	public Converter() {		
	}
	
	public void check() throws Exception {
		Path workingDir = Paths.get(System.getProperty("user.dir"));
		Path generatorJarPath = Paths.get(workingDir.getParent().getParent().toString() 
				+ File.separator 
				+ GENERATOR_DIR 
				+ File.separator
				+ JAR_SUBPATH 
				+ File.separator
				+ GENERATOR_JAR);
		System.out.println("working dir : " + workingDir.toString());
		System.out.println("jar path: " + generatorJarPath.toString());
		File generatorJar = generatorJarPath.toFile();
		if (!generatorJar.exists()) {
			throw new Exception("jar does not exist: " + generatorJarPath.toString());
		}
	}
	
	public void convert() {
		
	}
}

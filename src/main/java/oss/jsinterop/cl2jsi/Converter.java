package oss.jsinterop.cl2jsi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Converter {
	
	public static final String JS_CODE_DIR = System.getProperty("user.dir") + File.separator + "js";
	public static final String GEN_CODE_DIR = System.getProperty("user.dir") + File.separator + "gen";
	
	public static final String GENERATOR_JAR = "ClosureJsinteropGenerator_deploy.jar";
	public static final String GENERATOR_DIR = "jsinterop-generator-master-2020-03-21";
	public static final String JAR_SUBPATH = "bazel-bin/java/jsinterop/generator/closure";
	
	private File generatorJar = null;
	
	public Converter() {
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
		generatorJar = generatorJarPath.toFile();
	}
	
	public void check() throws Exception {
		
		if (!generatorJar.exists()) {
			throw new Exception("jar does not exist: " + generatorJar.getAbsolutePath().toString());
		}
	}
	
	public void convert(String jsFilePath, String dependenciesFilePath, String generatedSourceJarPath, String packageName, String className) {
		String command = generatorJar.getAbsolutePath()  + "\n"
				+ "  --debug_mode" + "\n"
				+ "  --strict"  + "\n"
				+ "  --output " + generatedSourceJarPath  + "\n"
				+ "  --package_prefix " + packageName  + "\n"
				+ "  --global_scope_class_name "	+ className  + "\n"
				+ "  --output_dependency_file " + dependenciesFilePath  + "\n"
			    + "  " + jsFilePath;
		System.out.println("command: " + command);
		command = command.replaceAll("\\n +", " ");
		System.out.println("command: " + command);
	}
	
	//source: https://www.programcreek.com/2012/08/unzip-a-jar-file-in-java-program/
	public static void unzipJar(String destinationDir, String jarPath) throws IOException {
		File file = new File(jarPath);
		@SuppressWarnings("resource")
		JarFile jar = new JarFile(file);
		// fist get all directories,
		// then make those directory on the destination Path
		for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements();) {
			JarEntry entry = (JarEntry) enums.nextElement();
 
			String fileName = destinationDir + File.separator + entry.getName();
			File f = new File(fileName);
 
			if (fileName.endsWith("/")) {
				f.mkdirs();
			}
		}
		//now create all files
		for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements();) {
			JarEntry entry = (JarEntry) enums.nextElement();
			String fileName = destinationDir + File.separator + entry.getName();
			File f = new File(fileName);
			if (!fileName.endsWith("/")) {
				InputStream is = jar.getInputStream(entry);
				FileOutputStream fos = new FileOutputStream(f);
 
				// write contents of 'is' to 'fos'
				while (is.available() > 0) {
					fos.write(is.read());
				}
				fos.close();
				is.close();
			}
		}
	}
}

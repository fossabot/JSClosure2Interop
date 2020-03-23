package oss.jsinterop.cl2jsi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException; 

public class Converter {
	
	public static final String JS_CODE_DIR = System.getProperty("user.dir") + File.separator + "js";
	public static final String GEN_CODE_DIR = System.getProperty("user.dir") + File.separator + "gen";
	public static final String JARS_DIR = System.getProperty("user.dir") + File.separator + "jars";
	public static final String DEP_DIR = System.getProperty("user.dir") + File.separator + "dep";
	
	public static final String GENERATOR_JAR = "ClosureJsinteropGenerator_deploy.jar";
	public static final String GENERATOR_DIR = "jsinterop-generator-master-2020-03-21";
	public static final String JAR_SUBPATH = "bazel-bin/java/jsinterop/generator/closure";
	
	private File generatorJar = null;
	private String stdOut = null;
	private String stdErr = null;
	
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
	
	public boolean check() throws Exception {
		if (!generatorJar.exists()) {
			throw new IOException("jar does not exist: " + generatorJar.getAbsolutePath().toString());
		}
		return true;
	}
	
	public int convert(String jsFilePath, String dependencyFiles, String dependencyMappingFilePath, String outputDependencyFilePath, String generatedSourceJarPath, String packageName, String className) throws IOException, InterruptedException {
		String command = "java -jar " + generatorJar.getAbsolutePath()  + "\n"
				+ "  --output " + generatedSourceJarPath  + "\n"
				+ "  --package_prefix " + packageName  + "\n"
				+ "  --extension_type_prefix " + "foo"  + "\n" //TODO: understand what it is used for
				+ "  --global_scope_class_name "	+ className  + "\n"
				+ "  --output_dependency_file "	+ outputDependencyFilePath  + "\n"
				//+ "  --dependency_mapping_file " + dependencyMappingFilePath  + "\n"
				;
		if (dependencyFiles != null) {
			command += "  --dependency " + dependencyFiles  + "\n";
		}
		command += "  --debug_mode" + "\n";
		command += "  --strict"  + "\n";
		
		command	+= "  " + jsFilePath; //the file to transpile
		System.out.println("command: " + command);
		command = command.replaceAll("\\n +", " ");
		//System.out.println("command: " + command);
		Runtime runtime = Runtime.getRuntime();
		Process process =  runtime.exec(command);
		System.out.println("waiting for command execution...");
	    process.waitFor();
		int returnCode = process.exitValue();
		InputStream stdoutStream = process.getInputStream();
		stdOut = new BufferedReader(new InputStreamReader(stdoutStream)).lines().collect(Collectors.joining("\n"));
		InputStream stderrStream = process.getErrorStream();
		stdErr = new BufferedReader(new InputStreamReader(stderrStream)).lines().collect(Collectors.joining("\n"));
		return returnCode;
	}
	
	public void unzipJar(String jarPath, String destinationDirPath) throws IOException, FormatterException {
		File jarFile = new File(jarPath);
		File destDir = new File(destinationDirPath);
		System.out.println("jar to unzip: " + jarFile.getAbsolutePath());
		System.out.println("dest dir for jar unzip: " + destDir.getAbsolutePath());
		//check and setup desintation dir
		if (!destDir.exists()) {
			destDir.mkdirs();
		} 
		if (!destDir.isDirectory()) {
			throw new IOException("not a directory: " + destDir.getAbsolutePath());
		}
		//
		@SuppressWarnings("resource")
		JarFile jar = new JarFile(jarFile);
		ArrayList<JarEntry> jarEntries = Collections.list(jar.entries());
		for (JarEntry jarEntry : jarEntries) {
			String fileName = destinationDirPath + File.separator + jarEntry.getName();
			if (!fileName.contains("META-INF")) { //avoid jar specific files
				System.out.println("file name: " + fileName);
				File file = new File(fileName);
				if (!file.getParentFile().exists()) {
					boolean created = file.getParentFile().mkdirs();
					if (!created) {
						throw new IOException("parent dir not created: " + file.getParentFile().getAbsolutePath());
					}
				}
				InputStream is = jar.getInputStream(jarEntry);
				FileOutputStream fos = new FileOutputStream(file);
				while (is.available() > 0) {
					fos.write(is.read());
				}
				fos.close();
				is.close();
				if (fileName.endsWith(".java")) {
					formatJava(fileName);
				}
			}
		}
	}
	
	public void formatJava(String javaFilePath) throws FormatterException, IOException {
		Path path = Paths.get(javaFilePath);
		String javaSource = new String(Files.readAllBytes(path));
		String formattedSource = new Formatter().formatSource(javaSource);
		Files.write(path, formattedSource.getBytes());
	}

	public String getStdOut() {
		return stdOut;
	}

	public String getStdErr() {
		return stdErr;
	}	
}

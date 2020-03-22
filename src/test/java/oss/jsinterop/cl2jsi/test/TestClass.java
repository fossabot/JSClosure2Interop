package oss.jsinterop.cl2jsi.test;

import static org.junit.Assume.assumeTrue;

import org.junit.jupiter.api.BeforeEach;

import oss.jsinterop.cl2jsi.Converter;

public class TestClass {
	
	@BeforeEach
	public void setup() throws Exception {
		assumeTrue((System.getenv("GITHUB_ACTION") == null)
				&& (System.getenv("GITHUB_WORKFLOW") == null)); //not running on Github
		Converter converter = new Converter();
		converter.check();
	}

}

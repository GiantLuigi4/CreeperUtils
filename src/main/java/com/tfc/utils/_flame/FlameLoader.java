package com.tfc.utils._flame;

import com.tfc.utils.Files;
import com.tfc.flame.FlameURLLoader;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class FlameLoader {
	private final FlameURLLoader loader;
	
	public FlameLoader(FlameURLLoader loader) {
		this.loader = loader;
	}
	
	public FlameLoader(String jarName) throws MalformedURLException {
		loader = new FlameURLLoader(new URL[]{new File(Files.dir + "\\" + jarName).toURL()});
	}
	
	public void addDep(File path) throws MalformedURLException {
		loader.addURL(path.toURL());
	}
	
	public void addDep(URL path) {
		loader.addURL(path);
	}
	
	public void addDep(String path) throws MalformedURLException {
		loader.addURL(new File(path).toURL());
	}
	
	public Class<?> load(String Class) throws ClassNotFoundException {
		return loader.loadClass(Class);
	}
	
	public InputStream getResourceAsStream(String resource) {
		return loader.getResourceAsStream(resource);
	}
}

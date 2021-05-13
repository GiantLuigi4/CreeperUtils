package tfc.utils.groovy;

import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A wrapper for a groovy class loader
 */
public class GroovyLoader {
	private final GroovyClassLoader loader;
	
	public GroovyLoader(GroovyClassLoader loader) {
		this.loader = loader;
	}
	
	public GroovyLoader() {
		loader = new GroovyClassLoader();
	}
	
	public Class<?> load(String Class) throws ClassNotFoundException {
		return loader.loadClass(Class);
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
	
	public void addClassPath(String path) {
		loader.addClasspath(path);
	}
	
	public InputStream getResourceAsStream(String resource) {
		return loader.getResourceAsStream(resource);
	}
}

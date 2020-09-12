package com.tfc.utils.groovy.flame;

import com.tfc.flame.FlameURLLoader;
import com.tfc.utils.Files;
import com.tfc.utils.flame.FlameLoader;
import com.tfc.utils.flame.GroovyFlameLoader;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class GroovyFlameClassLoader extends FlameLoader {
	private final GroovyFlameLoader loader;
	
	public GroovyFlameClassLoader(GroovyFlameLoader loader) {
		super((FlameURLLoader) null);
		this.loader = loader;
	}
	
	public GroovyFlameClassLoader(URL[] urls) {
		super((FlameURLLoader) null);
		loader = new GroovyFlameLoader(urls);
	}
	
	public GroovyFlameClassLoader(String jarName) throws MalformedURLException {
		super((FlameURLLoader) null);
		loader = new GroovyFlameLoader(new URL[]{new File(Files.dir + "\\" + jarName).toURL()});
	}
	
	@Override
	public void addDep(File path) throws MalformedURLException {
		loader.addURL(path.toURL());
	}
	
	@Override
	public void addDep(URL path) {
		loader.addURL(path);
	}
	
	@Override
	public void addDep(String path) throws MalformedURLException {
		loader.addURL(new File(path).toURL());
	}
	
	@Override
	public Class<?> load(String Class) throws ClassNotFoundException {
		return loader.loadClass(Class);
	}
	
	@Override
	public InputStream getResourceAsStream(String resource) {
		return loader.getResourceAsStream(resource);
	}
}

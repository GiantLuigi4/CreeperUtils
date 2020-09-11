package com.tfc.utils._flame.dependency_management;

import com.tfc.flame.FlameConfig;
import com.tfc.flame.FlameURLLoader;

import com.tfc.utils._flame.FlameLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class Manager {
	private final FlameLoader loader;
	
	public FlameLoader getLoader() {
		return loader;
	}
	
	public Manager(FlameLoader loader) {
		this.loader = loader;
	}
	
	public Manager(String jarName) throws MalformedURLException {
		this.loader = new FlameLoader(jarName);
	}
	
	public static Manager constructFromDependencies(Class<? extends FlameLoader> loader, String URLDeps, String gradleLikeDeps, File[] otherDeps) throws MalformedURLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		ArrayList<URL> urls = new ArrayList<>();
		String repo = "";
		for (String s : gradleLikeDeps.split(",")) {
			try {
				if (repo.equals("")) {
					repo = s;
				} else {
					System.out.println(repo);
					System.out.println(s);
					String s1 = s.replace("", "" + File.separatorChar);
					String[] info = s1.split(":", 3);
					info[2] = info[2].replace("" + File.separatorChar, "");
					s1 = info[0] + File.separatorChar + info[2] + File.separatorChar + info[1] + File.separatorChar + info[1] + "-" + info[2] + ".jar";
					String urlS = info[0] + File.separatorChar + info[1] + File.separatorChar + info[2] + File.separatorChar + info[1] + "-" + info[2] + ".jar";
					File output = new File((com.tfc.utils.Files.dir + File.separatorChar + "libs" + File.separatorChar + s1));
					if (!output.exists()) {
						output.getParentFile().mkdirs();
						output.createNewFile();
						
						String url = repo + (urlS);
						try {
							URL url1 = new URL(url.replace("" + File.separatorChar, "/"));
							System.out.println(url1.toString());
							//https://stackabuse.com/how-to-download-a-file-from-a-url-in-java/
							Files.copy(url1.openStream(), output.toPath(), StandardCopyOption.REPLACE_EXISTING);
						} catch (Throwable err) {
							FlameConfig.logError(err);
						}
					}
					urls.add(output.toURL());
					repo = "";
				}
			} catch (Throwable err) {
				FlameConfig.logError(err);
				repo = "";
			}
		}
		
		String file = "";
		for (String s : URLDeps.split(",")) {
			try {
				if (file.equals("")) {
					file = s;
				} else {
					File output = new File((com.tfc.utils.Files.dir + File.separatorChar + file.replace("/", "" + File.separatorChar)));
					if (!output.exists()) {
						output.getParentFile().mkdirs();
						output.createNewFile();
						
						String url = s;
						try {
							URL url1 = new URL(url.replace("" + File.separatorChar, "/"));
							System.out.println(url1.toString());
							//https://stackabuse.com/how-to-download-a-file-from-a-url-in-java/
							Files.copy(url1.openStream(), output.toPath(), StandardCopyOption.REPLACE_EXISTING);
						} catch (Throwable err) {
							FlameConfig.logError(err);
						}
					}
					urls.add(output.toURL());
					file = "";
				}
			} catch (Throwable err) {
				FlameConfig.logError(err);
				file = "";
			}
		}
		
		for (File f : otherDeps) urls.add(f.toURL());
		
		URL[] urls1 = new URL[urls.size()];
		for (int i = 0; i < urls.size(); i++) urls1[i] = urls.get(i);
		return new Manager(loader.getConstructor(URL[].class).newInstance((Object) urls1));
	}
	
	public Manager(FlameURLLoader loader) {
		this.loader = new FlameLoader(loader);
	}
	
	public void add(String dependencies) {
		String repo = "";
		for (String s : dependencies.split(",")) {
			try {
				if (repo.equals("")) {
					repo = s;
				} else {
					System.out.println(repo);
					System.out.println(s);
					String s1 = s.replace("", "" + File.separatorChar);
					String[] info = s1.split(":", 3);
					info[2] = info[2].replace("" + File.separatorChar, "");
					s1 = info[0] + File.separatorChar + info[2] + File.separatorChar + info[1] + File.separatorChar + info[1] + "-" + info[2] + ".jar";
					String urlS = info[0] + File.separatorChar + info[1] + File.separatorChar + info[2] + File.separatorChar + info[1] + "-" + info[2] + ".jar";
					File output = new File((com.tfc.utils.Files.dir + File.separatorChar + "libs" + File.separatorChar + s1));
					if (!output.exists()) {
						output.getParentFile().mkdirs();
						output.createNewFile();
						
						String url = repo + (urlS);
						try {
							URL url1 = new URL(url.replace("" + File.separatorChar, "/"));
							System.out.println(url1.toString());
							//https://stackabuse.com/how-to-download-a-file-from-a-url-in-java/
							Files.copy(url1.openStream(), output.toPath(), StandardCopyOption.REPLACE_EXISTING);
						} catch (Throwable err) {
							FlameConfig.logError(err);
						}
					}
					loader.addDep(output.toURL());
					repo = "";
				}
			} catch (Throwable err) {
				FlameConfig.logError(err);
				repo = "";
			}
		}
	}
	
	public void addFromURL(String dependencies) {
		String file = "";
		for (String s : dependencies.split(",")) {
			try {
				if (file.equals("")) {
					file = s;
				} else {
					File output = new File((com.tfc.utils.Files.dir + File.separatorChar + file.replace("/", "" + File.separatorChar)));
					if (!output.exists()) {
						output.getParentFile().mkdirs();
						output.createNewFile();
						
						String url = s;
						try {
							URL url1 = new URL(url.replace("" + File.separatorChar, "/"));
							System.out.println(url1.toString());
							//https://stackabuse.com/how-to-download-a-file-from-a-url-in-java/
							Files.copy(url1.openStream(), output.toPath(), StandardCopyOption.REPLACE_EXISTING);
						} catch (Throwable err) {
							FlameConfig.logError(err);
						}
					}
					loader.addDep(output.toURL());
					file = "";
				}
			} catch (Throwable err) {
				FlameConfig.logError(err);
				file = "";
			}
		}
	}
}

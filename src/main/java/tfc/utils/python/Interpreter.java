package tfc.utils.python;

import org.nfunk.jep.JEP;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

//TODO
public class Interpreter {
	private final ClassLoader loader;
	private final JEP jep = new JEP();
	
	public Interpreter(ClassLoader loader) {
		this.loader = loader;
	}
	
	public void read(String name) throws IOException {
		URL url = loader.getResource(name);
		InputStream stream = url.openStream();
		stream.close();
	}
}

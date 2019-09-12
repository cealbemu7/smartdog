package co.com.smart.dog.utility;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.axis.encoding.Base64;

import co.com.smart.dog.infraestructure.dto.SmartExcepcionSerializada;

/**
 * Clase utilidad para el contexto Smart
 * 
 * @author SmartJugle S.A.S
 *
 */
public class SmartUtilities implements Serializable {
	
	private static final ResourceBundle PROPERTIES = ResourceBundle.getBundle("co.com.smart.dog.properties.smart-config");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String encodeString(String value) {
		return Base64.encode(value.getBytes());
	}

	public static String decodeToString(String value) throws IOException {
		return decodeToString(value, false);
	}

	public static String decodeToString(String value, boolean separatorChar)
			throws SmartExcepcionSerializada {
		try {
			return new String(Base64.decode(separatorChar ? cleanString(value)
					: value), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new SmartExcepcionSerializada(e.getMessage());
		}
	}

	public static byte[] decodeToByte(String value) {
		return Base64.decode(value);
	}

	private static String cleanString(String value) {
		String real = "";
		real += value.substring(value.indexOf("[") + 1, value.indexOf("]"));
		return real;
	}
	
	@SuppressWarnings("rawtypes")
	public static Class[] getClasses(String packageName)
			throws ClassNotFoundException, IOException {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        
        return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * Recursive method used to find all classes in a given directory and
	 * subdirs.
	 *
	 * @param directory
	 *            The base directory
	 * @param packageName
	 *            The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("rawtypes")
	private static List<Class> findClasses(File directory, String packageName)
			throws ClassNotFoundException {
		
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file,
						packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName
						+ '.'
						+ file.getName().substring(0,
								file.getName().length() - 6)));
			}
		}
		return classes;
	}
	
	/**
	 * Obtiene las propiedades del contexto de la aplicacion
	 * @param key
	 * @return
	 */
	public static String getProperty(String key){
		return PROPERTIES.getString(key);
	}

}

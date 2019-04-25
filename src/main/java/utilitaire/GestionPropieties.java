package utilitaire;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GestionPropieties {

	public final static String DEFAULT_PROPERTIES_FILE_XML = "../Proprieties/proprieties.xml";

	private static GestionPropieties instance;
	private Properties properties;

	private GestionPropieties() {
		properties = new Properties();
	}

	public static GestionPropieties getInstance() {
		if (instance == null) {
			instance = new GestionPropieties();
			init();
		}
		return instance;
	}

	public void loadProperties() {
		Properties xmlProperties = new Properties();
		InputStream inXML = null;
	
			inXML = this.getClass().getResourceAsStream(DEFAULT_PROPERTIES_FILE_XML);

			try {
				xmlProperties.loadFromXML(inXML);
				properties.putAll(xmlProperties);
				inXML.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public int getIntProperty(String name) {
		String prop = properties.getProperty(name);
		int retVal = -1;
		if (prop != null) {
			retVal = Integer.parseInt(prop);
		}
		return retVal;
	}

	public float getFloatProperty(String name) {
		String prop = properties.getProperty(name);
		float retVal = -1;
		if (prop != null) {
			retVal = Float.parseFloat(prop);
		}
		return retVal;
	}

	public String getStringProperty(String name) {
		String prop = properties.getProperty(name);
		String retVal = "";
		if (prop != null) {
			retVal = prop;
		}
		return retVal;
	}

	public static void init() {
		instance.properties.clear();
		instance.loadProperties();
	}

}

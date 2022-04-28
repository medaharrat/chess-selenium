package dataprovider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	
	private Properties properties;
	private final String propertyFilePath= "configs//Configuration.properties";

	
	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public long getImplicitWait() {		
		String implicitWait = properties.getProperty("implicitWait");
		if(implicitWait != null) return Long.parseLong(implicitWait);
		else throw new RuntimeException("implicitWait not specified in the Configuration.properties file.");		
	}

	public String getBrowser() {		
		String browser = properties.getProperty("browser");
		if(browser != null) return browser;
		else throw new RuntimeException("Browser not specified in the Configuration.properties file.");		
	}
	
	public String getURL() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file.");
	}

	public String getUsername() {
		String username = properties.getProperty("username");
		if(username != null) return username;
		else throw new RuntimeException("username not specified in the Configuration.properties file.");
	}

	public String getPassword() {
		String password = properties.getProperty("password");
		if(password != null) return password;
		else throw new RuntimeException("password not specified in the Configuration.properties file.");
	}

	public String getCountryCode() {
		String countryCode = properties.getProperty("countryCode");
		if(countryCode != null) return countryCode;
		else throw new RuntimeException("countryCode not specified in the Configuration.properties file.");
	}

	public String getFirstName() {
		String firstname = properties.getProperty("firstname");
		if(firstname != null) return firstname;
		else throw new RuntimeException("firstname not specified in the Configuration.properties file.");
	}

	public String getLastName() {
		String lastname = properties.getProperty("lastname");
		if(lastname != null) return lastname;
		else throw new RuntimeException("lastname not specified in the Configuration.properties file.");
	}
}
package ch.daniel.todobackend.dao;

public class ApplicationProperties {
	
	public static boolean isTestMode() {
		return Boolean.valueOf(System.getProperty("TEST_MODE", "true"));
	}
}

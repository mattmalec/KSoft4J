package net.explodingbush.ksoftapi.utils;

public class Checks {

	public static void notNull(Object object, String name) {
		if(object == null) {
			throw new IllegalArgumentException(name + " cannot be null");
		}
	}
	public static void positive(int num, String name) {
		if(num < 1) {
			throw new IllegalArgumentException(name + " cannot be smaller than 1");
		}
	}
	public static void notBlank(String s, String name){
		notNull(s, name);
		if(s.equals("")){
			throw new IllegalArgumentException(name + " cannot be empty!");
		}
	}
	public static void positive(double num, String name) {
		if(num <= 0) {
			throw new IllegalArgumentException(name + " must be larger that 0");
		}
	}
}

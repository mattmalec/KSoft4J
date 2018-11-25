package net.explodingbush.ksoftapi.entities;

public class Kumo {

	private String token;
	
	public Kumo(String token) {
		this.token = token;
	}
	
	public KumoWeatherAction getWeather() {
		return new KumoWeatherAction(token);
	}
	public KumoCurrencyAction getCurrency() {
		return new KumoCurrencyAction(token);
	}
}

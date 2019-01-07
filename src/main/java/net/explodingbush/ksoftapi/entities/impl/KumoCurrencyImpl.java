package net.explodingbush.ksoftapi.entities.impl;

import java.util.Currency;

import org.json.JSONObject;

import net.explodingbush.ksoftapi.entities.KumoCurrency;

public class KumoCurrencyImpl implements KumoCurrency {

	private Currency to, from;
	private double converted, original;
	private String pretty;
	
	public KumoCurrencyImpl(JSONObject json, Currency to, Currency from, double original){
		this.to = to;
		this.from = from;
		this.original = original;
		this.converted = json.getDouble("value");
		this.pretty = json.getString("pretty");
	}
	@Override
	public Currency to() {
		return to;
	}

	@Override
	public Currency from() {
		return from;
	}

	@Override
	public double originalAmount() {
		return original;
	}

	@Override
	public double convertedAmount() {
		return converted;
	}

	@Override
	public String convertedAmountPretty() {
		return pretty;
	}

}

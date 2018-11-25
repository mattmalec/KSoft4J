package net.explodingbush.ksoftapi.entities;

import java.util.Currency;

import org.json.JSONObject;

import net.explodingbush.ksoftapi.KSoftAction;
import net.explodingbush.ksoftapi.entities.impl.KumoCurrencyImpl;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.utils.Checks;
import net.explodingbush.ksoftapi.utils.JSONBuilder;

public class KumoCurrencyAction implements KSoftAction<KumoCurrency>{

	private String token;
	private Currency from, to;
	private double amount;
	
	protected KumoCurrencyAction(String token){
		this.token = token;
		this.amount = 0.0;
		this.to = Currency.getInstance("USD");
		this.from = Currency.getInstance("USD");
	}
	public KumoCurrencyAction from(String code){
		return this.from(Currency.getInstance(code));
	}
	public KumoCurrencyAction from(Currency currency){
		Checks.notNull(currency, "currency");
		this.from = currency;
		return this;
	}
	public KumoCurrencyAction to(String code){
		return this.to(Currency.getInstance(code));
	}
	public KumoCurrencyAction to(Currency currency){
		Checks.notNull(currency, "currency");
		this.to = currency;
		return this;
	}
	public KumoCurrencyAction amount(double amount){
		Checks.positive(amount, "amount");
		this.amount = amount;
		return this;
	}
	@Override
	public KumoCurrency execute() {
		if(to.equals(from)){
			throw new IllegalStateException("Cannot convert a currency to itself!");
		}
		JSONObject json = new JSONBuilder().requestKsoft(String.format(Routes.KUMO_CURRENCY.toString(), from.getCurrencyCode(), to.getCurrencyCode(), amount), token);
		return new KumoCurrencyImpl(json, to, from, amount);
	}

}

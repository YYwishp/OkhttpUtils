package com.example.gyx.okhttputils;

/**
 * Created by GYX on 2017/10/27.
 */
public class GloableBean {
	
	private String buy;
	private String last;
	private String sell;
	private String symbol;
	
	private String refreshrate;
	private String updatedAt;
	
	public String getBuy() {
		return buy;
	}
	
	public void setBuy(String buy) {
		this.buy = buy;
	}
	
	public String getLast() {
		return last;
	}
	
	public void setLast(String last) {
		this.last = last;
	}
	
	public String getSell() {
		return sell;
	}
	
	public void setSell(String sell) {
		this.sell = sell;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getRefreshrate() {
		return refreshrate;
	}
	
	public void setRefreshrate(String refreshrate) {
		this.refreshrate = refreshrate;
	}
	
	public String getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}

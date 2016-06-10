package org.cdac.aai.classes;

//The class needs an argument-less c'tor for init
public class Doc {
	public Doc() {
		super();

	}

	@Override
	public String toString() {
		return "{\"content\":\"" + content + "\", \"id\":\"" + id
				+ "\", \"title\":\"" + title + "\", \"boost\":\"" + boost
				+ "\", \"tstamp\":\"" + tstamp + "\", \"url\":\"" + url + "\"}";
	}

	public String content;
	public String id;
	public String title;
	public String boost;
	public String tstamp;
	public String url;
}

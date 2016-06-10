package org.cdac.aai.classes;



import java.util.List;

//The class needs an argument-less c'tor for init
public class Response {
	public Response() {
		super();

	}

	public int numFound;
	public int start;
	public List<Doc> docs;
}
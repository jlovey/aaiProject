package org.cdac.aai.Daos;

import java.net.URL;
import java.util.Scanner;

import org.cdac.aai.classes.SolrResult;
import org.cdac.aai.pojos.QueryPojo;

import com.google.gson.Gson;

public class ProcessingDao {

	private String solrQuery;
	private String solrParams;
	private String solrResponse;

	private QueryPojo queryPojo;
	SolrResult result;
	private String solrout;

	private boolean setDefaultParams(QueryPojo queryPojo, String s) {
		switch (s) {
		case "userStartPoint":
			queryPojo.setStart("0");
			break;
		case "userMaxRows":
			queryPojo.setRows("10");
			break;
		case "userFormatType":
			queryPojo.setWt("json");
			break;
		default:
			System.out.println("Wrong Choice");
		}

		return false;
	}

	public boolean queryIntialization(String userQuery, String userStartPoint,
			String userMaxRows, String userFormatType) {
		try {
			/* Init a query pojo object for seperately storing a query parameter */
			queryPojo = new QueryPojo();
			if (userStartPoint.equals("")) {
				setDefaultParams(queryPojo, "userStartPoint");
			} else {
				queryPojo.setStart(userStartPoint);
			}
			if (userMaxRows.equals("")) {
				setDefaultParams(queryPojo, "userMaxRows");
			} else {
				queryPojo.setRows(userMaxRows);
			}
			if (userFormatType.equals("")) {
				setDefaultParams(queryPojo, "userFormatType");
			} else {
				queryPojo.setWt(userFormatType);
			}
			if (userQuery != null && !userQuery.equals("")) {

				queryPojo.setQ(userQuery);
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean queryProcessing() {
		try {

			return true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	public boolean queryResponse() {
		try {
			/* Config from Property File */
			String server = "sp-hadoop5.pune.cdac.in";
			String port = "8983";
			String collection = "collection1";
			String urlForSolrResponse = "http://" + server + ":" + port
					+ "/solr/" + collection + "/";
			try {
				System.out.println("Query to solr:" + queryPojo.getQ());
				System.out.println("Query Params to solr: ");
				solrQuery = "select?q=" + queryPojo.getQ() + "&wt="
						+ queryPojo.getWt() + "&indent=true&rows="
						+ queryPojo.getRows();
			} catch (NullPointerException e) {
				e.printStackTrace();
				System.out.println("Query not init,setting it to default");
				solrQuery = "select?q=*%3A*&wt=json&indent=true&rows=10";
			}
			this.solrout = new Scanner(
					new URL(urlForSolrResponse + solrQuery).openStream(),
					"UTF-8").useDelimiter("\\A").next();
			return true;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;

	}

	public SolrResult getSolrResult() {
		this.result = new Gson().fromJson(this.solrout, SolrResult.class);
		return this.result;
	}
}

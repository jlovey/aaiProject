package org.webservices;

import org.cdac.aai.Daos.ProcessingDao;
import org.cdac.aai.classes.Doc;
import org.cdac.aai.classes.SolrResult;

public class NewsSearchService {
	public String getNewsResult(String query, String startRow, String maxRow,
			String format) {
		ProcessingDao processingDao = new ProcessingDao();
		String q = query != null ? query : "*.*";
		String s = startRow != null ? startRow : "";
		String m = maxRow != null ? maxRow : "";
		String f = format != null ? format : "";
		System.out.println("Got input: q:" + q + " s:" + s + " m:" + m + " f:"
				+ f);
		String response = "{\"response\":[";
		int i = 1;
		try {
			if (!processingDao.queryIntialization(q, s, m, f))
				throw new Exception();

			if (!processingDao.queryProcessing())
				throw new Exception();

			if (!processingDao.queryResponse())
				throw new Exception();
			SolrResult solrResult = processingDao.getSolrResult();
			if (solrResult != null) {

				for (Doc d : solrResult.response.docs) {
					if (solrResult.response.docs.size() != i) {

						response += d + ",";
					} else {

						response += d;
					}
					i++;
				}
			} else {
				response += "\"result\":\"No Result Found\"";
			}
			response += "]}";
		} catch (Exception e) {
			e.printStackTrace();
			response += "\"result\":\"No Result Found\""+"]}";
		}
		System.out.println("Response: " + response);
		return response;

	}
}

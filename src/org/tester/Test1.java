package org.tester;

import org.cdac.aai.Daos.ProcessingDao;
import org.cdac.aai.classes.Doc;
import org.cdac.aai.classes.SolrResult;

public class Test1 {

	public static void main(String[] args) {
		ProcessingDao processingDao = new ProcessingDao();
		int i = 1;
		try {
			if (!processingDao.queryIntialization("asian", "", "", ""))
				throw new Exception();

			if (!processingDao.queryProcessing())
				throw new Exception();

			if (!processingDao.queryResponse())
				throw new Exception();
			SolrResult solrResult = processingDao.getSolrResult();
			if (solrResult != null)

				System.out.println("{[");
			for (Doc d : solrResult.response.docs) {
				if (solrResult.response.docs.size() != i)
					System.out.println(d + ",");
				else
					System.out.println(d);
				i++;
			}
			System.out.println("]}");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

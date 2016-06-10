package org.tester;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.webservices.NewsSearchServiceCallbackHandler;
import org.webservices.NewsSearchServiceStub;
import org.webservices.NewsSearchServiceStub.GetNewsResult;
import org.webservices.NewsSearchServiceStub.GetNewsResultResponse;



public class Test1 {

	public static void main(String[] args) throws RemoteException {
		NewsSearchServiceStub stub=new NewsSearchServiceStub();
		GetNewsResult getNewsResult=new GetNewsResult();
		getNewsResult.setQuery("India");
		getNewsResult.setStartRow("0");
		getNewsResult.setMaxRow("1");
		GetNewsResultResponse response=new GetNewsResultResponse();
		response=stub.getNewsResult(getNewsResult);
		System.out.println("Response recived: "+response.get_return());
	}

}

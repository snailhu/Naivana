package com.nirvana.test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.junit.Test;
import com.google.gson.Gson;
import com.nirvana.erp.wsdl.WsdlOperator;

public class TestCom {

	@Test
	public void requestImpOrder() {

		try {
			String str = WsdlOperator.requestImportOrder("CUSTOMER_NO~G00249^SHIP_ADDR_NO~1^END_ORDER~^PART_NO~1401^BUY_QTY_DUE~30^PART_NO~1221^BUY_QTY_DUE~100^");
			System.out.println(str);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void gson() {

		Gson gson = new Gson();

		Abc abc = new Abc();
		List<Domain> domains = new ArrayList<TestCom.Domain>();
		domains.add(new Domain());
		domains.add(new Domain());
		domains.add(new Domain());
		domains.add(new Domain());
		abc.setDomains(domains);
		String gs = gson.toJson(abc);

		System.out.println(gs);

		Abc abc2 = gson.fromJson(gs, Abc.class);

		System.out.println(abc.getDomains().size());
	}

	class Abc {

		private List<Domain> domains;

		public List<Domain> getDomains() {
			return domains;
		}

		public void setDomains(List<Domain> domains) {
			this.domains = domains;
		}

	}

	class Domain {

		private int a = 5;

		private String b = "woca";

		public Domain() {
		}

		public int getA() {
			return a;
		}

		public void setA(int a) {
			this.a = a;
		}

		public String getB() {
			return b;
		}

		public void setB(String b) {
			this.b = b;
		}

	}

}

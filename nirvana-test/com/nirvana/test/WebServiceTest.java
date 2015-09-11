package com.nirvana.test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class WebServiceTest {

	private URL url;
	private HttpURLConnection conn;
	public WebServiceTest() throws Exception{
		url = new URL("http://221.226.124.37:8092");
		conn = (HttpURLConnection) url.openConnection();
		//save();//����������һ��ֻ���Ե���һ��
		allUsers();
	}
	/**
	 * ���ӱ���һ���û�
	 */
	public void save() throws Exception{
		String name = "����";//���Խ��ն�̬����
		Integer age = 100;	//���Խ��ն�̬����
		String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "+
					  "xmlns:q0=\"http://ws2.itcast.cn/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "+
				      "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"+
					  "<soapenv:Body><q0:save><arg0><age>"+age+"</age><name>"+name+"</name></arg0></q0:save>"+
				      "</soapenv:Body></soapenv:Envelope>";
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type","text/xml;charset=UTF-8");
		OutputStream out = conn.getOutputStream();
		out.write(soap.getBytes("UTF-8"));
		out.flush();
		if(conn.getResponseCode()==200){
			InputStream in = conn.getInputStream();
			byte[] b = new byte[1024];
			int len = 0;
			StringBuilder sb = new StringBuilder();
			while((len=in.read(b))!=-1){
				String s = new String(b,0,len,"UTF-8");
				sb.append(s);
			}
			System.err.println("���صĽ���ǣ�\n"+sb.toString());  //�����ٴ�ʹ��dom4j���н���
		}
	}
	/**
	 * ��ʾ�����û�
	 */
	public void allUsers() throws Exception{
		String soap = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
						  "<soap:Body>"+
						    "<GetCustomerInfo xmlns=\"http://tempuri.org/\">"+
						      "<customer_no>"+"G10357"+"</customer_no>"+
						    "</GetCustomerInfo>"+
						  "</soap:Body>"+
						"</soap:Envelope>";
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type","text/xml;charset=UTF-8");
		OutputStream out = conn.getOutputStream();
		out.write(soap.getBytes("UTF-8"));
		out.flush();
		if(conn.getResponseCode()==200){
			InputStream in = conn.getInputStream();
			byte[] b = new byte[1024];
			int len = 0;
			StringBuilder sb = new StringBuilder();
			while((len=in.read(b))!=-1){
				String s = new String(b,0,len,"UTF-8");
				sb.append(s);
			}
			System.err.println("���صĽ���ǣ�\n"+sb.toString());  //�����ٴ�ʹ��dom4j���н���
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		new WebServiceTest();
	}

}

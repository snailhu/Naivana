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
		//save();//两个方法，一次只可以调用一个
		allUsers();
	}
	/**
	 * 增加保存一个用户
	 */
	public void save() throws Exception{
		String name = "王健";//可以接收动态参数
		Integer age = 100;	//可以接收动态参数
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
			System.err.println("返回的结果是：\n"+sb.toString());  //可以再次使用dom4j进行解析
		}
	}
	/**
	 * 显示所有用户
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
			System.err.println("返回的结果是：\n"+sb.toString());  //可以再次使用dom4j进行解析
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		new WebServiceTest();
	}

}

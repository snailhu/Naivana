package com.nirvana.erp.wsdl;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

public class WsdlOperator {

	public static String endPoint = "http://221.226.124.37:8096/WebService.asmx?WSDL";

	public static String request(String operation, String namespace, String parameter, String uri, String arg) throws ServiceException, RemoteException {
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(endPoint);
		call.setOperationName(new QName(namespace, operation));// WSDL���������Ľӿ�����
		call.addParameter(parameter, XMLType.SOAP_STRING, ParameterMode.IN);// �ӿڵĲ���
		call.setReturnType(XMLType.SOAP_STRING);// ���÷�������
		call.setSOAPActionURI(uri);
		String result = (String) call.invoke(new Object[] { arg });
		return result;
	}

	public static String requestImportOrder(String arg) throws RemoteException, ServiceException {
		return request("ImpAppOrder", "http://www.apporder.com/Su", "attr", "http://www.apporder.com/ImprtErpOrder", arg);
	}

}

package com.nirvana.service;

import java.io.IOException;
import java.io.InputStream;

public interface OperateSdoExcelService {

	/**
	 * Excel�ϴ�
	 * 
	 * @param is
	 *            �ļ�������
	 * @throws IOException
	 */
	public void uploadExcelSdoFile(InputStream is, Integer date) throws IOException;

}

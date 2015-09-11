package com.nirvana.service;

import java.io.IOException;
import java.io.InputStream;

public interface OperateSdoExcelService {

	/**
	 * Excel上传
	 * 
	 * @param is
	 *            文件输入流
	 * @throws IOException
	 */
	public void uploadExcelSdoFile(InputStream is, Integer date) throws IOException;

}

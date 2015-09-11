package com.nirvana.fdfs;

import java.io.IOException;
import org.csource.common.MyException;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.config.NirvanaConfig;
import com.nirvana.exception.ServerException;
import com.nirvana.exception.ServerIOException;

public class FileOperator {

	private static String PREFIX = NirvanaConfig.getProperty("fdfs.file.prefix");

	public static String upload(byte[] bs, String file_ext_name) throws IOException, MyException {
		String string = FastDFSContext.getClient().upload_file1(bs, file_ext_name, null);
		return string;
	}

	public static String savePicture(MultipartFile picture) {
		Assert.notNull(picture, "ͼƬ����Ϊ�ա�");
		byte[] bytes;
		try {
			bytes = picture.getBytes();
			String fileName = picture.getOriginalFilename();
			String[] strings = fileName.split("\\.");
			String file_ext_name = null;
			if (strings.length != 0) {
				file_ext_name = strings[strings.length - 1];
			}

			if (file_ext_name == null || file_ext_name.equals("")) {
				throw new IllegalArgumentException("�����ļ���չ���Ƿ���ȷ��");
			}
			String fileId = FileOperator.upload(bytes, file_ext_name);
			return getUrl(fileId);
		} catch (IOException e) {
			throw new ServerIOException("ͼƬ�ϴ�ʧ�ܣ������ԡ�");
		} catch (MyException e) {
			throw new ServerException("�������ڲ��������Ժ����ԡ�");
		}
	}

	public static String getUrl(String fileId) {
		return PREFIX + fileId;
	}

}

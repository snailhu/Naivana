package com.nirvana.pojo4json;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PackageHelper {

	/** ������ */
	private String packageName;

	/** �Ƿ������Ӱ��� */
	private boolean childPackage;

	public PackageHelper(String packageName) {
		this(packageName, true);
	}

	public PackageHelper(String packageName, boolean childPackage) {
		if (packageName == null) {
			throw new IllegalArgumentException("packageName����Ϊ�ա�");
		}
		this.packageName = packageName;
		this.childPackage = childPackage;
	}

	public List<Class<?>> getClasses() throws Exception {
		List<Class<?>> classList = new ArrayList<Class<?>>();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(".", "/");
		URL url = loader.getResource(packagePath);
		if (url != null) {

			/*
			 * ���Ϊfile
			 */
			if (url.getProtocol().equals("file")) {
				File file;
				try {
					file = new File(url.toURI());
					classList = getDirectoryPathClasses(file.getPath(), packageName);
				} catch (URISyntaxException e) {
					throw new RuntimeException(e);
				}

			}

			// ���Ϊjar��
			else if (url.getProtocol().equals("jar")) {
			}

			else if (url.getProtocol().equals("vfs")) {
				Object content = url.openConnection().getContent();
				Class<?> czz = Class.forName("org.jboss.vfs.VirtualFile");
				Method m = czz.getMethod("getPhysicalFile");
				File physicalFile = (File) m.invoke(content);
				classList = getDirectoryPathClasses(physicalFile.getPath(), packageName);
			}

		}
		return classList;
	}

	private List<Class<?>> getDirectoryPathClasses(String path, String packageName) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		List<Class<?>> classes = new ArrayList<Class<?>>();
		File file = new File(path);
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				if (childPackage) {
					List<Class<?>> list = getDirectoryPathClasses(childFile.getPath(), packageName + "." + childFile.getName());
					classes.addAll(list);
				}
			} else {
				String name = childFile.getName();
				String className = packageName + "." + (name.substring(0, name.indexOf(".class")));
				Class<?> clazz;
				try {
					clazz = loader.loadClass(className);
					classes.add(clazz);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					throw new RuntimeException("��ȡPackage���෢���쳣��");
				}
			}
		}
		return classes;
	}

}
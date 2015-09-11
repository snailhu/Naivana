package com.nirvana.pojo4json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** DTOContext */
public class DTOContext {

	private static final Map<String, Object> cache = new HashMap<String, Object>();

	static {
		String packageName = "com.nirvana.controller.dto";
		PackageHelper helper = new PackageHelper(packageName);
		List<Class<?>> classes = null;
		try {
			classes = helper.getClasses();
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}
		for (Class<?> clz : classes) {
			Object value = null;
			try {
				value = clz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
				throw new RuntimeException(clz.getName() + ":��DTO��ʼ���쳣����ȷ������Ĭ�ϵ��޲ι��캯����");
			} catch (IllegalAccessException e) {
				throw new RuntimeException(clz.getName() + ":��DTO��ʼ���쳣����Ȩ���ʡ�");
			}
			cache.put(clz.getName(), value);
		}

	}

	public static <Domain, DTO extends BaseDTO<Domain>> Converter<Domain, DTO> getConverter(Class<DTO> cls) {
		@SuppressWarnings("unchecked")
		Converter<Domain, DTO> converter = (Converter<Domain, DTO>) cache.get(cls.getName());
		if (converter == null) {
			throw new RuntimeException("cache��δ�ҵ���Conventer�ࡣ");
		}
		return converter;
	}

}

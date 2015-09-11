package com.nirvana.security;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nirvana.domain.backend.usersys.ERole;
import com.nirvana.domain.dealer.usersys.DRole;
import com.nirvana.domain.store.usersys.SRole;
import com.nirvana.pojo4json.PackageHelper;
import com.nirvana.security.annotation.NeedDRole;
import com.nirvana.security.annotation.NeedERole;
import com.nirvana.security.annotation.NeedSRole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;

public class SecurityMetadataHolder {

	private static Map<String, Collection<ConfigAttribute>> backendResourceMap = new HashMap<String, Collection<ConfigAttribute>>();

	private static Map<String, Collection<ConfigAttribute>> dealerResourceMap = new HashMap<String, Collection<ConfigAttribute>>();

	private static Map<String, Collection<ConfigAttribute>> storeResourceMap = new HashMap<String, Collection<ConfigAttribute>>();

	public static Map<String, Collection<ConfigAttribute>> getBackendResourceMap() {
		return backendResourceMap;
	}

	public static Map<String, Collection<ConfigAttribute>> getDealerResourceMap() {
		return dealerResourceMap;
	}

	public static Map<String, Collection<ConfigAttribute>> getStoreResourceMap() {
		return storeResourceMap;
	}

	static {
		PackageHelper helper = new PackageHelper("com.nirvana.controller");
		List<Class<?>> classes = null;
		try {
			classes = helper.getClasses();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		for (Class<?> clazz : classes) {
			// 判断类是否存在注解。
			if (clazz.isAnnotationPresent(Controller.class) && clazz.isAnnotationPresent(RequestMapping.class) && clazz.isAnnotationPresent(SecurityUnit.class)) {
				String[] cMappings = clazz.getAnnotation(RequestMapping.class).value();
				SecurityUnit unit = clazz.getAnnotation(SecurityUnit.class);
				Method[] methods = clazz.getMethods();

				if (unit.value() == Warehouse.BACKEND) {

					ERole[] defaultRoles = null;
					if (clazz.isAnnotationPresent(NeedERole.class)) {
						NeedERole defaultNeedERole = clazz.getAnnotation(NeedERole.class);
						defaultRoles = defaultNeedERole.value();
					} else {
						defaultRoles = new ERole[] { ERole.USER };
					}

					for (Method method : methods) {
						if (method.isAnnotationPresent(RequestMapping.class)) {
							String[] mMappings = method.getAnnotation(RequestMapping.class).value();
							ERole[] roles = null;
							if (method.isAnnotationPresent(NeedERole.class)) {
								NeedERole needERole = method.getAnnotation(NeedERole.class);
								roles = needERole.value();
								boolean notNeed = needERole.notNeed();
								if (notNeed) {
									continue;
								}
							} else {
								roles = defaultRoles;
							}
							addBackendResource(mMappings, cMappings, roles);
						}
					}

				} else if (unit.value() == Warehouse.DEALER) {

					DRole[] defaultRoles = null;
					if (clazz.isAnnotationPresent(NeedDRole.class)) {
						NeedDRole defaultNeedDRole = clazz.getAnnotation(NeedDRole.class);
						defaultRoles = defaultNeedDRole.value();
					} else {
						defaultRoles = new DRole[] { DRole.USER };
					}

					for (Method method : methods) {
						if (method.isAnnotationPresent(RequestMapping.class)) {
							String[] mMappings = method.getAnnotation(RequestMapping.class).value();
							DRole[] roles = null;
							if (method.isAnnotationPresent(NeedDRole.class)) {
								NeedDRole needDRole = method.getAnnotation(NeedDRole.class);
								roles = needDRole.value();
								boolean notNeed = needDRole.notNeed();
								if (notNeed) {
									continue;
								}
							} else {
								roles = defaultRoles;
							}
							addDealerResource(mMappings, cMappings, roles);
						}
					}
				} else if (unit.value() == Warehouse.STORE) {

					SRole[] defaultRoles = null;
					if (clazz.isAnnotationPresent(NeedSRole.class)) {
						NeedSRole defaultNeedSRole = clazz.getAnnotation(NeedSRole.class);
						defaultRoles = defaultNeedSRole.value();
					} else {
						defaultRoles = new SRole[] { SRole.USER };
					}

					for (Method method : methods) {
						if (method.isAnnotationPresent(RequestMapping.class)) {
							String[] mMappings = method.getAnnotation(RequestMapping.class).value();
							SRole[] roles = null;
							if (method.isAnnotationPresent(NeedSRole.class)) {
								NeedSRole needSRole = method.getAnnotation(NeedSRole.class);
								roles = needSRole.value();
								boolean notNeed = needSRole.notNeed();
								if (notNeed) {
									continue;
								}
							} else {
								roles = defaultRoles;
							}
							addStoreResource(mMappings, cMappings, roles);
						}
					}
				}
			}
		}
	}

	private static void addBackendResource(String[] mappings, String[] fatherMappings, ERole[] roles) {
		Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
		for (ERole role : roles) {
			ConfigAttribute configAttribute = new SecurityConfig(role.getRoleName());
			configAttributes.add(configAttribute);
		}
		for (String fmapping : fatherMappings) {
			String furl = repairMapping(fmapping);
			for (String mapping : mappings) {
				String url = repairMapping(mapping);
				String resource = furl + url;
				backendResourceMap.put(resource, configAttributes);
			}
		}
	}

	private static void addDealerResource(String[] mappings, String[] fatherMappings, DRole[] roles) {
		Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
		for (DRole role : roles) {
			ConfigAttribute configAttribute = new SecurityConfig(role.getRoleName());
			configAttributes.add(configAttribute);
		}
		for (String fmapping : fatherMappings) {
			String furl = repairMapping(fmapping);
			for (String mapping : mappings) {
				String url = repairMapping(mapping);
				String resource = furl + url;
				dealerResourceMap.put(resource, configAttributes);
			}
		}
	}

	private static void addStoreResource(String[] mappings, String[] fatherMappings, SRole[] roles) {
		Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
		for (SRole role : roles) {
			ConfigAttribute configAttribute = new SecurityConfig(role.getRoleName());
			configAttributes.add(configAttribute);
		}
		for (String fmapping : fatherMappings) {
			String furl = repairMapping(fmapping);
			for (String mapping : mappings) {
				String url = repairMapping(mapping);
				String resource = furl + url;
				storeResourceMap.put(resource, configAttributes);
			}
		}
	}

	private static String repairMapping(String mapping) {
		Assert.hasLength(mapping);
		int index = 0;
		while ((index = mapping.indexOf("{")) != -1) {
			int indexright = mapping.indexOf("}", index);
			int nextleft = mapping.indexOf("{", index + 1);
			if (indexright == -1 || indexright == index + 1 || ((nextleft != -1) && (nextleft < indexright))) {
				throw new IllegalArgumentException("参数格式错误。");
			}
			String string = mapping.substring(index, indexright + 1);
			mapping = mapping.replace(string, "*");
		}
		return mapping;
	}
}

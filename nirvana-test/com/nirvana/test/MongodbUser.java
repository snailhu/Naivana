package com.nirvana.test;

import java.io.Serializable;
import java.util.List;

/**
 * java��ת��Ϊmongodb���ĵ�,�������¼���ע�ͣ� 1.@Id -
 * �ĵ���Ψһ��ʶ����mongodb��ΪObjectId������Ψһ�ģ�ͨ��ʱ���+������ʶ+����ID+������������ȷ��ͬһ���ڲ�����Id�����ͻ�����ɡ�
 * 2.@Document - ��һ��java������Ϊmongodb���ĵ�������ͨ��collection����ָ��������Ӧ���ĵ��� 3.@Indexed -
 * �������ֶ���Ҫ���������������Դ�����߲�ѯЧ�ʡ� 4.@Transient - ӳ����Ե��ֶΣ����ֶβ��ᱣ�浽MongoDB
 * 5.@CompoundIndex - ��������������������������������Ч����߶��ֶεĲ�ѯЧ�ʡ� 6.@PersistenceConstructor -
 * �������캯���������ǰѴ����ݿ�ȡ��������ʵ����Ϊ���󡣸ù��캯�������ֵΪ��DBObject��ȡ�������ݡ�
 * 
 * @author zhangguochen
 * 
 */

public class MongodbUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private int age;
	private List<Interest> interest;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Interest> getInterest() {
		return interest;
	}

	public void setInterest(List<Interest> interest) {
		this.interest = interest;
	}

	@Override
	public String toString() {
		return "MongodbUser [id=" + id + ", name=" + name + ", age=" + age + ", interest=" + interest + "]";
	}

}

package com.nirvana.domain.backend;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.nirvana.domain.backend.usersys.BackEndUser;

/**
 * ��̨��Աʵ�塣
 * 
 * */
@Entity
@Table(name = "backend_employee")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2481356242801091449L;

	/** ��ԱID */
	@Id
	@GeneratedValue
	private Long id;

	/** Ա����� */
	@Column(length = 20, nullable = false, unique = true)
	private String ECode;

	/** �����İ��º�̨ϵͳ�û� */
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
	private BackEndUser user;

	/** ӵ�е�ְλ */
	@OneToMany
	@JoinColumn(name = "employee_id")
	private Set<Position> positions;

	/** ��Ա��ʵ���� */
	@Column(length = 20, nullable = false)
	private String name;

	public Employee() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BackEndUser getUser() {
		return user;
	}

	public void setUser(BackEndUser user) {
		this.user = user;
	}

	public Set<Position> getPositions() {
		return positions;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getECode() {
		return ECode;
	}

	public void setECode(String eCode) {
		ECode = eCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Employee other = (Employee) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}

}

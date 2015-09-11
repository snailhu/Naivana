package com.nirvana.domain.backend.usersys;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.nirvana.domain.backend.Employee;

@Entity
@Table(name = "backend_usersys_user")
public class BackEndUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1147249361717307495L;

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 用户名 */
	@Column(length = 40, unique = true, nullable = false)
	private String username;

	/** 密码 */
	@Column(nullable = false, length = 20)
	private String password;

	/** 账户是否启用 */
	@Column(nullable = false)
	private Boolean enabled = true;

	/** 账户是否未过期 */
	@Column(nullable = false)
	private Boolean accountNonExpired = true;

	/** 证书是否未过期 */
	@Column(nullable = false)
	private Boolean credentialsNonExpired = true;

	/** 账户绑定的手机号 */
	@Column(unique = true, length = 20)
	private String phone;

	/** 账户是否未被锁定 */
	@Column(nullable = false)
	private Boolean accountNonLocked = true;

	/** 所属的百事雇员 */
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;

	public BackEndUser() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BackEndUser other = (BackEndUser) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

}

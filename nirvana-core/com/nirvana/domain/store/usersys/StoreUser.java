package com.nirvana.domain.store.usersys;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nirvana.domain.store.Store;

/**
 * 一阶门店用户实体。
 * 
 * */
@Entity
@Table(name = "store_usersys_storeuser")
public class StoreUser {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 用户名 */
	@Column(length = 30, nullable = false, unique = true)
	private String username;

	/** 密码 */
	@Column(length = 20, nullable = false)
	private String password;

	/** 账户绑定的手机号 */
	@Column(unique = true, length = 20)
	private String phone;

	/** 账户是否启用 */
	@Column(nullable = false)
	private Boolean enabled = true;

	/** 账户是否未过期 */
	@Column(nullable = false)
	private Boolean accountNonExpired = true;

	/** 证书是否未过期 */
	@Column(nullable = false)
	private Boolean credentialsNonExpired = true;

	/** 账户是否未被锁定 */
	@Column(nullable = false)
	private Boolean accountNonLocked = true;

	/** 关联的一阶门店 */
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;

	/** 创建日期 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createDate = new Date();

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public StoreUser() {
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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}

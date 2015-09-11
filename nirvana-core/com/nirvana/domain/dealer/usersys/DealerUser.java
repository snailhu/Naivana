package com.nirvana.domain.dealer.usersys;

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

import com.nirvana.domain.dealer.Dealer;

/**
 * �������û�ʵ�塣
 * 
 * */
@Entity
@Table(name = "dealer_usersys_dealeruser")
public class DealerUser {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** �û��� */
	@Column(length = 30, unique = true, nullable = false)
	private String username;

	/** ���� */
	@Column(length = 20, nullable = false)
	private String password;

	/** �󶨵��ֻ��� */
	@Column(length = 20, unique = true)
	private String phone;

	/** �˻��������� */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	/** �˻��Ƿ����� */
	@Column(nullable = false)
	private Boolean enabled = true;

	/** �˻��Ƿ�δ���� */
	@Column(nullable = false)
	private Boolean accountNonExpired = true;

	/** ֤���Ƿ�δ���� */
	@Column(nullable = false)
	private Boolean credentialsNonExpired = true;

	/** �˻��Ƿ�δ������ */
	@Column(nullable = false)
	private Boolean accountNonLocked = true;

	/** �����ľ����� */
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "dealer_id", nullable = false)
	private Dealer dealer;

	public DealerUser() {
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}

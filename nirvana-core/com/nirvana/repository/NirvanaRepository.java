package com.nirvana.repository;

import java.io.Serializable;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * ��չ��Spring jpa data �Զ���ӿڡ�
 * 
 * */
@NoRepositoryBean
public interface NirvanaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	public T findOne(ID id, LockModeType type);

	public void refresh(T object);

}

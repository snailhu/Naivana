package com.nirvana.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

/**
 * 本地通用Repository的实现类。
 * 
 * 获取实体bean类型的方法：getDomainClass()
 * */
public class NirvanaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements NirvanaRepository<T, ID> {

	private EntityManager em;

	// There are two constructors to choose from, either can be used.
	public NirvanaRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		// This is the recommended method for accessing inherited class
		// dependencies.
		this.em = entityManager;
	}

	@Override
	public T findOne(ID id, LockModeType type) {
		Assert.notNull(id, "The given id must not be null!");
		Assert.notNull(type, "The type must not be null!");

		Class<T> domainType = getDomainClass();

		return em.find(domainType, id, type);
	}

	@Override
	public void refresh(T object) {
		Assert.notNull(object, "The given id must not be null!");
		em.refresh(object);
	}

}

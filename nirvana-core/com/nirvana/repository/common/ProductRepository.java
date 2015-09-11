package com.nirvana.repository.common;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.common.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

	@Query("from Product p where p.brand.name=?1")
	public List<Product> findByBrandName(String name);

	@Query("from Product p where p.brand.name=?1")
	Page<Product> findByBrandName(String name, Pageable page);
}

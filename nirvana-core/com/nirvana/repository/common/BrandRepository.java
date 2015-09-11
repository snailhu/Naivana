package com.nirvana.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nirvana.domain.common.Brand;

public interface BrandRepository extends JpaRepository<Brand, String> {

}

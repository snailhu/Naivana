package com.nirvana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nirvana.domain.common.NnClient;

public interface NnClientRepository extends JpaRepository<NnClient, Integer> {

}

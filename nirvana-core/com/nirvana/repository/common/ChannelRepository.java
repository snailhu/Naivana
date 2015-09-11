package com.nirvana.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nirvana.domain.common.Channel;

public interface ChannelRepository extends JpaRepository<Channel, String> {

}

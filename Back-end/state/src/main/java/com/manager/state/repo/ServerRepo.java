package com.manager.state.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.state.model.Server;

public interface ServerRepo extends JpaRepository<Server,Long>{
    Server findByIpAddress(String ipAddress);
    Server findByName(String name);
}

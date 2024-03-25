package com.dira.multidb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dira.multidb.database2.Server;
import com.dira.multidb.database2.ServerRepository;
import com.dira.multidb.service.ServerService;

@Service
public class ServerServiceImpl implements ServerService{

	@Autowired
	private ServerRepository serverRepository;
	
	@Override
	public Server setServer(Server server) {
		return serverRepository.save(server);
	}

}

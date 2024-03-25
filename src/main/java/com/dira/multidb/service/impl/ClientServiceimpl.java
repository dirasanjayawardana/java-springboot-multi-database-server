package com.dira.multidb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dira.multidb.database1.Client;
import com.dira.multidb.database1.ClientRepository;
import com.dira.multidb.service.ClientService;

@Service
public class ClientServiceimpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Client setClient(Client client) {
		return clientRepository.save(client);
	}

}

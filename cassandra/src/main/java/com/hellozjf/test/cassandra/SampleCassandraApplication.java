/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hellozjf.test.cassandra;

import com.datastax.driver.core.utils.UUIDs;
import com.hellozjf.test.cassandra.domain.Csadstate;
import com.hellozjf.test.cassandra.domain.Customer;
import com.hellozjf.test.cassandra.domain.Customservice;
import com.hellozjf.test.cassandra.domain.CustomserviceKey;
import com.hellozjf.test.cassandra.repository.CsadstateRepository;
import com.hellozjf.test.cassandra.repository.CustomerRepository;
import com.hellozjf.test.cassandra.repository.CustomserviceRepository;
import com.hellozjf.test.cassandra.service.CsadstateService;
import com.hellozjf.test.cassandra.service.CustomserviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SampleCassandraApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CsadstateRepository csadstateRepository;

	@Autowired
	private CsadstateService csadstateService;

	@Autowired
	private CustomserviceRepository customserviceRepository;

	@Autowired
	private CustomserviceService customserviceService;

	/**
	 * 操作Customer表
	 */
	private void operateCustomer() {
		this.customerRepository.deleteAll();

		// save a couple of customers
		this.customerRepository.save(new Customer(UUIDs.timeBased(), "Alice", "Smith"));
		this.customerRepository.save(new Customer(UUIDs.timeBased(), "Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : this.customerRepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(this.customerRepository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : this.customerRepository.findByLastName("Smith")) {
			System.out.println(customer);
		}
	}

	/**
	 * 操作csadstate表
	 */
	private void operateCsadstate() {
		csadstateService.printAll();

		Csadstate csadstate = new Csadstate();
		csadstate.setCsadid("7554acff1b764e72b2f78c9a1693373c");
		csadstate.setCsadstate(1);
		csadstate.setGroupid("111000000");
		csadstate.setMaxservicenum(1);
		csadstate.setProtocol("HTTP");
		csadstate.setServicenum(0);
		csadstateRepository.save(csadstate);

		csadstateService.printAll();
	}

	/**
	 * 操作customservice表
	 */
	private void operateCustomservice() {
		customserviceService.printAll();
		CustomserviceKey customServiceKey = new CustomserviceKey();
		customServiceKey.setClient_id("f9ddbf8ff61e47d2a34374eb82bb227c");
		customServiceKey.setCsad_id("b40176837c7d4cedabb9706297eb2455");
		Customservice customservice = new Customservice();
		customservice.setCustomServiceKey(customServiceKey);
		customservice.setApply_type(1);
		customservice.setCacsi_inform_state(1);
		customservice.setClient_info("s");
		customservice.setClient_type("s");
		customservice.setNid("nid");
		customservice.setOvertime_client_inform_state(1);
		customservice.setService_time(1550218699295L);
		customservice.setSession_client_inform_state(1);
		customservice.setSession_client_inform_state(1);
		customservice.setSession_id("403d209861054ddea04e87cd7a13f3a6");
		customservice.setTime_client_lastest(1550218699295L);
		customservice.setTime_client_lastest(1550218699295L);
		customserviceRepository.save(customservice);
		customserviceService.printAll();
	}

	@Override
	public void run(String... args) throws Exception {
//		operateCustomservice();
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleCassandraApplication.class, args);
	}

}

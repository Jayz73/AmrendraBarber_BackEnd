package com.climbingfox.barber.controller;

import com.climbingfox.barber.dto.APIException;
import com.climbingfox.barber.entity.Customer;
import com.climbingfox.barber.service.StatusService;
import com.climbingfox.barber.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/shop")
public class CustomerController {
	@Autowired
	private WelcomeService service;

	@Autowired
	private StatusService statusService;

	@PostMapping("/welcome")
	public Mono<ResponseEntity<Customer>> newCustomer(@RequestBody Customer customer) throws APIException {
		return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(service.newCustomer(customer)));
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello";
	}

	@GetMapping(value = "/status",
			produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> receiveMessagesForCaller() {
		return statusService.consumeViaCaller();
	}
}

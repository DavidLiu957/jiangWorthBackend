package com.jiangworth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangworth.dto.req.CustomerReq;
import com.jiangworth.dto.res.CustomerRes;
import com.jiangworth.entity.CustomerEntity;
import com.jiangworth.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;

	@Transactional(readOnly = true)
	public List<CustomerRes> getAllCustomers() {
		return customerRepository.findAll().stream().map(this::convertToRes).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CustomerRes getCustomerById(String idStr) {
		Long customerId = Long.parseLong(idStr);
		CustomerEntity entity = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
		return convertToRes(entity);
	}

	@Transactional
	public CustomerRes createCustomer(CustomerReq req) {
		CustomerEntity entity = new CustomerEntity();
		entity.setCustomerName(req.getCustomerName());
		entity.setBirthday(req.getBirthday());
		entity.setSex(req.getSex());
		entity.setPhone(req.getPhone());
		entity.setAddress(req.getAddress());
		entity.setRiskProfile(req.getRiskProfile());
		entity.setNote(req.getNote());
		entity.setIsArchived(Boolean.TRUE);

		CustomerEntity saved = customerRepository.save(entity);
		return convertToRes(saved);
	}

	@Transactional
	public CustomerRes updateCustomer(String idStr, CustomerReq req) {
		Long customerId = Long.parseLong(idStr);
		CustomerEntity entity = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

		entity.setCustomerName(req.getCustomerName());
		entity.setBirthday(req.getBirthday());
		entity.setSex(req.getSex());
		entity.setPhone(req.getPhone());
		entity.setAddress(req.getAddress());
		entity.setRiskProfile(req.getRiskProfile());
		entity.setNote(req.getNote());

		CustomerEntity updated = customerRepository.save(entity);
		return convertToRes(updated);
	}

	@Transactional
	public void deleteCustomer(String idStr) {
		Long customerId = Long.parseLong(idStr);
		CustomerEntity entity = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

		entity.setIsArchived(Boolean.TRUE);

		customerRepository.save(entity);
	}

	private CustomerRes convertToRes(CustomerEntity entity) {
		return CustomerRes.builder().customerId(entity.getCustomerId()).customerName(entity.getCustomerName())
				.birthday(entity.getBirthday()).sex(entity.getSex()).phone(entity.getPhone())
				.address(entity.getAddress()).riskProfile(entity.getRiskProfile()).build();
	}

}
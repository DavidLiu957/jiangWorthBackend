package com.jiangworth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangworth.dto.req.UserReq;
import com.jiangworth.dto.res.UserRes;
import com.jiangworth.entity.UserEntity;
import com.jiangworth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<UserRes> getAllUsers() {
		return userRepository.findAll().stream().map(this::convertToRes).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public UserRes getUserById(String userId) {
		UserEntity entity = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
		return convertToRes(entity);
	}

	@Transactional
	public UserRes createUser(UserReq req) {
		UserEntity entity = new UserEntity();
		entity.setUsername(req.getUsername());
		entity.setEmail(req.getEmail());

		UserEntity saved = userRepository.save(entity);
		return convertToRes(saved);
	}

	@Transactional
	public UserRes updateUser(String userId, UserReq req) {
		UserEntity entity = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

		entity.setUsername(req.getUsername());
		entity.setEmail(req.getEmail());

		UserEntity updated = userRepository.save(entity);
		return convertToRes(updated);
	}

	@Transactional
	public void deleteUser(String userId) {
		if (!userRepository.existsById(userId)) {
			throw new RuntimeException("User not found with id: " + userId);
		}
		userRepository.deleteById(userId);
	}

	private UserRes convertToRes(UserEntity entity) {
		return UserRes.builder().userId(entity.getUserId()).username(entity.getUsername()).email(entity.getEmail())
				.build();
	}
}
package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PracticeEntity;
import com.example.demo.service.PracticeService;
import com.example.demo.utility.JwtTokenUtil;

@RestController
public class PracticeController {

	@Autowired
	private PracticeService practiceService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping("/generateToken")
	public String generateToken() {
		String username = "default_user";
		return jwtTokenUtil.generateToken(username);
	}

	@GetMapping("/getData")
	public List<PracticeEntity> getData() {
		return practiceService.getData();
	}

	@GetMapping("/getData/{id}")
	public Optional<PracticeEntity> getDataById(@PathVariable Long id) {
		return practiceService.getDataById(id);
	}

	@PostMapping("/insertData")
	public PracticeEntity insertData(@RequestBody PracticeEntity practiceDto) {
		return practiceService.insertData(practiceDto);
	}

}

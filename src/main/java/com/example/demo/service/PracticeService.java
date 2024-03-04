package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PracticeEntity;
import com.example.demo.repository.PracticeRepository;

@Service
public class PracticeService {

	@Autowired
	private PracticeRepository practiceRepository;
	
	public List<PracticeEntity> getData() {
		return practiceRepository.findAll();
	}

	public Optional<PracticeEntity> getDataById(Long id) {
	  return practiceRepository.findById(id);
	}
	
	public PracticeEntity insertData(PracticeEntity practiceDto) {
		return practiceRepository.save(practiceDto);
	}
}

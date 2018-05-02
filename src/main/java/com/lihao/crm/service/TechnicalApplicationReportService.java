package com.lihao.crm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.TechnicalApplicationReport;
import com.lihao.crm.repository.TechnicalApplicationReportRepository;

@Service
public class TechnicalApplicationReportService {
	
	@Autowired
	TechnicalApplicationReportRepository technicalApplicationReportRepository;

	public TechnicalApplicationReport findById(long id) {
		TechnicalApplicationReport technicalApplicationReport = new TechnicalApplicationReport();
		technicalApplicationReport.setCretateTime(null);
		technicalApplicationReport.setIsDelete(false);
		technicalApplicationReport.setId(id);
		Example<TechnicalApplicationReport> example = Example.of(technicalApplicationReport);
		Optional<TechnicalApplicationReport> temp = technicalApplicationReportRepository.findOne(example);

		return temp.get();
	}
}

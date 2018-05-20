package com.lihao.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lihao.crm.entity.SysUser;
import com.lihao.crm.entity.TechnicalApplication;
import com.lihao.crm.repository.TechnicalApplicationReportRepository;
import com.lihao.crm.repository.TechnicalApplicationRepository;

@Service
public class TechnicalApplicationService {

	@Autowired
	TechnicalApplicationRepository repository;

	@Autowired
	TechnicalApplicationReportRepository technicalApplicationReportRepository;

	public TechnicalApplication findById(long id) {
		TechnicalApplication technicalApplication = new TechnicalApplication();
		technicalApplication.setCretateTime(null);
		technicalApplication.setIsDelete(false);
		technicalApplication.setId(id);
		Example<TechnicalApplication> example = Example.of(technicalApplication);
		Optional<TechnicalApplication> temp = repository.findOne(example);

		return temp.get();
	}

	public List<TechnicalApplication> loadMine(SysUser me) {
		return (List<TechnicalApplication>) repository.findAllByUserAndIsDeleteNotOrderByIdDesc(me, true);
	}

	public List<TechnicalApplication> loadByTechnicist(SysUser technicist) {
		return (List<TechnicalApplication>) repository.findAllByTechnicistAndIsDeleteNotOrderByIdDesc(technicist, true);
	}

	public void save(TechnicalApplication technicalApplication) {
		if (technicalApplication.getReports() != null)
			technicalApplicationReportRepository.saveAll(technicalApplication.getReports());
		repository.save(technicalApplication);
	}
}

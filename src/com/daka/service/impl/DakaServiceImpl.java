package com.daka.service.impl;

import java.util.Calendar;
import java.util.List;

import com.daka.po.RecordsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daka.mapper.RecordsMapper;
import com.daka.po.Records;
import com.daka.service.DakaService;

@Service
public class DakaServiceImpl implements DakaService {

	@Autowired
	private RecordsMapper recordsMapper;
	public void save(Records records) throws Exception {
		recordsMapper.insert(records);
		
	}
	public List<Records> findAll() {
		return recordsMapper.selectByExample(null);
	}

	@Override
	public List<Records> findAllByMonth(int i) {
		RecordsExample example = new RecordsExample();
//		Calendar instance = Calendar.getInstance();
//
//		example.createCriteria().andRecordsDateBetween()
		recordsMapper.selectByExample(example);
		return null;
	}

}

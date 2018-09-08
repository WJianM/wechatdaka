package com.daka.service;

import java.util.List;

import com.daka.po.Records;

public interface DakaService {

	void save(Records records) throws Exception;

	List<Records> findAll();

    List<Records> findAllByMonth(int i);

	List<Records> findIsLateByMonth(String month);
}

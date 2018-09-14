package com.daka.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.daka.mapper.RecordtimeMapper;
import com.daka.po.RecordsExample;
import com.daka.po.Recordtime;
import com.daka.po.RecordtimeExample;
import com.daka.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daka.mapper.RecordsMapper;
import com.daka.po.Records;
import com.daka.service.DakaService;

@Service
public class DakaServiceImpl implements DakaService {

	@Autowired
	private RecordsMapper recordsMapper;

	@Autowired
	private RecordtimeMapper recordtimeMapper;
	public int save(Records records) throws Exception {
		int flag = 0;
		/*
		 *  判断是否迟到
		 *  获取所有的上班卡时间
		 *  正常为 0 ,迟到为1
		 */
		RecordtimeExample example = new RecordtimeExample();
		example.createCriteria().andTypeEqualTo(0);
		List<Recordtime> recordtimes = recordtimeMapper.selectByExample(example);
		if (recordtimes.size()==0){
			// 未初始化,返回-1
			flag = -1;
		}if(recordtimes.size()==1){
			Recordtime recordtime = recordtimes.get(0);
			// 如果当前时间晚于打卡时间, 则迟到,返回标记1

			Date time = recordtime.getRecordTime();

			int i = DateUtils.daysBetween(time, new Date());

		}

		recordsMapper.insert(records);
		return 0;
	}
	public List<Records> findAll() {
		return recordsMapper.selectByExample(null);
	}

	@Override
	public List<Records> findAllByMonth(int i) {
		RecordsExample example = new RecordsExample();
//		Calendar instance = Calendar.getInstance();
//
		example.createCriteria().andRecordsDateBetween(DateUtils.getFirstDayDateOfMonth(),DateUtils.getLastDayTimeOfMonth2());
		List<Records> records = recordsMapper.selectByExample(example);
		return records;
	}

	/**
	 * 查询本月异常记录
	 * @param month
	 * @return
	 */
	@Override
	public List<Records> findIsLateByMonth(String month) {
		RecordsExample example = new RecordsExample();
//		RecordsExample.Criteria criteria = example.createCriteria().andRecordsDateBetween(DateUtils.getFirstDayDateOfMonth(), DateUtils.getLastDayTimeOfMonth2());
		RecordsExample.Criteria criteria = example.createCriteria();
		criteria.andRecordsDateBetween(DateUtils.getFirstDayDateOfMonth(),DateUtils.getLastDayTimeOfMonth2());
		criteria.andIsLateEqualTo(1);

		List<Records> records = recordsMapper.selectByExample(example);
		return records;
	}

	@Override
	public List<Records> getTodayList(String recordsDate) {

		RecordsExample exampl = new RecordsExample();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH,Integer.parseInt(recordsDate));
		c.set(Calendar.HOUR_OF_DAY, 0);
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.DAY_OF_MONTH,Integer.parseInt(recordsDate)+1);
		c1.set(Calendar.HOUR_OF_DAY, 0);

		exampl.createCriteria().andRecordsDateBetween(c.getTime(),c1.getTime());
		return recordsMapper.selectByExample(exampl);
	}

}

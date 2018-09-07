package com.daka.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.daka.po.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daka.po.Records;
import com.daka.po.User;
import com.daka.service.DakaService;
import com.daka.service.RecordtimeService;

@Controller
public class DakaController {
	@Autowired
	private DakaService dakaService;
	@Autowired
	private RecordtimeService recordtimeService;

	@RequestMapping("add")
	@ResponseBody
	public Result addRecord(Records records, User user) {
		recordtimeService.findAround(records.getRecordsDate(),user.getId());
		// 新增一条记录,首先判断是不是上午 , 
//		if (records.getRecordsDate().before(arg0)) {
//
//		}

		try {
			dakaService.save(records);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"打卡失败");
		}

		return new Result(true,"打卡成功");
	}

	/**
	 * 查询所有打卡记录
	 * @param 用户Id
	 * @return
	 */

	@RequestMapping("findAll")
	@ResponseBody
	public List<Records> selectAllRecord(String userId) {
		List<Records> list = dakaService.findAll();
		return list;
	}

	@RequestMapping("findAllByMonth")
	@ResponseBody
	public List<Records> selectAllRecordByMonth(String userId) {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        int i = c.get(Calendar.MONTH);
        List<Records> list = dakaService.findAllByMonth(i+1);
		return list;
	}


}
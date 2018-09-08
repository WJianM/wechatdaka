package com.daka.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.daka.po.Result;
import com.daka.utils.DateUtils;
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

	/**
	 * 增加一条打卡记录
	 * @param records 打卡记录
	 * @param user 用户信息
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Result addRecord(Records records, User user) {
		recordtimeService.findAround(records.getRecordsDate(),user.getId());


		try {
			int i = dakaService.save(records);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"打卡失败");
		}

		return new Result(true,"打卡成功");
	}



	/**
	 * 获取所有的打卡记录
	 * @param userId
	 * @return
	 */
	@RequestMapping("findAll")
	@ResponseBody
	public List<Records> selectAllRecord(String userId) {
		List<Records> list = dakaService.findAll();
		return list;
	}

	/**
	 * 获取当前月份所有的打卡记录
	 * @param userId
	 * @return
	 */
	@RequestMapping("findAllByMonth")
	@ResponseBody
	public List<Records> selectAllRecordByMonth(String userId) {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        int i = c.get(Calendar.MONTH);
        List<Records> list = dakaService.findAllByMonth(i+1);
		return list;
	}

	/**
	 * 查询本月异常记录
	 * @param userId
	 * @return
	 */
	@RequestMapping("findIsLateByMonth")
	@ResponseBody
	public List<Records> findIsLateByMonth(String userId) {
		Date date = new Date();

		String month = DateUtils.getMonth();
		List<Records> list = dakaService.findIsLateByMonth(month);
		return list;
	}




}

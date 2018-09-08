package com.daka.test;

import com.daka.mapper.RecordsMapper;
import com.daka.po.Records;
import com.daka.utils.DateUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.Date;


public class TestDaka {

	@Test
	public void test() {
//		ArrayList
//		String s = "24ab_2t2";
//		444_aaaaa_b_\\ul_ttt_2
//		HashMap
		ApplicationContext ctx=new ClassPathXmlApplicationContext("*.xml");

		RecordsMapper recordsMapper= ctx.getBean(RecordsMapper.class);
        Records records = new Records();
        records.setRecordsDate(new Date());
        records.setUserId(1);

        recordsMapper.insert(records);


	}
	@Test
	public void Test1(){
		System.out.println(DateUtils.getMonth());
	}

}

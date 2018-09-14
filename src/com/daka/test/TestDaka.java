package com.daka.test;

import com.daka.mapper.RecordsMapper;
import com.daka.mapper.RecordtimeMapper;
import com.daka.po.Records;
import com.daka.po.Recordtime;
import com.daka.po.RecordtimeExample;
import com.daka.utils.DateUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.Date;
import java.util.List;


public class TestDaka {
    RecordsMapper recordsMapper = null;
    RecordtimeMapper recordtimeMapper = null;
    @org.junit.Before
    public void before(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("*.xml");

        recordsMapper= ctx.getBean(RecordsMapper.class);
        recordtimeMapper = ctx.getBean(RecordtimeMapper.class);
    }
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
        RecordtimeExample example = new RecordtimeExample();

        example.createCriteria().andTypeEqualTo(0);
        List<Recordtime> recordtimes = recordtimeMapper.selectByExample(example);
        System.out.println(recordtimes);
        if (recordtimes.size()==0){
            // 未初始化,返回-1
            //flag = -1;
        } else if(recordtimes.size()==1){
            Recordtime recordtime = recordtimes.get(0);
            // 如果当前时间晚于打卡时间, 则迟到,返回标记1

            Date time = recordtime.getRecordTime();

            int i = DateUtils.daysBetween(time, new Date());
            System.out.println(i+"?????????????");

        } else {

            for (Recordtime recordtime : recordtimes) {
                Date recordTime = recordtime.getRecordTime();
//                DateUtils.gett
                System.out.println(DateUtils.hoursBetween(recordTime,new Date()));

            }
        }
	}

}

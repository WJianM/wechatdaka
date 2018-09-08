package com.daka.mapper;

import com.daka.po.Recordtime;
import com.daka.po.RecordtimeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordtimeMapper {
    int countByExample(RecordtimeExample example);

    int deleteByExample(RecordtimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Recordtime record);

    int insertSelective(Recordtime record);

    List<Recordtime> selectByExample(RecordtimeExample example);

    Recordtime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Recordtime record, @Param("example") RecordtimeExample example);

    int updateByExample(@Param("record") Recordtime record, @Param("example") RecordtimeExample example);

    int updateByPrimaryKeySelective(Recordtime record);

    int updateByPrimaryKey(Recordtime record);
}
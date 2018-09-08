package com.daka.mapper;

import com.daka.po.Records;
import com.daka.po.RecordsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordsMapper {
    int countByExample(RecordsExample example);

    int deleteByExample(RecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Records record);

    int insertSelective(Records record);

    List<Records> selectByExample(RecordsExample example);

    Records selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Records record, @Param("example") RecordsExample example);

    int updateByExample(@Param("record") Records record, @Param("example") RecordsExample example);

    int updateByPrimaryKeySelective(Records record);

    int updateByPrimaryKey(Records record);
}
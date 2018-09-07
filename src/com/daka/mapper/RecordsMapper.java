package com.daka.mapper;

import com.daka.po.Records;
import com.daka.po.RecordsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecordsMapper {
    int countByExample(RecordsExample example);

    int deleteByExample(RecordsExample example);

    int insert(Records record);

    int insertSelective(Records record);

    List<Records> selectByExample(RecordsExample example);

    int updateByExampleSelective(@Param("record") Records record, @Param("example") RecordsExample example);

    int updateByExample(@Param("record") Records record, @Param("example") RecordsExample example);
}
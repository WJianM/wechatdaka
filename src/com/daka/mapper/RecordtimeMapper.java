package com.daka.mapper;

import com.daka.po.Recordtime;
import com.daka.po.RecordtimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecordtimeMapper {
    int countByExample(RecordtimeExample example);

    int deleteByExample(RecordtimeExample example);

    int insert(Recordtime record);

    int insertSelective(Recordtime record);

    List<Recordtime> selectByExample(RecordtimeExample example);

    int updateByExampleSelective(@Param("record") Recordtime record, @Param("example") RecordtimeExample example);

    int updateByExample(@Param("record") Recordtime record, @Param("example") RecordtimeExample example);
}
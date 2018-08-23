package cn.cote.mapper;

import cn.cote.pojo.MyCommodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyCommodityMapper {

    List<MyCommodity> selectCommodityAndMaster(@Param("start") int start, @Param("end") int end);
}

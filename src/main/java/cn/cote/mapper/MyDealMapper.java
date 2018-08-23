package cn.cote.mapper;

import cn.cote.pojo.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyDealMapper {
    List<Commodity> selectBuyShop(@Param("userId") int userId);
}

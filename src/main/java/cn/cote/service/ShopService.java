package cn.cote.service;

import cn.cote.pojo.Commodity;
import cn.cote.pojo.Deal;
import cn.cote.pojo.MyCommodity;

import java.util.List;

public interface ShopService {
    /**
     * 分页查找商品信息
     * @param pageId
     * @return
     */
    List<Commodity> getShopByPage(int pageId);
    /**
     * 查找并降序排列信息
     * @param number
     * @return
     */
    List<Commodity> getShop(int number);
    /**
     * 插入商品信息
     * @param commodity
     * @return
     */
    int insertCommodity(Commodity commodity);
    /**
     * 修改商品图片
     * @param newFileName
     * @param this_id
     * @return
     */
    int addCommodityImg(String newFileName, int this_id);
    /**
     * 查找并降序排列信息和master信息
     * @param number
     * @return
     */
    List<MyCommodity> getShopAndMaster(int number);

    int buyShop(Deal newDeal);

    List<Commodity> getBuyShop(int userId);

    int deleteDeal(int commodityId);

    List<Commodity> getShopByLike(String word);
}

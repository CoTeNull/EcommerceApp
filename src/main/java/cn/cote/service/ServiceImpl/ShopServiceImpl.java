package cn.cote.service.ServiceImpl;

import cn.cote.mapper.CommodityMapper;
import cn.cote.mapper.DealMapper;
import cn.cote.mapper.MyCommodityMapper;
import cn.cote.mapper.MyDealMapper;
import cn.cote.pojo.*;
import cn.cote.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    CommodityMapper commodityMapper;

    @Override
    public List<Commodity> getShopByPage(int pageId) {
        CommodityExample example = new CommodityExample();
//      根据id倒序排列
        example.setOrderByClause("commodity_id DESC");
        example.setStartRow((pageId-1)*6);
//      读取六条数据 number = 5
        example.setEndRow(pageId*6);
        List<Commodity> data=commodityMapper.selectByExampleWithBLOBs(example);
        return data;
    }

    @Override
    public List<Commodity> getShop(int number) {
        CommodityExample example = new CommodityExample();
//      根据id倒序排列
        example.setOrderByClause("commodity_id DESC");
        example.setStartRow(0);
        example.setEndRow(number);
        List<Commodity> data=commodityMapper.selectByExampleWithBLOBs(example);
        return data;
    }

    @Override
    public int insertCommodity(Commodity commodity) {
//        int code=commodityMapper.insert(commodity);
        commodityMapper.insertSelective(commodity);
        int code = commodity.getCommodityId();
        return code;
    }

    @Override
    public int addCommodityImg(String newFileName, int this_id) {
        Commodity this_shop;
        this_shop = commodityMapper.selectByPrimaryKey(this_id);
        this_shop.setCommodityImg("/img/commoditys/"+newFileName);
        int code = commodityMapper.updateByPrimaryKey(this_shop);
        return code;
    }

    @Autowired
    MyCommodityMapper myCommodityMapper;
    @Override
    public List<MyCommodity> getShopAndMaster(int number) {
        List<MyCommodity> data=myCommodityMapper.selectCommodityAndMaster(0,number);
        return data;
    }
    @Autowired
    DealMapper dealMapper;
    @Override
    public int buyShop(Deal newDeal) {
    int code=dealMapper.insert(newDeal);
        return code;
    }
    @Autowired
    MyDealMapper myDealMapper;
    @Override
    public List<Commodity> getBuyShop(int userId) {
       List<Commodity> newList=myDealMapper.selectBuyShop(userId);
        return newList;
    }

    @Override
    public int deleteDeal(int commodityId) {
        DealExample example =new DealExample();
        DealExample.Criteria criteria=example.createCriteria();
        criteria.andDealShopIdEqualTo(commodityId);
       int code= dealMapper.deleteByExample(example);
        return code;
    }

    @Override
    public List<Commodity> getShopByLike(String word) {
        CommodityExample example = new CommodityExample();
        CommodityExample.Criteria criteria = example.createCriteria();
        criteria.andCommodityNameLike("%"+word+"%");
        List<Commodity> list = commodityMapper.selectByExampleWithBLOBs(example);
        System.out.println(list.size());
        return list;
    }
}

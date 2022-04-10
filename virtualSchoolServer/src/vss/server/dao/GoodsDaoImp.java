package vss.server.dao;

import java.util.List;

import vss.common.Goods;
import vss.common.User;
import vss.server.db.SqlHelperImp;

public class GoodsDaoImp implements GoodsDao{
    @Override
    public Goods searchGoods(String goodsName) {
        // TODO Auto-generated method stub
        String sql = "select * from tb_Goods where gName = ?";
        String[] paras = new String[1];
        paras[0] = goodsName;
        List<Goods> goodsList=new SqlHelperImp().sqlGoodsQuery(sql,paras);
        if(goodsList!=null&&goodsList.size()>0) {
            return goodsList.get(0);
        }else
            return null;
    }
    @Override
    public boolean buyGoods(String goodsName, int goodsAmount, String buyerID) {
        // TODO Auto-generated method stub
        String[] paras = new String[2];
        paras[1] = goodsName;
        Goods goods = this.searchGoods(goodsName);
        int left = Integer.valueOf(goods.getGoodsAmount());
        left = left - goodsAmount;
        if(left<0)
        {
            return false;
        }
        else{
        	UserDaoImpl userDao =  new UserDaoImpl();
        	User user =  userDao.searchUser(buyerID);
        	String moneyStr = user.getMoney();
        	int money =Integer.valueOf(moneyStr);

        	String priceStr = goods.getGoodsPrice();
        	int price = Integer.valueOf(priceStr);
        	if(money - price * goodsAmount >= 0) {
	            paras[0] = String.valueOf(left);
	            String sql = "update tb_Goods set gAmount = ? where gName = ?";
	            String sql2 = "update tb_User set uMoney = ? where uID = ?";
	            String[] paras2= new String[2];
	            
	            if(user.getRole().equals("4"))
	            		paras2[0] = String.valueOf(money);
	            else
	            	paras2[0] = String.valueOf(money - price * goodsAmount);
	            System.out.println("money set to: " + paras2[0]);
	            paras2[1]= buyerID;
	            new SqlHelperImp().sqlUpdate(sql, paras);
	            new SqlHelperImp().sqlUpdate(sql2, paras2);
	            return true;
        	}
        	else return false;
        }
    }

    @Override
    public boolean addGoods(Goods goods) {
        // TODO Auto-generated method stub
        String sql = "insert into tb_Goods(gID,gName,gAmount,gPrice) values (?,?,?,?)";
        String[] paras = new String[4];
        paras[0]=goods.getGoodsID();
        paras[1]=goods.getGoodsName();
        paras[2]=goods.getGoodsAmount();
        paras[3]=goods.getGoodsPrice();
        new SqlHelperImp().sqlUpdate(sql, paras);
        Goods temp=searchGoods(goods.getGoodsName());
        if(temp==null)
            return false;
        else
            return true;
    }

    @Override
    public boolean removeGoods(String goodsName) {
        // TODO Auto-generated method stub
        String sql = "delete from tb_Goods where gName = ?";
        return new SqlHelperImp().sqlUpdate(sql , new String[] {goodsName});
    }
	@Override
	public List<Goods> getAllGoods() {
		String sql = "select * from tb_Goods";
		return new SqlHelperImp().sqlGoodsQuery(sql, new String[] {});
	}
}

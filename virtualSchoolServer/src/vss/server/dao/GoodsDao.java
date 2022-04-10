package vss.server.dao;
import java.util.*;

import vss.common.*;

public interface GoodsDao {
	/**
	 * student buy goods
	 * @param goodsName Name of the goods to buy
	 * @param goodsAmount amount of the goods to buy
	 * @param buyerID id of the buyer
	 * @return return a value of boolean, showing this function success or fail
	 */
	public boolean buyGoods(String goodsName, int goodsAmount, String buyerID);
	/**
	 * Administrator add goods
	 * @param good Details of the goods to be added
	 * @return return a value of boolean, showing this function success or fail
	 */
	public boolean addGoods(Goods good);
	/**
	 * Administrator remove goods
	 * @param goodsName Name of the goods to delete
	 * @return return a value of boolean, showing this function success or fail
	 */
	public boolean removeGoods(String goodsName);
	/**
	 * Get information about all goods
	 * @return Full information of all goods
	 */
	public List<Goods> getAllGoods();
	/**
	 * search good by name
	 * @param goodsName Name of the goods to search
	 * @return Details of the good be searched 
	 */
	public Goods searchGoods(String goodsName);
}

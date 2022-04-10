package vss.common;

import java.util.Vector;

public class Goods {
	private String goodsID;
	private String goodsName;
	private String goodsAmount;
	private String goodsPrice;
	private Vector<String> goodsContent;
	
	public Goods() {
	}	
	public String getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsAmount() {
		return this.goodsAmount;
	}

	public void setGoodsAmount(String goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public String getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Goods(String goodsID, String goodsName, String goodsAmount, String goodsPrice) {
		this.goodsID = goodsID;
		this.goodsName = goodsName;
		this.goodsAmount = goodsAmount;
		this.goodsPrice = goodsPrice;
	}

	@Override
	public String toString() {
		return "{" +
			" goodsID='" + getGoodsID() + "'" +
			", goodsName='" + getGoodsName() + "'" +
			", goodsAmount='" + getGoodsAmount() + "'" +
			", goodsPrice='" + getGoodsPrice() + "'" +
			"}";
	}
	public Vector<String> getContent() {
			goodsContent = new Vector<String>();
			goodsContent.add(goodsID);
			goodsContent.add(goodsName);
			goodsContent.add(goodsAmount);
			goodsContent.add(goodsPrice);
			return goodsContent;
	}
	public void setContent(Vector<String> content) {
		 goodsID = content.get(0);
		 goodsName = content.get(1);
		 goodsAmount = content.get(2);
		 goodsPrice = content.get(3);
	}
	
}

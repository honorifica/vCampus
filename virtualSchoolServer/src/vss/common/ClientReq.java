package vss.common;

import java.util.*;
import vss.common.*;
public class ClientReq implements java.io.Serializable {
	private static final long serialVersionUID = 8454558035178168414L;
	//client���ڷ�����ض�����
	private String type;
	private String level;//������Ȩ��
	private Vector<String> Content;//���ڴ��ǰ�˴����ĳ�ʼ������
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Vector<String> getContent() {
		return Content;
	}
	public void setContent(Vector<String> content) {
		Content = content;
	}
}

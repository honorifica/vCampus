package vss.common;

public interface ClientReqType {
	/**
	 * 
	 */
	//----�����¼----//
	String REQ_LOGIN="REQ_LOGIN";
	String REQ_LOGOUT="REQ_LOGOUT";
	String REQ_REGISTER="REQ_REGISTER";
	
	//----ѧ������----//
	String REQ_SHOW_SIG_INFOR="REQ_SHOW_SIG_INFOR";//�鿴����ѧ����Ϣ��client��vector���ṩID��
	String REQ_REMOVE_SIG_INFOR="REQ_REMOVE_SIG_INFOR";//ɾ������ѧ����Ϣ��client��vector���ṩID��
	String REQ_ADD_SIG_INFOR="REQ_ADD_SIG_INFOR";//���ӵ���ѧ����Ϣ��client��Vector���ṩȫ����Ϣ
	String REQ_SHOW_ALL_INFOR="REQ_SHOW_ALL_INFOR";//�鿴ȫ��ѧ����Ϣ
	
	//----ѡ��ģ��----//
	String REQ_SEARCH_LESSON="REQ_SEARCH_LESSON";//���ݿ��ѯ�γ�
	String REQ_REMOVE_LESSON="REQ_REMOVE_LESSON";//��ʦ�ӿ�
	String REQ_ADD_LESSON="REQ_ADD_LESSON";//��ʦɾ��
	String REQ_SHOW_ALL_LESSON="REQ_SHOW_ALL_LESSON";//��ʾȫ���γ�
	String REQ_STU_ADD_LESSON="REQ_STU_ADD_LESSON";//ѧ��ѡ��
	String REQ_STU_REMOVE_LESSON="REQ_STU_REMOVE_LESSON";//ѧ���˿�
	
	//----ͼ���ģ��----//
	String REQ_ADD_BOOK="REQ_ADD_BOOK";
	String REQ_REMOVE_BOOK="REQ_REMOVE_BOOK";
	String REQ_BORROW_BOOK="REQ_BORROW_BOOK";
	String REQ_RETURN_BOOK="REQ_RETURN_BOOK";
	String REQ_SHOW_ALL_BOOK="REQ_SHOW_ALL_BOOK";
	String REQ_SHOW_SIG_BOOK = "REQ_SHOW_SIG_BOOK";
	
	//----�̵�ģ��----//
	String REQ_BUY_GOODS="REQ_BUY_GOODS";
	String REQ_ADD_GOODS="REQ_ADD_GOODS";
	String REQ_REMOVE_GOODS="REQ_REMOVE_GOODS";
	String REQ_SHOW_ALL_GOODS="REQ_SHOW_ALL_GOODS";
	String REQ_SHOW_SIG_GOODS = "REQ_SHOW_SIG_GOODS";
	
	//----ѧ�����顢ѡ��������Ϣ----//
	String REQ_STU_ALL_CHOOOSE="REQ_STU_ALL_CHOOOSE";
	String REQ_STU_ALL_BORROW="REQ_STU_ALL_BORROW";

}

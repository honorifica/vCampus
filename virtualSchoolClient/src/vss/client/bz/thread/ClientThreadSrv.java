package vss.client.bz.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import vss.client.view.ClientLibraryModifyFrame;
import vss.client.view.ClientLoginFrame;
import vss.client.view.ClientRegisterFrame;
import vss.client.view.ClientShopManagerFrame;
import vss.client.view.ClientStudentFrame;
import vss.client.view.ClientTeacherFrame;
import vss.common.ClientReq;
import vss.common.IConstant;

/**
 * 
 * @author �����
 * �ͻ��˵��̷߳���
 */
public class ClientThreadSrv extends Thread {

	private static final String SERVER_ADDRESS = IConstant.SERVER_ADDRESS;
	private static final int SERVER_PORT = IConstant.SERVER_PORT;
	private Socket socket = null;
	private boolean isOffline;
	private String userID;
	private int sign;
	/**
	 * �ͻ��˵��̳߳�ʼ��
	 * @param userID �û���ID
	 * @param socket ����ͨ�ŵ�socket
	 * @param sign �û��Ĳ���Ȩ�޵ȼ�
	 * @throws UnknownHostException �׳�Socketδ��ʼ�����쳣
	 * @throws IOException �׳�IO���쳣
	 */
	public ClientThreadSrv(String userID, Socket socket, int sign) throws UnknownHostException, IOException {
			this.socket = socket;
			ClientThreadSrvMgr.add(userID, this);
			this.userID = userID;
			isOffline = false;
			this.sign = sign;
			// this.start(); // Ӧ�ڵ�¼��֤�ɹ�������		
	}

	@Override
	/**
	 * �����̷߳��������
	 */
	public void run() {
	
		if(sign == 2) {
			ClientStudentFrame csf = new ClientStudentFrame(userID);//�����������
			csf.setSocket(socket);
		} 
		else if(sign == 3) 
		{	
			try {
				ClientTeacherFrame ctf = new ClientTeacherFrame(userID,socket);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		else if(sign == 4) 
		{//�Ķ�
			ClientShopManagerFrame csmf = null;
			try {
				csmf = new ClientShopManagerFrame(socket);
				csmf.setOperatorID(userID);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//�������
			csmf.setSocket(socket);
		} 
		else if(sign == 5) {
			try {
				ClientLibraryModifyFrame clmf = new ClientLibraryModifyFrame(socket);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setUser(String userID) {
		this.userID = userID;
	}

	public void dispose() throws IOException {
		isOffline = true;
		this.socket.close();		
	}
	
	public void start() {
		new Thread(this).start();
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}
}

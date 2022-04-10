package vss.server.bz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.Map;

import vss.common.*;
import vss.server.dao.UserDao;
import vss.server.dao.UserDaoImpl;

/**
 * 
 * @author �����
 * �̷߳���ķ���
 */
public class ServerSocketSrvImp implements ServerSocketSrv{
	private ServerSocket serverSocket;
	private boolean closed;
	
	/**
	 * ���ڳ�ʼ�������߳�
	 */
	public ServerSocketSrvImp() {
		// ��8888�˿ڼ���
		System.out.println("���Ƿ�����,��" + IConstant.SERVER_PORT + "����");
		serverSocket = null;
		try {
			serverSocket = new ServerSocket(IConstant.SERVER_PORT);
			closed = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override 
	/**
	 * ���ڷ�����������̵߳Ľ���
	 */
	public void run() {
		try {
			
			UserDao iud = new UserDaoImpl();
			while (!closed && !serverSocket.isClosed())// ѭ������
			{
				Socket s = serverSocket.accept();
	
				// ���ܿͻ��˷�������Ϣ
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				ClientReq clientReq = (ClientReq) ois.readObject();
				
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				String clientReqType = clientReq.getType();
				User u = new User();
				u.setContent(clientReq.getContent());
				System.out.println("User " + u.getId() + " operation: "	+ clientReqType);

				if (clientReqType.equals(ClientReqType.REQ_LOGIN)) {// ���ݿ����֤��½
					User user = iud.getUserByPwd(clientReq.getContent());
					if (user == null) {
						System.out.println("User " + u.getId() + " ��֤ʧ�� ");
						clientReq.setType("FAILED");
						clientReq.setLevel("0");
						oos.writeObject(clientReq);
						oos.flush();
						// �ر�����
					} else {
						System.out.println("User " + u.getId() + " ��֤�ɹ� ");
						// ����һ���ɹ���½����Ϣ��
						clientReq.setLevel(user.getRole());
						oos.writeObject(clientReq);
						oos.flush();
						// ����͵���һ���̡߳��ø��߳���ÿͻ��˱���ͨѶ
						ServerSocketSrvThread ssst = new ServerSocketSrvThread(s);
						ServerClientThreadMgr.add(user.getId(), ssst);
						// ������ÿͻ���ͨ�ŵ��߳�
						ssst.start();
					}
				}else if (clientReqType.equals(ClientReqType.REQ_REGISTER)) {// �û�ע��
					User user = iud.getUser(u);
					if (user == null) {
						user = iud.addUser(u);
						clientReq.setLevel(u.getRole());
						oos.writeObject(clientReq);
						oos.flush();
					}else {
						System.out.println("User " + u.getId() + " ע��ʧ�ܣ����д���");			
						clientReq.setType("FAILED");
						clientReq.setLevel("1");//1��ʾ�ظ���
						oos.writeObject(clientReq);
						oos.flush();
					}
				} else if(clientReqType.equals(ClientReqType.REQ_LOGOUT)){
					String userID = clientReq.getContent().get(0);
					User user = iud.searchUser(userID);
					if(user == null) {
						System.out.println("����δ��¼");
					} else {
						ServerClientThreadMgr.remove(user.getId());
						System.out.println("User " + u.getId() + " �ǳ��ɹ� ");
					}
					
				} else {
					throw new Exception("δ֪����Ϣ���ͻ�����Socket");
				}
			}
		} catch (SocketException se) {
			if (serverSocket.isClosed()) {
				System.out.println(" �������ر� ");
			} else {
				se.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * �����̵߳�����
	 */
	@Override
	public void start() {
		new Thread(this).start();
	}

	@Override
	public void close() {
		if (serverSocket != null)
			if (serverSocket != null)
				try {
					// ��ǰ�����̷߳�������֪ͨ
					Map<String, ServerSocketSrvThread> sctPool = ServerClientThreadMgr.getPool();
					Iterator<String> key = sctPool.keySet().iterator();
					while (key.hasNext()) {
						sctPool.get(key.next()).close();//ÿ���̶߳�close����ѭ���޷���������ȫ�����ṩ����
					}
					ServerClientThreadMgr.clear();
					// �رշ�����Socket
					closed = true;
					serverSocket.close();
					System.out.println("�������رգ�");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	/**
	 * �����ж��߳��Ƿ�ر�
	 */
	@Override
	public boolean isClosed() {
		return closed;
	}
}

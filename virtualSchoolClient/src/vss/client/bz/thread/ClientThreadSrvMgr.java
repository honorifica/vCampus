package vss.client.bz.thread;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * �����̹߳���
 * @author �����
 *
 */
public class ClientThreadSrvMgr {

	private static Map<String, ClientThreadSrv> clientThreadPool = new LinkedHashMap<String, ClientThreadSrv>();
/**
 * �����̵߳����
 * @param userID �û�ID
 * @param clientThreadSrv ���û�������߳�
 */
	public static void add(String userID, ClientThreadSrv clientThreadSrv) {
		clientThreadPool.put(userID, clientThreadSrv);		
	}

	/**
	 * �����Ƴ��û��߳�
	 * @param userID ��Ҫ�Ƴ��Ŀͻ�ID
	 * @return ����ֵΪ�õ����߳�
	 */
	public static ClientThreadSrv remove(String userID) {
		return clientThreadPool.remove(userID);		
	}

	/**
	 * ���ڵõ������̳߳�
	 * @return ����ֵΪ��ǰ���̳߳�
	 */
	public static Map<String, ClientThreadSrv> getPool(){
		return clientThreadPool;
	}
}
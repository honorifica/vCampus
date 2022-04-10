package vss.server.bz;

import java.util.LinkedHashMap;
import java.util.Map;

import vss.server.bz.ServerSocketSrvThread;

/**
 * @author �����
 * ServerClientThreadMgr���ڽ����̹߳���
 */
public class ServerClientThreadMgr {

	private  static  Map<String, ServerSocketSrvThread>  clientThreadPool = new LinkedHashMap<String, ServerSocketSrvThread>();

	/**
	 * ���������߳�
	 * @param id ��ʹ����ID��Ϊ�̵߳ı��
	 * @param clientThreadSrv �̣߳����ڼ����̳߳ؽ��й���
	 */
	public synchronized static void add(String id, ServerSocketSrvThread clientThreadSrv) {
		clientThreadPool.put(id, clientThreadSrv);		
	}

	/**
	 * ���������̵߳��̳߳�
	 * @param id ����ʹ���ߵ�ID�������̵߳��Ƴ�
	 * @return ����ֵΪ��
	 */
	public synchronized static ServerSocketSrvThread remove(String id) {
		return clientThreadPool.remove(id);		
	}

	/**
	 * ���ڵõ��̳߳�
	 * @return ����ֵΪ��
	 */
	public synchronized static Map<String, ServerSocketSrvThread> getPool(){
		return clientThreadPool;
	}
	
	/**
	 * ���ڽ����̳߳ص�����
	 */
	public synchronized static void clear() {
		clientThreadPool.clear();		
	}

}

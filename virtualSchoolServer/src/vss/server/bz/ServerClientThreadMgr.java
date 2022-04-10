package vss.server.bz;

import java.util.LinkedHashMap;
import java.util.Map;

import vss.server.bz.ServerSocketSrvThread;

/**
 * @author 姜云骞
 * ServerClientThreadMgr用于进行线程管理
 */
public class ServerClientThreadMgr {

	private  static  Map<String, ServerSocketSrvThread>  clientThreadPool = new LinkedHashMap<String, ServerSocketSrvThread>();

	/**
	 * 用于增加线程
	 * @param id 用使用者ID作为线程的编号
	 * @param clientThreadSrv 线程，用于加入线程池进行管理
	 */
	public synchronized static void add(String id, ServerSocketSrvThread clientThreadSrv) {
		clientThreadPool.put(id, clientThreadSrv);		
	}

	/**
	 * 用于增加线程到线程池
	 * @param id 传入使用者的ID，进行线程的移出
	 * @return 返回值为空
	 */
	public synchronized static ServerSocketSrvThread remove(String id) {
		return clientThreadPool.remove(id);		
	}

	/**
	 * 用于得到线程池
	 * @return 返回值为空
	 */
	public synchronized static Map<String, ServerSocketSrvThread> getPool(){
		return clientThreadPool;
	}
	
	/**
	 * 用于进行线程池的清理
	 */
	public synchronized static void clear() {
		clientThreadPool.clear();		
	}

}

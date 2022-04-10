package vss.server.bz;

/**
 * 线程服务的接口
 * @author 姜云骞
 *
 */
public interface ServerSocketSrv extends Runnable {
	
	/**
	 * 用于线程启动
	 */
	void start();

	/**
	 * 用于线程关闭
	 */
	void close();

	/**
	 * 用于判断线程是否关闭
	 * @return 当关闭时返回true
	 */
	boolean isClosed();
	
}

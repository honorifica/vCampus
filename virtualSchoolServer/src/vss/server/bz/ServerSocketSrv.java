package vss.server.bz;

/**
 * �̷߳���Ľӿ�
 * @author �����
 *
 */
public interface ServerSocketSrv extends Runnable {
	
	/**
	 * �����߳�����
	 */
	void start();

	/**
	 * �����̹߳ر�
	 */
	void close();

	/**
	 * �����ж��߳��Ƿ�ر�
	 * @return ���ر�ʱ����true
	 */
	boolean isClosed();
	
}

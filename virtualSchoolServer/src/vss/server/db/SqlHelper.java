package vss.server.db;
/**
 * This class operates the Access database through the SQL statement.
 * @author gxc,lsy
 * @date 2021-7-26
 * @version 1.0
 */
import java.util.*;
import vss.common.*;

/**
 * Interface to operate database
 * @author lsy,gxc
 */
public interface SqlHelper {
	/**
	 * This method is used to update the database
	 * @param sql is an SQL statement that operates on the database which ddta is ?.
	 * @param paras The elements in Paras will replace the ? in SQL statement in order.
	 * @return List if users
	 */
	public List<User> sqlUserQuery(String sql,String []paras);
	/**
	 * This method is used to update the database
	 * @param sql is an SQL statement that operates on the database which ddta is ?.
	 * @param paras The elements in Paras will replace the ? in SQL statement in order.
	 * @return When operating success return true, otherwise return false.
	 */
	public List<Book> sqlBookQuery(String sql,String []paras);
	/**
	 * This method is used to obtain books in the database.
	 * @param sql is an SQL statement that operates on the database which ddta is ?.
	 * @param paras The elements in Paras will replace the ? in SQL statement in order.
	 * @return A list of books.
	 */
	public List<Goods> sqlGoodsQuery(String sql,String []paras);
	/**
	 * This method is used to obtain goods in the database.
	 * @param sql is an SQL statement that operates on the database which ddta is ?.
	 * @param paras The elements in Paras will replace the ? in SQL statement in order.
	 * @return A list of goods.
	 */
	public List<Course> sqlCourseQuery(String sql,String []paras);
	/**
	 * This method is used to obtain courses in the database.
	 * @param sql is an SQL statement that operates on the database which ddta is ?.
	 * @param paras The elements in Paras will replace the ? in SQL statement in order.
	 * @return A list of courses.
	 */
	public List<String> sqlRelationQuery(String sql,String[] paras);
	//连接数据库并更新数据
	/**
	 * This method is used to query relational tables.
	 * @param sql is an SQL statement that operates on the database which ddta is ?.
	 * @param paras The elements in Paras will replace the ? in SQL statement in order.
	 * @return A String list of bookID or courseID.
	 */
	public boolean sqlUpdate(String sql,String []paras);
}

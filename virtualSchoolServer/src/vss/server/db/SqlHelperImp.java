package vss.server.db;
/**
 * This class operates the Access database through the SQL statement.
 * @author gxc,lsy
 * @date 2021-7-26
 * @version 1.0
 */
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import vss.common.Book;
import vss.common.Course;
import vss.common.Goods;
import vss.common.User;
import vss.server.dao.DAOUtil;
/**
 * implements of SqlHelper
 * @author gxcnlsy
 *
 */
public class SqlHelperImp implements SqlHelper{
	/**
	 * Relative address of access database.
	 */
	private static final String HXTT_ACCESS_JDBC_DRIVER = "com.hxtt.sql.access.AccessDriver";
	private static final String ACCESS_DRIVER = HXTT_ACCESS_JDBC_DRIVER;
	
	private static String url;
	/**
	 * The absolute address of the used to connect to the local database.
	 */
	static {
		String dbpath = new File("").getAbsolutePath().replace('\\', '/') + "/db_vCampus.accdb";
		url = "jdbc:access:/" + dbpath;
	}
	/**
	 * Empty user name and password.
	 */
	String user="";
	String passwd=null;
	
	public boolean sqlUpdate(String sql,String []paras)
	{
		/**
		 * This method is used to update the database
		 * @param sql is an SQL statement that operates on the database which ddta is ?.The elements in Paras will replace the ? in SQL statement in order.
		 * @return When operating success return true, otherwise return false.
		 * @exception Database connection failed.
		 */
		PreparedStatement ps=null;
		Connection ct=null;
		boolean b=true;
		try {
			 Class.forName(ACCESS_DRIVER);
			ct=DriverManager.getConnection(url);
			ps=ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++)
			{
				ps.setString(i+1, paras[i]);
			}
			ps.executeUpdate();
			
		} catch (Exception e) {
			b=false;
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null)
					ps.close();
				if(ct!=null)
					ct.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return b;
	}
	
	public List<User> sqlUserQuery(String sql,String []paras)
	{
		/**
		 * This method is used to update the database
		 * @param sql is an SQL statement that operates on the database which ddta is ?.The elements in Paras will replace the ? in SQL statement in order.
		 * @return When operating success return true, otherwise return false.
		 * @exception Database connection failed.
		 */
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;	
		List<User> users = null;
		
		try {
			Class.forName(ACCESS_DRIVER);
			ct=DriverManager.getConnection(url,user,passwd);
			ps=ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
			users = DAOUtil.UserResultSet2List(rs);			
		} catch (Exception e) {		
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(ct!=null)
					ct.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	@Override
	public List<Book> sqlBookQuery(String sql, String[] paras) {
		/**
		 * This method is used to obtain books in the database.
		 * @param sql is an SQL statement that operates on the database which ddta is ?.The elements in Paras will replace the ? in SQL statement in order.
		 * @return A list of books.
		 * @exception Failed to connect database.
		 */
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;	
		List<Book> books = null;
		
		try {
			Class.forName(ACCESS_DRIVER);
			ct=DriverManager.getConnection(url,user,passwd);
			ps=ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
			books = DAOUtil.BookResultSet2List(rs);			
		} catch (Exception e) {		
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(ct!=null)
					ct.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return books;
	}

	@Override
	public List<Goods> sqlGoodsQuery(String sql, String[] paras) {
		/**
		 * This method is used to obtain goods in the database.
		 * @param sql is an SQL statement that operates on the database which data is ?.The elements in Paras will replace the ? in SQL statement in order.
		 * @return A list of goods.
		 * @exception Failed to connect database.
		 */
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;	
		List<Goods> goods = null;
		
		try {
			Class.forName(ACCESS_DRIVER);
			ct=DriverManager.getConnection(url,user,passwd);
			ps=ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
			goods = DAOUtil.GoodsResultSet2List(rs);			
		} catch (Exception e) {		
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(ct!=null)
					ct.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return goods;
	}

	@Override
	public List<Course> sqlCourseQuery(String sql, String[] paras) {
		/**
		 * This method is used to obtain courses in the database.
		 * @param sql is an SQL statement that operates on the database which ddta is ?.The elements in Paras will replace the ? in SQL statement in order.
		 * @return A list of courses.
		 * @exception Failed to connect database.
		 */
		PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs=null;
		List<Course> courses=null;
		try{
			Class.forName(ACCESS_DRIVER);
			ct=DriverManager.getConnection(url, user, passwd);
			ps=ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
			courses = DAOUtil.CourseResultSet2List(rs);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(ct!=null)
					ct.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return courses;
	}

	@Override
	public List<String> sqlRelationQuery(String sql, String[] paras) {
		/**
		 * This method is used to query relational tables.
		 * @param sql is an SQL statement that operates on the database which ddta is ?.The elements in Paras will replace the ? in SQL statement in order.
		 * @return A String list of bookID or courseID.
		 * @exception Failed to connect database.
		 */
		PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs=null;
		List<String> objs=null;
		try{
			Class.forName(ACCESS_DRIVER);
			ct=DriverManager.getConnection(url, user, passwd);
			ps=ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
			objs = DAOUtil.relationResulList(rs);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(ct!=null)
					ct.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objs;
	}
}

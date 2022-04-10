package vss.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vss.common.*;

public class DAOUtil {
	/**
	 * Get all users
	 * @param rs Return result of database
	 * @return A list containing all users
	 * @throws SQLException
	 */
	public static List<User> UserResultSet2List(ResultSet rs) throws SQLException {
		List<User> users = new ArrayList<User>();
		while(rs.next())
		{				
			User u = new User();
			u.setId(rs.getString(1));
			u.setName(rs.getString(2));
			u.setAge(rs.getString(3));
			u.setSex(rs.getString(4));
			u.setGrade(rs.getString(5));
			u.setMajor(rs.getString(6));
			u.setPwd(rs.getString(7));
			u.setRole(rs.getString(8));
			u.setMoney(rs.getString(9));
			users.add(u);
		}	
		return users;
	}
	/**
	 * Get all books
	 * @param rs Return result of database
	 * @return A list containing all books
	 * @throws SQLException
	 */
	public static List<Book> BookResultSet2List(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		while(rs.next())
		{				
			Book  b= new Book(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
			books.add(b);
		}	
		return books;
	}
	/**
	 * Get all goods
	 * @param rs Return result of database
	 * @return A list containing all goods
	 * @throws SQLException
	 */
	public static List<Goods> GoodsResultSet2List(ResultSet rs) throws SQLException {
		List<Goods> goods = new ArrayList<Goods>();
		while(rs.next())
		{				
			Goods g = new Goods(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
			goods.add(g);
		}	
		return goods;
	}
	/**
	 * Get all courses
	 * @param rs Return result of database
	 * @return A list containing all courses
	 * @throws SQLException
	 */
	public static List<Course> CourseResultSet2List(ResultSet rs) throws SQLException {
		List<Course> courses = new ArrayList<Course>();
		while(rs.next())
		{				
			Course c = new Course(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			courses.add(c);
		}	
		return courses;
	}
	/**
	 * Get all items related to the user
	 * @param rs Return result of database
	 * @return A list containing all items about this user
	 * @throws SQLException
	 */
	public static List<String> relationResulList(ResultSet rs) throws SQLException{
		List<String> objs = new  ArrayList<String>();
		while(rs.next())
		{
			String obj=rs.getString(3);
			objs.add(obj); 
		}
		return objs;
	}
}

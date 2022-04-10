package vss.server.dao;

import java.util.List;
import java.util.Vector;

import vss.common.User;

public interface UserDao {
    
	/**
	 * Details of the user
	 * @param user Detail of the user be searched
	 * @return Detail of the user be searched
	 */
	public User getUser(User user);
	/**
	 * search user by his password
	 * @param content Used to store user's password
	 * @return Detail of the user be searched
	 */
	public User getUserByPwd(Vector<String> content);
	/**
	 * Add a user to the database
	 * @param user Detail of the user to be added
	 * @return Detail of the user to be added
	 */
	public User addUser(User user);
	/**
	 * delete user by his ID
	 * @param userID ID of the user to be deleted
	 * @return return a value of boolean, showing this function success or fail
	 */
	public boolean delUser(String userID);
	/**
	 * Search user by his ID
	 * @param id ID of the user to be searched
	 * @return Detail of the user to be searched
	 */
	public User searchUser(String id);	
	/**
	 * Get information about all users
	 * @return Full information of all users
	 */
	public List<User> getAllUsers();

}

package vss.server.dao;

import java.util.List;
import java.util.Vector;

import vss.common.*;

import vss.server.db.SqlHelperImp;

public class UserDaoImpl implements UserDao {
	/**
	 * �ⲿ����������User
	 */
	@Override
	public User getUser(User user) {
		// TODO �Զ����ɵķ������
		String sql = "select * from tb_User where uID= ? and uName=?";
		String[] paras = new String[2];
		paras[0] = user.getId();
		paras[1] = user.getName();
		List<User> users = new SqlHelperImp().sqlUserQuery(sql, paras);
		if (users != null && users.size() > 0) {
			return users.get(0);
		} else
			return null;
	}

	@Override
	public User addUser(User user) {
		// TODO �Զ����ɵķ������
		String sql = "insert into tb_User(uID, uName, uAge, uSex,uGrade, uMajor,uPwd,uRole,uMoney) values (?,?,?,?,?,?,?,?,?)";
		String[] paras = new String[9];
		paras[0] = user.getId();
		paras[1] = user.getName();
		paras[2] = user.getAge();
		paras[3] = user.getSex();
		paras[4] = user.getGrade();
		paras[5] = user.getMajor();
		paras[6] = user.getPwd();
		paras[7] = user.getRole();
		paras[8] = user.getMoney();
		new SqlHelperImp().sqlUpdate(sql, paras);
		return searchUser(user.getId());
	}

	@Override
	public boolean delUser(String userID) {
		String sql = "delete from tb_User where uID = ?";
		return new SqlHelperImp().sqlUpdate(sql , new String[] {userID});
	}

	@Override
	public User searchUser(String id) {
		// TODO �Զ����ɵķ������
		String sql = "select * from tb_User where uID=?";
		String[] paras = new String[1];
		paras[0] = id;
		List<User> users = new SqlHelperImp().sqlUserQuery(sql, paras);
		if (users != null && users.size() > 0) {
			return users.get(0);
		} else
			return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO �Զ����ɵķ������
		String sql = "select * from tb_User";
		return new SqlHelperImp().sqlUserQuery(sql, new String[] {});
	}

	@Override
	public User getUserByPwd(Vector<String> content) {
		String sql = "select * from tb_User where uID= ? and uPwd=?";
		String[] paras = new String[2];
		paras[0] = content.get(0);
		paras[1] = content.get(6);
		List<User> users = new SqlHelperImp().sqlUserQuery(sql, paras);
		if (users != null && users.size() > 0) {
			return users.get(0);
		} else
			return null;
	}

}

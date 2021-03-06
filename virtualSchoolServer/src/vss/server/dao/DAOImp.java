package vss.server.dao;

import java.util.List;
import java.util.Vector;

import vss.common.*;

import vss.server.db.SqlHelperImp;
public class DAOImp implements BookDao,CourseDao,GoodsDao,UserDao{

	@Override
	public User getUser(User user) {
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

    /**
     * ????????????Goods
     */

    @Override
    public Goods searchGoods(String goodsName) {
        // TODO Auto-generated method stub
        String sql = "select * from tb_Goods where gName = ?";
        String[] paras = new String[1];
        paras[0] = goodsName;
        List<Goods> goodsList=new SqlHelperImp().sqlGoodsQuery(sql,paras);
        if(goodsList!=null&&goodsList.size()>0) {
            return goodsList.get(0);
        }else
            return null;
    }
    @Override
    public boolean buyGoods(String goodsName, int goodsAmount, String buyerID) {
    	 // TODO Auto-generated method stub
        String[] paras = new String[2];
        paras[1] = goodsName;
        Goods goods = this.searchGoods(goodsName);
        int left = Integer.valueOf(goods.getGoodsAmount());
        left = left - goodsAmount;
        if(left==0)
        {
            return false;
        }
        else{
        	UserDaoImpl userDao =  new UserDaoImpl();
        	User user =  userDao.searchUser(buyerID);
        	String moneyStr = user.getMoney();
        	int money =Integer.valueOf(moneyStr);
        	String priceStr = goods.getGoodsPrice();
        	int price = Integer.valueOf(priceStr);
        	if(money - price * goodsAmount >=0) {
	            paras[0] = String.valueOf(left);
	            String sql = "update tb_Goods set gAmount = ? where gName = ?";
	            String sql2 = "update tb_User set uMoney = ? where uID = ?";
	            String[] paras2= new String[2];
	            paras2[0] = String.valueOf(money - price * goodsAmount);
	            paras2[1]= buyerID;
	            new SqlHelperImp().sqlUpdate(sql, paras);
	            new SqlHelperImp().sqlUpdate(sql2, paras2);
	            return true;
        	}
        	else return false;
        }
    }

    @Override
    public boolean addGoods(Goods goods) {
        // TODO Auto-generated method stub
        String sql = "insert into tb_Goods(gID,gName,gAmount,gPrice) values (?,?,?,?)";
        String[] paras = new String[4];
        paras[0]=goods.getGoodsID();
        paras[1]=goods.getGoodsName();
        paras[2]=goods.getGoodsAmount();
        paras[3]=goods.getGoodsPrice();
        new SqlHelperImp().sqlUpdate(sql, paras);
        Goods temp=searchGoods(goods.getGoodsName());
        if(temp==null)
            return false;
        else
            return true;
    }

    @Override
    public boolean removeGoods(String goodsName) {
        // TODO Auto-generated method stub
        String sql = "delete from tb_Goods where gName = ?";
            return new SqlHelperImp().sqlUpdate(sql , new String[] {goodsName});
    }
    
    @Override
	public List<Goods> getAllGoods() {
    	String sql = "select * from tb_Goods";
    	return new SqlHelperImp().sqlGoodsQuery(sql,new String[] {});
	}
    
    /**
     * ??????Course
     */
    @Override
    public Course searchCourseByID(String courseID) {
        // TODO Auto-generated method stub
        String sql = "select from tb_Class where cID = ?";
        String[] paras = new String[1];
        paras[0] = courseID;
        List<Course> cList=new SqlHelperImp().sqlCourseQuery(sql, paras);
        if(cList!=null&&cList.size()>0) {
            return cList.get(0);
        }else
            return null;
    }
    @Override
    public List<Course> getAllCourse() {
        // TODO Auto-generated method stub
        String sql = "select * from tb_Class";
		return new SqlHelperImp().sqlCourseQuery(sql, new String[] {});
    }

    @Override
    public boolean sigAddCourse(String courseID,String uID) {
        String[] paras1 = new String[2];
        String[] paras2 = new String[2];
        paras1[1] = courseID;
        Course course = this.searchCourseByID(courseID);
        int left = Integer.valueOf(course.getCNownum());
        left += 1;
        if(left>Integer.valueOf(course.getCMaxnum()))
        {//??????????????????????????????false
            return false;
        }
        else{
            paras1[0] = String.valueOf(left);
            paras2[0]=uID;
            paras2[1]=courseID;
            String sql1 = "update tb_Class set cNownum = ? where cID = ?";
            String sql2 = "insert into tb_Stc(uID,cID) value(?,?)";
            return new SqlHelperImp().sqlUpdate(sql1, paras1) && new SqlHelperImp().sqlUpdate(sql2, paras2);
        }
    }

    @Override
    public boolean sigRemoveCourse(String courseID,String uID) {
        
        String[] paras1=new String[2];
        String[] paras2=new String[2];
        String sql1="update tb_Class set cNownum = ? where cID = ?";
        String sql2="delete from tb_Class where cID = ? and cID = ?";
        Course course = this.searchCourseByID(courseID);
        int left = Integer.valueOf(course.getCNownum());
        left--;
        if(left<0){
            return false;
        } else{
            paras1[0]=String.valueOf(left);
            paras1[1]=courseID;
            paras2[0] = uID;
            paras2[1] = courseID;
            return new SqlHelperImp().sqlUpdate(sql2, paras2) && new SqlHelperImp().sqlUpdate(sql1, paras1);
        }
    }

    @Override
    public boolean genAddCourse(Course course) {
        // TODO Auto-generated method stub
        String sql="insert into tb_Class(cID,cName,cHours,cMaxnum,cNownum) values (?,?,?,?,?)";
        String[] paras=new String[5];
        paras[0]=course.getCourseID();
        paras[1]=course.getCourseName();
        paras[2]=course.getCourseHours();
        paras[3]=course.getCMaxnum();
        paras[4]=course.getCNownum();
        return new SqlHelperImp().sqlUpdate(sql, paras);
    }

    @Override
    public boolean genRemoveCourse(String courseName) {
    	 String sql="delete from tb_Class where cName = ?";
         String[] paras=new String[1];
         paras[0]=courseName;
         return new SqlHelperImp().sqlUpdate(sql, paras);
    }
    
    /**
     * ??????book
     */
    @Override
    public List<Book> getAllBook() {
        // TODO Auto-generated method stub
        String sql="select * from tb_Book";
        return new SqlHelperImp().sqlBookQuery(sql, new String[] {});
    }

    @Override
    public boolean addBook(Book book) {
        // TODO Auto-generated method stub
        String sql="insert into tb_Book(bID,bName,bAmount,bLeft) value(?,?,?,?)";
        String[] paras=new String[4];
        paras[0]=book.getBookID();
        paras[1]=book.getBookName();
        paras[2]=book.getBookAmount();
        paras[3]=book.getLeft();
        return new SqlHelperImp().sqlUpdate(sql, paras);
    }

    @Override
    public boolean borrowBook(String bookID,String uID) {
        String sql1="update tb_Book set bAmount = ? where bID = ? ";
        String sql2="insert into tb_Stb(uID,bID) value(?,?)";
        String[] paras1=new String[2];
        String[] paras2=new String[2];
        Book temp=searchBookByID(bookID);
        int bLeft=Integer.valueOf(temp.getBookAmount());
        bLeft--;
        if(bLeft<0)return false;
        else{
            paras1[0]=String.valueOf(bLeft);
            paras1[1]=bookID;
            paras2[0]=uID;
            paras2[1]=bookID;
            return new SqlHelperImp().sqlUpdate(sql1, paras1) && new SqlHelperImp().sqlUpdate(sql2, paras2);
        }
    }

    @Override
    public boolean removeBook(String bookID) {
        // TODO Auto-generated method stub
        String sql="delete from tb_Book where bID=?";
        String[] paras=new String[1];
        paras[0]=bookID;
        return new SqlHelperImp().sqlUpdate(sql, paras);
    }

    @Override
    public boolean returnBook(String bookID,String uID) {
        String sql1="update tb_Class set bAmount = ? where bID = ? ";
        String sql2="delete from tb_Stb where bID = ? and bID= ?";
        String[] paras1=new String[2];
        String[] paras2=new String[2];
        Book temp=searchBookByID(bookID);
        int bLeft=Integer.valueOf(temp.getBookAmount());
        paras1[0]=String.valueOf(bLeft);
        paras1[1]=bookID;
        paras2[0]=uID;
        paras2[1]=bookID;
        bLeft++;
        return new SqlHelperImp().sqlUpdate(sql1, paras1) && new SqlHelperImp().sqlUpdate(sql2, paras2);
    }

    @Override
    public Book searchBookByID(String bookID) {
        // TODO Auto-generated method stub
        String sql="select from tb_BOOk where bID = ?";
        String[] paras=new String[1];
        paras[0]=bookID;
        List<Book> bList=new SqlHelperImp().sqlBookQuery(sql, paras);
        if(bList!=null&&bList.size()>0)return bList.get(0);
        else return null;
    }

    @Override
    public Book searchBookByName(String bookName) {
        // TODO Auto-generated method stub
        String sql="select from tb_Book where bName= ? ";
        String[] paras=new String[1];
        paras[0]=bookName;
        List<Book> bList=new SqlHelperImp().sqlBookQuery(sql, paras);
        if(bList!=null&&bList.size()>0)return bList.get(0);
        else return null;
    }
}

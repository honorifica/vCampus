package vss.server.dao;

import java.util.LinkedList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import vss.common.Book;
import vss.common.User;
import vss.server.db.SqlHelperImp;

public class BookDaoImp implements BookDao {
	
    @Override
    public List<Book> getAllBook() {
        String sql="select * from tb_Book";
        return new SqlHelperImp().sqlBookQuery(sql, new String[] {});
    }

    @Override
    public boolean addBook(Book book) {
        // TODO Auto-generated method stub
        String sql="insert into tb_Book(bID, bName, bAmount, bLeft) values(?,?,?,?)";
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
        String sql2="insert into tb_Stb(sbID, uID,bID) values(?,?,?)";
        String[] paras1=new String[2];
        String[] paras2=new String[3];
        Book temp=searchBookByID(bookID);
        int bLeft=Integer.valueOf(temp.getBookAmount());
        bLeft--;
        boolean excuteSuccess = false;
        if(bLeft<0)return excuteSuccess;
        else{
            paras1[0]=String.valueOf(bLeft);
            paras1[1]=bookID;
            paras2[0]=bookID+uID;
            paras2[1]=uID;
            paras2[2]=bookID;
            return new SqlHelperImp().sqlUpdate(sql1, paras1) &&  new SqlHelperImp().sqlUpdate(sql2, paras2);
	       
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
        String sql1="update tb_Book set bAmount = ? where bId = ? ";
        String sql2="delete from tb_Stb where uId = ? and bId = ?";
        String[] paras1=new String[2];
        String[] paras2=new String[2];
        Book temp=searchBookByID(bookID);
        int bLeft=Integer.valueOf(temp.getBookAmount());
        bLeft++;
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
        String sql="select * from tb_Book where bID = ?";
        String[] paras=new String[1];
        paras[0]=bookID;
        List<Book> bList=new SqlHelperImp().sqlBookQuery(sql, paras);
        if(bList!=null&&bList.size()>0)return bList.get(0);
        else return null;
    }

   
    @Override
    public Book searchBookByName(String bookName) {
        // TODO Auto-generated method stub
        String sql="select *from tb_Book where bName= ? ";
        String[] paras=new String[1];
        paras[0]=bookName;
        List<Book> bList=new SqlHelperImp().sqlBookQuery(sql, paras);
        if(bList!=null&&bList.size()>0)return bList.get(0);
        else return null;
    }
}

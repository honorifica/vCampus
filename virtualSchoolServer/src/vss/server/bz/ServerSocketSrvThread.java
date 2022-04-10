package vss.server.bz;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import vss.common.*;
import vss.server.bz.*;
import vss.server.dao.*;

import java.util.*;
/**
 *  ����������Ϣ�����߳�
 */
public class ServerSocketSrvThread extends Thread {
	private Socket clientReqSocket;
	private boolean isClosed;

	/**
	 * ����socket�Ĵ���
	 * @param s ���߳���������õ�socket
	 * @throws IOException �׳��������������
	 * @throws ClassNotFoundException �׳�����δ���ִ���
	 */
	public ServerSocketSrvThread(Socket s) throws IOException, ClassNotFoundException {
		this.clientReqSocket = s;
		this.isClosed = false;	
	}
 
	@Override
	/**
	 * �����̵߳ķ����ȡ��ִ��
	 */
	public void run() {

		while (!isClosed) {
			// ������߳̾Ϳ��Խ��տͻ��˵Ĳ�������
			try {
				ObjectInputStream ois = new ObjectInputStream(clientReqSocket.getInputStream());
				ClientReq clientReq = (ClientReq) ois.readObject();
				String type = clientReq.getType();
				// �Դӿͻ���ȡ�õ���Ϣ���������жϣ��ú�����Ӧ�Ĵ���
				if (type.equals(ClientReqType.REQ_SHOW_SIG_INFOR)) {
					System.out.println("serving REQ_SHOW_SIG_INFOR");
					System.out.println("grabbing.....");
					//���ص���ѧ��ѧ����Ϣ
					User user = new User();
					String studentID = clientReq.getContent().get(0);
					UserDaoImpl userDao=new UserDaoImpl();
					user = userDao.searchUser(studentID);
					//content�ĸ���
					clientReq.setContent(user.getContent());
					// --��������ѯ�û�
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);//�����д��
					oos.flush();
					System.out.println("REQ_SHOW_SIG_INFOR finished");
				} else if(type.equals(ClientReqType.REQ_ADD_SIG_INFOR)) {
					System.out.println("serving REQ_ADD_SIG_INFOR");
					System.out.println("adding...... ");
					//���ӵ���ѧ��ѧ����Ϣ
					User user = new User();
					user.setContent(clientReq.getContent());
					UserDaoImpl userDao=new UserDaoImpl();
					userDao.addUser(user);
					System.out.println("REQ_ADD_SIG_INFOR finished");
				} else if(type.equals(ClientReqType.REQ_REMOVE_SIG_INFOR)) {
					System.out.println("serving REQ_REMOVE_SIG_INFOR");
					System.out.println("removing....");
					//ɾ������ѧ��ѧ����Ϣ
					String userID = clientReq.getContent().get(0);
					UserDaoImpl userDao=new UserDaoImpl();
					userDao.delUser(userID);
					System.out.println("REQ_REMOVE_SIG_INFOR finished");
				} else if(type.equals(ClientReqType.REQ_SHOW_ALL_INFOR)) {
					System.out.println("serving REQ_SHOW_ALL_INFOR");
					System.out.println("grabbing......");
					//����ȫ��ѧ����Ϣ������˽���Vector���д���
					List<User> userList = new LinkedList<User>();
					UserDaoImpl userDao = new UserDaoImpl();
					userList = userDao.getAllUsers();
					Iterator<User> listIterator = userList.iterator();
					Vector<String> sigUserContent=new Vector<String>();
					Vector<String> allUserContent= new Vector<String>();
					while(listIterator.hasNext()){
						sigUserContent = listIterator.next().getContent();
						for(int i=0;i<=8;i++) {
							allUserContent.add(sigUserContent.get(i));
						}
					}
					clientReq.setContent(allUserContent);
					ObjectOutputStream oos=new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					System.out.println("REQ_SHOW_ALL_INFOR finished");
				} else if(type.equals(ClientReqType.REQ_ADD_GOODS)) {
					System.out.println("serving REQ_ADD_GOODS");
					System.out.println("adding....");
					//����Ա����������Ʒ
					GoodsDaoImp goodsDao= new GoodsDaoImp();
					Goods goods=new Goods();
					goods.setContent(clientReq.getContent()); 
					goodsDao.addGoods(goods);
					System.out.println("REQ_ADD_GOODS finished");
				} else if(type.equals(ClientReqType.REQ_REMOVE_GOODS)) {
					//����Ա�����Ƴ���Ʒ
					System.out.println("serving REQ_REMOVE_GOODS");
					System.out.println("removing.....");
					String goodsName = clientReq.getContent().get(1);
					GoodsDaoImp goodsDao = new GoodsDaoImp();
					boolean result = goodsDao.removeGoods(goodsName);
					System.out.println("REQ_REMOVE_GOODS finished");
				} else if(type.equals(ClientReqType.REQ_BUY_GOODS)) {
					System.out.println("serving REQ_BUY_GOODS");
					System.out.println("buying.....");
					//������ڹ�����Ʒ
					String goodsName = clientReq.getContent().get(0);
					GoodsDaoImp goodsDao = new GoodsDaoImp();
					Goods goods = goodsDao.searchGoods(goodsName);
					if(goods==null) {
						clientReq.setType("NULL");
						clientReq.setContent(new Vector<String>());
					}
					else {
						String goodAmountString = clientReq.getContent().get(1);
						String buyerID = clientReq.getContent().get(2);
						int goodsAmount = Integer.valueOf(goodAmountString,10);
						goodsDao.buyGoods(goodsName, goodsAmount, buyerID);
					}
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					System.out.println("REQ_BUY_GOODS finished");
				} else if(type.equals(ClientReqType.REQ_SHOW_ALL_GOODS)) {
					System.out.println("serving REQ_SHOW_ALL_GOODS");
					System.out.println("grabbing");
					//����ȫ���Ļ�����Ϣ
					Vector<String> sigGoodsContent= new Vector<String>();
					Vector<String> allGoodsContent = new Vector<String>();
					List<Goods> allGoods = new LinkedList<Goods>();
					GoodsDaoImp goodsDao= new GoodsDaoImp();
					allGoods = goodsDao.getAllGoods();
					Iterator<Goods> iteAllGoods = allGoods.iterator();
					while(iteAllGoods.hasNext()) {
						sigGoodsContent = iteAllGoods.next().getContent();
						for(int i=0;i<=3;i++) {
							allGoodsContent.add(sigGoodsContent.get(i));
						}
					}
					clientReq.setContent(allGoodsContent);
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					System.out.println("REQ_SHOW_ALL_GOODS finished");
				}  else if(type.equals(ClientReqType.REQ_ADD_BOOK)) {
					System.out.println("serving REQ_ADD_BOOK");
					System.out.println("adding.....");
					BookDaoImp bookDao = new BookDaoImp();
					Book book = new Book();
					book.setContent(clientReq.getContent());
					bookDao.addBook(book);
					System.out.println("REQ_ADD_BOOK finished");
				} else if(type.equals(ClientReqType.REQ_REMOVE_BOOK)) {
					System.out.println("serving REQ_REMOVE_BOOK");
					System.out.println("removing.....");
					BookDaoImp bookDao = new BookDaoImp();
					String bookID=clientReq.getContent().get(0);
					bookDao.removeBook(bookID);
					System.out.println("REQ_REMOVE_BOOK finished");
				} else if(type.equals(ClientReqType.REQ_SHOW_ALL_BOOK)) {
					System.out.println("serving REQ_SHOW_ALL_BOOK");
					System.out.println("processing.....");
					BookDaoImp bookDao = new BookDaoImp();
					List<Book> allBook= new LinkedList<Book>();
					allBook = bookDao.getAllBook();
					Iterator<Book>iteBook = allBook.iterator();
					Vector<String>allBookInfor = new Vector<String>();
					Book tempBook = new Book();
					while(iteBook.hasNext()) {
						tempBook= iteBook.next();
						for(int i=0;i<4;i++) {
							allBookInfor.add(tempBook.getContent().get(i));
						}
					}
					clientReq.setContent(allBookInfor);
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					System.out.println("REQ_SHOW_ALL_BOOK finished");
				} else if(type.equals(ClientReqType.REQ_BORROW_BOOK)) {
					System.out.println("serving REQ_BORROW_BOOK");
					System.out.println("borrowing......");
					String bookID = clientReq.getContent().get(0);
					String userID = clientReq.getContent().get(1);
					BookDaoImp bookDao = new BookDaoImp();
					bookDao.borrowBook(bookID, userID);
					System.out.println("REQ_BORROW_BOOK finished");
				} else if(type.equals(ClientReqType.REQ_RETURN_BOOK)) {
					System.out.println("serving REQ_RETURN_BOOK");
					System.out.println("returning.....");
					String bookID = clientReq.getContent().get(0);
					String userID = clientReq.getContent().get(1);
					BookDaoImp bookDao = new BookDaoImp();
					bookDao.returnBook(bookID, userID);
					System.out.println("REQ_RETURN_BOOK finished");
				} else if(type.equals(ClientReqType.REQ_SHOW_SIG_BOOK)) {
					System.out.println("serving REQ_SHOW_SIG_BOOK");
					System.out.println("grabbing....");
					BookDaoImp bookDao = new BookDaoImp();
					String bookID = clientReq.getContent().get(0);
					Book book = bookDao.searchBookByID(bookID);
					if(book!=null) {
						clientReq.setContent(book.getContent());
						ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
					} else {
						clientReq.setContent(new Vector<String>());
						clientReq.setType("NULL");
						ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
					}
					System.out.println("REQ_SHOW_SIG_BOOK finished");
				} else if (type.equals(ClientReqType.REQ_STU_ADD_LESSON)) {
					System.out.println("serving REQ_STU_ADD_LESSON");
					System.out.println("adding....");
					//ѧ��ѡ��
					CourseDaoImp courseDao= new CourseDaoImp();
					String CourseId = clientReq.getContent().get(0);
					String UserId = clientReq.getContent().get(1);
					boolean result = courseDao.sigAddCourse(CourseId,UserId);
					System.out.println("REQ_STU_ADD_LESSON finished");
				} else if (type.equals(ClientReqType.REQ_STU_REMOVE_LESSON)) {
					System.out.println("serving REQ_STU_REMOVE_LESSON");
					System.out.println("removing....");
					//ѧ���˿�
					CourseDaoImp courseDao= new CourseDaoImp();
					String CourseId = clientReq.getContent().get(0);
					String UserId = clientReq.getContent().get(1);
					courseDao.sigRemoveCourse(CourseId,UserId);
					System.out.println("REQ_STU_REMOVE_LESSON finshed");
				} else if (type.equals(ClientReqType.REQ_ADD_LESSON)) {
					System.out.println("serving REQ_ADD_LESSON");
					System.out.println("adding....");
					//��ʦ��ӿγ�
					CourseDaoImp courseDao= new CourseDaoImp();
					Course course=new Course();
					course.setContent(clientReq.getContent()); 
					courseDao.genAddCourse(course);
					System.out.println("REQ_ADD_LESSON finished");
				} else if (type.equals(ClientReqType.REQ_REMOVE_LESSON)) {
					//��ʦɾ���γ�
					System.out.println("serving REQ_REMOVE_LESSON");
					System.out.println("removing.....");
					String courseName = clientReq.getContent().get(1);
					CourseDaoImp courseDao = new CourseDaoImp();
					boolean result = courseDao.genRemoveCourse(courseName);
					System.out.println("REQ_REMOVE_LESSON finished");
				} else if (type.equals(ClientReqType.REQ_SEARCH_LESSON)) {
					System.out.println("serving REQ_SEARCH_LESSON");
					System.out.println("searching.....");
					//���ص����γ���Ϣ
					Course course = new Course();
					String courseID = clientReq.getContent().get(0);
				    CourseDaoImp courseDao=new CourseDaoImp();
				    course = courseDao.searchCourseByID(courseID);
				    //course.print();
					//content�ĸ���
					clientReq.setContent(course.getContent());
					// --��������ѯ�û�
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);//�����д��
					oos.flush();
					System.out.println("REQ_SEARCH_LESSON finished");
				} else if (type.equals(ClientReqType.REQ_SHOW_ALL_LESSON)) {
					System.out.println("serving REQ_SHOW_ALL_LESSON");
					System.out.println("grabbing.....");
					//�������пγ�
					Vector<String> sigCourseContent= new Vector<String>();
					Vector<String> allCourseContent = new Vector<String>();
					List<Course> allCourse = new LinkedList<Course>();
					CourseDaoImp courseDao= new CourseDaoImp();
					allCourse = courseDao.getAllCourse();
					Iterator<Course> iteAllCourse = allCourse.iterator();
					while(iteAllCourse.hasNext()) {
						sigCourseContent = iteAllCourse.next().getContent();
						for(int i=0;i<=4;i++) {
							allCourseContent.add(sigCourseContent.get(i));
						}
					}
					clientReq.setContent(allCourseContent);
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					System.out.println("REQ_SHOW_ALL_LESSON finished");
				} else if (type.equals(ClientReqType.REQ_STU_ALL_CHOOOSE)) {
					System.out.println("serving REQ_STU_ALL_CHOOOSE");
					System.out.println("grabbing......");
					//�������и�ѧ����ѡ�γ�
					Vector<String> sigCourseContent= new Vector<String>();
					Vector<String> allCourseContent = new Vector<String>();
					List<Course> allCourse = new LinkedList<Course>();
					User user = new User();
					user.setContent(clientReq.getContent());
					allCourse = user.getCourses();
					Iterator<Course> iteAllCourse = allCourse.iterator();
					while(iteAllCourse.hasNext()) {
						sigCourseContent = iteAllCourse.next().getContent();
						for(int i=0;i<=4;i++) {
							allCourseContent.add(sigCourseContent.get(i));
						}
					}
					clientReq.setContent(allCourseContent);
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					System.out.println("REQ_STU_ALL_CHOOOSE finished");
				} else if (type.equals(ClientReqType.REQ_STU_ALL_BORROW)) {
					System.out.println("serving REQ_STU_ALL_BORROW");
					System.out.println("grabbing......");
					//�������и�ѧ���ѽ����
					Vector<String> sigBookContent= new Vector<String>();
					Vector<String> allBookContent = new Vector<String>();
					List<Book> allBook = new LinkedList<Book>();
					User user = new User();
					user.setContent(clientReq.getContent());
					allBook = user.getBooks();
					Iterator<Book> iteAllBook = allBook.iterator();
					while(iteAllBook.hasNext()) {
						sigBookContent = iteAllBook.next().getContent();
						for(int i=0;i<=3;i++) {
							allBookContent.add(sigBookContent.get(i));
						}
					}
					clientReq.setContent(allBookContent);
					ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					System.out.println("REQ_STU_ALL_BORROW finished");
				} else if(type.equals(ClientReqType.REQ_SHOW_SIG_GOODS)) {
					System.out.println("serving REQ_SHOW_SIG_GOODS");
					System.out.println("grabbing......");
					Vector<String> sigGoodsInfor = new Vector<String>();
					String goodsName = clientReq.getContent().get(0);
					GoodsDaoImp goodsDao = new GoodsDaoImp();
					Goods goods = goodsDao.searchGoods(goodsName);
					if(goods!=null) {
						clientReq.setContent(goods.getContent());
						ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
					} else {
						clientReq.setContent(new Vector<String>());
						clientReq.setType("NULL");
						ObjectOutputStream oos = new ObjectOutputStream(clientReqSocket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
					}
					System.out.println("REQ_SHOW_SIG_GOODS finished");
				} else if(type.equals(ClientReqType.REQ_LOGOUT)) {
					System.out.println("serving REQ_LOGOUT");
					isClosed = true;
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
				isClosed = true;
			}
		}	
	}

	/**
	 * ���ڹر��̣߳������к����ķ����ȡ
	 */
	public void close() {
		isClosed = true;
	}

}

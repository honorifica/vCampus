package vss.client.view;
//借还书管理
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import vss.common.ClientReq;
import vss.common.User;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * 在需要借阅图书等操作时的时候进行调用
 * @author 姜云骞
 */
public class ClientLibraryBR extends JFrame implements ActionListener {
	JTable jt;
	
	int index = 0;
	private JTable table;
	private JTextField bookID;
	private Socket socket;
	private JButton searchRecord;
	private JScrollPane scrollPane;
	private JComboBox<String> range;
	private String userID;
	protected int originX;
	protected int originY;
	private JButton Maximized;
	private JButton Closed;
	private JButton Minimized;
	/**
	 * 用于初始化socket和管理框的初始显示
	 * @param socket1 通信时用的socket
	 */
	public ClientLibraryBR(Socket socket1)
	{
		
		getContentPane().setBackground(new Color(255, 255, 255));
		setUndecorated(true);//去边框
		socket = socket1;
		String [] seOp = {"全部","书名","书号"};
		
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		Maximized = new JButton("");
		Maximized.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login5_1.png")));
		Maximized.setBounds(744, 8, 18, 18);
		Maximized.setContentAreaFilled(false);
		Maximized.setBorder(null);
		Maximized.addActionListener(this);
		Maximized.setActionCommand("Maximized");
		
		Closed = new JButton("");
		Closed.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login6_1.png")));
		Closed.setContentAreaFilled(false);
		Closed.setBorder(null);
		Closed.setBounds(772, 8, 18, 18);
		Closed.addActionListener(this);
		Closed.setActionCommand("Closed");
		
		
		Minimized = new JButton("");
		Minimized.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login7_1.png")));
		Minimized.setContentAreaFilled(false);
		Minimized.setBorder(null);
		Minimized.setBounds(716, 8, 18, 18);
		Minimized.addActionListener(this);
		Minimized.setActionCommand("Minimized");
		
		JPanel border = new JPanel();
		border.setBounds(0, 0, 800, 30);
		getContentPane().add(border);
		border.setLayout(null);
		border.setOpaque(false);//将上边框设为透明
		JFrame that = this;
		
		border.addMouseListener((MouseListener) new MouseAdapter(){
		public void mousePressed(MouseEvent e){
				           // 记录鼠标按下时的点
				            originX = e.getX();
				            originY = e.getY();
				      }
				});
			    border.addMouseMotionListener((MouseMotionListener) new MouseMotionAdapter(){
			          @Override
			    public void mouseDragged(MouseEvent e){
			               // 拖拽时移动
			          Point point = that.getLocation();
			               // 偏移距离
			          int offsetX = e.getX() - originX;
			          int offsetY = e.getY() - originY;
			          that.setLocation(point.x + offsetX, point.y + offsetY);
			   }
		});
		border.add(Maximized);
		border.add(Closed);
		border.add(Minimized);
		
		range = new JComboBox<String>();
		range.setFont(new Font("宋体", Font.PLAIN, 20));
		range.setBackground(new Color(255, 255, 255));
		range.setModel(new DefaultComboBoxModel<String>(new String[] {"\u5168\u90E8", "\u4E2A\u4EBA"}));
		range.setToolTipText("");
		range.setBounds(27, 82, 80, 30);
		getContentPane().add(range);
		
		searchRecord = new JButton("");
		searchRecord.setIcon(new ImageIcon(ClientLibraryBR.class.getResource("/image/lib_button_show.png")));
		searchRecord.addActionListener(this);
		searchRecord.setActionCommand("searchRecord");
		
		searchRecord.setBounds(27, 146, 80, 30);
		getContentPane().add(searchRecord);
		
		ClientReq clientReq = new ClientReq();
		clientReq.setType("REQ_SHOW_ALL_BOOK");
		
		try {
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(clientReq);
		oos.flush();
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		clientReq = (ClientReq) ois.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		}
		Vector<String>	allBooksInfor = clientReq.getContent();
		int rowNumber = allBooksInfor.size()/4;
		int storingPlace = 0;
		String[][] allBooksTable = new String[rowNumber][4];
		for(int i=0;i<rowNumber;i++) {
			for(int j=0;j<4;j++)
				allBooksTable[i][j] = allBooksInfor.get(storingPlace++);
		}
		scrollPane = new JScrollPane();
		table = new JTable();//这两个必须得初始化
		scrollPane.setBounds(167, 85, 610, 0);
		getContentPane().add(scrollPane);//在初始化函数中加进去即可，后面根据操作需要的时候具体的具体设
		
		JButton borrowBook = new JButton("");
		borrowBook.setIcon(new ImageIcon(ClientLibraryBR.class.getResource("/image/lib_button_borrow.png")));
		borrowBook.addActionListener(this);
		borrowBook.setActionCommand("borrowBook");
		borrowBook.setBounds(27, 209, 80, 30);
		getContentPane().add(borrowBook);
		
		JButton returnBook = new JButton("");
		returnBook.setIcon(new ImageIcon(ClientLibraryBR.class.getResource("/image/lib_button_return.png")));
		returnBook.setBounds(27, 277, 80, 30);
		returnBook.addActionListener(this);
		returnBook.setActionCommand("returnBook");
		getContentPane().add(returnBook);
		
		JButton searchBook = new JButton("");
		searchBook.setIcon(new ImageIcon(ClientLibraryBR.class.getResource("/image/lib_button_search.png")));
		searchBook.setBounds(27, 335, 80, 30);
		searchBook.addActionListener(this);
		searchBook.setActionCommand("searchBook");
		getContentPane().add(searchBook);
		
		bookID = new JTextField();
		bookID.setOpaque(false);
		bookID.setBounds(20, 420, 100, 25);
		getContentPane().add(bookID);
		bookID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u4E66\u672CID");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(33, 381, 69, 31);
		getContentPane().add(lblNewLabel);
		
		JLabel backGround = new JLabel("");
		backGround.setIcon(new ImageIcon(ClientLibraryBR.class.getResource("/image/lib_bg.jpg")));
		backGround.setBounds(0, 0, 800, 500);
		getContentPane().add(backGround);
		
		this.setVisible(true);
	}
	@Override
	/**
	 * 事件监听服务程序
	 * @param e 监听到的事件
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="searchRecord") {
			if(range.getSelectedItem().equals("全部")) {
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_SHOW_ALL_BOOK");
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				Vector<String>	allBooksInfor = clientReq.getContent();
				int rowNumber = allBooksInfor.size()/4;
				String[][] allBooksTable = new String[rowNumber][4];
				int storingPlace = 0;
				for(int i=0;i<rowNumber;i++) {
					for(int j=0;j<4;j++)
						allBooksTable[i][j] = allBooksInfor.get(storingPlace++);
				}
				
				scrollPane.setViewportBorder(null);
				scrollPane.setBounds(167, 85, 610, 340 < rowNumber * 17 + 21 ? 340 : rowNumber * 17 + 21);
				scrollPane.setOpaque(false);//透明
				table.setModel(new DefaultTableModel(
					allBooksTable,
					new String[] {
						"\u4E66\u672CID", "\u4E66\u672C\u540D\u79F0", "\u4E66\u672C\u6570\u91CF", "\u4E66\u672C\u5269\u4F59\u91CF"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				scrollPane.setViewportView(table);
				
				table = new JTable();
				table.setModel(new DefaultTableModel(
					allBooksTable,
					new String[] {
						"\u4E66\u672CID", "\u4E66\u672C\u540D\u79F0", "\u4E66\u672C\u6570\u91CF", "\u4E66\u672C\u5269\u4F59\u91CF"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				scrollPane.setViewportView(table);
			} else if(range.getSelectedItem().equals("个人")) {
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_STU_ALL_BORROW");
				User user =new User();
				user.setId(userID);
				clientReq.setContent(user.getContent());
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				Vector<String>	allBooksInfor = clientReq.getContent();
				int rowNumber = allBooksInfor.size()/4;
				String[][] allBooksTable = new String[rowNumber][4];
				int storingPlace = 0;
				for(int i=0;i<rowNumber;i++) {
					for(int j=0;j<4;j++)
						allBooksTable[i][j] = allBooksInfor.get(storingPlace++);
				}
				scrollPane.setViewportBorder(null);
				scrollPane.setBounds(167, 85, 610, 340 < rowNumber * 17 + 21 ? 340 : rowNumber * 17 + 21);
				scrollPane.setOpaque(false);//透明
				table.setModel(new DefaultTableModel(
					allBooksTable,
					new String[] {
						"\u4E66\u672CID", "\u4E66\u672C\u540D\u79F0", "\u4E66\u672C\u6570\u91CF", "\u4E66\u672C\u5269\u4F59\u91CF"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				scrollPane.setViewportView(table);
				
				table = new JTable();
				table.setModel(new DefaultTableModel(
					allBooksTable,
					new String[] {
						"\u4E66\u672CID", "\u4E66\u672C\u540D\u79F0", "\u4E66\u672C\u6570\u91CF", "\u4E66\u672C\u5269\u4F59\u91CF"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				scrollPane.setViewportView(table);
			}
		} else if(e.getActionCommand().equals("borrowBook")) {
			if(bookID.getText().length()==0) JOptionPane.showMessageDialog(null,"请输入书本ID！","错误",JOptionPane.ERROR_MESSAGE);
			else {
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_BORROW_BOOK");
				Vector<String> content = new Vector<String>();
				content.add(bookID.getText());//书ID
				content.add(userID);//人ID
				clientReq.setContent(content);
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
				} catch(Exception e2) {
					e2.printStackTrace();
				}
				clientReq.setType("REQ_SHOW_ALL_BOOK");
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				Vector<String>	allBooksInfor = clientReq.getContent();
				int rowNumber = allBooksInfor.size()/4;
				String[][] allBooksTable = new String[rowNumber][4];
				int storingPlace = 0;
				for(int i=0;i<rowNumber;i++) {
					for(int j=0;j<4;j++)
						allBooksTable[i][j] = allBooksInfor.get(storingPlace++);
				}
				scrollPane.setViewportBorder(null);
				scrollPane.setBounds(167, 85, 610, 340 < rowNumber * 17 + 21 ? 340 : rowNumber * 17 + 21);
				scrollPane.setOpaque(false);//透明
				table.setModel(new DefaultTableModel(
					allBooksTable,
					new String[] {
						"\u4E66\u672CID", "\u4E66\u672C\u540D\u79F0", "\u4E66\u672C\u6570\u91CF", "\u4E66\u672C\u5269\u4F59\u91CF"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				scrollPane.setViewportView(table);
				
				table = new JTable();
				table.setModel(new DefaultTableModel(
					allBooksTable,
					new String[] {
						"\u4E66\u672CID", "\u4E66\u672C\u540D\u79F0", "\u4E66\u672C\u6570\u91CF", "\u4E66\u672C\u5269\u4F59\u91CF"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				scrollPane.setViewportView(table);
			}
		} else if(e.getActionCommand().equals("returnBook")) {
			if(bookID.getText().length()==0) JOptionPane.showMessageDialog(null,"请输入书本ID！","错误",JOptionPane.ERROR_MESSAGE);
			else {
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_RETURN_BOOK");
				Vector<String> content = new Vector<String>();
				content.add(bookID.getText());//书ID
				content.add(userID);//人ID
				clientReq.setContent(content);
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
				} catch(Exception e3) {
					e3.printStackTrace();
				}
				clientReq.setType("REQ_STU_ALL_BORROW");
				User user =new User();
				user.setId(userID);
				clientReq.setContent(user.getContent());
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				Vector<String>	allBooksInfor = clientReq.getContent();
				int rowNumber = allBooksInfor.size()/4;
				String[][] allBooksTable = new String[rowNumber][4];
				int storingPlace = 0;
				for(int i=0;i<rowNumber;i++) {
					for(int j=0;j<4;j++)
						allBooksTable[i][j] = allBooksInfor.get(storingPlace++);
				}
				scrollPane.setViewportBorder(null);
				scrollPane.setBounds(167, 85, 610, 340 < rowNumber * 17 + 21 ? 340 : rowNumber * 17 + 21);
				scrollPane.setOpaque(false);//透明
				table.setModel(new DefaultTableModel(
					allBooksTable,
					new String[] {
						"\u4E66\u672CID", "\u4E66\u672C\u540D\u79F0", "\u4E66\u672C\u6570\u91CF", "\u4E66\u672C\u5269\u4F59\u91CF"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				scrollPane.setViewportView(table);
				
				table = new JTable();
				table.setModel(new DefaultTableModel(
					allBooksTable,
					new String[] {
						"\u4E66\u672CID", "\u4E66\u672C\u540D\u79F0", "\u4E66\u672C\u6570\u91CF", "\u4E66\u672C\u5269\u4F59\u91CF"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				scrollPane.setViewportView(table);
			}
		} else if(e.getActionCommand().equals("searchBook")) {
			if(bookID.getText().length()==0) JOptionPane.showMessageDialog(null,"请输入书本ID！","错误",JOptionPane.ERROR_MESSAGE);
			else {
				ClientReq clientReq=new ClientReq();
				clientReq.setType("REQ_SHOW_SIG_BOOK");
				Vector<String> content = new Vector<String>();
				content.add(bookID.getText());
				clientReq.setContent(content);
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
					System.out.println(clientReq.getType());
				} catch(Exception e4) {
					e4.printStackTrace();
				}
				
				if(clientReq.getType().equals("NULL")) {
					JOptionPane.showMessageDialog(null, "此图书编号不存在");
				} else {
					Vector<String>	allBooksInfor = clientReq.getContent();
					int rowNumber = allBooksInfor.size()/4;
					String[][] allBooksTable = new String[rowNumber][4];
					int storingPlace = 0;
					for(int i=0;i<rowNumber;i++) {
						for(int j=0;j<4;j++)
							allBooksTable[i][j] = allBooksInfor.get(storingPlace++);
					}
					scrollPane.setViewportBorder(null);
					scrollPane.setBounds(167, 85, 610, 340 < rowNumber * 17 + 21 ? 340 : rowNumber * 17 + 21);
					scrollPane.setOpaque(false);//透明
					table.setModel(new DefaultTableModel(
						allBooksTable,
						new String[] {
							"\u4E66\u672CID", "\u4E66\u672C\u540D\u79F0", "\u4E66\u672C\u6570\u91CF", "\u4E66\u672C\u5269\u4F59\u91CF"
						}
					));
					table.getColumnModel().getColumn(0).setPreferredWidth(161);
					table.getColumnModel().getColumn(1).setPreferredWidth(161);
					table.getColumnModel().getColumn(2).setPreferredWidth(161);
					table.getColumnModel().getColumn(3).setPreferredWidth(161);
					scrollPane.setViewportView(table);
					
					table = new JTable();
					table.setModel(new DefaultTableModel(
						allBooksTable,
						new String[] {
							"\u4E66\u672CID", "\u4E66\u672C\u540D\u79F0", "\u4E66\u672C\u6570\u91CF", "\u4E66\u672C\u5269\u4F59\u91CF"
						}
					));
					table.getColumnModel().getColumn(0).setPreferredWidth(161);
					table.getColumnModel().getColumn(1).setPreferredWidth(161);
					table.getColumnModel().getColumn(2).setPreferredWidth(161);
					table.getColumnModel().getColumn(3).setPreferredWidth(161);
					scrollPane.setViewportView(table);
				}
			}
		} else if (e.getActionCommand().equals("Closed")) {
			this.dispose();
		} else if (e.getActionCommand().equals("Minimized")) {
			this.setExtendedState(ICONIFIED);
		} else if (e.getActionCommand().equals("Maximized")) {
			boolean isMax = false;
			if (!isMax) {
				this.setExtendedState(MAXIMIZED_BOTH);
				isMax = true;
			} else {
				this.setExtendedState(NORMAL);
				isMax = false;
			}
		}
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
}

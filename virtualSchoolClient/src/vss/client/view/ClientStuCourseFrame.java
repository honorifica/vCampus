package vss.client.view;
//学生选课界面
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
import vss.common.Course;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class ClientStuCourseFrame extends JFrame implements ActionListener{

JTable jt;
	
	int index = 0;
	private JTable table;
	private JTextField courseID;
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
	
	public ClientStuCourseFrame(String number, Socket socket1) throws IOException, ClassNotFoundException 
	{
		
		getContentPane().setBackground(new Color(255, 255, 255));
		setUndecorated(true);//去边框
		socket = socket1;
		userID=number;
		
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
		searchRecord.setIcon(new ImageIcon(ClientStuCourseFrame.class.getResource("/image/lib_button_show.png")));
		searchRecord.addActionListener(this);
		searchRecord.setActionCommand("searchRecord");
		
		searchRecord.setBounds(27, 146, 80, 30);
		getContentPane().add(searchRecord);
		
		ClientReq clientReq = new ClientReq();
		clientReq.setType("REQ_SHOW_ALL_LESSON");
		
		try {
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(clientReq);
		oos.flush();
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		clientReq = (ClientReq) ois.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		}
		Vector<String>	allCourseInfor = clientReq.getContent();
		int rowNumber = allCourseInfor.size()/5;
		int storingPlace = 0;
		String[][] allBooksTable = new String[rowNumber][5];
		for(int i=0;i<rowNumber;i++) {
			for(int j=0;j<5;j++)
				allBooksTable[i][j] = allCourseInfor.get(storingPlace++);
		}
		scrollPane = new JScrollPane();
		table = new JTable();//这两个必须得初始化
		scrollPane.setBounds(167, 85, 610, 0);
		getContentPane().add(scrollPane);//在初始化函数中加进去即可，后面根据操作需要的时候具体的具体设
		
		JButton chooseCourse = new JButton("");
		chooseCourse.setIcon(new ImageIcon(ClientStuCourseFrame.class.getResource("/image/course_button_choose.png")));
		chooseCourse.addActionListener(this);
		chooseCourse.setActionCommand("chooseCourse");
		chooseCourse.setBounds(27, 209, 80, 30);
		getContentPane().add(chooseCourse);
		
		JButton cancelCourse = new JButton("");
		cancelCourse.setIcon(new ImageIcon(ClientStuCourseFrame.class.getResource("/image/course_button_cancel.png")));
		cancelCourse.setBounds(27, 277, 80, 30);
		cancelCourse.addActionListener(this);
		cancelCourse.setActionCommand("cancelCourse");
		getContentPane().add(cancelCourse);
		
		JButton searchCourse = new JButton("");
		searchCourse.setIcon(new ImageIcon(ClientStuCourseFrame.class.getResource("/image/lib_button_search.png")));
		searchCourse.setBounds(27, 335, 80, 30);
		searchCourse.addActionListener(this);
		searchCourse.setActionCommand("searchCourse");
		getContentPane().add(searchCourse);
		
		courseID = new JTextField();
		courseID.setBounds(27, 420, 80, 25);
		getContentPane().add(courseID);
		courseID.setColumns(10);
		
		JLabel scfNewLabel = new JLabel("课程ID");
		scfNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		scfNewLabel.setForeground(new Color(0, 0, 0));
		scfNewLabel.setBounds(33, 381, 69, 31);
		getContentPane().add(scfNewLabel);
		
		JLabel backGround = new JLabel("");
		backGround.setIcon(new ImageIcon(ClientStuCourseFrame.class.getResource("/image/cou_bg.png")));
		backGround.setBounds(0, 0, 800, 500);
		getContentPane().add(backGround);
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="searchRecord") {
			if(range.getSelectedItem().equals("全部")) {
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_SHOW_ALL_LESSON");
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				Vector<String>	allCourseInfor = clientReq.getContent();
				int rowNumber = allCourseInfor.size()/5;
				String[][] allCourseTable = new String[rowNumber][5];
				int storingPlace = 0;
				for(int i=0;i<rowNumber;i++) {
					for(int j=0;j<5;j++)
						allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
				}
				
				scrollPane.setViewportBorder(null);
				scrollPane.setBounds(167, 85, 610, 340 < rowNumber * 17 + 21 ? 340 : rowNumber * 17 + 21);
				scrollPane.setOpaque(false);//透明
				table.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"课程ID","课程名称","课程课时","最大容量","选课人数"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				table.getColumnModel().getColumn(4).setPreferredWidth(161);
				scrollPane.setViewportView(table);
				
				table = new JTable();
				table.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"课程ID","课程名称","课程课时","最大容量","选课人数"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				table.getColumnModel().getColumn(4).setPreferredWidth(161);
				scrollPane.setViewportView(table);
			} else if(range.getSelectedItem().equals("个人")) {
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_STU_ALL_CHOOOSE");
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
				Vector<String>	allCourseInfor = clientReq.getContent();
				int rowNumber = allCourseInfor.size()/5;
				String[][] allCourseTable = new String[rowNumber][5];
				int storingPlace = 0;
				for(int i=0;i<rowNumber;i++) {
					for(int j=0;j<5;j++)
						allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
				}
				scrollPane.setViewportBorder(null);
				scrollPane.setBounds(167, 85, 610, 340 < rowNumber * 17 + 21 ? 340 : rowNumber * 17 + 21);
				scrollPane.setOpaque(false);//透明
				table.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"课程ID","课程名称","课程课时","最大容量","选课人数"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				table.getColumnModel().getColumn(4).setPreferredWidth(161);
				scrollPane.setViewportView(table);
				
				table = new JTable();
				table.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"课程ID","课程名称","课程课时","最大容量","选课人数"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				table.getColumnModel().getColumn(4).setPreferredWidth(161);
				scrollPane.setViewportView(table);
			}
		} else if(e.getActionCommand().equals("chooseCourse")) {
			if(courseID.getText().length()==0) JOptionPane.showMessageDialog(null,"请输入课程ID！","错误",JOptionPane.ERROR_MESSAGE);
			else {
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_STU_ADD_LESSON");
				Vector<String> content = new Vector<String>();
				content.add(courseID.getText());//课ID
				content.add(userID);//人ID
				clientReq.setContent(content);
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
				} catch(Exception e2) {
					e2.printStackTrace();
				}
				clientReq.setType("REQ_SHOW_ALL_LESSON");
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				Vector<String>	allCourseInfor = clientReq.getContent();
				int rowNumber = allCourseInfor.size()/5;
				String[][] allCourseTable = new String[rowNumber][5];
				int storingPlace = 0;
				for(int i=0;i<rowNumber;i++) {
					for(int j=0;j<5;j++)
						allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
				}
				scrollPane.setViewportBorder(null);
				scrollPane.setBounds(167, 85, 610, 340 < rowNumber * 17 + 21 ? 340 : rowNumber * 17 + 21);
				scrollPane.setOpaque(false);//透明
				table.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"课程ID","课程名称","课程课时","最大容量","选课人数"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				table.getColumnModel().getColumn(4).setPreferredWidth(161);
				scrollPane.setViewportView(table);
				
				table = new JTable();
				table.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"课程ID","课程名称","课程课时","最大容量","选课人数"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				table.getColumnModel().getColumn(4).setPreferredWidth(161);
				scrollPane.setViewportView(table);
			}
		} else if(e.getActionCommand().equals("cancelCourse")) {
			if(courseID.getText().length()==0) JOptionPane.showMessageDialog(null,"请输入课程ID！","错误",JOptionPane.ERROR_MESSAGE);
			else {
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_STU_REMOVE_LESSON");
				Vector<String> content = new Vector<String>();
				content.add(courseID.getText());//课ID
				content.add(userID);//人ID
				clientReq.setContent(content);
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
				} catch(Exception e3) {
					e3.printStackTrace();
				}
				clientReq.setType("REQ_STU_ALL_CHOOOSE");
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
				Vector<String>	allCourseInfor = clientReq.getContent();
				int rowNumber = allCourseInfor.size()/5;
				String[][] allCourseTable = new String[rowNumber][5];
				int storingPlace = 0;
				for(int i=0;i<rowNumber;i++) {
					for(int j=0;j<5;j++)
						allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
				}
				scrollPane.setViewportBorder(null);
				scrollPane.setBounds(167, 85, 610, 340 < rowNumber * 17 + 21 ? 340 : rowNumber * 17 + 21);
				scrollPane.setOpaque(false);//透明
				table.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"课程ID","课程名称","课程课时","最大容量","选课人数"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				table.getColumnModel().getColumn(4).setPreferredWidth(161);
				scrollPane.setViewportView(table);
				
				table = new JTable();
				table.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"课程ID","课程名称","课程课时","最大容量","选课人数"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				table.getColumnModel().getColumn(4).setPreferredWidth(161);
				scrollPane.setViewportView(table);
			}
		} else if(e.getActionCommand().equals("searchCourse")) {
			if(courseID.getText().length()==0) JOptionPane.showMessageDialog(null,"请输入课程ID！","错误",JOptionPane.ERROR_MESSAGE);
			else {
				ClientReq clientReq=new ClientReq();
				clientReq.setType("REQ_SEARCH_LESSON");
				Vector<String> content = new Vector<String>();
				content.add(courseID.getText());
				clientReq.setContent(content);
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				} catch(Exception e4) {
					e4.printStackTrace();
				}
				Vector<String>	allCourseInfor = clientReq.getContent();
				int rowNumber = allCourseInfor.size()/5;
				String[][] allCourseTable = new String[rowNumber][5];
				int storingPlace = 0;
				for(int i=0;i<rowNumber;i++) {
					for(int j=0;j<5;j++)
						allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
				}
				scrollPane.setViewportBorder(null);
				scrollPane.setBounds(167, 85, 610, 340 < rowNumber * 17 + 21 ? 340 : rowNumber * 17 + 21);
				scrollPane.setOpaque(false);//透明
				table.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"课程ID","课程名称","课程课时","最大容量","选课人数"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				table.getColumnModel().getColumn(4).setPreferredWidth(161);
				scrollPane.setViewportView(table);
				
				table = new JTable();
				table.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"课程ID","课程名称","课程课时","最大容量","选课人数"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(161);
				table.getColumnModel().getColumn(2).setPreferredWidth(161);
				table.getColumnModel().getColumn(3).setPreferredWidth(161);
				table.getColumnModel().getColumn(4).setPreferredWidth(161);
				scrollPane.setViewportView(table);
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
}
	

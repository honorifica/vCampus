package vss.client.view;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import vss.common.ClientReq;
import vss.common.Course;
/**
 * 
* 
*   ��ʦ���пγ̹������Ľ���
* @author WGH
*
 */
public class ClientCourseFrame extends JFrame implements ActionListener{
	JTable jt;
	JComboBox Range;
	JTextField courseID;
	JButton Search,Add,Delete;
	Socket socket;
	JScrollPane scrollPane;
	protected int originX;
	protected int originY;
	private JButton Maximized;
	private JButton Closed;
	private JButton Minimized;
	int index = 0;
	private JTable courseInforList;
	private String number;
	private JLabel couNewLabel_1;
	private JTextField courseName;
	
	/**
	 * 
	 * ClientCourseFrame
	 * ClientCourseFrame���캯��
	 * @param ID ��ʦ��ID��
	 * @param socket1 ����ͨ�ŵ�socket
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 * @author WGH
	 *  2021-07-28 02:47:57
	 */
	public ClientCourseFrame(String ID,Socket socket1) throws ClassNotFoundException, SQLException,IOException
	{
		socket = socket1;
		number=ID;
		String[] seOp={"ȫ��","�γ���","�γ̺�"};
		setUndecorated(true);//ȥ�߿�
		Maximized = new JButton("");
		Maximized.setIcon(new ImageIcon(ClientCourseFrame.class.getResource("/image/login5_1.png")));
		Maximized.setBounds(744, 8, 18, 18);
		Maximized.setContentAreaFilled(false);
		Maximized.setBorder(null);
		Maximized.addActionListener(this);
		Maximized.setActionCommand("Maximized");
		
		Closed = new JButton("");
		Closed.setIcon(new ImageIcon(ClientCourseFrame.class.getResource("/image/login6_1.png")));
		Closed.setContentAreaFilled(false);
		Closed.setBorder(null);
		Closed.setBounds(772, 8, 18, 18);
		Closed.addActionListener(this);
		Closed.setActionCommand("Closed");
		
		
		Minimized = new JButton("");
		Minimized.setIcon(new ImageIcon(ClientCourseFrame.class.getResource("/image/login7_1.png")));
		Minimized.setContentAreaFilled(false);
		Minimized.setBorder(null);
		Minimized.setBounds(716, 8, 18, 18);
		Minimized.addActionListener(this);
		
		courseName = new JTextField(20);
		courseName.setBounds(27, 353, 80, 25);
		getContentPane().add(courseName);
		
		JLabel couNewLabel_2 = new JLabel("\u8BFE\u7A0B\u540D\u79F0");
		couNewLabel_2.setFont(new Font("����", Font.PLAIN, 20));
		couNewLabel_2.setBounds(27, 300, 80, 37);
		getContentPane().add(couNewLabel_2);
		Minimized.setActionCommand("Minimized");
		
		JPanel border = new JPanel();
		border.setBounds(0, 0, 800, 30);
		getContentPane().add(border);
		border.setLayout(null);
		border.setOpaque(false);//���ϱ߿���Ϊ͸��
		JFrame that = this;
		
		border.addMouseListener((MouseListener) new MouseAdapter(){
		public void mousePressed(MouseEvent e){
				           // ��¼��갴��ʱ�ĵ�
				            originX = e.getX();
				            originY = e.getY();
				      }
				});
			    border.addMouseMotionListener((MouseMotionListener) new MouseMotionAdapter(){
			          @Override
			    public void mouseDragged(MouseEvent e){
			               // ��קʱ�ƶ�
			          Point point = that.getLocation();
			               // ƫ�ƾ���
			          int offsetX = e.getX() - originX;
			          int offsetY = e.getY() - originY;
			          that.setLocation(point.x + offsetX, point.y + offsetY);
			   }
		});
		border.add(Maximized);
		border.add(Closed);
		border.add(Minimized);
		
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(167, 85, 0, 0);
		getContentPane().add(scrollPane);
		courseInforList = new JTable();
		courseInforList.setModel(new DefaultTableModel(
			new String[][] {{}},
			new String[] {}
		));

		Range = new JComboBox(seOp);
		Range.setBackground(new Color(255, 255, 255));
		Range.setFont(new Font("����", Font.PLAIN, 20));
		Range.setBounds(27, 40, 80, 30);
		getContentPane().add(Range);
		Range.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "\u5355\u8282"}));
		Search = new JButton("");
		Search.setIcon(new ImageIcon(ClientCourseFrame.class.getResource("/image/lib_button_show.png")));
		Search.setFont(new Font("����", Font.PLAIN, 20));
		Search.setBounds(27, 190, 80, 30);
		getContentPane().add(Search);
		Search.addActionListener(this);
		Search.setActionCommand("Search");
		courseID = new JTextField(20);
		courseID.setBounds(27, 140, 80, 25);
		getContentPane().add(courseID);
		
		JLabel couNewLabel = new JLabel("\u8BFE\u7A0BID");
		couNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		couNewLabel.setBounds(37, 85, 65, 37);
		getContentPane().add(couNewLabel);
		Add =new JButton("");
		Add.setIcon(new ImageIcon(ClientCourseFrame.class.getResource("/image/course_button_addcourse.png")));
		Add.setFont(new Font("����", Font.PLAIN, 20));
		Add.setBounds(27, 250, 80, 30);
		getContentPane().add(Add);
		Add.addActionListener(this);
		Add.setActionCommand("Add");
		Delete =new JButton("");
		Delete.setIcon(new ImageIcon(ClientCourseFrame.class.getResource("/image/course_button_deletecourse.png")));
		Delete.setFont(new Font("����", Font.PLAIN, 20));
		Delete.setBounds(27, 410, 80, 30);
		getContentPane().add(Delete);
		Delete.addActionListener(this);
		Delete.setActionCommand("Delete");
		
		couNewLabel_1 = new JLabel("");
		couNewLabel_1.setIcon(new ImageIcon(ClientCourseFrame.class.getResource("/image/cou_bg_manager.png")));
		couNewLabel_1.setBounds(0, 0, 800, 500);
		getContentPane().add(couNewLabel_1);
		
        this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	/**
	 * �����������
	 * @param e ���������¼�
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Search")) {
			if(Range.getSelectedItem().equals("ȫ��")) {
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
				
				Vector<String>allCourseInfor = clientReq.getContent();
				int rowAmount = allCourseInfor.size()/5;
				String[][] allCourseTable = new String[rowAmount][5];
				int stroingPlace = 0;
				for(int i=0;i<rowAmount;i++) {
					for(int j=0;j<5;j++) {
						allCourseTable[i][j] = allCourseInfor.get(stroingPlace++);
					}
				}
				scrollPane.setBounds(167, 85, 610, 340 < rowAmount * 17 + 21 ? 340 : rowAmount * 17 + 21 );
				courseInforList = new JTable();
				courseInforList.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"�γ�ID","�γ�����","�γ̿�ʱ","�������","ѡ������"
					}
				));
				scrollPane.setViewportView(courseInforList);
			} else if(Range.getSelectedItem().equals("����")) {
				if(courseID.getText().length()==0) JOptionPane.showMessageDialog(null,"������γ�ID��","����",JOptionPane.ERROR_MESSAGE);
				else {
					ClientReq clientReq = new ClientReq();
					Vector<String> content = new Vector<String>();
					content.add(courseID.getText());
					clientReq.setContent(content);
					clientReq.setType("REQ_SEARCH_LESSON");
					try {
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
						ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
						clientReq = (ClientReq) ois.readObject();
					} catch(Exception e1) {
						e1.printStackTrace();
					}
					
					Vector<String>allCourseInfor = clientReq.getContent();
					int rowAmount = allCourseInfor.size()/5;
					String[][] allCourseTable = new String[rowAmount][5];
					int stroingPlace = 0;
					for(int i=0;i<rowAmount;i++) {
						for(int j=0;j<5;j++) {
							allCourseTable[i][j] = allCourseInfor.get(stroingPlace++);
						}
					}
					scrollPane.setBounds(167, 85, 610, 340 < rowAmount * 17 + 21 ? 340 : rowAmount * 17 + 21 );
					courseInforList = new JTable();
					courseInforList.setModel(new DefaultTableModel(
						allCourseTable,
						new String[] {
								"�γ�ID","�γ�����","�γ̿�ʱ","�������","ѡ������"
						}
					));
					scrollPane.setViewportView(courseInforList);
				}
			}
		} else if(e.getActionCommand().equals("Delete")) {
			if(courseName.getText().length()==0) JOptionPane.showMessageDialog(null,"������γ�ID��","����",JOptionPane.ERROR_MESSAGE);
			else {
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_REMOVE_LESSON");
				Vector<String> content = new Vector<String>();
				content.setSize(5);
				content.set(1,courseName.getText());
				clientReq.setContent(content);
				try {
					ObjectOutputStream oos =  new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
				} catch(Exception e3) {
					e3.printStackTrace();
				}
				clientReq.setType("REQ_SHOW_ALL_LESSON");
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				} catch(Exception e4) {
					e4.printStackTrace();
				}
				
				Vector<String>allCourseInfor = clientReq.getContent();
				int rowAmount = allCourseInfor.size()/5;
				String[][] allCourseTable = new String[rowAmount][5];
				int stroingPlace = 0;
				for(int i=0;i<rowAmount;i++) {
					for(int j=0;j<5;j++) {
						allCourseTable[i][j] = allCourseInfor.get(stroingPlace++);
					}
				}
				scrollPane.setBounds(167, 85, 610, 340 < rowAmount * 17 + 21 ? 340 : rowAmount * 17 + 21 );
				courseInforList = new JTable();
				courseInforList.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"�γ�ID","�γ�����","�γ̿�ʱ","�������","ѡ������"
					}
				));
				scrollPane.setViewportView(courseInforList);
			}
		} else if(e.getActionCommand().equals("Add")) {
			//this.setVisible(false);
			scrollPane.setBounds(167, 85, 0, 0);
			//this.setVisible(true);
			CourseInfor cif = new CourseInfor(number,socket);
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

package vss.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
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
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import vss.common.ClientReq;
/**
 * 
*  ClientStuinfo 
*  (学生学籍信息显示界面) 
* @author WGH
*  2021年7月28日 下午3:06:57 
*
 */
public class ClientStuinfo extends JFrame implements ActionListener {

	JPanel jp2;
	JTable jtb_Stuinfolist;
	JScrollPane jsp;
	
	private JTextField goodsName;
	private JTextField goodsAmount;
	private String userID;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField stuID;
	private JTextField stuName;
	private JTextField stuAge;
	private JTextField stuMajor;
	private Socket socket;
	protected int originX;
	protected int originY;
	private JButton Maximized;
	private JButton Closed;
	private JButton Minimized;
	private JLabel lblNewLabel_4;
	private String UserID;
	private JTextField stuGrade;
	private JTextField stuPwd;
	private JTextField stuMoney;
	String role;
	String sex;
	private JTextField stuRole;
	private JTextField stuSex;
	
	public ClientStuinfo(Socket socket, String number) throws IOException, ClassNotFoundException{
		setUndecorated(true);//去边框
	
		
		userID = number;
		this.socket = socket;
		
        
        ClientReq clientReq = new ClientReq();
        Vector<String>content = new Vector<String>();
        content.add(userID);
        
        clientReq.setContent(content);
		clientReq.setType("REQ_SHOW_SIG_INFOR");
		//传数据
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(clientReq);
		oos.flush();
		//接受数据
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		clientReq = (ClientReq) ois.readObject();
		Vector<String> stu_info = clientReq.getContent();
		
		
		Maximized = new JButton("");
		Maximized.setBounds(295, 8, 18, 18);
		Maximized.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login5_1.png")));
		Maximized.setContentAreaFilled(false);
		Maximized.setBorder(null);
		Maximized.addActionListener(this);
		Maximized.setActionCommand("Maximized");
		
		Closed = new JButton("");
		Closed.setBounds(323, 8, 18, 18);
		Closed.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login6_1.png")));
		Closed.setContentAreaFilled(false);
		Closed.setBorder(null);
		Closed.addActionListener(this);
		Closed.setActionCommand("Closed");
		
		
		Minimized = new JButton("");
		Minimized.setBounds(267, 8, 18, 18);
		Minimized.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login7_1.png")));
		Minimized.setContentAreaFilled(false);
		Minimized.setBorder(null);
		Minimized.addActionListener(this);
		Minimized.setActionCommand("Minimized");
		
		JPanel border = new JPanel();
		border.setBounds(0, 0, 350, 30);
		getContentPane().add(border);
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
		border.setLayout(null);
		border.add(Maximized);
		border.add(Closed);
		border.add(Minimized);
		
		setBounds(100, 100, 350, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		stuSex = new JTextField();
		stuSex.setEditable(false);
		stuSex.setText(stu_info.get(3));
		stuSex.setOpaque(false);
		stuSex.setColumns(10);
		stuSex.setBounds(35, 344, 60, 25);
		contentPanel.add(stuSex);
		
		stuRole = new JTextField();
		stuRole.setText(stu_info.get(7));
		stuRole.setEditable(false);
		stuRole.setOpaque(false);
		stuRole.setColumns(10);
		stuRole.setBounds(190, 344, 100, 25);
		contentPanel.add(stuRole);
		
		stuMoney = new JTextField();
		stuMoney.setText(stu_info.get(8));
		stuMoney.setEditable(false);
		stuMoney.setOpaque(false);
		stuMoney.setColumns(10);
		stuMoney.setBounds(190, 270, 100, 25);
		contentPanel.add(stuMoney);
		
		stuPwd = new JTextField();
		stuPwd.setText(stu_info.get(6));
		stuPwd.setEditable(false);
		stuPwd.setOpaque(false);
		stuPwd.setColumns(30);
		stuPwd.setBounds(190, 190, 100, 25);
		contentPanel.add(stuPwd);
		
		stuGrade = new JTextField();
		stuGrade.setText(stu_info.get(4));
		stuGrade.setEditable(false);
		stuGrade.setOpaque(false);
		stuGrade.setColumns(10);
		stuGrade.setBounds(35, 422, 100, 25);
		contentPanel.add(stuGrade);
		
		JLabel stuNewLabel_8 = new JLabel("\u8EAB\u4EFD");
		stuNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 20));
		stuNewLabel_8.setBounds(190, 310, 86, 24);
		contentPanel.add(stuNewLabel_8);
		
		JLabel stuNewLabel_7 = new JLabel("\u6CE8\u518C\u91D1\u989D");
		stuNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 20));
		stuNewLabel_7.setBounds(190, 230, 86, 24);
		contentPanel.add(stuNewLabel_7);
		
		JLabel stuNewLabel_6 = new JLabel("\u5BC6\u7801");
		stuNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 20));
		stuNewLabel_6.setBounds(190, 150, 86, 24);
		contentPanel.add(stuNewLabel_6);
		
		JLabel stuNewLabel_5 = new JLabel("\u5E74\u7EA7");
		stuNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));
		stuNewLabel_5.setBounds(35, 388, 86, 24);
		contentPanel.add(stuNewLabel_5);
		
		JLabel stuNewLabel_4 = new JLabel("\u4E13\u4E1A");
		stuNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		stuNewLabel_4.setBounds(190, 70, 86, 24);
		contentPanel.add(stuNewLabel_4);
		
		stuID = new JTextField();
		stuID.setText(stu_info.get(0));
		stuID.setEditable(false);
		stuID.setOpaque(false);
		stuID.setBounds(35, 110, 100, 25);
		contentPanel.add(stuID);
		stuID.setColumns(10);
		
		stuName = new JTextField();
		stuName.setText(stu_info.get(1));
		stuName.setEditable(false);
		stuName.setOpaque(false);
		stuName.setBounds(35, 190, 100, 25);
		contentPanel.add(stuName);
		stuName.setColumns(10);
		
		stuAge = new JTextField();
		stuAge.setText(stu_info.get(2));
		stuAge.setEditable(false);
		stuAge.setOpaque(false);
		stuAge.setBounds(35, 270, 100, 25);
		contentPanel.add(stuAge);
		stuAge.setColumns(10);
		
		stuMajor = new JTextField();
		stuMajor.setText(stu_info.get(5));
		stuMajor.setEditable(false);
		stuMajor.setOpaque(false);
		stuMajor.setBounds(190, 110, 100, 25);
		contentPanel.add(stuMajor);
		stuMajor.setColumns(10);
		
		JLabel stuNewLabel = new JLabel("ID");
		stuNewLabel.setBounds(35, 70, 72, 18);
		stuNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPanel.add(stuNewLabel);
		
		JLabel stuNewLabel_1 = new JLabel("\u59D3\u540D");
		stuNewLabel_1.setBounds(35, 150, 86, 24);
		stuNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPanel.add(stuNewLabel_1);
		
		JLabel stuNewLabel_2 = new JLabel("\u5E74\u9F84");
		stuNewLabel_2.setBounds(35, 230, 86, 24);
		stuNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPanel.add(stuNewLabel_2);
		
		JLabel stuNewLabel_3 = new JLabel("\u6027\u522B");
		stuNewLabel_3.setBounds(35, 310, 50, 24);
		stuNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPanel.add(stuNewLabel_3);
		{
			JButton Confirm = new JButton("\u786E\u5B9A");
			Confirm.setForeground(Color.white);
			Confirm.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			Confirm.setIcon(null);
			Confirm.setBackground(new Color(84, 131, 53));
			Confirm.setBorder(null);
			Confirm.setBounds(208, 440, 100, 36);
			contentPanel.add(Confirm);
			Confirm.addActionListener(this);
			Confirm.setActionCommand("Confirm");
		}
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(BookInfor.class.getResource("/image/stu_studentInfor_bg.png")));
		lblNewLabel_4.setBounds(0, 0, 350, 500);
		contentPanel.add(lblNewLabel_4);
		
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Confirm")) {
			this.dispose();
		}
		if (e.getActionCommand().equals("Closed")) {
			this.dispose();
		} 
		else if (e.getActionCommand().equals("Minimized")) {
			this.setExtendedState(ICONIFIED);
		} 
		else if (e.getActionCommand().equals("Maximized")) 
		{
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}

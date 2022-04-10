package vss.client.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.net.Socket;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vss.client.bz.ClientConServer;
import java.awt.Color;

//需要包含ID、Age、Grade、Major、Money、Name、Pwd、Role、Sex
/**
 * 
*  ClientRegisterFrame 
*   (注册信息时弹出的界面) 
* @author WGH
*  2021年7月28日 上午12:25:57 
*
 */
public class ClientRegisterFrame extends JFrame implements ActionListener{
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
	private JComboBox stuSex;
	private JComboBox stuRole;
	String role;
	String sex;
	/**
	 * 
	 *  ClientRegisterFrame
	 *   ClientRegisterFrame构造函数
	 * @author WGH
	 *  2021-07-28 12:26:23
	 */
	public ClientRegisterFrame(){
		
		setUndecorated(true);//去边框
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
		
		stuMoney = new JTextField();
		stuMoney.setOpaque(false);
		stuMoney.setColumns(10);
		stuMoney.setBounds(190, 270, 100, 25);
		contentPanel.add(stuMoney);
		
		stuPwd = new JTextField();
		stuPwd.setOpaque(false);
		stuPwd.setColumns(30);
		stuPwd.setBounds(190, 190, 100, 25);
		contentPanel.add(stuPwd);
		
		stuGrade = new JTextField();
		stuGrade.setOpaque(false);
		stuGrade.setColumns(10);
		stuGrade.setBounds(35, 390, 100, 25);
		contentPanel.add(stuGrade);
		
		stuRole = new JComboBox();
		stuRole.setForeground(Color.BLACK);
		stuRole.setModel(new DefaultComboBoxModel(new String[] {"\u5B66\u751F", "\u8001\u5E08", "\u5546\u5E97\u7BA1\u7406\u5458", "\u56FE\u4E66\u7BA1\u7406\u5458"}));
		stuRole.setBounds(190, 350, 120, 25);
		contentPanel.add(stuRole);
		
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
		stuNewLabel_5.setBounds(35, 350, 86, 24);
		contentPanel.add(stuNewLabel_5);
		
		stuSex = new JComboBox();
		stuSex.setBorder(null);
		//stuSex.setForeground(Color.white);
		//stuSex.setBackground(new Color(83,130,52));
		stuSex.setOpaque(false);
		stuSex.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		stuSex.setBounds(95, 310, 40, 25);
		contentPanel.add(stuSex);
		
		JLabel stuNewLabel_4 = new JLabel("\u4E13\u4E1A");
		stuNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		stuNewLabel_4.setBounds(190, 70, 86, 24);
		contentPanel.add(stuNewLabel_4);
		
		stuID = new JTextField();
		stuID.setOpaque(false);
		stuID.setBounds(35, 110, 100, 25);
		contentPanel.add(stuID);
		stuID.setColumns(10);
		
		stuName = new JTextField();
		stuName.setOpaque(false);
		stuName.setBounds(35, 190, 100, 25);
		contentPanel.add(stuName);
		stuName.setColumns(10);
		
		stuAge = new JTextField();
		stuAge.setOpaque(false);
		stuAge.setBounds(35, 270, 100, 25);
		contentPanel.add(stuAge);
		stuAge.setColumns(10);
		
		stuMajor = new JTextField();
		stuMajor.setOpaque(false);
		stuMajor.setBounds(190, 110, 100, 25);
		contentPanel.add(stuMajor);
		stuMajor.setColumns(10);
		
		JLabel stuNewLabel = new JLabel("ID");
		stuNewLabel.setBounds(35, 70, 72, 18);
		stuNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPanel.add(stuNewLabel);
		
		JLabel stuNewLabel_1 = new JLabel("\u540D\u79F0");
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
			JButton register = new JButton("\u6CE8\u518C");
			register.setForeground(Color.white);
			register.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			register.setIcon(null);
			register.setBackground(new Color(84, 131, 53));
			register.setBorder(null);
			register.setBounds(230, 460, 80, 30);
			contentPanel.add(register);
			register.addActionListener(this);
			register.setActionCommand("Confirm");
		}
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(BookInfor.class.getResource("/image/stu_studentInfor_bg.png")));
		lblNewLabel_4.setBounds(0, 0, 350, 500);
		contentPanel.add(lblNewLabel_4);
		
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}



	@Override
	/**
	 * 事务监听函数
	 * @param e 用来监听事件
	 */
	public void actionPerformed(ActionEvent e) {
		//  Auto-generated method stub
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
		if (stuRole.getSelectedItem() =="学生")
		   {role="2";}
		if (stuRole.getSelectedItem() =="老师")
		   {role="3";}
		if (stuRole.getSelectedItem() =="商店管理员")
		   {role="4";}
		if (stuRole.getSelectedItem() =="图书管理员")
		   {role="5";}
		
		if (stuSex.getSelectedItem()=="男")
		{
			sex = "男";
		}
		else
		{
			sex = "女";
		}
		
		//传送数据
		/**
		 * 将注册界面得到的信息通过socket传到数据库中
		 */
		ClientConServer ccs = new ClientConServer();
		ccs.sendRegister(stuID.getText(),stuAge.getText(),stuGrade.getText(),stuMajor.getText(),stuMoney.getText(),stuName.getText(),stuPwd.getText(),role,sex);
		
		if(e.getActionCommand().equals("register"))
		{
			//System.out.print(ccs.sign);
			//返回的权限
			if(ccs.sign==0)
			{
				JOptionPane.showMessageDialog(null,"注册失败！","错误",JOptionPane.ERROR_MESSAGE);
			}
			else if(ccs.sign==1)
			{
				JOptionPane.showMessageDialog(null,"您已经成功注册，请不要重复注册！","错误",JOptionPane.ERROR_MESSAGE);
			}
			else if(ccs.sign==2)
			{
				JOptionPane.showMessageDialog(null,"同学，恭喜注册成功！","提示",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
			else if(ccs.sign==3)
			{
				JOptionPane.showMessageDialog(null,"老师，恭喜注册成功！","提示",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
			else if(ccs.sign==4)
			{
				JOptionPane.showMessageDialog(null,"店主，恭喜注册成功！","提示",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
			else if(ccs.sign==5)
			{
				JOptionPane.showMessageDialog(null,"图书管理员，恭喜注册成功！","提示",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
		}
	}

}

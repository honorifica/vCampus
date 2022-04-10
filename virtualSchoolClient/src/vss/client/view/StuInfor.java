package vss.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vss.common.User;
import vss.common.ClientReq;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
/**
 * 
*  StuInfor 
*  (老师) 
* @author WGH
*  2021年7月28日 下午2:39:26 
*
 */
public class StuInfor extends JFrame implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField studentID;
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
	
	/**
	 * Create the dialog.
	 */
	public  StuInfor(String ID,Socket intiSocket){
		this.UserID=ID;
		this.socket=intiSocket;
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
		stuRole.setModel(new DefaultComboBoxModel(new String[] {"学生", "商店管理员", "图书管理员"}));
		stuRole.setBounds(190, 350, 120, 25);
		contentPanel.add(stuRole);
		
		JLabel stuNewLabel_8 = new JLabel("\u8EAB\u4EFD");
		stuNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 20));
		stuNewLabel_8.setBounds(190, 310, 86, 24);
		contentPanel.add(stuNewLabel_8);
		
		JLabel stuNewLabel_7 = new JLabel("\u4F59\u989D");
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
		stuSex.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		stuSex.setBounds(95, 310, 40, 25);
		contentPanel.add(stuSex);
		
		JLabel stuNewLabel_4 = new JLabel("\u4E13\u4E1A");
		stuNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		stuNewLabel_4.setBounds(190, 70, 86, 24);
		contentPanel.add(stuNewLabel_4);
		
		studentID = new JTextField();
		studentID.setOpaque(false);
		studentID.setBounds(35, 110, 100, 25);
		contentPanel.add(studentID);
		studentID.setColumns(10);
		
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
			JButton Confirm = new JButton("");
			Confirm.setIcon(new ImageIcon(BookInfor.class.getResource("/image/lib_button_addConfirm.png")));
			Confirm.setBounds(230, 460, 80, 30);
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
		int stuAgeInt;
		int stuMoneyInt;
		int idSize = studentID.getText().length();
		int nameSize = stuName.getText().length();
		int gradeSize = stuGrade.getText().length();
		int majorSize = stuMajor.getText().length();
		int pwdSize = stuPwd.getText().length();
		if (e.getActionCommand().equals("Closed")) {
			this.dispose();
			return;
		} else if (e.getActionCommand().equals("Minimized")) {
			this.setExtendedState(ICONIFIED);
			return;
		} else if (e.getActionCommand().equals("Maximized")) {
			boolean isMax = false;
			if (!isMax) {
				this.setExtendedState(MAXIMIZED_BOTH);
				isMax = true;
			} else {
				this.setExtendedState(NORMAL);
				isMax = false;
			}
			return;
		} else if(e.getActionCommand().equals("Confirm")) {
			
			if(stuAge.getText().length()==0) {
				JOptionPane.showMessageDialog(null,"请输入年龄","错误",JOptionPane.ERROR_MESSAGE);
				return;
			} else if(stuMoney.getText().length()==0) {
				JOptionPane.showMessageDialog(null,"请输入余额","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			stuAgeInt = Integer.valueOf(stuAge.getText());
			stuMoneyInt = Integer.valueOf(stuMoney.getText());
			if(stuAgeInt < 0) {
					JOptionPane.showMessageDialog(null,"非法输入，年龄应大于0","错误",JOptionPane.ERROR_MESSAGE);
			} else if(stuMoneyInt < 0) {
				    JOptionPane.showMessageDialog(null,"非法输入，余额应大于0","错误",JOptionPane.ERROR_MESSAGE);
			}else if(stuAgeInt*stuMoneyInt*pwdSize*idSize*nameSize*gradeSize*majorSize==0) {
				JOptionPane.showMessageDialog(null,"请完善人员相关信息！","错误",JOptionPane.ERROR_MESSAGE);
			} else {	
				
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_ADD_SIG_INFOR");
				User user = new User();
				
				user.setId(studentID.getText());
				user.setAge(stuAge.getText());
				user.setGrade(stuGrade.getText());
				user.setMajor(stuMajor.getText());
				user.setMoney(stuMoney.getText());
				user.setName(stuName.getText());
                user.setPwd(stuPwd.getText());
                if(stuRole.getSelectedItem()=="学生") 
                {user.setRole("学生");}
                else if(stuRole.getSelectedItem()==("商店管理员")) {user.setRole("商店管理员");}
                else if(stuRole.getSelectedItem()==("图书管理员")) {user.setRole("图书管理员");}
                if(stuSex.getSelectedItem()=="男") {user.setSex("男");}
                else if(stuSex.getSelectedItem()==("女")) {user.setSex("女");}
				clientReq.setContent(user.getContent());
				try {
					ObjectOutputStream oos1=new ObjectOutputStream(socket.getOutputStream());
					oos1.writeObject(clientReq);
					oos1.flush();
				} catch (Exception e4) {
					e4.printStackTrace();
				}
				this.dispose();
			}
		}
	}
}
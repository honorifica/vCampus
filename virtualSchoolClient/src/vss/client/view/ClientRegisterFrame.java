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

//��Ҫ����ID��Age��Grade��Major��Money��Name��Pwd��Role��Sex
/**
 * 
*  ClientRegisterFrame 
*   (ע����Ϣʱ�����Ľ���) 
* @author WGH
*  2021��7��28�� ����12:25:57 
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
	 *   ClientRegisterFrame���캯��
	 * @author WGH
	 *  2021-07-28 12:26:23
	 */
	public ClientRegisterFrame(){
		
		setUndecorated(true);//ȥ�߿�
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
		stuNewLabel_8.setFont(new Font("����", Font.PLAIN, 20));
		stuNewLabel_8.setBounds(190, 310, 86, 24);
		contentPanel.add(stuNewLabel_8);
		
		JLabel stuNewLabel_7 = new JLabel("\u6CE8\u518C\u91D1\u989D");
		stuNewLabel_7.setFont(new Font("����", Font.PLAIN, 20));
		stuNewLabel_7.setBounds(190, 230, 86, 24);
		contentPanel.add(stuNewLabel_7);
		
		JLabel stuNewLabel_6 = new JLabel("\u5BC6\u7801");
		stuNewLabel_6.setFont(new Font("����", Font.PLAIN, 20));
		stuNewLabel_6.setBounds(190, 150, 86, 24);
		contentPanel.add(stuNewLabel_6);
		
		JLabel stuNewLabel_5 = new JLabel("\u5E74\u7EA7");
		stuNewLabel_5.setFont(new Font("����", Font.PLAIN, 20));
		stuNewLabel_5.setBounds(35, 350, 86, 24);
		contentPanel.add(stuNewLabel_5);
		
		stuSex = new JComboBox();
		stuSex.setBorder(null);
		//stuSex.setForeground(Color.white);
		//stuSex.setBackground(new Color(83,130,52));
		stuSex.setOpaque(false);
		stuSex.setModel(new DefaultComboBoxModel(new String[] {"��", "Ů"}));
		stuSex.setBounds(95, 310, 40, 25);
		contentPanel.add(stuSex);
		
		JLabel stuNewLabel_4 = new JLabel("\u4E13\u4E1A");
		stuNewLabel_4.setFont(new Font("����", Font.PLAIN, 20));
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
		stuNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		contentPanel.add(stuNewLabel);
		
		JLabel stuNewLabel_1 = new JLabel("\u540D\u79F0");
		stuNewLabel_1.setBounds(35, 150, 86, 24);
		stuNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		contentPanel.add(stuNewLabel_1);
		
		JLabel stuNewLabel_2 = new JLabel("\u5E74\u9F84");
		stuNewLabel_2.setBounds(35, 230, 86, 24);
		stuNewLabel_2.setFont(new Font("����", Font.PLAIN, 20));
		contentPanel.add(stuNewLabel_2);
		
		JLabel stuNewLabel_3 = new JLabel("\u6027\u522B");
		stuNewLabel_3.setBounds(35, 310, 50, 24);
		stuNewLabel_3.setFont(new Font("����", Font.PLAIN, 20));
		contentPanel.add(stuNewLabel_3);
		{
			JButton register = new JButton("\u6CE8\u518C");
			register.setForeground(Color.white);
			register.setFont(new Font("΢���ź�", Font.PLAIN, 16));
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
	 * �����������
	 * @param e ���������¼�
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
		if (stuRole.getSelectedItem() =="ѧ��")
		   {role="2";}
		if (stuRole.getSelectedItem() =="��ʦ")
		   {role="3";}
		if (stuRole.getSelectedItem() =="�̵����Ա")
		   {role="4";}
		if (stuRole.getSelectedItem() =="ͼ�����Ա")
		   {role="5";}
		
		if (stuSex.getSelectedItem()=="��")
		{
			sex = "��";
		}
		else
		{
			sex = "Ů";
		}
		
		//��������
		/**
		 * ��ע�����õ�����Ϣͨ��socket�������ݿ���
		 */
		ClientConServer ccs = new ClientConServer();
		ccs.sendRegister(stuID.getText(),stuAge.getText(),stuGrade.getText(),stuMajor.getText(),stuMoney.getText(),stuName.getText(),stuPwd.getText(),role,sex);
		
		if(e.getActionCommand().equals("register"))
		{
			//System.out.print(ccs.sign);
			//���ص�Ȩ��
			if(ccs.sign==0)
			{
				JOptionPane.showMessageDialog(null,"ע��ʧ�ܣ�","����",JOptionPane.ERROR_MESSAGE);
			}
			else if(ccs.sign==1)
			{
				JOptionPane.showMessageDialog(null,"���Ѿ��ɹ�ע�ᣬ�벻Ҫ�ظ�ע�ᣡ","����",JOptionPane.ERROR_MESSAGE);
			}
			else if(ccs.sign==2)
			{
				JOptionPane.showMessageDialog(null,"ͬѧ����ϲע��ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
			else if(ccs.sign==3)
			{
				JOptionPane.showMessageDialog(null,"��ʦ����ϲע��ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
			else if(ccs.sign==4)
			{
				JOptionPane.showMessageDialog(null,"��������ϲע��ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
			else if(ccs.sign==5)
			{
				JOptionPane.showMessageDialog(null,"ͼ�����Ա����ϲע��ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
		}
	}

}

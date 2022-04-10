package vss.client.view;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;

import javax.print.DocFlavor.URL;
import javax.swing.*;

import vss.client.bz.ClientConServer;
import vss.client.bz.thread.ClientThreadSrv;
import vss.client.bz.thread.ClientThreadSrvMgr;
import vss.common.VerifyCode;
/**
*   ��¼���棬���к���ʾ��������
* @author WGH
*   2021��7��27�� ����7:46:46 
 */


public class ClientLoginFrame extends JFrame implements ActionListener,MouseListener {

	/**
	 * ��������Ҫ�����
	 */
	JLabel tim;
	private JPanel jp1,jp2,jp3,jp4;
	private JLabel jlb_user,jlb_password,jlb_verify;
	private JButton jb_login,jb_register;
	private JTextField jtf_user,jtf_verify;
	private JPasswordField jpf_password;
	static int flag =0;
	VerifyCode vcode=new VerifyCode();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton Maximized;
	private JButton Closed;
	private JButton Minimized;
	private JLabel lNewLabel;
	protected int originX;
	protected int originY;
	
/**
 * 
 *  ClientLoginFrame
 *   ClientLoginFrame���캯��
 * @author WGH
 *   2021-07-27 08:26:29
 */
	public ClientLoginFrame(){
		/**
		 * ��������Ҫ�����
		 */
		getContentPane().setBackground(Color.WHITE);
		setUndecorated( true);//ȥ�߿�
		
		
		ImageIcon background = new ImageIcon("image/background.JPG");//����
        JLabel backgroundCon = new JLabel(background);//������ͼ����JLbel��
        backgroundCon.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());
        getLayeredPane().add(backgroundCon, new Integer(Integer.MIN_VALUE));//����LayeredPanel
        JPanel jp = (JPanel)getContentPane();
        jp.setOpaque(false);  //����contentPanel͸��
		
	
		Container c = getContentPane(); //��ȡJFrame��� 
		
		jp1 = new JPanel();
		jp1.setBounds(418, 180, 446, 37);
		jp1.setOpaque(false);
		jp2 = new JPanel();
		jp2.setBounds(418, 222, 446, 37);
		jp2.setOpaque(false);
		jp3 = new JPanel();
		jp3.setBounds(418, 264, 446, 37);
		jp3.setOpaque(false);
		jp4 = new JPanel();
		jp4.setBounds(418, 319, 446, 75);
		jp4.setOpaque(false);
		
		jlb_user = new JLabel("�û�����");
		jlb_user.setFont(new Font("������κ", Font.PLAIN, 16));
		jlb_password = new JLabel("��     �룺");
		jlb_password.setFont(new Font("������κ", Font.PLAIN, 16));
		jlb_verify= new JLabel("��֤��:");
		jlb_verify.setBounds(120, 10, 53, 17);
		jlb_verify.setFont(new Font("������κ", Font.PLAIN, 16));
		

		
		jtf_user = new JTextField(15);
		jtf_user.setForeground(SystemColor.activeCaption);
		jtf_user.setBackground(SystemColor.activeCaption);
		jtf_verify = new JTextField(4);
		jtf_verify.setBounds(187, 9, 38, 21);
		jpf_password = new JPasswordField(15);
		//jtf_user.setBorder(null);//���߽�ȥ��
		jtf_user.setOpaque(false); //���ֿ���Ϊ͸������
		jtf_verify.setOpaque(false);
		jpf_password.setOpaque(false);
		
		jp1.add(jlb_user);
		jp1.add(jtf_user);
		jp2.add(jlb_password);
		jp2.add(jpf_password);
		jp3.setLayout(null);
		jp3.add(jlb_verify);
		jp3.add(jtf_verify);
		vcode.setBounds(232, 5, 94, 30);
		jp3.add(vcode);
		jp4.setLayout(null);
		jb_login = new JButton("");
		jb_login.setLocation(148, 18);
		buttonGroup.add(jb_login);
		
		jb_login.setBackground(new Color(255, 255, 255));
		jb_login.setSize(60, 32);
		jb_login.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login3_1.png")));
		jb_login.setContentAreaFilled(false);
		jb_login.setBorder(null);//ȥ�߿�
		
		jb_register= new JButton();
		jb_register.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login4_1.png")));
		jb_register.setBounds(242, 18, 60, 32);
		jb_register.setBorder(null);
		
		jp4.add(jb_login);
		jb_login.addActionListener(this);
		jb_login.setActionCommand("jb_login");
		jp4.add(jb_register);
		jp1.setLayout(new FlowLayout());
		jp2.setLayout(new FlowLayout());
		
		//���ø�����ť����Ӧ
		jb_register.addActionListener(this);
		jb_register.setActionCommand("jb_register");
		tim = new JLabel();
		tim.setBounds(0, 185, 446, 37);
		getContentPane().setLayout(null);
		
		lNewLabel = new JLabel("");
		lNewLabel.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/SEULogo_2.png")));
		lNewLabel.setBounds(284, 158, 153, 143);
		getContentPane().add(lNewLabel);
		/**
		 * ���¶���һ��Jpanel������ʵ���϶�����С�����رյȲ���
		 * 
		 */
		JPanel border = new JPanel();
		border.setBounds(0, 0, 1000, 28);
		getContentPane().add(border);
		border.setLayout(null);
		border.setOpaque(false);//���ϱ߿���Ϊ͸��
		
		JFrame that = this;//ʵ���϶�

	    border.addMouseListener((MouseListener) new MouseAdapter(){

			@Override
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
		
		
		Maximized = new JButton("");
		Maximized.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login5_1.png")));
		Maximized.setBounds(949, 5, 18, 18);
		Maximized.setContentAreaFilled(false);
		Maximized.setBorder(null);
		Maximized.addActionListener(this);
		Maximized.setActionCommand("Maximized");
		border.add(Maximized);
		
		Closed = new JButton("");
		Closed.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login6_1.png")));
		Closed.setContentAreaFilled(false);
		Closed.setBorder(null);
		Closed.setBounds(975, 5, 18, 18);
		Closed.addActionListener(this);
		Closed.setActionCommand("Closed");
		border.add(Closed);
		
		
		Minimized = new JButton("");

		Minimized.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login7_1.png")));
		Minimized.setContentAreaFilled(false);
		Minimized.setBorder(null);
		Minimized.setBounds(921, 5, 18, 18);
		Minimized.addActionListener(this);
		Minimized.setActionCommand("Minimized");
		border.add(Minimized);
		
		
		JLabel Jlb_photo = new JLabel("");
		Jlb_photo.setBounds(198, 111, 311, 300);
		getContentPane().add(Jlb_photo);
		Jlb_photo.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login1_2_1.jpg")));
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		c.add(tim);
		
		JLabel lblNewLabel = new JLabel("VCampus\u8EAB\u4EFD\u8BA4\u8BC1");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel.setBounds(538, 132, 142, 31);
		getContentPane().add(lblNewLabel);
		
		JLabel backicon1 = new JLabel("");
		backicon1.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/white.png")));
		backicon1.setForeground(Color.BLACK);
		backicon1.setBackground(Color.WHITE);
		backicon1.setBounds(509, 111, 268, 300);
		getContentPane().add(backicon1);
		
		JLabel back = new JLabel("");
		back.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login1_1_1.jpg")));
		back.setBounds(0, 0, 1000, 562);
		getContentPane().add(back);
		
		
		this.setSize(1000,562);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
    	this.setLocationRelativeTo(null);
    	
  
	}
	//�ڽ��滭ֱ��
	/**
	 * @param g �������Ƶ�¼�����ϵ�ֱ��
	 */
	
	  public void paint(Graphics g) {
		    super.paint(g); 
		    Graphics2D g2 = (Graphics2D) g;
		    g2.setStroke(new BasicStroke(1.0f));
		    g2.setColor(new Color(84,130,53));
		    Line2D lin = new Line2D.Float(540, 161, 588, 161);//�ֱ����x��y����ֵ
		    g2.draw(lin);
		  }
	


	@Override
	/**
	 * �����������
	 * @param arg0 ���������¼�
	 */
	public void actionPerformed(ActionEvent arg0) {
		//  Auto-generated method stub
		/**
		 * ��֤������֤����ȷ��
		 */
		if(jtf_verify.getText()==null) {
			flag = 0;
		}else if(vcode.getCode()==null) {
			flag=1;	
		}else if(vcode.getCode().equals(jtf_verify.getText())) {
			flag=1;
		}else {
			flag=0;
		}
		
		//�����С���Լ��رղ���
		if (arg0.getActionCommand().equals("Closed")) {
			this.dispose();
		} 
		else if (arg0.getActionCommand().equals("Minimized")) {
			this.setExtendedState(ICONIFIED);
		} 
		else if (arg0.getActionCommand().equals("Maximized")) 
		{
			boolean isMax = false;
			if (!isMax) {
				this.setExtendedState(MAXIMIZED_BOTH);
				isMax = true;
			} else {
				this.setExtendedState(NORMAL);
				isMax = false;
			}
			//new ClientLoginFrame(!isMax);
			//this.dispose();
		}
		//ע�����
		if(arg0.getActionCommand().equals("jb_register")){	
			ClientRegisterFrame crf = new ClientRegisterFrame();
		}
		else if(arg0.getActionCommand().equals("jb_login"))
		{	
	        //��¼����
			ClientConServer ccs = new ClientConServer();
			if(!ccs.IsConnect)
				JOptionPane.showMessageDialog(null,"������δ������","����",JOptionPane.ERROR_MESSAGE);
			try {
				ccs.sendCheck(jtf_user.getText(),jpf_password.getText());
			} catch (ClassNotFoundException e) {
				//  Auto-generated catch block
				e.printStackTrace();
			}
			
			//����Ȩ��
			//System.out.println(ccs.sign);
			
			if(flag==0)
			{
				JOptionPane.showMessageDialog(null, "��֤�����");
			}
			if(flag==1) 
			{	
			 if(ccs.sign==0) {
				JOptionPane.showMessageDialog(null,"�û������������","����",JOptionPane.ERROR_MESSAGE);
			 } 
			 else if(ccs.sign==1) {
				JOptionPane.showMessageDialog(null,"�û������������","����",JOptionPane.ERROR_MESSAGE);
			} 
			else if(ccs.sign==2) {
				try {
					ClientThreadSrv cts = new ClientThreadSrv(ccs.getUser().getId(), ClientConServer.getSocket(), ccs.sign);
					this.setVisible(false);	
					ClientThreadSrvMgr.add(ccs.getUser().getId(), cts);
					cts.start();
				} catch (IOException e) {
					//  Auto-generated catch block
					e.printStackTrace();
				}
			} 
			else if(ccs.sign==3) {
				
				try {
					ClientThreadSrv cts = new ClientThreadSrv(ccs.getUser().getId(), ClientConServer.getSocket(), ccs.sign);
					this.setVisible(false);	
					ClientThreadSrvMgr.add(ccs.getUser().getId(), cts);
					cts.start();
				} catch (IOException e) {
					//  Auto-generated catch block
					e.printStackTrace();
				}
			} 
			else if(ccs.sign==4) {
				//�̵����Ա
				try {
					ClientThreadSrv cts = new ClientThreadSrv(ccs.getUser().getId(), ClientConServer.getSocket(),ccs.sign);
					this.setVisible(false);	
					ClientThreadSrvMgr.add(ccs.getUser().getId(), cts);
					cts.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(ccs.sign==5) {
				//ͼ�����Ա
				try {
					ClientThreadSrv cts = new ClientThreadSrv(ccs.getUser().getId(), ClientConServer.getSocket(), ccs.sign);
					this.setVisible(false);	
					ClientThreadSrvMgr.add(ccs.getUser().getId(), cts);
					cts.start();
				} catch (IOException e) {
					//  Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//  Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//  Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//  Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//  Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//  Auto-generated method stub
		
	}
}

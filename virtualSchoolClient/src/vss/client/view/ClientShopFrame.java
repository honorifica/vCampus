package vss.client.view;
//ѧ���������
import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vss.common.ClientReq;
import vss.common.ClientReqType;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JEditorPane;
import java.awt.Color;
/**
 * ���ڵ��ù������ʱʹ��
 * @author �����
 *
 */
public class ClientShopFrame extends JFrame implements ActionListener{
	JPanel jp2;
	JTable jtb_goodslist;
	JScrollPane jsp;
	Socket socket;//��������
	private JTextField goodsName;
	private JTextField goodsAmount;
	private JComboBox<String> range;
	private JScrollPane scrollPane;
	private String userID;
	protected int originX;
	protected int originY;
	private JButton Maximized;
	private JButton Closed;
	private JButton Minimized;
	private JLabel backGround;
	private JButton show;
	private JLabel label;
	private JLabel lblNewLabel;
	private String money;
	private JLabel userMoney;
	/**
	 * ���ڳ�ʼ�����������ͳ�ʼ��ʾ
	 * @param socket ͨ��ʹ�õ�socket
	 * @param userID1 ʹ���ߵ�ID
	 * @throws IOException �׳���IO�쳣
	 * @throws ClassNotFoundException �׳������޷�ȷ���쳣
	 */
	public ClientShopFrame(Socket socket, String userID1) throws IOException, ClassNotFoundException{
	
		this.socket = socket;
		userID=userID1;
		
		ClientReq clientReq = new ClientReq();//�½��������ڽ���
		clientReq.setType("REQ_SHOW_SIG_INFOR");//������õ���ѧ��ѧ��
		Vector<String> contents = new Vector<String>();//����ID�洢
		contents.add(userID);
		clientReq.setContent(contents);//������Ϣ�������
		//ͨ��
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());//�����socketͨ��OutputStream�����Server��
		oos.writeObject(clientReq);//д����������ȥ�����л���
		oos.flush();//�ϴ��ȴ�����
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());//�����socketͨ��InputStreamд��client��
		clientReq = (ClientReq) ois.readObject();//������ȥ���л���
		money = clientReq.getContent().get(8);
		
		Maximized = new JButton("");
		Maximized.setBounds(744, 8, 18, 18);
		Maximized.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login5_1.png")));
		Maximized.setContentAreaFilled(false);
		Maximized.setBorder(null);
		Maximized.addActionListener(this);
		Maximized.setActionCommand("Maximized");
		
		Closed = new JButton("");
		Closed.setBounds(772, 8, 18, 18);
		Closed.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login6_1.png")));
		Closed.setContentAreaFilled(false);
		Closed.setBorder(null);
		Closed.addActionListener(this);
		Closed.setActionCommand("Closed");
		
		
		Minimized = new JButton("");
		Minimized.setBounds(716, 8, 18, 18);
		Minimized.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login7_1.png")));
		Minimized.setContentAreaFilled(false);
		Minimized.setBorder(null);
		Minimized.addActionListener(this);
		getContentPane().setLayout(null);
		
		userMoney = new JLabel(money);
		userMoney.setFont(new Font("����", Font.PLAIN, 20));
		userMoney.setBounds(67, 428, 90, 30);
		getContentPane().add(userMoney);
		
		lblNewLabel = new JLabel("\u4F59\u989D:");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel.setBounds(19, 428, 90, 30);
		getContentPane().add(lblNewLabel);
		
		range = new JComboBox();
		range.setBackground(new Color(255, 255, 255));
		range.setFont(new Font("����", Font.PLAIN, 20));
		range.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "\u5355\u4E2A"}));
		range.setBounds(30, 87, 80, 30);
		getContentPane().add(range);
		
		show = new JButton("");
		show.setIcon(new ImageIcon(ClientShopFrame.class.getResource("/image/shop_button_show.png")));
		show.setFont(new Font("����", Font.PLAIN, 20));
		show.setBounds(30, 138, 80, 30);
		show.addActionListener(this);
		show.setActionCommand("show");
		getContentPane().add(show);
		Minimized.setActionCommand("Minimized");
		
		JPanel border = new JPanel();
		border.setBounds(0, 0, 800, 30);
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
		
		setUndecorated(true);
		JLabel jlb_num,jlb_id;
		JButton buy;
		Object[][] goodsinformation= {};
        Object[] goodslist = {"��ƷID","��Ʒ����","��Ʒ����","��Ʒ�۸�"};
        
		jtb_goodslist=new JTable();
		jtb_goodslist.setModel(new DefaultTableModel(
        		new String[][] {{}}, 
        		new String[] {}
        ));
		scrollPane = new JScrollPane(jtb_goodslist);
		scrollPane.setBounds(167, 97, 0, 0);
		getContentPane().add(scrollPane);
		
		jlb_id= new JLabel("\u5546\u54C1\u540D\u79F0");
		jlb_id.setFont(new Font("����", Font.PLAIN, 20));
		jlb_id.setBounds(30, 190, 90, 33);
		getContentPane().add(jlb_id);
		
		goodsName = new JTextField();
		goodsName.setOpaque(false);
		goodsName.setBounds(20, 231, 100, 25);
		getContentPane().add(goodsName);
		goodsName.setColumns(10);
		jlb_num = new JLabel("\u8D2D\u4E70\u6570\u91CF");
		jlb_num.setFont(new Font("����", Font.PLAIN, 20));
		jlb_num.setBounds(30, 277, 90, 25);
		getContentPane().add(jlb_num);
		
		goodsAmount = new JTextField();
		goodsAmount.setOpaque(false);
		goodsAmount.setBackground(new Color(255, 255, 255));
		goodsAmount.setBounds(20, 310, 100, 25);
		getContentPane().add(goodsAmount);
		goodsAmount.setColumns(10);
		
		buy = new JButton("");
		buy.setIcon(new ImageIcon(ClientShopFrame.class.getResource("/image/shop_button_buy.png")));
		buy.setFont(new Font("����", Font.PLAIN, 20));
		buy.setBounds(30, 369, 80, 30);
		getContentPane().add(buy);
		buy.addActionListener(this);
		buy.setActionCommand("buy");
		
		backGround = new JLabel("");
		backGround.setIcon(new ImageIcon(ClientShopFrame.class.getResource("/image/shop_buyer_bg.jpg")));
		backGround.setBounds(0, 0, 800, 500);
		getContentPane().add(backGround);
		
		label = new JLabel("New label");
		label.setBounds(0, 427, 50, 12);
		getContentPane().add(label);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(13, 407, 107, 18);
		getContentPane().add(editorPane);
		this.setSize(800,500);
		this.setTitle("�������");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}

	/**
	 * �������ñ���
	 */
	private void setBak() {
		((JPanel)this.getContentPane()).setOpaque(false); 
		ImageIcon img = new ImageIcon("images/background.JPG"); 
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE)); 
		background.setBounds(0, 10, img.getIconWidth(), img.getIconHeight()); 
	}


	@Override
	/**
	 * �¼����������
	 */
	public void actionPerformed(ActionEvent e) {
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
		} else if(e.getActionCommand() == "buy") {
			if(goodsName.getText().length()==0) {
				JOptionPane.showMessageDialog(null, "��������Ʒ����","����", JOptionPane.ERROR_MESSAGE);
			} else if(goodsAmount.getText().length()==0) {
				JOptionPane.showMessageDialog(null, "��������Ʒ����","����", JOptionPane.ERROR_MESSAGE);
			} else {
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_BUY_GOODS");
				Vector<String> reqContent=new Vector<String>();
				reqContent.add(goodsName.getText());
				reqContent.add(goodsAmount.getText());
				reqContent.add(userID);
				clientReq.setContent(reqContent);
				try {
					ObjectOutputStream oos1 = new ObjectOutputStream(socket.getOutputStream());
					oos1.writeObject(clientReq);
					oos1.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				if(clientReq.getType().equals("NULL")) 
					JOptionPane.showMessageDialog(null,"����Ʒ��δ����");
				else {
					Object[][] goodsinformation= {};
			        Object[] goodslist = {"��ƷID","��Ʒ����","��Ʒ����","��Ʒ�۸�"};
			        DefaultTableModel goodsInforTable =  new DefaultTableModel(goodsinformation, goodslist);
					clientReq.setType("REQ_SHOW_ALL_GOODS");
					try{
					//������
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
						//��������
						ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
						clientReq = (ClientReq) ois.readObject();
					} catch(Exception e2) {
						e2.printStackTrace();
					}
					Vector<String> allGoodsContents = clientReq.getContent();
					Object sigRow[] = new  String[4];
					int inforSize = allGoodsContents.size();
					for(int i=0;i<inforSize;) {
						for(int j=0;j<4;) {
							sigRow[j++]=allGoodsContents.get(i++);
						}
						goodsInforTable.addRow(sigRow);
					}
					getContentPane().setLayout(null);
					int rowAmount = inforSize/4;
					jtb_goodslist.setModel(goodsInforTable);
					scrollPane.setBounds(168, 85, 610, 340 < 17 * rowAmount + 22 ? 340 : 17 * rowAmount + 22);
					
					clientReq.setType("REQ_SHOW_SIG_INFOR");//������õ���ѧ��ѧ��
					Vector<String> contents = new Vector<String>();//����ID�洢
					contents.add(userID);
					clientReq.setContent(contents);//������Ϣ�������
					//ͨ��
					try {
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());//�����socketͨ��OutputStream�����Server��
						oos.writeObject(clientReq);//д����������ȥ�����л���
						oos.flush();//�ϴ��ȴ�����
						ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());//�����socketͨ��InputStreamд��client��
						clientReq = (ClientReq) ois.readObject();//������ȥ���л���
					}catch(Exception e3) {
						e3.printStackTrace();
					}
					money = clientReq.getContent().get(8);
					userMoney.setText(money);
				}
			}
		} else if(e.getActionCommand().equals("show")) {
			if(range.getSelectedItem().equals("ȫ��")) {
				Object[][] goodsinformation= {};
		        Object[] goodslist = {"��ƷID","��Ʒ����","��Ʒ����","��Ʒ�۸�"};
		        
		        DefaultTableModel goodsInforTable =  new DefaultTableModel(goodsinformation, goodslist);
		        ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_SHOW_ALL_GOODS");
				try{
				//������
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					//��������
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				} catch(Exception e2) {
					e2.printStackTrace();
				}
				
				Vector<String> allGoodsContents = clientReq.getContent();
				Object sigRow[] = new  String[4];
				int inforSize = allGoodsContents.size();
				for(int i=0;i<inforSize;) {
					for(int j=0;j<4;) {
						sigRow[j++]=allGoodsContents.get(i++);
					}
					goodsInforTable.addRow(sigRow);
				}
				getContentPane().setLayout(null);
				int rowAmount = inforSize/4;
				jtb_goodslist.setModel(goodsInforTable);
				scrollPane.setBounds(168, 85, 610, 340 < 17 * rowAmount + 22 ? 340 : 17 * rowAmount + 22);
			} else if(range.getSelectedItem().equals("����")) {
				if(goodsName.getText().length()==0) JOptionPane.showMessageDialog(null,"��������Ʒ���ƣ�","����",JOptionPane.ERROR_MESSAGE);
				else {
					ClientReq clientReq = new ClientReq();
					clientReq.setType(ClientReqType.REQ_SHOW_SIG_GOODS);
					Vector<String> content = new Vector<String>();
					content.add(goodsName.getText());
					clientReq.setContent(content);
					try {
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
						ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
						clientReq = (ClientReq) ois.readObject();
					} catch(Exception e1){
						e1.printStackTrace();
					}
					if(clientReq.getType().equals("NULL")) JOptionPane.showMessageDialog(null,"�˻����̵�δ����");
					else {
						Vector<String> goodsContents = clientReq.getContent();
						Object sigRow[] = new  String[4];
						DefaultTableModel goodsInforTable =  new DefaultTableModel(
								null, 
								new String[] {"��ƷID","��Ʒ����","��Ʒ����","��Ʒ�۸�"}
						);
						int inforSize = goodsContents.size();
						for(int i=0;i<inforSize;) {
							for(int j=0;j<4;) {
								sigRow[j++]=goodsContents.get(i++);
							}
							goodsInforTable.addRow(sigRow);
						}
						getContentPane().setLayout(null);
						int rowAmount = inforSize/4;
						jtb_goodslist.setModel(goodsInforTable);
						scrollPane.setBounds(168, 85, 610, 340 < 17 * rowAmount + 22 ? 340 : 17 * rowAmount + 22);
					}
				}
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

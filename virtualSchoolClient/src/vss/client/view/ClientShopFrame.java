package vss.client.view;
//学生购物界面
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
 * 用于调用购物服务时使用
 * @author 姜云骞
 *
 */
public class ClientShopFrame extends JFrame implements ActionListener{
	JPanel jp2;
	JTable jtb_goodslist;
	JScrollPane jsp;
	Socket socket;//传送数据
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
	 * 用于初始化各个参数和初始显示
	 * @param socket 通信使用的socket
	 * @param userID1 使用者的ID
	 * @throws IOException 抛出的IO异常
	 * @throws ClassNotFoundException 抛出的类无法确认异常
	 */
	public ClientShopFrame(Socket socket, String userID1) throws IOException, ClassNotFoundException{
	
		this.socket = socket;
		userID=userID1;
		
		ClientReq clientReq = new ClientReq();//新建申请用于交换
		clientReq.setType("REQ_SHOW_SIG_INFOR");//申请调用单个学生学籍
		Vector<String> contents = new Vector<String>();//调用ID存储
		contents.add(userID);
		clientReq.setContent(contents);//调用信息存进申请
		//通信
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());//把这个socket通过OutputStream输出给Server端
		oos.writeObject(clientReq);//写进申请里面去（序列化）
		oos.flush();//上传等待处理
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());//把这个socket通过InputStream写回client端
		clientReq = (ClientReq) ois.readObject();//读出（去序列化）
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
		userMoney.setFont(new Font("黑体", Font.PLAIN, 20));
		userMoney.setBounds(67, 428, 90, 30);
		getContentPane().add(userMoney);
		
		lblNewLabel = new JLabel("\u4F59\u989D:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(19, 428, 90, 30);
		getContentPane().add(lblNewLabel);
		
		range = new JComboBox();
		range.setBackground(new Color(255, 255, 255));
		range.setFont(new Font("宋体", Font.PLAIN, 20));
		range.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "\u5355\u4E2A"}));
		range.setBounds(30, 87, 80, 30);
		getContentPane().add(range);
		
		show = new JButton("");
		show.setIcon(new ImageIcon(ClientShopFrame.class.getResource("/image/shop_button_show.png")));
		show.setFont(new Font("宋体", Font.PLAIN, 20));
		show.setBounds(30, 138, 80, 30);
		show.addActionListener(this);
		show.setActionCommand("show");
		getContentPane().add(show);
		Minimized.setActionCommand("Minimized");
		
		JPanel border = new JPanel();
		border.setBounds(0, 0, 800, 30);
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
		
		setUndecorated(true);
		JLabel jlb_num,jlb_id;
		JButton buy;
		Object[][] goodsinformation= {};
        Object[] goodslist = {"商品ID","商品名称","商品数量","商品价格"};
        
		jtb_goodslist=new JTable();
		jtb_goodslist.setModel(new DefaultTableModel(
        		new String[][] {{}}, 
        		new String[] {}
        ));
		scrollPane = new JScrollPane(jtb_goodslist);
		scrollPane.setBounds(167, 97, 0, 0);
		getContentPane().add(scrollPane);
		
		jlb_id= new JLabel("\u5546\u54C1\u540D\u79F0");
		jlb_id.setFont(new Font("宋体", Font.PLAIN, 20));
		jlb_id.setBounds(30, 190, 90, 33);
		getContentPane().add(jlb_id);
		
		goodsName = new JTextField();
		goodsName.setOpaque(false);
		goodsName.setBounds(20, 231, 100, 25);
		getContentPane().add(goodsName);
		goodsName.setColumns(10);
		jlb_num = new JLabel("\u8D2D\u4E70\u6570\u91CF");
		jlb_num.setFont(new Font("宋体", Font.PLAIN, 20));
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
		buy.setFont(new Font("宋体", Font.PLAIN, 20));
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
		this.setTitle("购物界面");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}

	/**
	 * 用于设置背景
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
	 * 事件监听程序和
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
				JOptionPane.showMessageDialog(null, "请输入商品名称","错误", JOptionPane.ERROR_MESSAGE);
			} else if(goodsAmount.getText().length()==0) {
				JOptionPane.showMessageDialog(null, "请输入商品数量","错误", JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null,"该商品并未进货");
				else {
					Object[][] goodsinformation= {};
			        Object[] goodslist = {"商品ID","商品名称","商品数量","商品价格"};
			        DefaultTableModel goodsInforTable =  new DefaultTableModel(goodsinformation, goodslist);
					clientReq.setType("REQ_SHOW_ALL_GOODS");
					try{
					//传数据
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
						//接受数据
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
					
					clientReq.setType("REQ_SHOW_SIG_INFOR");//申请调用单个学生学籍
					Vector<String> contents = new Vector<String>();//调用ID存储
					contents.add(userID);
					clientReq.setContent(contents);//调用信息存进申请
					//通信
					try {
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());//把这个socket通过OutputStream输出给Server端
						oos.writeObject(clientReq);//写进申请里面去（序列化）
						oos.flush();//上传等待处理
						ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());//把这个socket通过InputStream写回client端
						clientReq = (ClientReq) ois.readObject();//读出（去序列化）
					}catch(Exception e3) {
						e3.printStackTrace();
					}
					money = clientReq.getContent().get(8);
					userMoney.setText(money);
				}
			}
		} else if(e.getActionCommand().equals("show")) {
			if(range.getSelectedItem().equals("全部")) {
				Object[][] goodsinformation= {};
		        Object[] goodslist = {"商品ID","商品名称","商品数量","商品价格"};
		        
		        DefaultTableModel goodsInforTable =  new DefaultTableModel(goodsinformation, goodslist);
		        ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_SHOW_ALL_GOODS");
				try{
				//传数据
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					//接受数据
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
			} else if(range.getSelectedItem().equals("单个")) {
				if(goodsName.getText().length()==0) JOptionPane.showMessageDialog(null,"请输入商品名称！","错误",JOptionPane.ERROR_MESSAGE);
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
					if(clientReq.getType().equals("NULL")) JOptionPane.showMessageDialog(null,"此货物商店未进货");
					else {
						Vector<String> goodsContents = clientReq.getContent();
						Object sigRow[] = new  String[4];
						DefaultTableModel goodsInforTable =  new DefaultTableModel(
								null, 
								new String[] {"商品ID","商品名称","商品数量","商品价格"}
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

package vss.client.view;
//商店管理员界面
import java.awt.BorderLayout;
import java.awt.Container;
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import vss.common.ClientReq;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Point;
import java.awt.Color;

/**
 * 用于在商店管理员登陆时调用
 * @author 姜云骞
 *
 */
public class ClientShopManagerFrame extends JFrame implements ActionListener{
	JPanel jp2;
	JTable jtb_goodslist;
	JScrollPane jsp;
	JComboBox<String> jcb_operation;
	private Socket socket;
	private JTextField goodsName;
	private JTextField goodsAmount;
	private String operatorID;
	protected int originX;
	protected int originY;
	private JButton Maximized;
	private JButton Closed;
	private JButton Minimized;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	/**
	 * 用于商店管理员登陆时进行初始化
	 * @param socket 通信时用的socket
	 * @throws IOException 抛出的IO异常
	 * @throws ClassNotFoundException 抛出的类无法定义异常
	 */
	public ClientShopManagerFrame(Socket socket) throws IOException, ClassNotFoundException
	{
		setUndecorated(true);//去边框
		this.socket= socket;
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
		setBak(); //调用背景方法 
		Container c = getContentPane(); //获取JFrame面板
		
		JLabel jlb_num,jlb_id;
		JButton jb_operate,jb_change;
		JPanel jp;
		
        Object[] goodslist = {"商品ID","商品名称","商品数量","商品价格"};
        DefaultTableModel model;

        ClientReq clientReq = new ClientReq();
		clientReq.setType("REQ_SHOW_ALL_GOODS");
		//传数据
		ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
		oos.writeObject(clientReq);
		oos.flush();
		//接受数据
		ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
		clientReq = (ClientReq) ois.readObject();
		Vector<String> allGoodsContents = clientReq.getContent();
		String[] goods = new String[4];
		String[][] goodsTable = new String[allGoodsContents.size()/4][4];
		int storingPlace = 0;
		int rowAmount = allGoodsContents.size()/4;
		for(int i=0;i<rowAmount;i++) {
			for(int j=0;j<4;j++) {
				goodsTable[i][j] = allGoodsContents.get(storingPlace++);
			}
		}
		jp2 = new JPanel();
		jp2.setBounds(0, 461, 784, 0);
		String x[]= {"增加","减少","删除"};
		getContentPane().setLayout(null);
		
		jtb_goodslist=new JTable();
		jtb_goodslist.setModel(new DefaultTableModel(
			goodsTable,
			new String[] {
				"\u5546\u54C1ID", "\u5546\u54C1\u540D\u79F0", "\u5546\u54C1\u6570\u91CF", "\u5546\u54C1\u4EF7\u683C"
			}
		));
		scrollPane = new JScrollPane(jtb_goodslist);
		scrollPane.setBounds(162, 100, 610, 340 < rowAmount * 17 + 21 ? 340 : rowAmount * 17 + 21 );
		getContentPane().add(scrollPane);
		jp2.setLayout(null);
		jp2.setLayout(new FlowLayout());
		getContentPane().add(jp2);
		
		jcb_operation= new JComboBox(x);
		jcb_operation.setBackground(new Color(255, 255, 255));
		jcb_operation.setFont(new Font("宋体", Font.PLAIN, 15));
		jcb_operation.setBounds(21, 97, 100, 30);
		getContentPane().add(jcb_operation);
		jcb_operation.setModel(new DefaultComboBoxModel(new String[] {"\u5220\u9664\u8D27\u7269\u6761\u76EE", "\u589E\u52A0\u8D27\u7269\u6761\u76EE", "\u589E\u52A0\u8D27\u7269\u6570\u91CF"}));
		
		jlb_id= new JLabel("\u5546\u54C1\u540D\u79F0");
		jlb_id.setFont(new Font("宋体", Font.PLAIN, 20));
		jlb_id.setBounds(28, 161, 87, 30);
		getContentPane().add(jlb_id);
		
		goodsName = new JTextField();
		goodsName.setOpaque(false);
		goodsName.setFont(new Font("宋体", Font.PLAIN, 20));
		goodsName.setBounds(21, 212, 100, 25);
		getContentPane().add(goodsName);
		goodsName.setColumns(10);
		jlb_num = new JLabel("\u64CD\u4F5C\u6570\u91CF");
		jlb_num.setFont(new Font("宋体", Font.PLAIN, 20));
		jlb_num.setBounds(28, 269, 87, 36);
		getContentPane().add(jlb_num);
		
		goodsAmount = new JTextField();
		goodsAmount.setOpaque(false);
		goodsAmount.setFont(new Font("宋体", Font.PLAIN, 20));
		goodsAmount.setBounds(21, 329, 100, 25);
		getContentPane().add(goodsAmount);
		goodsAmount.setColumns(10);
		
		jb_operate = new JButton("");
		jb_operate.setOpaque(false);
		jb_operate.setIcon(new ImageIcon(ClientShopManagerFrame.class.getResource("/image/shop_butto_excute.png")));
		jb_operate.setFont(new Font("宋体", Font.PLAIN, 20));
		jb_operate.setBounds(28, 398, 80, 30);
		getContentPane().add(jb_operate);
		jb_operate.addActionListener(this);
		jb_operate.setActionCommand("operate");
				
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ClientShopManagerFrame.class.getResource("/image/shop_manager_bg.jpg")));
		lblNewLabel.setBounds(0, 0, 800, 500);
		getContentPane().add(lblNewLabel);
		
		this.setTitle("商品管理");
		this.setSize(800,500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    	this.setLocationRelativeTo(null);
    	
	}


	private void setBak() {
	}


	@Override
	/**
	 * 事件服务程序
	 */
	public void actionPerformed(ActionEvent e) {
		//添删货物等
		if(e.getActionCommand() == "operate") {
			if(jcb_operation.getSelectedItem().equals("删除货物条目"))
			{
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_REMOVE_GOODS");
				Vector<String> reqContent = new Vector<String>();
				reqContent.setSize(4);
				reqContent.set(1,goodsName.getText());
				clientReq.setContent(reqContent);
				ObjectOutputStream oos;
				try {
					oos = new ObjectOutputStream(this.socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				clientReq.setType("REQ_SHOW_ALL_GOODS");
				//传数据
				try {
					ObjectOutputStream oos2 = new ObjectOutputStream(this.socket.getOutputStream());
					oos2.writeObject(clientReq);
					oos2.flush();
					//接受数据
					ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
				Vector<String> allGoodsContents = clientReq.getContent();
				String[] goods = new String[4];
				String[][] goodsTable = new String[allGoodsContents.size()/4][4];
				int storingPlace = 0;
				int rowAmount = allGoodsContents.size()/4;
				for(int i=0;i<rowAmount;i++) {
					for(int j=0;j<4;j++) {
						goodsTable[i][j] = allGoodsContents.get(storingPlace++);
					}
				}
				jp2 = new JPanel();
				jp2.setBounds(0, 461, 784, 0);
				String x[]= {"增加","减少","删除"};
				getContentPane().setLayout(null);
				
				jtb_goodslist=new JTable();
				jtb_goodslist.setModel(new DefaultTableModel(
					goodsTable,
					new String[] {
						"\u5546\u54C1ID", "\u5546\u54C1\u540D\u79F0", "\u5546\u54C1\u6570\u91CF", "\u5546\u54C1\u4EF7\u683C"
					}
				));
				scrollPane.setViewportView(jtb_goodslist);
				scrollPane.setBounds(162, 100, 610, 340 < rowAmount * 17 + 21 ? 340 : rowAmount * 17 + 21 );
			} else if(jcb_operation.getSelectedItem().equals("增加货物条目")) {
				scrollPane.setViewportView(null);
				scrollPane.setBounds(162, 100, 610, 0);
				GoodsInfor goodsInfor = new GoodsInfor(socket);
			} else if(jcb_operation.getSelectedItem().equals("增加货物数量")) {
				if(goodsName.getText().length()==0) {
					JOptionPane.showMessageDialog(null,"请输入商品名称");
				} else if(goodsAmount.getText().length()==0) {
					JOptionPane.showMessageDialog(null,"请输入商品操作数量");
				} else {
					ClientReq clientReq = new ClientReq();
					clientReq.setType("REQ_BUY_GOODS");
					Vector<String> reqContent=new Vector<String>();
					reqContent.add(goodsName.getText());
					String nGoodsAmount = String.valueOf(-Integer.valueOf(goodsAmount.getText()));
					reqContent.add(nGoodsAmount);
					reqContent.add(operatorID);
					clientReq.setLevel("4");
					clientReq.setContent(reqContent);
					try {
						ObjectOutputStream oos1 = new ObjectOutputStream(socket.getOutputStream());
						oos1.writeObject(clientReq);
						oos1.flush();
						this.setVisible(false);
						ClientShopManagerFrame csmf = new ClientShopManagerFrame(socket);
					} catch(Exception e1) {
						e1.printStackTrace();
					}
					clientReq.setType("REQ_SHOW_ALL_GOODS");
					//传数据
					try {
						ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
						//接受数据
						ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
						clientReq = (ClientReq) ois.readObject();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					Vector<String> allGoodsContents = clientReq.getContent();
					String[] goods = new String[4];
					String[][] goodsTable = new String[allGoodsContents.size()/4][4];
					int storingPlace = 0;
					int rowAmount = allGoodsContents.size()/4;
					for(int i=0;i<rowAmount;i++) {
						for(int j=0;j<4;j++) {
							goodsTable[i][j] = allGoodsContents.get(storingPlace++);
						}
					}
					jp2 = new JPanel();
					jp2.setBounds(0, 461, 784, 0);
					String x[]= {"增加","减少","删除"};
					getContentPane().setLayout(null);
					
					jtb_goodslist=new JTable();
					jtb_goodslist.setModel(new DefaultTableModel(
						goodsTable,
						new String[] {
							"\u5546\u54C1ID", "\u5546\u54C1\u540D\u79F0", "\u5546\u54C1\u6570\u91CF", "\u5546\u54C1\u4EF7\u683C"
						}
					));
					scrollPane.setViewportView(jtb_goodslist);
					scrollPane.setBounds(162, 100, 610, 340 < rowAmount * 17 + 21 ? 340 : rowAmount * 17 + 21 );
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


	public String getOperatorID() {
		return operatorID;
	}


	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

}

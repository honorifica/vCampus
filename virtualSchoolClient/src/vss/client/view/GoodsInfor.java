package vss.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import vss.common.ClientReq;
import vss.common.Goods;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class GoodsInfor extends JFrame implements ActionListener {
	private JTextField goodsID;
	private JTextField goodsName;
	private JTextField goodsAmount;
	private JTextField goodsPrice;
	private Socket socket;
	private JButton Maximized;
	private JButton Closed;
	private JButton Minimized;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	protected int originX;
	protected int originY;
	/**
	 * Create the dialog.
	 */
	public GoodsInfor(Socket intiSocket) {
		setUndecorated(true);
		this.socket=intiSocket;
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
		getContentPane().setLayout(null);
		
		goodsID = new JTextField();
		goodsID.setOpaque(false);
		goodsID.setBounds(176, 98, 100, 25);
		getContentPane().add(goodsID);
		goodsID.setColumns(10);
		
		goodsName = new JTextField();
		goodsName.setOpaque(false);
		goodsName.setBounds(176, 189, 100, 25);
		getContentPane().add(goodsName);
		goodsName.setColumns(10);
		
		goodsAmount = new JTextField();
		goodsAmount.setOpaque(false);
		goodsAmount.setBounds(176, 266, 100, 25);
		getContentPane().add(goodsAmount);
		goodsAmount.setColumns(10);
		
		goodsPrice = new JTextField();
		goodsPrice.setOpaque(false);
		goodsPrice.setBounds(176, 351, 100, 25);
		getContentPane().add(goodsPrice);
		goodsPrice.setColumns(10);
		
		JLabel lblGoodsID = new JLabel("\u5546\u54C1ID");
		lblGoodsID.setFont(new Font("宋体", Font.PLAIN, 20));
		lblGoodsID.setBounds(79, 101, 87, 22);
		getContentPane().add(lblGoodsID);
		
		JLabel lblGoodsName = new JLabel("\u5546\u54C1\u540D\u79F0");
		lblGoodsName.setFont(new Font("宋体", Font.PLAIN, 20));
		lblGoodsName.setBounds(79, 192, 87, 22);
		getContentPane().add(lblGoodsName);
		
		JLabel lblGoodsAmount = new JLabel("\u5546\u54C1\u6570\u91CF");
		lblGoodsAmount.setFont(new Font("宋体", Font.PLAIN, 20));
		lblGoodsAmount.setBounds(79, 269, 87, 22);
		getContentPane().add(lblGoodsAmount);
		
		JLabel lblGoodPrice = new JLabel("\u5546\u54C1\u4EF7\u683C");
		lblGoodPrice.setFont(new Font("宋体", Font.PLAIN, 20));
		lblGoodPrice.setBounds(79, 354, 87, 22);
		getContentPane().add(lblGoodPrice);
		
		JButton confirmButtom = new JButton("");
		confirmButtom.setIcon(new ImageIcon(GoodsInfor.class.getResource("/image/shop_button_addFonfirm.png")));
		confirmButtom.setBounds(247, 423, 80, 30);
		getContentPane().add(confirmButtom);
		confirmButtom.addActionListener(this);
		confirmButtom.setActionCommand("confirm");
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(GoodsInfor.class.getResource("/image/shop_infor_bg.jpg")));
		lblNewLabel_1.setBounds(0, 0, 350, 500);
		getContentPane().add(lblNewLabel_1);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
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
		} else if(e.getActionCommand()=="confirm") {
			int idLength = goodsID.getText().length();
			int nameLength = goodsName.getText().length();
			int amountLength = goodsAmount.getText().length();
			int priceLength = goodsPrice.getText().length();
			if(idLength==0 || nameLength==0 || amountLength == 0 || priceLength == 0) {
				JOptionPane.showMessageDialog(null,"请将商品信息完善","错误",JOptionPane.ERROR_MESSAGE);
			} else {
				Goods goods = new Goods();
				goods.setGoodsID(goodsID.getText());
				goods.setGoodsName(goodsName.getText());
				goods.setGoodsAmount(goodsAmount.getText());
				goods.setGoodsPrice(goodsPrice.getText());
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_ADD_GOODS");
				clientReq.setContent(goods.getContent());
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
				}
				catch(Exception error) {
					error.printStackTrace();
				}
				this.dispose();
			}
		}
	}
}


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

import vss.common.Book;
import vss.common.ClientReq;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;

/**
 * 使用于需要增加图书的时候
 */
public class BookInfor extends JFrame implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField bookID;
	private JTextField bookName;
	private JTextField bookAmount;
	private JTextField bookLeft;
	private Socket socket;
	protected int originX;
	protected int originY;
	private JButton Maximized;
	private JButton Closed;
	private JButton Minimized;
	private JLabel lblNewLabel_4;
	/**
	 * 用于初始化图书显示框的通信 socket和初始显示
	 * @param socket1 设置好的通信socket
	 */
	public BookInfor(Socket socket1) {
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
		
		socket = socket1;
		setBounds(100, 100, 350, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		bookID = new JTextField();
		bookID.setOpaque(false);
		bookID.setBounds(183, 106, 100, 25);
		contentPanel.add(bookID);
		bookID.setColumns(10);
		
		bookName = new JTextField();
		bookName.setOpaque(false);
		bookName.setBounds(183, 176, 100, 25);
		contentPanel.add(bookName);
		bookName.setColumns(10);
		
		bookAmount = new JTextField();
		bookAmount.setOpaque(false);
		bookAmount.setBounds(183, 248, 100, 25);
		contentPanel.add(bookAmount);
		bookAmount.setColumns(10);
		
		bookLeft = new JTextField();
		bookLeft.setOpaque(false);
		bookLeft.setBounds(183, 321, 100, 25);
		contentPanel.add(bookLeft);
		bookLeft.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u4E66\u672CID");
		lblNewLabel.setBounds(65, 106, 72, 18);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u4E66\u672C\u540D\u79F0");
		lblNewLabel_1.setBounds(59, 173, 86, 24);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u53EF\u501F\u9605\u6570");
		lblNewLabel_2.setBounds(59, 245, 86, 24);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u4E66\u672C\u603B\u91CF");
		lblNewLabel_3.setBounds(59, 315, 86, 30);
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPanel.add(lblNewLabel_3);
		{
			JButton Confirm = new JButton("");
			Confirm.setIcon(new ImageIcon(BookInfor.class.getResource("/image/lib_button_addConfirm.png")));
			Confirm.setBounds(237, 414, 80, 30);
			contentPanel.add(Confirm);
			Confirm.addActionListener(this);
			Confirm.setActionCommand("Confirm");
		}
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(BookInfor.class.getResource("/image/lib_bookInfor_bg.jpg")));
		lblNewLabel_4.setBounds(0, 0, 350, 500);
		contentPanel.add(lblNewLabel_4);
		
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	/**
	 * 用于监听事件并进行服务
	 * @param e 事件
	 */
	public void actionPerformed(ActionEvent e) {
		int bookAmountInt;
		int bookLeftInt;
		int idSize = bookID.getText().length();
		int nameSize = bookName.getText().length();
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
			
			if(bookAmount.getText().length()==0) {
				JOptionPane.showMessageDialog(null,"请输入图书可借阅量","错误",JOptionPane.ERROR_MESSAGE);
				return;
			} else if(bookLeft.getText().length()==0) {
				JOptionPane.showMessageDialog(null,"请输入图书馆藏总量","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			bookAmountInt = Integer.valueOf(bookAmount.getText());
			bookLeftInt = Integer.valueOf(bookLeft.getText());
			if(bookLeftInt < bookAmountInt) {
					JOptionPane.showMessageDialog(null,"非法输入，可借阅数应当小于馆藏量","错误",JOptionPane.ERROR_MESSAGE);
			} else if(bookAmountInt*bookLeftInt*idSize*nameSize==0) {
				JOptionPane.showMessageDialog(null,"请完善图书相关信息！","错误",JOptionPane.ERROR_MESSAGE);
			} else {	
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_ADD_BOOK");
				Book book = new Book();
				book.setBookID(bookID.getText());
				book.setBookName(bookName.getText());
					book.setBookAmount(bookAmount.getText());
				book.setLeft(bookLeft.getText());
				clientReq.setContent(book.getContent());
				try {
					ObjectOutputStream oos1=new ObjectOutputStream(getSocket().getOutputStream());
					oos1.writeObject(clientReq);
					oos1.flush();
				} catch (Exception e4) {
					e4.printStackTrace();
				}
				this.dispose();
			}
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}

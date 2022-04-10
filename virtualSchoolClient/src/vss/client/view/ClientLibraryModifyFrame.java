package vss.client.view;
import java.awt.Point;
//图书信息管理界面
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import vss.common.ClientReq;
import java.awt.Font;
import java.awt.Color;

/**
 * 当以图书管理员启动时调用
 * @author 姜云骞
 */
public class ClientLibraryModifyFrame extends JFrame implements ActionListener{
	JTable jt;
	JComboBox Range;
	JTextField bookID;
	JButton Search,Add,Delete;
	Socket socket;
	JScrollPane scrollPane;
	protected int originX;
	protected int originY;
	private JButton Maximized;
	private JButton Closed;
	private JButton Minimized;
	int index = 0;
	private JTable bookInforList;
	private JLabel lblNewLabel_1;
	/**
	 * 用于初始化需要使用的socket和初始显示
	 * @param socket1 用于通信的socket
	 */
	public ClientLibraryModifyFrame(Socket socket1)
	{
		socket = socket1;
		
		String [] seOp = {"全部","书名","书号"};
		setUndecorated(true);//去边框
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
		
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(167, 85, 0, 0);
		getContentPane().add(scrollPane);
		bookInforList = new JTable();
		bookInforList.setModel(new DefaultTableModel(
			new String[][] {{}},
			new String[] {}
		));

		Range = new JComboBox(seOp);
		Range.setBackground(new Color(255, 255, 255));
		Range.setFont(new Font("宋体", Font.PLAIN, 20));
		Range.setBounds(27, 85, 80, 30);
		getContentPane().add(Range);
		Range.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "\u5355\u672C"}));
		Search = new JButton("");
		Search.setIcon(new ImageIcon(ClientLibraryModifyFrame.class.getResource("/image/lib_button_show.png")));
		Search.setFont(new Font("宋体", Font.PLAIN, 20));
		Search.setBounds(27, 154, 80, 30);
		getContentPane().add(Search);
		Search.addActionListener(this);
		Search.setActionCommand("Search");
		bookID = new JTextField(20);
		bookID.setOpaque(false);
		bookID.setBounds(17, 400, 100, 25);
		getContentPane().add(bookID);
		
		JLabel lblNewLabel = new JLabel("\u4E66\u672CID");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(30, 343, 65, 37);
		getContentPane().add(lblNewLabel);
		Add =new JButton("");
		Add.setIcon(new ImageIcon(ClientLibraryModifyFrame.class.getResource("/image/lib_button_addBook.png")));
		Add.setFont(new Font("宋体", Font.PLAIN, 20));
		Add.setBounds(27, 222, 80, 30);
		getContentPane().add(Add);
		Add.addActionListener(this);
		Add.setActionCommand("Add");
		Delete =new JButton("");
		Delete.setIcon(new ImageIcon(ClientLibraryModifyFrame.class.getResource("/image/lib_button_deleteBook.png")));
		Delete.setFont(new Font("宋体", Font.PLAIN, 20));
		Delete.setBounds(27, 288, 80, 30);
		getContentPane().add(Delete);
		Delete.addActionListener(this);
		Delete.setActionCommand("Delete");
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ClientLibraryModifyFrame.class.getResource("/image/lib_bg_manager.jpg")));
		lblNewLabel_1.setBounds(0, 0, 800, 500);
		getContentPane().add(lblNewLabel_1);
		
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	@Override
	/**
	 * 事件服务程序
	 * @param e 监听到的事件
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Search")) {
			if(Range.getSelectedItem().equals("全部")) {
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_SHOW_ALL_BOOK");
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				
				Vector<String>allBookInfor = clientReq.getContent();
				int rowAmount = allBookInfor.size()/4;
				String[][] allBookTable = new String[rowAmount][4];
				int stroingPlace = 0;
				for(int i=0;i<rowAmount;i++) {
					for(int j=0;j<4;j++) {
						allBookTable[i][j] = allBookInfor.get(stroingPlace++);
					}
				}
				scrollPane.setBounds(167, 85, 610, 340 < rowAmount * 17 + 21 ? 340 : rowAmount * 17 + 21 );
				bookInforList = new JTable();
				bookInforList.setModel(new DefaultTableModel(
					allBookTable,
					new String[] {
						"\u4E66\u672CID", "\u4E66\u672C\u540D\u79F0", "\u4E66\u672C\u53EF\u501F\u9605\u6570", "\u4E66\u672C\u603B\u91CF"
					}
				));
				scrollPane.setViewportView(bookInforList);
			} else if(Range.getSelectedItem().equals("单本")) {
				if(bookID.getText().length()==0) JOptionPane.showMessageDialog(null,"请输入书本ID！","错误",JOptionPane.ERROR_MESSAGE);
				else {
					ClientReq clientReq = new ClientReq();
					Vector<String> content = new Vector<String>();
					content.add(bookID.getText());
					clientReq.setContent(content);
					clientReq.setType("REQ_SHOW_SIG_BOOK");
					try {
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
						ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
						clientReq = (ClientReq) ois.readObject();
					} catch(Exception e1) {
						e1.printStackTrace();
					}
					
					Vector<String>allBookInfor = clientReq.getContent();
					int rowAmount = allBookInfor.size()/4;
					String[][] allBookTable = new String[rowAmount][4];
					int stroingPlace = 0;
					for(int i=0;i<rowAmount;i++) {
						for(int j=0;j<4;j++) {
							allBookTable[i][j] = allBookInfor.get(stroingPlace++);
						}
					}
					scrollPane.setBounds(167, 85, 610, 340 < rowAmount * 17 + 21 ? 340 : rowAmount * 17 + 21 );
					bookInforList = new JTable();
					bookInforList.setModel(new DefaultTableModel(
						allBookTable,
						new String[] {
							"\u4E66\u672CID", "\u4E66\u672C\u540D\u79F0", "\u4E66\u672C\u53EF\u501F\u9605\u6570", "\u4E66\u672C\u603B\u91CF"
						}
					));
					scrollPane.setViewportView(bookInforList);
				}
			}
		} else if(e.getActionCommand().equals("Delete")) {
			if(bookID.getText().length()==0) JOptionPane.showMessageDialog(null,"请输入书本ID！","错误",JOptionPane.ERROR_MESSAGE);
			else {
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_REMOVE_BOOK");
				Vector<String> content = new Vector<String>();
				content.add(bookID.getText());
				clientReq.setContent(content);
				try {
					ObjectOutputStream oos =  new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
				} catch(Exception e3) {
					e3.printStackTrace();
				}
				clientReq.setType("REQ_SHOW_ALL_BOOK");
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				} catch(Exception e4) {
					e4.printStackTrace();
				}
				
				Vector<String>allBookInfor = clientReq.getContent();
				int rowAmount = allBookInfor.size()/4;
				String[][] allBookTable = new String[rowAmount][4];
				int stroingPlace = 0;
				for(int i=0;i<rowAmount;i++) {
					for(int j=0;j<4;j++) {
						allBookTable[i][j] = allBookInfor.get(stroingPlace++);
					}
				}
				scrollPane.setBounds(167, 85, 610, 340 < rowAmount * 17 + 21 ? 340 : rowAmount * 17 + 21 );
				bookInforList = new JTable();
				bookInforList.setModel(new DefaultTableModel(
					allBookTable,
					new String[] {
						"\u4E66\u672CID", "\u4E66\u672C\u540D\u79F0", "\u4E66\u672C\u53EF\u501F\u9605\u6570", "\u4E66\u672C\u603B\u91CF"
					}
				));
				scrollPane.setViewportView(bookInforList);
			}
		} else if(e.getActionCommand().equals("Add")) {
			//this.setVisible(false);
			scrollPane.setBounds(167, 85, 0, 0);
			//this.setVisible(true);
			BookInfor bf = new BookInfor(socket);
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
}

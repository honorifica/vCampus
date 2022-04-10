package vss.client.view;
import java.awt.Point;
//ѧ����Ϣ�������
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
import java.sql.SQLException;
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
 * 
*  ClientStudentManegerFrame 
*   (��ʦ����ѧ������Ľ���) 
* @author WGH
*  2021��7��28�� ����2:41:24 
*
 */
public class ClientStudentManegerFrame extends JFrame implements ActionListener{
	JTable jt;
	JComboBox Range;
	JTextField studentID;
	JButton Show,Add,Delete;
	Socket socket;
	JScrollPane scrollPane;
	protected int originX;
	protected int originY;
	private JButton Maximized;
	private JButton Closed;
	private JButton Minimized;
	int index = 0;
	private JTable studentInforList;
	private JLabel lblNewLabel_1;
	private String number;
	
	/**
	 * 
	 *  ClientStudentManegerFrame
	 *   ClientStudentManegerFrame���캯��
	 * @param ID ��ʦ��Ӧ��ID
	 * @param socket1 ����ͨ�ŵ�socket
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 * @author WGH
	 *  2021-07-28 02:41:49
	 */
	public ClientStudentManegerFrame(String ID,Socket socket1)throws ClassNotFoundException, SQLException,IOException
	{
		socket = socket1;
		number=ID;
		String [] seOp = {"ȫ��","ѧ����","ѧ����"};
		setUndecorated(true);//ȥ�߿�
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
		border.add(Maximized);
		border.add(Closed);
		border.add(Minimized);
		
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(167, 85, 0, 0);
		getContentPane().add(scrollPane);
		studentInforList = new JTable();
		studentInforList.setModel(new DefaultTableModel(
			new String[][] {{}},
			new String[] {}
		));

		Range = new JComboBox(seOp);
		Range.setBackground(new Color(255, 255, 255));
		Range.setFont(new Font("����", Font.PLAIN, 20));
		Range.setBounds(27, 85, 80, 30);
		getContentPane().add(Range);
		Range.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "\u5355\u4EBA"}));
		Show = new JButton("");
		Show.setIcon(new ImageIcon(ClientStudentManegerFrame.class.getResource("/image/lib_button_show.png")));
		Show.setFont(new Font("����", Font.PLAIN, 20));
		Show.setBounds(27, 154, 80, 30);
		getContentPane().add(Show);
		Show.addActionListener(this);
		Show.setActionCommand("Show");
		studentID = new JTextField(20);
		studentID.setBounds(17, 400, 100, 25);
		getContentPane().add(studentID);
		
		JLabel stuNewLabel = new JLabel("\u5B66\u751FID");
		stuNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		stuNewLabel.setBounds(30, 343, 65, 37);
		getContentPane().add(stuNewLabel);
		Add =new JButton("");
		Add.setIcon(new ImageIcon(ClientStudentManegerFrame.class.getResource("/image/stu_button_addstudent.png")));
		Add.setFont(new Font("����", Font.PLAIN, 20));
		Add.setBounds(27, 222, 80, 30);
		getContentPane().add(Add);
		Add.addActionListener(this);
		Add.setActionCommand("Add");
		Delete =new JButton("");
		Delete.setIcon(new ImageIcon(ClientStudentManegerFrame.class.getResource("/image/stu_button_deletestudent.png")));
		Delete.setFont(new Font("����", Font.PLAIN, 20));
		Delete.setBounds(27, 288, 80, 30);
		getContentPane().add(Delete);
		Delete.addActionListener(this);
		Delete.setActionCommand("Delete");
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ClientStudentManegerFrame.class.getResource("/image/stu_bg_manager.png")));
		lblNewLabel_1.setBounds(0, 0, 800, 500);
		getContentPane().add(lblNewLabel_1);
		
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	@Override
	/**
	 * �����������
	 * @param e ���������¼�
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Show")) {
			if(Range.getSelectedItem().equals("ȫ��")) {
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_SHOW_ALL_INFOR");
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				
				Vector<String>allStudentInfor = clientReq.getContent();
				int rowAmount = allStudentInfor.size()/9;
				String[][] allStudentTable = new String[rowAmount][9];
				
				int stroingPlace = 0;
				int index=0;
				for(int i=0;i<rowAmount;i++) 
				{
					//System.out.print(allStudentInfor.get(9*i+7)+"\n");
					if(allStudentInfor.get(9*i+7).equals("3")==false)
					{
					  for(int j=0;j<9;j++) 
					    {
						allStudentTable[index][j] = allStudentInfor.get(stroingPlace++);
						//System.out.print(allStudentTable[index][j]+" ");
				        }
					  System.out.print("\n");
					  index++;
					}
					else {
						stroingPlace=stroingPlace+9;
					}
					
				}
				scrollPane.setBounds(167, 85, 610, 340 < rowAmount * 17 + 21 ? 340 : rowAmount * 17 + 21 );
				studentInforList = new JTable();
				studentInforList.setModel(new DefaultTableModel(
					allStudentTable,
					new String[]{
						"ID", "����", "����", "�Ա�","�꼶","רҵ","����","���","���"}
				));
				scrollPane.setViewportView(studentInforList);
				
			} else if(Range.getSelectedItem().equals("����")) {
				if(studentID.getText().length()==0) JOptionPane.showMessageDialog(null,"������ID��","����",JOptionPane.ERROR_MESSAGE);
				else {
					ClientReq clientReq = new ClientReq();
					Vector<String> content = new Vector<String>();
					content.add(studentID.getText());
					clientReq.setContent(content);
					clientReq.setType("REQ_SHOW_SIG_INFOR");
					try {
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(clientReq);
						oos.flush();
						ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
						clientReq = (ClientReq) ois.readObject();
					} catch(Exception e1) {
						e1.printStackTrace();
					}
					
					Vector<String>allStudentInfor = clientReq.getContent();
					int rowAmount = allStudentInfor.size()/9;
					String[][] allStudentTable = new String[rowAmount][9];
					int stroingPlace = 0;
					for(int i=0;i<rowAmount;i++) {
						for(int j=0;j<9;j++) {
							allStudentTable[i][j] = allStudentInfor.get(stroingPlace++);
						}
					}
					scrollPane.setBounds(167, 85, 610, 340 < rowAmount * 17 + 21 ? 340 : rowAmount * 17 + 21 );
					studentInforList = new JTable();
					studentInforList.setModel(new DefaultTableModel(
						allStudentTable,
						new String[] {
								"ѧ��ID", "ѧ������", "����", "�Ա�","�꼶","רҵ","����","���","���"
						}
					));
					scrollPane.setViewportView(studentInforList);
				}
			}
		} else if(e.getActionCommand().equals("Delete")) {
			if(studentID.getText().length()==0) JOptionPane.showMessageDialog(null,"������ID��","����",JOptionPane.ERROR_MESSAGE);
			else {
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_REMOVE_SIG_INFOR");
				Vector<String> content = new Vector<String>();
				content.add(studentID.getText());
				clientReq.setContent(content);
				try {
					ObjectOutputStream oos =  new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
				} catch(Exception e3) {
					e3.printStackTrace();
				}
				clientReq.setType("REQ_SHOW_ALL_INFOR");
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					clientReq = (ClientReq) ois.readObject();
				} catch(Exception e4) {
					e4.printStackTrace();
				}
				
				Vector<String>allStudentInfor = clientReq.getContent();
				int rowAmount = allStudentInfor.size()/9;
				String[][] allStudentTable = new String[rowAmount][9];
				int stroingPlace = 0;
				for(int i=0;i<rowAmount;i++) {
					for(int j=0;j<9;j++) {
						allStudentTable[i][j] = allStudentInfor.get(stroingPlace++);
					}
				}
				scrollPane.setBounds(167, 85, 610, 340 < rowAmount * 17 + 21 ? 340 : rowAmount * 17 + 21 );
				studentInforList = new JTable();
				studentInforList.setModel(new DefaultTableModel(
					allStudentTable,
					new String[] {
							"ѧ��ID", "ѧ������", "����", "�Ա�","�꼶","רҵ","����","���","���"
					}
				));
				scrollPane.setViewportView(studentInforList);
			}
		} else if(e.getActionCommand().equals("Add")) {
			//this.setVisible(false);
			scrollPane.setBounds(167, 85, 0, 0);
			//this.setVisible(true);
			StuInfor sif = new StuInfor(number,socket);
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
	private void Systemprint(String string) {
		//  Auto-generated method stub
		
	}
}

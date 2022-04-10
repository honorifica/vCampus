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

import vss.common.Course;
import vss.common.ClientReq;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
/**
 * 
*  CourseInfor 
*  (老师添加课程时显示的界面) 
* @author WGH
*  2021年7月28日 下午2:50:00 
*
 */
public class CourseInfor extends JFrame implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField courseID;
	private JTextField courseName;
	private JTextField courseHour;
	private JTextField courseMax;
	private Socket socket;
	protected int originX;
	protected int originY;
	private JButton Maximized;
	private JButton Closed;
	private JButton Minimized;
	private JLabel lblNewLabel_4;
	private String UserID;
	/**
	 * Create the dialog.
	 */
	/**
	 * 
	 *  CourseInfor
	 *  CourseInfor构造函数
	 * @param ID 添加课程老师的ID
	 * @param intiSocket 用于通信的socket
	 * @author WGH
	 *  2021-07-28 02:50:22
	 */
	public  CourseInfor(String ID,Socket intiSocket){
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
		
		courseID = new JTextField();
		courseID.setOpaque(false);
		courseID.setBounds(183, 106, 100, 25);
		contentPanel.add(courseID);
		courseID.setColumns(10);
		
		courseName = new JTextField();
		courseName.setOpaque(false);
		courseName.setBounds(183, 176, 100, 25);
		contentPanel.add(courseName);
		courseName.setColumns(10);
		
		courseHour = new JTextField();
		courseHour.setOpaque(false);
		courseHour.setBounds(183, 248, 100, 25);
		contentPanel.add(courseHour);
		courseHour.setColumns(10);
		
		courseMax = new JTextField();
		courseMax.setOpaque(false);
		courseMax.setBounds(183, 321, 100, 25);
		contentPanel.add(courseMax);
		courseMax.setColumns(10);
		
		JLabel couNewLabel = new JLabel("\u8BFE\u7A0BID");
		couNewLabel.setBounds(65, 106, 72, 18);
		couNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPanel.add(couNewLabel);
		
		JLabel couNewLabel_1 = new JLabel("\u8BFE\u7A0B\u540D\u79F0");
		couNewLabel_1.setBounds(59, 173, 86, 24);
		couNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPanel.add(couNewLabel_1);
		
		JLabel couNewLabel_2 = new JLabel("\u8BFE\u7A0B\u8BFE\u65F6");
		couNewLabel_2.setBounds(59, 245, 86, 24);
		couNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPanel.add(couNewLabel_2);
		
		JLabel couNewLabel_3 = new JLabel("\u6700\u5927\u603B\u91CF");
		couNewLabel_3.setBounds(59, 315, 86, 30);
		couNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPanel.add(couNewLabel_3);
		{
			JButton Confirm = new JButton("");
			Confirm.setIcon(new ImageIcon(BookInfor.class.getResource("/image/lib_button_addConfirm.png")));
			Confirm.setBounds(237, 414, 80, 30);
			contentPanel.add(Confirm);
			Confirm.addActionListener(this);
			Confirm.setActionCommand("Confirm");
		}
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(BookInfor.class.getResource("/image/cou_courseInfor_bg.png")));
		lblNewLabel_4.setBounds(0, 0, 350, 500);
		contentPanel.add(lblNewLabel_4);
		
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	/**
	 * 事务监听程序
	 * @param e 用来监听事件
	 */
	public void actionPerformed(ActionEvent e) {
		int courseHoursInt;
		int courseAmountInt;
		int idSize = courseID.getText().length();
		int nameSize = courseName.getText().length();
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
			
			if(courseHour.getText().length()==0) {
				JOptionPane.showMessageDialog(null,"请输入课程课时","错误",JOptionPane.ERROR_MESSAGE);
				return;
			} else if(courseMax.getText().length()==0) {
				JOptionPane.showMessageDialog(null,"请输入最大总量","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			courseHoursInt = Integer.valueOf(courseHour.getText());
			courseAmountInt = Integer.valueOf(courseMax.getText());
			if(courseAmountInt < 0) {
					JOptionPane.showMessageDialog(null,"非法输入，最大容量应大于0","错误",JOptionPane.ERROR_MESSAGE);
			} else if(courseHoursInt < 0) {
				    JOptionPane.showMessageDialog(null,"非法输入，课程课时应大于0","错误",JOptionPane.ERROR_MESSAGE);
			}else if(courseHoursInt*courseAmountInt*idSize*nameSize==0) {
				JOptionPane.showMessageDialog(null,"请完善课程相关信息！","错误",JOptionPane.ERROR_MESSAGE);
			} else {	
				ClientReq clientReq = new ClientReq();
				clientReq.setType("REQ_ADD_LESSON");
				Course course = new Course();
				course.setCourseID(courseID.getText());
				course.setCourseName(courseName.getText());
			    course.setCourseHours(courseHour.getText());
				course.setCMaxnum(courseMax.getText());
				course.setCNownum("0");
				clientReq.setContent(course.getContent());
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
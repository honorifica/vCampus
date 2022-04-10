package vss.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.net.Socket;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Component;
/**
 * 
*  ClientStudentFrame 
*   (ѧ����¼ʱ��ʾ�Ľ���) 
* @author WGH
*  2021��7��28�� ����12:36:38 
*
 */
public class ClientStudentFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField text;
	private Socket socket;//����ͨ��
	private String number;
	private JButton Maximized;
	private JButton Closed;
	private JButton Minimized;
	protected int originX;
	protected int originY;
	
	JPanel body = new JPanel();
	JPanel foot = new JPanel();
	JPanel Clock = new JPanel();

	Calendar calendar=Calendar.getInstance();
    int dayNow=calendar.get(Calendar.DATE);
    int monthNow=calendar.get(Calendar.MONTH)+1;
    int yearNow=calendar.get(Calendar.YEAR);
    int year = calendar.get(Calendar.YEAR);//��ȡ��ǰ��ѯ��ݣ�Ĭ��Ϊ��ǰ���
    int month = calendar.get(Calendar.MONTH) + 1;//��ȡ��ǰ��ѯ�·ݣ�Ĭ��Ϊ��ǰ�·�


    /**
     * 
     *  ClientStudentFrame
     *   ClientStudentFrame���캯��
     * @param number ѧ��������
     * @author WGH
     *  2021-07-28 12:37:19
     */
	public ClientStudentFrame(String number) 
	{
		setUndecorated( true);//ȥ�߿�
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//JPanel Clock = new JPanel();
		Clock.setBounds(600, 35, 223, 201);
		Clock.setOpaque(false);
		contentPane.add(Clock);
		Clock.setLayout(null);
		
		JPanel border = new JPanel();
		border.setBounds(0, 0, 1000, 40);
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
		 Maximized.setBounds(944, 5, 18, 18);
		 Maximized.setContentAreaFilled(false);
		 Maximized.setBorder(null);
		 Maximized.addActionListener(this);
			    
		JLabel lblNewLabel_1 = new JLabel("��ӭ����"+number);
		lblNewLabel_1.setBounds(10, 10, 205, 20);
		border.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		Maximized.setActionCommand("Maximized");
		border.add(Maximized);
			    
		Closed = new JButton("");
			    
		Closed.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login6_1.png")));
		Closed.setContentAreaFilled(false);
		Closed.setBorder(null);
		Closed.setBounds(972, 5, 18, 18);
		Closed.addActionListener(this);
		Closed.setActionCommand("Closed");
		border.add(Closed);
			    				    		
		Minimized = new JButton("");
		Minimized.setIcon(new ImageIcon(ClientLoginFrame.class.getResource("/image/login7_1.png")));
		Minimized.setContentAreaFilled(false);
		Minimized.setBorder(null);
		Minimized.setBounds(916, 5, 18, 18);
		Minimized.addActionListener(this);
		Minimized.setActionCommand("Minimized");
		border.add(Minimized);
		
		JPanel Function = new JPanel();
		Function.setBounds(10, 19, 173, 488);
		Function.setOpaque(false);
		contentPane.add(Function);
		Function.setLayout(null);
		
		
		
		JButton Stu_infoButton = new JButton("\u5B66\u7C4D\u4FE1\u606F");
		Stu_infoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ClientStuinfo csi = new ClientStuinfo(socket, number);
				} catch(Exception e2) {
					e2.printStackTrace();
				
				}
			}
		});
		Stu_infoButton.setForeground(Color.WHITE);
		Stu_infoButton.setFont(new Font("΢���ź�", Font.BOLD, 16));
		Stu_infoButton.setBorder(null);
		Stu_infoButton.setBackground(new Color(36, 68, 88));
		Stu_infoButton.setBounds(10, 114, 96, 36);
		Function.add(Stu_infoButton);
		
		JButton Stu_courseButton = new JButton("\u9009\u8BFE\u6982\u51B5");
		Stu_courseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ClientStuCourseFrame cscf= new ClientStuCourseFrame(number, socket);
				} catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		Stu_courseButton.setForeground(Color.WHITE);
		Stu_courseButton.setFont(new Font("΢���ź�", Font.BOLD, 16));
		Stu_courseButton.setBorder(null);
		Stu_courseButton.setBackground(new Color(36, 68, 88));
		Stu_courseButton.setBounds(10, 162, 96, 36);
		Function.add(Stu_courseButton);
		
		JButton Stu_shopButton = new JButton("\u8D2D\u7269");
		Stu_shopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ClientShopFrame csf =new ClientShopFrame(socket, number);
					csf.socket = socket;
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Stu_shopButton.setForeground(Color.WHITE);
		Stu_shopButton.setFont(new Font("΢���ź�", Font.BOLD, 16));
		Stu_shopButton.setBorder(null);
		Stu_shopButton.setBackground(new Color(36, 68, 88));
		Stu_shopButton.setBounds(10, 208, 96, 36);
		Function.add(Stu_shopButton);
		
		JButton Stu_libraryButton = new JButton("\u56FE\u4E66\u9986");
		Stu_libraryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ClientLibraryBR clbr = new ClientLibraryBR(socket);
					clbr.setSocket(socket);
					clbr.setUserID(number);
				} catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		Stu_libraryButton.setForeground(Color.WHITE);
		Stu_libraryButton.setFont(new Font("΢���ź�", Font.BOLD, 16));
		Stu_libraryButton.setBorder(null);
		Stu_libraryButton.setBackground(new Color(36, 68, 88));
		Stu_libraryButton.setBounds(10, 254, 96, 36);
		Function.add(Stu_libraryButton);
		
		JLabel back_icon1 = new JLabel("");
		back_icon1.setIcon(new ImageIcon(ClientStudentFrame.class.getResource("/image/SEU_logoW_1.png")));
		back_icon1.setBounds(237, 99, 211, 149);
		contentPane.add(back_icon1);
		
		JLabel back_icon2 = new JLabel("");
		back_icon2.setIcon(new ImageIcon(ClientStudentFrame.class.getResource("/image/bg6_1.jpg")));
		back_icon2.setBounds(189, 99, 274, 245);
		contentPane.add(back_icon2);
   

		JPanel CalendarPart = new JPanel();
		CalendarPart.setBounds(497, 240, 425, 267);
		contentPane.add(CalendarPart);
		CalendarPart.setLayout(null);
		CalendarPart.setOpaque(false);
		
		JPanel head = new JPanel();
		head.setBounds(0, 0, 425, 38);
		CalendarPart.add(head);
		head.setLayout(null);
		head.setOpaque(false);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u5E74\u4EFD\uFF1A");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel.setBounds(21, 10, 112, 18);
		head.add(lblNewLabel);
		
		text = new JTextField();
		text.setBounds(112, 9, 83, 21);
		head.add(text);
		text.setColumns(10);
		text.setOpaque(false);
		
		JButton SearchButton = new JButton("\u67E5\u8BE2");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    year = Integer.parseInt(text.getText());
                    month=1;
                    getDateInfo(String.valueOf(year)+"-"+String.valueOf(month));
                }catch (NumberFormatException e1){
                    System.out.println("�������쳣�ѱ����񣬽���������");
                }
			}
		});
		SearchButton.setFont(new Font("΢���ź�", Font.BOLD, 16));
		SearchButton.setBounds(205, 4, 50, 30);
		SearchButton.setForeground(Color.WHITE);
		SearchButton.setBackground(new Color(36,68,88));
		SearchButton.setBorder(null);
		head.add(SearchButton);
		
		JButton UpButton = new JButton("\u4E0A\u6708");
		UpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (month==1){
                    year--;
                    month=12;
                }else
                    month--;
                getDateInfo(String.valueOf(year)+"-"+String.valueOf(month));
			}
		});
		UpButton.setForeground(Color.WHITE);
		UpButton.setFont(new Font("΢���ź�", Font.BOLD, 16));
		UpButton.setBorder(null);
		UpButton.setBackground(new Color(36, 68, 88));
		UpButton.setBounds(278, 4, 50, 30);
		head.add(UpButton);
		
		JButton DownButton = new JButton("\u4E0B\u6708");
		DownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (month==12){
                    year++;
                    month=1;
                }else
                    month++;
                getDateInfo(String.valueOf(year)+"-"+String.valueOf(month));
			}
		});
		DownButton.setForeground(Color.WHITE);
		DownButton.setFont(new Font("΢���ź�", Font.BOLD, 16));
		DownButton.setBorder(null);
		DownButton.setBackground(new Color(36, 68, 88));
		DownButton.setBounds(349, 4, 50, 30);
		head.add(DownButton);
		
		//JPanel body = new JPanel();
		body.setBounds(10, 48, 415, 182);
		getDateInfo(String.valueOf(year)+"-"+String.valueOf(month));
		CalendarPart.add(body);
		body.setOpaque(false);
		body.setLayout(new GridLayout(7, 7, 1, 1));
		
		//JPanel foot = new JPanel();
		foot.setBounds(0, 228, 425, 29);
		CalendarPart.add(foot);
		foot.setOpaque(false);
		
		JLabel Background = new JLabel("");
		Background.setSize(1000, 562);
		Background.setIcon(new ImageIcon(ClientStudentFrame.class.getResource("/image/Stu_back.jpg")));
		contentPane.add(Background);
		this.setVisible(true);
		
		/**  
		 * ���߳���ʵ�ֶ�̬ʱ��
		 */
		Thread thread1 = new Thread()
		{
			public void run()
			{
				StillClock Sclock = new StillClock();
            	MessagePanel messagePanel1=new MessagePanel(Sclock.getHour()+":"+
                        Sclock.getMinute()+":"+Sclock.getSecond());
				while(true) 
				{
					    Sclock.setCurrentTime();
					    messagePanel1.setMessage(Sclock.getHour()+":"+
		                        Sclock.getMinute()+":"+Sclock.getSecond());
					    
					    Sclock.setSize(160, 160);
			            messagePanel1.setLocation(20, 160);
			            messagePanel1.setSize(120, 40);
			            Sclock.setOpaque(false);
			            messagePanel1.setOpaque(false);
			            //������ʾ����
			           messagePanel1.setCentered(true);
			           //����ǰ����ɫ
			           messagePanel1.setForeground(Color.black);
			           //��������
			           messagePanel1.setFont(new Font("Courier",Font.BOLD,16));

			          Clock.add(Sclock);
			          Clock.add(messagePanel1,BorderLayout.SOUTH);
			                    
			           Sclock.setVisible(true);
			           messagePanel1.setVisible(true);
			           Clock.validate();        //��������ÿ��һ���ػ�һ��ʱ�ӡ���������frame�н�clock���ɾ��������˵���validate������ʹ�������²����������
			           try {
			                 Thread.sleep(1000);
			                } catch (InterruptedException e) 
			                 {e.printStackTrace();}
			           Sclock.setVisible(false);
			           Clock.remove(Sclock);     //�ڸ������н���ɾ��
			           Sclock.invalidate();        //ʹ����ʧЧ
			           messagePanel1.setVisible(false);
			           Clock.remove(messagePanel1);
			           messagePanel1.invalidate();
			                    
				}
			}
			
		};
		thread1.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,562);
		this.setResizable(false);
		this.setVisible(true);
    	this.setLocationRelativeTo(null);
	}
	
	/**
	 * 
	*  getDateInfo 
	*   (��ȡ������Ϣ) 
	* @param @param date  ������Ϣ 
	* @return void     
	* @throws ParseException
	 */
    private void getDateInfo(String date) {//��ȡ������Ϣ
        
    	try {
            SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM");// ���ڸ�ʽ����
            java.util.Date parse = dFormat.parse(date);// ���ַ������͵�����ת��Ϊdate���͵�
            Calendar calendar = new GregorianCalendar();// ����һ���������ʵ��
            calendar.setTime(parse);// �Ѹ�ʽ���õ����ڶ���Ž�Calendar
            calendar.set(Calendar.DATE, 1);//��������Ϊ��һ��
            // ��ȡ����µĵ�һ�����ܼ�
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            // ��ȡÿ������������
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            body.removeAll();
            body.repaint();

            String[] title = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
            for (String label : title)
            {
                JLabel jLabel = new JLabel(label);
                jLabel.setHorizontalAlignment(JLabel.CENTER);
                jLabel.setForeground(new Color(36, 68, 88));
                jLabel.setBorder(null);
                jLabel.setFont(new Font("Arial", Font.BOLD, 18));
                body.add(jLabel);
                body.revalidate();
            }
            for (int i = 1; i <= 42; i++) {
                if (i >= weekDay && i <= (maxDay + weekDay - 1)) {
                    JLabel jLabel = new JLabel(String.valueOf(i - weekDay + 1));
                    jLabel.setForeground(new Color(90,92,93));
                    jLabel.setFont(new Font("Arial", Font.BOLD, 15));
                    jLabel.setHorizontalAlignment(JLabel.CENTER);
                    if ((year==yearNow)&&(month==monthNow)&&(i - weekDay + 1==dayNow)){
                        
                        //jLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    	jLabel.setForeground(Color.WHITE);
                		jLabel.setOpaque(true);
                		jLabel.setBackground(Color.RED);
                        jLabel.setBorder(null);
                    }
                    body.add(jLabel);
                    body.revalidate();
                } else {
                    JLabel jLabel = new JLabel("");
                    jLabel.setHorizontalAlignment(JLabel.CENTER);
                    jLabel.setFont(new Font("SimHei", Font.BOLD, 15));
                    body.add(jLabel);
                    body.revalidate();
                }
            }
            
            if (year > 0 && year <= 9999) {
                foot.removeAll();
                foot.repaint();
                JLabel show = new JLabel(year + "��" + month + "��");
                show.setFont(new Font("SimHei", Font.BOLD, 20));
                foot.add(show);//����ǩ��ӵ��ϲ�����
                foot.revalidate();
            }
        }catch (ParseException e){
            System.out.println("�����쳣���ѱ����񣬽���������");
        } 
       }

    /**
     * 
    *  getSocket 
    *   (����ͨ�ŵ�socket) 
    * @return Socket ����ͨ�ŵ�socket   
     */
    public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	/**
	 * �¼���������
	 * @param arg0 �����������
	 */
	public void actionPerformed(ActionEvent arg0) {
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
		}
		//  Auto-generated method stub
		
	}

 }


    


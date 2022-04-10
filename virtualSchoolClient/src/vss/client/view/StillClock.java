package vss.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
/**
 * 
*  StillClock 
*  (���Ƴ�ʱ��ÿһ���Ӧ��״̬) 
* @author WGH
*  2021��7��28�� ����12:43:47 
*
 */
public class StillClock extends JPanel{
	
	/**
	 * 
	 *  StillClock
	 *  StillClock���캯��
	 * @param hour ʱ
	 * @param minute ��
	 * @param second ��
	 * @author WGH
	 *  2021-07-28 12:44:48
	 */
    public StillClock(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    /**
     * 
    *  getHour 
    *  (��ȡʱ���Ӧ��ֵ) 
    * @return ʱ���Ӧ��ֵ 
     */
	public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
        repaint();
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        repaint();
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
        repaint();
    }

    private int hour;
    private int minute;
    private int second;

    /**
     * 
     *  StillClock
     *  StillClock���캯��
     * @author WGH
     *  2021-07-28 01:40:18
     */
    public StillClock() {
        setCurrentTime();
    }


    //ʹ��Graphics�����ͼ�Σ���Ҫ��дpaintComponent����
    @Override
    /**
     * ����ʱ��
     */
    protected void paintComponent(Graphics g) {
    	
    	 Graphics2D g2d = (Graphics2D)g;
         AffineTransform old = g2d.getTransform();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//ȥ�����
         
        super.paintComponent(g);
        //����ʱ�Ӳ���
        int clockRadius=(int)(Math.min(getWidth(),getHeight())*0.8*0.5);
        int xCenter=getWidth()/2;
        int yCenter=getHeight()/2;

      
        // ����뾶  
        int radius = (int) (Math.min(this.getWidth(), this.getHeight()) * 0.8 * 0.5);  
        // ��Բ  
        g2d.drawOval(xCenter - radius, yCenter - radius, radius * 2, radius * 2); 
  
 
        //��ʱ�ӵ�12������(�����rotate���������ֻ���б����)
        for (int i = 0; i < 12; i++)  
        {   
                
            double dd = Math.PI / 180 * i * (360 /12); //ÿ��ת360/12��  
            int x = (xCenter-4) + (int)((radius - 12) * Math.cos(dd));  
            int y = (yCenter+4) + (int)((radius - 12) * Math.sin(dd));  
  
            //��˳ʱ��3���ӿ�ʼ��������i����3  
            int j = i + 3;  
            if (j > 12)  
                 j = j - 12;  
            g2d.drawString(Integer.toString(j), x, y);                
       }  
        //��ӿ̶�
        /*
        for (int i = 0; i < 60; i++)  
        {  
            int w = i % 5 == 0 ? 5 : 3;  
            g2d.fillRect(xCenter-2, 28, w, 3); 
            g2d.rotate(Math.toRadians(6), xCenter, yCenter);  
        }*/
        
    
        //��������
        int sLength=(int)(clockRadius*0.8);
        int xSecond=(int)(xCenter+sLength*Math.sin(second*(2*Math.PI/60)));
        int ySecond=(int)(xCenter-sLength*Math.cos(second*(2*Math.PI/60)));
        g.setColor(Color.red);
        g.drawLine(xCenter,yCenter,xSecond,ySecond);
   
        
        //���Ʒ���
        
        double mAngle = (minute + second / 60d) * 360d / 60d;
        g2d.rotate(Math.toRadians(mAngle), xCenter, yCenter); 
        int xmArr[] = { xCenter, xCenter+6, xCenter, xCenter-6 };
        int ymArr[] = { 45, yCenter, yCenter+12, yCenter};  
        g2d.setColor(Color.blue);
        g2d.drawPolygon(xmArr, ymArr, xmArr.length);
        g2d.setTransform(old);

        //����ʱ��

        double hAngle = (hour - 12 + minute / 60d) * 360d / 12d;    
        g2d.rotate(Math.toRadians(hAngle), xCenter, yCenter);    
        int xhArr[] = { xCenter, xCenter+9, xCenter, xCenter-9 };
        int yhArr[] = { 65, yCenter, yCenter+10, yCenter}; 
        g2d.setColor(Color.green);
        g2d.drawPolygon(xhArr, yhArr, xhArr.length);
        g2d.setTransform(old);
  

        }
    /**
     * 
    *  setCurrentTime 
    *  (�õ���ǰʱ��) 
     */
    public void setCurrentTime(){
        //����һ���������趨��ǰ���ں�ʱ��
        Calendar calendar=new GregorianCalendar();

        //�趨ʱ����
        this.hour=calendar.get(Calendar.HOUR_OF_DAY);
        this.minute=calendar.get(Calendar.MINUTE);
        this.second=calendar.get(Calendar.SECOND);
    }

    public Dimension getPreferredSize(){
        return new Dimension(200,200);
    }


}

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
*  (绘制出时钟每一秒对应的状态) 
* @author WGH
*  2021年7月28日 上午12:43:47 
*
 */
public class StillClock extends JPanel{
	
	/**
	 * 
	 *  StillClock
	 *  StillClock构造函数
	 * @param hour 时
	 * @param minute 分
	 * @param second 秒
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
    *  (获取时针对应的值) 
    * @return 时针对应的值 
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
     *  StillClock构造函数
     * @author WGH
     *  2021-07-28 01:40:18
     */
    public StillClock() {
        setCurrentTime();
    }


    //使用Graphics类绘制图形，需要重写paintComponent方法
    @Override
    /**
     * 绘制时钟
     */
    protected void paintComponent(Graphics g) {
    	
    	 Graphics2D g2d = (Graphics2D)g;
         AffineTransform old = g2d.getTransform();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//去掉锯齿
         
        super.paintComponent(g);
        //绘制时钟参数
        int clockRadius=(int)(Math.min(getWidth(),getHeight())*0.8*0.5);
        int xCenter=getWidth()/2;
        int yCenter=getHeight()/2;

      
        // 计算半径  
        int radius = (int) (Math.min(this.getWidth(), this.getHeight()) * 0.8 * 0.5);  
        // 画圆  
        g2d.drawOval(xCenter - radius, yCenter - radius, radius * 2, radius * 2); 
  
 
        //画时钟的12个数字(如果用rotate方法则数字会倾斜倒立)
        for (int i = 0; i < 12; i++)  
        {   
                
            double dd = Math.PI / 180 * i * (360 /12); //每次转360/12度  
            int x = (xCenter-4) + (int)((radius - 12) * Math.cos(dd));  
            int y = (yCenter+4) + (int)((radius - 12) * Math.sin(dd));  
  
            //从顺时钟3点钟开始画，索引i加上3  
            int j = i + 3;  
            if (j > 12)  
                 j = j - 12;  
            g2d.drawString(Integer.toString(j), x, y);                
       }  
        //添加刻度
        /*
        for (int i = 0; i < 60; i++)  
        {  
            int w = i % 5 == 0 ? 5 : 3;  
            g2d.fillRect(xCenter-2, 28, w, 3); 
            g2d.rotate(Math.toRadians(6), xCenter, yCenter);  
        }*/
        
    
        //绘制秒针
        int sLength=(int)(clockRadius*0.8);
        int xSecond=(int)(xCenter+sLength*Math.sin(second*(2*Math.PI/60)));
        int ySecond=(int)(xCenter-sLength*Math.cos(second*(2*Math.PI/60)));
        g.setColor(Color.red);
        g.drawLine(xCenter,yCenter,xSecond,ySecond);
   
        
        //绘制分针
        
        double mAngle = (minute + second / 60d) * 360d / 60d;
        g2d.rotate(Math.toRadians(mAngle), xCenter, yCenter); 
        int xmArr[] = { xCenter, xCenter+6, xCenter, xCenter-6 };
        int ymArr[] = { 45, yCenter, yCenter+12, yCenter};  
        g2d.setColor(Color.blue);
        g2d.drawPolygon(xmArr, ymArr, xmArr.length);
        g2d.setTransform(old);

        //绘制时针

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
    *  (得到当前时间) 
     */
    public void setCurrentTime(){
        //构造一个日历类设定当前日期和时间
        Calendar calendar=new GregorianCalendar();

        //设定时分秒
        this.hour=calendar.get(Calendar.HOUR_OF_DAY);
        this.minute=calendar.get(Calendar.MINUTE);
        this.second=calendar.get(Calendar.SECOND);
    }

    public Dimension getPreferredSize(){
        return new Dimension(200,200);
    }


}

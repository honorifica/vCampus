package vss.client.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MessagePanel extends JPanel{

	//��ʾ����Ϣ
    private String message="Welcome to java";
    //��ʾ��Ϣx������
    private int xCoordinate=20;
    //��ʾ��Ϣy������
    private int yCoordinate=20;
    //��Ϣ�Ƿ���ʾ�����Ĳ�λ
    private boolean centered;
    //ˮƽ�ʹ�ֱ���ƶ���ʾ��Ϣ
    private int interval=10;

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
        repaint();
    }

    //�޲ι���
    public MessagePanel() {
    	
    }
    //���ι���
    public MessagePanel(String message) {
        this.setMessage(message);
        repaint();

    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
        repaint();

    }

    public boolean isCentered() {
        return centered;

    }

    public void setCentered(boolean centered) {
        this.centered = centered;
        repaint();

    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(centered){
            //�趨����
            FontMetrics fm=g.getFontMetrics();
            //������ʾ����
            int stringWidth=fm.stringWidth(getMessage());
            int stringAscent=fm.getAscent();
            xCoordinate=getWidth()/2-stringWidth/2;
            yCoordinate=getHeight()/2+stringAscent/2;
        }
        g.drawString(getMessage(),xCoordinate,yCoordinate);
    }
    //����Ϣ������ƶ�
    public void moveLeft(){
        xCoordinate-=interval;
        repaint();
}
    //����Ϣ���ұ��ƶ�
    public void moveRight(){
        xCoordinate+=interval;
        repaint();
    }
    //����Ϣ�����ƶ�
    public void moveUp(){
        yCoordinate+=interval;
        repaint();
    }
    public void moveDown(){
        yCoordinate-=interval;
        repaint();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200,30);
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}

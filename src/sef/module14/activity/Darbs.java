package sef.module14.activity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Darbs extends JFrame{
private JPanel topLeft, topRight, top, bot;
private JSlider red, green, blue;
private JLabel rLabel, gLabel, bLabel, botLabel;
private int redV, greenV, blueV;
private JTextField redT, greenT, blueT;


	public Darbs() {

		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		top = new JPanel();
		top.setLayout(new GridLayout(1,2));
		red = new JSlider();
		red.setMaximum(255);
		red.setMinimum(0);
		green = new JSlider();
		green.setMaximum(255);
		green.setMinimum(0);
		blue = new JSlider();
		blue.setMaximum(255);
		blue.setMinimum(0);
		red.setValue(0);
		green.setValue(0);
		blue.setValue(0);
		
		red.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				redV = red.getValue();
				
				redT.setText(String.valueOf(redV)); 
				bot.setBackground(new Color(redV, greenV, blueV));
				// TODO Auto-generated method stub
				
			}});
		green.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				greenV = green.getValue();
				greenT.setText(String.valueOf(greenV)); 
				bot.setBackground(new Color(redV, greenV, blueV));
				// TODO Auto-generated method stub
				
			}});
		blue.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				blueV = blue.getValue();
				blueT.setText(String.valueOf(blueV)); 
				bot.setBackground(new Color(redV, greenV, blueV));
				// TODO Auto-generated method stub
				
			}});
		
		
		rLabel = new JLabel("Red value: ");
		gLabel = new JLabel("Green value: ");
		bLabel = new JLabel("Blue value: ");
		redT = new JTextField("0");
		greenT = new JTextField("0");
		blueT = new JTextField("0");
		redT.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				
				
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				try{
					redV=Integer.parseInt(redT.getText());
					if(redV>255)
						redV=255;
					if (redV<0)
						redV=0;
				}
				catch(Exception g){
					System.out.println("Incorrect Value");
					if(redT.getText().length()>1)
					redT.setText(redT.getText().substring(0, redT.getText().length()-1));
					else redT.setText("");
				}
				finally{
					redT.setText(String.valueOf(redV));
					red.setValue(redV);}
			}});
		greenT.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				
				
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				try{
					greenV=Integer.parseInt(greenT.getText());
					if(greenV>255)
						greenV=255;
					if (greenV<0)
						greenV=0;
				}
				catch(Exception g){
					System.out.println("Incorrect Value");
					if(greenT.getText().length()>1)
					greenT.setText(greenT.getText().substring(0, greenT.getText().length()-1));
					else greenT.setText("");
				}
				finally{
					greenT.setText(String.valueOf(greenV));
					green.setValue(greenV);}
			}});
		blueT.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				
				
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				try{
					blueV=Integer.parseInt(blueT.getText());
					if(blueV>255)
						blueV=255;
					if (blueV<0)
						blueV=0;
				}
				catch(Exception g){
					System.out.println("Incorrect Value");
					if(blueT.getText().length()>1)
					blueT.setText(blueT.getText().substring(0, blueT.getText().length()-1));
					else blueT.setText("");
				}
				finally{
					blueT.setText(String.valueOf(blueV));
					blue.setValue(blueV);}
			}});
		topLeft= new JPanel();
		topLeft.setLayout(new GridLayout(3,1));
		topLeft.add(red);
		
		topLeft.add(green);
		topLeft.add(blue);
		topRight=new JPanel();
		topRight.setLayout(new GridLayout(3,2));
		topRight.add(rLabel);
		topRight.add(redT);
		topRight.add(gLabel);
		topRight.add(greenT);
		topRight.add(bLabel);
		topRight.add(blueT);
		top.add(topLeft);
		top.add(topRight);
		botLabel=new JLabel("Result");
		bot=new JPanel();
		bot.add(botLabel);
		bot.setPreferredSize(new Dimension(pane.getWidth(), 20));
		pane.add(top, BorderLayout.CENTER);
		pane.add(bot, BorderLayout.SOUTH);
		
		setLocation(720, 450);
		pack();
		
		setVisible(true);
		
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Darbs();

	}

}

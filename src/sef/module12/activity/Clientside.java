package sef.module12.activity;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Clientside extends JFrame {
	static Socket socket;
	JTextArea textAreaInput;
	static JTextArea textAreaOutput;
	public Clientside() {
		
		JLabel inputL, outputL;
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		JPanel textPanel = new JPanel();
		inputL=new JLabel("Enter text");
		outputL=new JLabel("Server messages");
		textAreaInput=new JTextArea();
		textAreaOutput=new JTextArea();
		topPanel.setLayout(new GridLayout(1,2));
		textPanel.setLayout(new GridLayout(1,2));
		topPanel.add(inputL);
		topPanel.add(outputL);
		
		
		

			
		
		textAreaInput.addKeyListener(new KeyListener(){

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
				int key = e.getKeyCode();
			     if (key == e.VK_ENTER) {
			    	 try {System.out.println("called1");
			 			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			    	 out.println(textAreaInput.getText());
			    	 textAreaInput.setText("");
			    	 } catch (IOException ex) {
			 			// TODO Auto-generated catch block
			 			ex.printStackTrace();
			 		} 
			        }
				// TODO Auto-generated method stub
				
			}});
		textPanel.add(textAreaInput);
		textPanel.add(textAreaOutput);
		pane.add(topPanel, BorderLayout.NORTH);
		pane.add(textPanel, BorderLayout.CENTER);
		setLocation(200,300);
		setSize(1200,300);
		setResizable(false);
		setVisible(true);
		// TODO Auto-generated constructor stub
	}
public static void setText(String text){
	
	textAreaOutput.append(text);
	
}
	public static void main(String arg[]) {
		 new Clientside();
		 
		
		BufferedReader inS;
		inS = new BufferedReader(new InputStreamReader(System.in));
		try {
			socket = new Socket(InetAddress.getLocalHost(), 9998);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			Runnable r2 = () -> {
				String text = "";
				BufferedReader in = null;
				try {
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					while ((text = in.readLine()) != null) {
						System.out.println(text+" tika atsutits");
						setText(text);
					}
				} catch (IOException e) {
					e.printStackTrace();

				} finally {
					try {
						System.out.println("finally");
						if (in != null)
							in.close();
						if (out != null)
							out.close();
						if (inS != null)
							inS.close();
						if (socket != null)
							socket.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}

			};
			new Thread(r2).start();

			/*String line = "";
			try {

				while ((line = inS.readLine()) != null) {

					out.println(line);
					if (socket != null) {
						socket.close();
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (socket != null)
					socket.close();
				if (inS != null)
					inS.close();
			}

			out.close();*/

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

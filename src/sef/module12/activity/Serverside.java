package sef.module12.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Serverside {
	private static List<ServerClient> Clients;

	public Serverside() {
	}

	public static void MessageEveryone(String text, Socket socket) {
		for (int i = 0; i < Clients.size(); i++) {
			if (Clients.get(i).getClient() != socket) {
				System.out.println("invoked");
				try {
					PrintWriter out = new PrintWriter(Clients.get(i).getClient().getOutputStream(), true);
					out.println("Client socket " + socket + " wrote: " + text);
				} catch (Exception e) {
				}
			}
		}

	}

	public static void main(String arg[]) {
		ServerSocket server = null;
		Socket client = null;

		boolean endless = true;
		int port = 9998;
		Clients = new ArrayList<ServerClient>();
		try {
			server = new ServerSocket(port, 50, InetAddress.getLocalHost());
			System.out.println("ServerSocket created at " + server.getInetAddress().getHostAddress());
			System.out.println("Waiting for connection");

			while (endless) {
				client = server.accept();

				ServerClient c = new ServerClient(client);
				Thread t = new Thread(c);
				System.out.println("Client added");
				Clients.add(c);
				t.start();

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (client != null)
					client.close();

				if (server != null)
					server.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			// TODO Auto-generated constructor stub
		}

	}
}

class ServerClient implements Runnable {
	BufferedReader in;
	Socket client;

	public ServerClient(Socket client) {
		this.client = client;
		// TODO Auto-generated constructor stub
	}

	public Socket getClient() {
		return client;
	}

	@Override
	public void run() {
		System.out.println("new thread");
		String text = "";
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			while ((text = in.readLine()) != null) {

				Serverside.MessageEveryone(text, client);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (client != null)
					client.close();
				if (in != null)
					in.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}
	// TODO Auto-generated method stub

}

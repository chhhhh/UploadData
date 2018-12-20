/**
 * @author jazy
 *
 */
package com.embedded;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/*建立TCP/IP连接的Server*/
public class Main {

	public static void main(String args[]) throws IOException {
		// 为了简单起见，所有的异常信息都往外抛
		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			Socket client = null;
			boolean flag = true;

			while (flag) {
				System.out.println("服务器已启动，等待客户端请求。。。。");
				client = serverSocket.accept();
				new Thread(new EchoThread(client)).start();
			}
			client.close();
			serverSocket.close();
			System.out.println("服务器已关闭。");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class EchoThread implements Runnable {
	private Socket client;

	public EchoThread(Socket client) {
		this.client = client;
	}

	public void run() {
		try {
			BufferedReader in = null;
			String br = null;
			boolean flag = true;
			while (true == flag) {
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				br = in.readLine();
				if (br != null) {
					InsertDB db = new InsertDB();
					db.insertDB(AnalyzeData.getIdCode(AnalyzeData.hexStringToBtyes(br)),
							AnalyzeData.dataGather(AnalyzeData.hexStringToBtyes(br)));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

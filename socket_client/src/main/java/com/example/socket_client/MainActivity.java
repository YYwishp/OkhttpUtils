package com.example.socket_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
	private Socket socket;
	private DataInputStream reader;
	private DataOutputStream writer;
	private WebSocketClient mWebSocketClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * 建立连接
	 * @param view
	 */
	public void connect(View view) {
		connectWebSocket();
	/*	new Thread() {

			@Override
			public void run() {

				try {

					//socket = new Socket("192.168.0.113", 9999);
					socket = new Socket("api.lifemenu.net", 8382);
					reader = new DataInputStream(socket.getInputStream());
					writer = new DataOutputStream(socket.getOutputStream());

					Log.e("测试", "建立连接：" + socket);

					//输入流
					InputStream is=socket.getInputStream();
					BufferedReader br=new BufferedReader(new InputStreamReader(is));
					//接收服务器的相应
					String reply=null;
					while(!((reply=br.readLine())==null)){
						Log.e("接收服务器的信息：",""+reply);
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();*/

	}

	private void receiveMessage(String message) {
		Log.e("接收消息" , message);
	}

	/**
	 * 发送消息
	 * @param view
	 */
	public void send(View view) {
		new Thread() {
			@Override
			public void run() {

				try {
					// socket.getInputStream()
					DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
					writer.writeUTF("{type: 'login',data: {uid: 103}}");

					Log.e("发送消息" , "发送消息");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/*public void getMessage(final Socket socket) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					DataInputStream reader = new DataInputStream(socket.getInputStream());
					while (true) {
						String s = reader.readUTF();
						Log.e("接收消息" , s);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}*/

	private void connectWebSocket() {
		URI uri;
		try {
			uri = new URI("ws://api.lifemenu.net:8382");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return;
		}
		mWebSocketClient = new WebSocketClient(uri) {
			@Override
			public void onOpen(ServerHandshake serverHandshake) {
				Log.e("Websocket", "打开");
				//mWebSocketClient.send("{type: 'login',data: {uid: 103}}");
			}



			@Override
			public void onMessage(String s) {
				final String message = s;
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Log.e("接收消息" , message);
						if ("{\"type\":\"ping\"}".equals(message)) {
							Log.e("发送消息","1");
							mWebSocketClient.send("1");
						}
					}
				});
			}

			@Override
			public void onClose(int i, String s, boolean b) {
				Log.e("Websocket", "关闭 " + s);
			}

			@Override
			public void onError(Exception e) {
				Log.e("Websocket", "错误 " + e.getMessage());
			}
		};
		mWebSocketClient.connect();


	}
}



























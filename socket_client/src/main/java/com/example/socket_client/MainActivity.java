package com.example.socket_client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.java_websocket.client.DefaultSSLWebSocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
	 *
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
		Log.e("接收消息", message);
	}

	/**
	 * 发送消息
	 *
	 * @param view
	 */
	public void send(View view) {
	/*	new Thread() {
			@Override
			public void run() {
				try {
					// socket.getInputStream()
					DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
					writer.writeUTF("{type: 'login',data: {uid: 103}}");
					Log.e("发送消息", "发送消息");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();*/
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
	//===================================================================================
	//这个方法只能在加密的时候调用ssl，不加密的时候不能调用，否则链接不上
	public void trustAllHosts(WebSocketClient mWebSocketClient) {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[]{};
			}

			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}
		}};
		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			mWebSocketClient.setWebSocketFactory(new DefaultSSLWebSocketClientFactory(sc));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//======================================================================================

	private void connectWebSocket() {
		URI uri;
		try {
			//uri = new URI("ws://api.lifemenu.net:8382");
//			uri = new URI("wss://ssl.91relax.com");
			uri = new URI("ws://ssl.lifemenu.net");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return;
		}
		mWebSocketClient = new WebSocketClient(uri) {
			@Override
			public void onOpen(ServerHandshake serverHandshake) {
				Log.e("Websocket", "打开");
				mWebSocketClient.send("{type: 'login',data: {uid: 103}}");
			}

			@Override
			public void onMessage(String s) {
				final String message = s;
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Log.e("接收消息", message);
						if ("{\"type\":\"ping\"}".equals(message)) {
							Log.e("发送消息", "1");
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
		//trustAllHosts(mWebSocketClient);
		mWebSocketClient.connect();
	}
}



























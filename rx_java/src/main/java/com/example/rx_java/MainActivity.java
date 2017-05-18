package com.example.rx_java;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "测试RxJava";
	private Handler handler;
	Runnable updateThread = new Runnable() {
		@Override
		public void run() {
			//Log.e(TAG, "线程名字 : " + Thread.currentThread().getName());
			handler.postDelayed(this, 1000);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*Observable.create(new ObservableOnSubscribe<Object>() {
			*//*
			 *
			 * @param emitter 是发射器的意思
			 * @throws Exception
			 *//*
			@Override
			public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
				emitter.onNext(1);
				emitter.onNext(2);
				emitter.onNext(3);
				emitter.onComplete();

			}
		}).subscribe(new Observer<Object>() {

			private int i;
			private Disposable mDisposable;
			*//*
			 *  我们可以把它理解成两根管道之间的一个机关, 当调用它的dispose()方法时, 它就会将两根管道切断, 从而导致下游收不到事件
			 * @param disposable
			 *//*
			@Override
			public void onSubscribe(Disposable disposable) {
				mDisposable = disposable;
				Log.e(TAG, "onSubscribe");
			}

			@Override
			public void onNext(Object value) {
				Log.e(TAG, "onNext: " + value);
				i++;
				if (i == 2) {
					Log.e(TAG, "dispose");
					mDisposable.dispose();
					Log.e(TAG, "isDisposed : " + mDisposable.isDisposed());
				}
			}

			@Override
			public void onError(Throwable e) {
				Log.e(TAG, "error");
			}

			@Override
			public void onComplete() {
				Log.e(TAG, "complete");
			}
		});*/
	/*	String str = "100.00";
		int posDot = str.indexOf(".");
		Toast.makeText(this, "点是第几位"+posDot+"长度是"+str.length(), Toast.LENGTH_LONG).show();*/
//		doRxJava();
		doRxjava2();
		handler = new Handler(new Handler.Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				String obj = (String) msg.obj;
				Toast.makeText(MainActivity.this, obj, Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		Message message = new Message();
		message.obj = "消息。。。。";
		//handler.sendMessage(message);
		//handler.post(updateThread);



		//
		HandlerThread handlerThread = new HandlerThread("handler_thread");
		//在使用HandlerThread的getLooper()方法之前，必须先调用该类的start()，同时开启一个新线程;
		handlerThread.start();
		final Handler myHandler =new Handler(handlerThread.getLooper()){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				Log.e(TAG, "接收消息，当前线程是---" + Thread.currentThread().getName());
			}
		};

		new Thread(new Runnable() {
			@Override
			public void run() {
				Log.e(TAG, "发送消息，当前线程是---" + Thread.currentThread().getName());
				//myHandler.sendEmptyMessage(1);
			}
		}).start();




	}

	public void doRxJava() {
		Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
				Log.e(TAG, "Observable thread is : " + Thread.currentThread().getName());
				Log.e(TAG, "emitter 1");
				for (int i = 0;; i++) {
					emitter.onNext(i);
					Thread.sleep(2000);  //每次发送完事件延时2秒
				}

			}
		}).subscribeOn(Schedulers.io())
				/*.filter(new Predicate<Integer>() {
					@Override
					public boolean test(Integer integer) throws Exception {
						return integer%10==0;//被10整除
					}

				})*/
				//.sample(1, TimeUnit.SECONDS)//2秒
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(Integer integer) throws Exception {
						Log.e(TAG, "" + integer);
					}
				});
	}

	public void doRxjava2() {
		Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
				for (int i = 0; ; i++) {
					emitter.onNext(i);
					Log.e(TAG, "emitter"+i);
					Thread.sleep(2000);  //发送事件之后延时2秒
				}
			}
		}).subscribeOn(Schedulers.io());

		Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
				emitter.onNext("A");
				emitter.onNext("B");
				emitter.onNext("C");
			}
		}).subscribeOn(Schedulers.io());

		Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
			@Override
			public String apply(Integer integer, String s) throws Exception {
				return integer + s;
			}
		}).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
			@Override
			public void accept(String s) throws Exception {
				Log.e(TAG, s);
			}
		}, new Consumer<Throwable>() {
			@Override
			public void accept(Throwable throwable) throws Exception {
				Log.w(TAG, throwable);

			}
		});
	}
}












































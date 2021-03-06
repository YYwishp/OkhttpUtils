package com.example.rx_java;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
//		String s = doRxJava();
//		Log.e("异步回调用", s+"qqqqq");
//		doRxjava2();
//		doRxJavaFlowable();
//		doRxjavaTest();
		flatMap();
		/*handler = new Handler(new Handler.Callback() {
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
		}).start();*/
	}

	public String doRxJava() {
		final String[] str = new String[1];
		Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
				Log.e(TAG, "Observable thread is : " + Thread.currentThread().getName());
				Log.e(TAG, "emitter 1");
				/*for (int i = 0;; i++) {
					emitter.onNext(i);
					//Thread.sleep(2000);  //每次发送完事件延时2秒
				}*/
				emitter.onNext(1);
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
						//Thread.sleep(2000);
						str[0] = "asdjkfsdf";
					}
				});
		return str[0];
	}

	public void doRxjava2() {
		Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
				for (int i = 0; ; i++) {
					emitter.onNext(null);
					Log.e(TAG, "emitter" + i);
					Thread.sleep(2000);  //发送事件之后延时2秒
				}
			}
		}).subscribeOn(Schedulers.io());
		Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
				emitter.onNext(null);
				/*emitter.onNext("B");
				emitter.onNext("C");*/
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

	public void doRxJavaFlowable() {
		Flowable.create(new FlowableOnSubscribe<Integer>() {
			@Override
			public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
				for (int i = 0; ; i++) {
					Log.e(TAG, "emit " + i);
					emitter.onNext(i);
				}
			}
		}, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<Integer>() {
					@Override
					public void onSubscribe(Subscription s) {
						Log.e(TAG, "onSubscribe");
					}

					@Override
					public void onNext(Integer integer) {
						Log.e(TAG, "onNext: " + integer);
					}

					@Override
					public void onError(Throwable t) {
						Log.e(TAG, "onError: ", t);
					}

					@Override
					public void onComplete() {
						Log.e(TAG, "onComplete");
					}
				});
	}

	public void doRxjavaTest() {
		Flowable.interval(1, TimeUnit.MICROSECONDS)
				.onBackpressureDrop()  //加上背压策略
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<Long>() {
					@Override
					public void onSubscribe(Subscription s) {
						Log.e(TAG, "onSubscribe");
//						mSubscription = s;
						s.request(Long.MAX_VALUE);
					}

					@Override
					public void onNext(Long aLong) {
						Log.e(TAG, "onNext: " + aLong);
						try {
							Thread.sleep(1000);  //延时1秒
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onError(Throwable t) {
						Log.e(TAG, "onError: ", t);
					}

					@Override
					public void onComplete() {
						Log.e(TAG, "onComplete");
					}
				});
	}

	public void flatMap() {
		Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> e) throws Exception {
				Log.e("create", Thread.currentThread().getName());
				e.onNext("1111");
			}
		}).doOnNext(new Consumer<String>() {
			@Override
			public void accept(String s) throws Exception {
				String s1 = s + "222";
				Log.e("线程doOnNext"+s1, Thread.currentThread().getName());
			}
		}).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.flatMap(new Function<String, ObservableSource<Integer>>() {
					@Override//将string转化INteger
					public ObservableSource<Integer> apply(String s) throws Exception {
						Integer integer = Integer.valueOf(s);
						ArrayList<Integer> objects = new ArrayList<>();
						Log.e("flatMap", Thread.currentThread().getName());
						return Observable.just(integer);
					}
				})
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(Integer integer) throws Exception {
						Log.e("结果", integer + "");
						Log.e("subscribe", Thread.currentThread().getName());
					}
				});
	}
}












































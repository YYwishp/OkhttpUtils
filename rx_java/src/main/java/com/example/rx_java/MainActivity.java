package com.example.rx_java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Observable.create(new ObservableOnSubscribe<Object>() {
			@Override
			public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
				emitter.onNext(1);
				emitter.onNext(2);
				emitter.onNext(3);
				emitter.onComplete();

			}
		}).subscribe(new Observer<Object>() {
			@Override
			public void onSubscribe(Disposable d) {
			}

			@Override
			public void onNext(Object value) {
			}

			@Override
			public void onError(Throwable e) {
			}

			@Override
			public void onComplete() {
			}
		});
	}
}

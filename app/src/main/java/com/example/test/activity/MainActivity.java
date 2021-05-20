package com.example.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.tvTest1)
    TextView mTvTest1;
    @BindView(R.id.tvTest2)
    TextView mTvTest2;
    @BindView(R.id.ivTest3)
    ImageView mIvTest3;

    @BindView(R.id.tvMessage)
    TextView mTvMessage;

    public static class TestEvent {
        private String msg;

        public TestEvent(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    public static class TestEvent2 extends TestEvent {

        public TestEvent2(String msg) {
            super(msg);
        }

        public String getMsg() {
            return super.getMsg();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainActivityEvent(TestEvent event) {
        mTvTest1.setText(event.getMsg());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainActivityEvent2(TestEvent2 event) {
        mTvTest2.setText(event.getMsg());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);
        mTvTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                EventBus.getDefault().post(new TestEvent("Send事件!"));
                EventBus.getDefault().postSticky(new TestEvent2("Send事件2222222222222222"));
            }
        });
        mTvTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        rxjavaExample();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void showLog(String message){
        String preText = mTvMessage.getText().toString();
        preText += TextUtils.isEmpty(preText) ? "" : "\n";
        mTvMessage.setText(preText + message);
    }

    private void rxjavaExample(){
        //步骤1. 创建被观察者(Observable),定义要发送的事件。
        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("文章1");
                emitter.onNext("文章2");
                emitter.onNext("文章3");
                emitter.onComplete();
            }
        });

        //步骤2. 创建观察者(Observer)，接受事件并做出响应操作。
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                showLog("onSubscribe");
            }

            @Override
            public void onNext(String s) {
                showLog("onNext : " + s);
            }

            @Override
            public void onError(Throwable e) {
                showLog("onError : " + e.toString());
            }

            @Override
            public void onComplete() {
                showLog("onComplete");
            }
        };

        //步骤3. 观察者通过订阅（subscribe）被观察者把它们连接到一起。
        observable.subscribe(observer);
    }
}

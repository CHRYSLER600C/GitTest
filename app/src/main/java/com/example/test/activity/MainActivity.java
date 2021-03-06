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
//                EventBus.getDefault().post(new TestEvent("Send??????!"));
                EventBus.getDefault().postSticky(new TestEvent2("Send??????2222222222222222"));
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
        //??????1. ??????????????????(Observable),???????????????????????????
        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("??????1");
                emitter.onNext("??????2");
                emitter.onNext("??????3");
                emitter.onComplete();
            }
        });

        //??????2. ???????????????(Observer)???????????????????????????????????????
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

        //??????3. ????????????????????????subscribe??????????????????????????????????????????
        observable.subscribe(observer);
    }
}

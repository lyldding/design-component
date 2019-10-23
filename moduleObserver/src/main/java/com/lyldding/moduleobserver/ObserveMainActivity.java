package com.lyldding.moduleobserver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lyldding.commonlib.Constants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author https://github.com/lyldding
 */
@Route(path = Constants.GROUP_OBSERVE + "main")
public class ObserveMainActivity extends AppCompatActivity {
    @BindView(R2.id.name)
    TextView mName;
    private BroadcastTest mBroadcastTest;
    private int mType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.observe_activity_main);
        ButterKnife.bind(this);
        mBroadcastTest = new BroadcastTest();
        initView();
    }

    private void initView() {
        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BroadcastTest.ACTION);
                intent.putExtra(BroadcastTest.TYPE, mType++);
                LocalBroadcastManager.getInstance(ObserveMainActivity.this).sendBroadcast(intent);
            }
        });

        //例子
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastTest, new IntentFilter(BroadcastTest.ACTION));
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastTest);
        super.onDestroy();
    }

}

package com.ashlikun.loadswitch.simple;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.ashlikun.loadswitch.ContextData;
import com.ashlikun.loadswitch.LoadSwitch;
import com.ashlikun.loadswitch.LoadSwitchService;
import com.ashlikun.loadswitch.DefaultOnLoadLayoutListener;
import com.ashlikun.loadswitch.OnLoadSwitchClick;

public class MainActivity extends AppCompatActivity
        implements OnLoadSwitchClick {
    LoadSwitchService loadSwitchService;
    int currStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadSwitchService = LoadSwitch
                .get()
                .register(findViewById(R.id.switchRoot), new DefaultOnLoadLayoutListener(this, this));
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currStatus == 0) {
                    loadSwitchService.showEmpty(new ContextData().setImgSize(500, 500));
                    currStatus = 1;
                } else if (currStatus == 1) {
                    loadSwitchService.showLoading(new ContextData());
                    currStatus = 2;
                } else if (currStatus == 2) {
                    loadSwitchService.showContent();
                    currStatus = 3;
                } else if (currStatus == 3) {
                    loadSwitchService.showRetry(new ContextData());
                    currStatus = 0;
                }
            }
        });
    }

    @Override
    public void onRetryClick(ContextData data) {

    }

    @Override
    public void onEmptyClick(ContextData data) {

    }
}

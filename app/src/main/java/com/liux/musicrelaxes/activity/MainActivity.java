package com.liux.musicrelaxes.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.liux.musicrelaxes.R;
import com.liux.musicrelaxes.activity.blueTooth.BlueToothScanActivity;
import com.liux.musicrelaxes.base.BaseSwipeBackActivity;
import com.liux.musicrelaxes.view.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseSwipeBackActivity {


    @BindView(R.id.btn_jump)
    Button btnJump;
    @BindView(R.id.btn_jump_blueTooth)
    Button btnJumpBlueTooth;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }



    @OnClick({R.id.btn_jump_blueTooth, R.id.btn_jump})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_jump_blueTooth:
                intent.setClass(MainActivity.this,BlueToothScanActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_jump:
                intent.setClass(MainActivity.this,ChooseMusicActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

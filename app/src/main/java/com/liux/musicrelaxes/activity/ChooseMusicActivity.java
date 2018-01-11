package com.liux.musicrelaxes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.liux.musicrelaxes.R;
import com.liux.musicrelaxes.base.BaseSwipeBackActivity;
import com.liux.musicrelaxes.view.ToolbarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by
 * 项目名称：com.liux.musicrelaxes.activity
 * 项目日期：2017/12/27
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class ChooseMusicActivity extends BaseSwipeBackActivity {


    @BindView(R.id.btn_jump)
    Button btnJump;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_choose_music;
    }

    @Override
    protected void initView() {

    }


    @OnClick(R.id.btn_jump)
    public void onViewClicked() {
        Intent intent = new Intent(this,PlayMusicActivity.class);
        startActivity(intent);
    }
}

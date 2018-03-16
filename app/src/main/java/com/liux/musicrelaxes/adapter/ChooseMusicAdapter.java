package com.liux.musicrelaxes.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liux.module.BlueToothBean.music.MusicListDto;
import com.liux.musicrelaxes.R;

import java.util.List;

/**
 * Created by
 * 项目名称：com.liux.musicrelaxes.adapter
 * 项目日期：2018/1/17
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class ChooseMusicAdapter extends BaseQuickAdapter<MusicListDto.DataBean.RowsBean,BaseViewHolder> {

    private int index = -1;   // 标记用户当前选择的那一个


    public ChooseMusicAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<MusicListDto.DataBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MusicListDto.DataBean.RowsBean item) {
        helper.setText(R.id.tv_number,""+helper.getLayoutPosition())
                .setText(R.id.tv_Music_Name,item.getName())
                .setOnCheckedChangeListener(R.id.btn_Radio, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            index = helper.getLayoutPosition();
                            mOnClickLisent.onRadioClickLinsenter(buttonView,index);
                            notifyDataSetChanged();
                        }
                    }
                });
        // 选中的条目和当前的条目是否相等
        if (index == helper.getLayoutPosition()){
            helper.setChecked(R.id.btn_Radio,true);
        }else {
            helper.setChecked(R.id.btn_Radio,false);
        }
        ImageView imgLinsenter = helper.getView(R.id.img_Linsenter);

        imgLinsenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickLisent.onImgClickLinsenter(v,helper.getLayoutPosition());
            }
        });

    }


    public interface onOtherClickLinsenter{
        void onRadioClickLinsenter(View v,int position);
        void onImgClickLinsenter(View v,int position);
    }

    private onOtherClickLinsenter mOnClickLisent;

    public void setOnOtherClickLisenter(onOtherClickLinsenter onClickLisent) {
        mOnClickLisent = onClickLisent;
    }
}

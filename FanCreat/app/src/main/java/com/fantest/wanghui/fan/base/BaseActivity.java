package com.fantest.wanghui.fan.base;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fantest.wanghui.fan.R;

/**
 * Created by wanghui on 2016/3/29.
 */
public class BaseActivity extends FragmentActivity {

    public <T extends View> T getView(int id){
        return (T)findViewById(id);
    }

    public <T extends View> T getView(ViewGroup viewGroup, int id){
        return (T)viewGroup.findViewById(id);
    }


}

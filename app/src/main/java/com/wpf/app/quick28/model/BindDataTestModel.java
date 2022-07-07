package com.wpf.app.quick28.model;

import android.annotation.SuppressLint;

import com.wpf.app.quick.base.helper.binddatahelper.Url2ImageView;
import com.wpf.app.quick.base.widgets.recyclerview.QuickBindData;
import com.wpf.app.quick28.R;
import com.wpf.app.quickbind.annotations.BindData2View;

/**
 * Created by 王朋飞 on 2022/7/5.
 */
public class BindDataTestModel extends QuickBindData {

    public BindDataTestModel() {
        super(R.layout.holder_test_bind_data);
    }

    @SuppressLint("NonConstantResourceId")
    @BindData2View(id = R.id.title , helper = Url2ImageView.class)
    String title = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2F4k%2Fs%2F02%2F2109242306111155-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1659674125&t=990c637869e9e67501c9db10e592a0c7";

}

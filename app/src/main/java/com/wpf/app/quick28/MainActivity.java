package com.wpf.app.quick28;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.wpf.app.quick.annotations.BindView;
import com.wpf.app.quick.base.activity.TestActivity;
import com.wpf.app.quick.base.activity.ViewModelBindingActivity;
import com.wpf.app.quickbind.annotations.BindSp2View;
import com.wpf.app.quick28.databinding.ActivityMainBinding;
import com.wpf.app.quick28.model.MyMessage;
import com.wpf.app.quick28.model.TestModel;
import com.wpf.app.quick28.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
public class MainActivity extends ViewModelBindingActivity<MainViewModel, ActivityMainBinding> {

    public MainActivity() {
        super(R.layout.activity_main, "快捷");
    }

    @SuppressLint("NonConstantResourceId")
    @BindSp2View(bindSp = "绑定的SpKey1", defaultValue = "默认值1")
    @BindView(R.id.spTextView1)
    TextView text1;

    @SuppressLint("NonConstantResourceId")
    @BindSp2View(bindSp = "绑定的SpKey2", defaultValue = "默认值2")
    @BindView(R.id.spTextView2)
    TextView text2;

    @SuppressLint("NonConstantResourceId")
    @BindSp2View(bindSp = "绑定的SpKey3", defaultValue = "默认值3")
    @BindView(R.id.spTextView3)
    TextView text3;

    @SuppressLint("SetTextI18n")
    @Override
    public void initView() {
        super.initView();
        text1.postDelayed(() -> text1.setText(System.currentTimeMillis() + ""), 1000);
        text2.postDelayed(() -> text2.setText(System.currentTimeMillis() + ""), 1000);
        text3.postDelayed(() -> text3.setText(System.currentTimeMillis() + ""), 1000);
    }

    public void gotoR2Test(View view) {
        startActivity(TestActivity.class);
    }

    public void gotoGlide(View view) {

    }

    public void gotoList(View view) {
        startActivity(RecyclerViewTestActivity.class);
    }

    public void gotoData(View view) {
        startActivity(IntentDataTestActivity.class, new HashMap<String, Object>() {{
            put("activityTitle", "数据测试页");
            put("intD", 2);
            put("floatD", 3f);
            put("doubleD", 4.0);
            put("charD", 'b');
            put("byteD", (byte) 6);
            put("data", new MyMessage("31"));
            put("data1", new TestModel("41"));
            put("map", new HashMap<String, String>() {{
                put("map1", "51");
            }});
            put("list", new ArrayList<String>() {{
                add("61");
                add("62");
            }});
            put("array", new String[]{"71", "72"});
            put("listS", new ArrayList<MyMessage>() {{
                add(new MyMessage("81"));
                add(new MyMessage("82"));
            }});
            put("listP", new ArrayList<TestModel>() {{
                add(new TestModel("91"));
                add(new TestModel("92"));
            }});
            put("arrayS", new MyMessage[]{new MyMessage("101"), new MyMessage("102")});
            put("arrayP", new TestModel[]{new TestModel("101"), new TestModel("102")}); //暂不支持
        }});
    }
}

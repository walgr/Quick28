package com.wpf.app.quick28.viewmodel;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.wpf.app.quick.annotations.AutoGet;
import com.wpf.app.quick.annotations.BindView;
import com.wpf.app.quick.base.viewmodel.BindingViewModel;
import com.wpf.app.quick28.R;
import com.wpf.app.quick28.databinding.ActivityDataTestBinding;
import com.wpf.app.quick28.model.MyMessage;
import com.wpf.app.quick28.model.TestModel;

import java.util.List;
import java.util.Map;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
public class IntentDataTestViewModel extends BindingViewModel<ActivityDataTestBinding> {
    private final String TAG = "IntentDataTestViewModel";

    @AutoGet
    int intD = 0;
    @AutoGet
    byte byteD = 0;
    @AutoGet
    float floatD = 0f;
    @AutoGet
    double doubleD = 0.0;
    @AutoGet
    char charD = 'a';
    @AutoGet
    MyMessage data = null;
    @AutoGet
    TestModel data1 = null;
    @AutoGet
    Map<String, Object> map = null;
    @AutoGet
    List<Object> list = null;
    @AutoGet
    Object[] array = null;
    @AutoGet
    List<MyMessage> listS = null;
    @AutoGet
    MyMessage[] arrayS = null;
    @AutoGet
    List<TestModel> listP = null;
    @AutoGet
    TestModel[] arrayP = null;

    @SuppressLint({"NonConstantResourceId", "StaticFieldLeak"})
    @BindView(R.id.title1)
    TextView title;

    @Override
    public void onBindingCreate(@Nullable ActivityDataTestBinding mViewBinding) {
        printData();
    }

    private void printData() {
        Log.i(TAG, intD + "");
        Log.i(TAG, floatD + "");
        Log.i(TAG, doubleD + "");
        Log.i(TAG, charD + "");
        Log.i(TAG, byteD + "");
        Log.i(TAG, data + "");
        Log.i(TAG, data1 + "");
        Log.i(TAG, map + "");
        Log.i(TAG, list + "");
        Log.i(TAG, array[0] + "");
        Log.i(TAG, listS.get(0).getUserName() + "");
        Log.i(TAG, listP.get(0).getText() + "");
        Log.i(TAG, arrayS[0].getUserName() + "");
        Log.i(TAG, arrayP + "");
        title.setText("数据测试页ViewModel");
    }
}

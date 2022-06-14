package com.wpf.app.quick28.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.wpf.app.quick.base.widgets.recyclerview.HolderClass;
import com.wpf.app.quick.base.widgets.recyclerview.QuickItemData;
import com.wpf.app.quick28.adapterholder.TestHolder;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
@HolderClass(holderClass = TestHolder.class)
public class TestModel extends QuickItemData implements Parcelable {

    String text = "1";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TestModel() {
    }

    public TestModel(String text) {
        this.text = text;
    }


    protected TestModel(Parcel in) {
        text = in.readString();
    }

    public static final Creator<TestModel> CREATOR = new Creator<TestModel>() {
        @Override
        public TestModel createFromParcel(Parcel in) {
            return new TestModel(in);
        }

        @Override
        public TestModel[] newArray(int size) {
            return new TestModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
    }
}

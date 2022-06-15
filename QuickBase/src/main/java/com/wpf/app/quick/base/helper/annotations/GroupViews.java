package com.wpf.app.quick.base.helper.annotations;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
public class GroupViews {

    public ArrayList<View> viewList = new ArrayList<>();

    public void showAll() {
        for (View view: viewList) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public void goneAll() {
        for (View view: viewList) {
            view.setVisibility(View.GONE);
        }
    }
}

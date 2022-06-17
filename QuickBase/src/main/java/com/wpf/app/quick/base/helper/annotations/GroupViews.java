package com.wpf.app.quick.base.helper.annotations;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
public class GroupViews {

    public ArrayList<View> viewList = new ArrayList<>();

    public void addView(View... view) {
        viewList.addAll(Arrays.asList(view));
    }

    public void removeView(View view) {
        viewList.remove(view);
    }

    public void removeAll() {
        viewList.clear();
    }

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

    public void onClick(View.OnClickListener onClickListener) {
        for (View view : viewList) {
            view.setOnClickListener(onClickListener);
        }
    }
}

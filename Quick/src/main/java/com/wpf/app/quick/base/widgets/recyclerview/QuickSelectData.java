package com.wpf.app.quick.base.widgets.recyclerview;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class QuickSelectData extends QuickBindData {
    protected String id;
    protected boolean isSelect;

    public QuickSelectData() {
    }

    public QuickSelectData(int viewType) {
        this("", false, viewType);
    }

    public QuickSelectData(String id, boolean isSelect, int viewType) {
        super(viewType);
        this.id = id;
        this.isSelect = isSelect;
    }

    public String getId() {
        return id;
    }

    public boolean getIsSelect() {
        return isSelect;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}

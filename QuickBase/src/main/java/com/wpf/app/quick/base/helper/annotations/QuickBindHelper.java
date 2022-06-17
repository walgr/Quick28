package com.wpf.app.quick.base.helper.annotations;

import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.wpf.app.quick.base.activity.BaseActivity;
import com.wpf.app.quick.base.activity.BaseFragment;
import com.wpf.app.quick.base.helper.annotations.plugins.AutoGetAnnPlugin;
import com.wpf.app.quick.base.helper.annotations.plugins.BindFragmentAnnPlugin;
import com.wpf.app.quick.base.helper.annotations.plugins.BindFragmentsAnnPlugin;
import com.wpf.app.quick.base.helper.annotations.plugins.FieldAnnBasePlugin;
import com.wpf.app.quick.base.helper.annotations.plugins.FindViewAnnPlugin;
import com.wpf.app.quick.base.helper.annotations.plugins.LoadSpPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 王朋飞 on 2022/6/8.
 * 自动获取Activity、Fragment里Intent里的值到属性上
 * 支持 char、byte、int、float、long、short、double、String、array、list、map、Serializable、Parcelable
 * 暂不支持 Parcelable[]
 */
public class QuickBindHelper {

    private static String bindSpFileName = "QuickViewSpBindFile";

    private static final ArrayList<FieldAnnBasePlugin> plugins = new ArrayList<>();

    public static <T extends FieldAnnBasePlugin> void registerPlugin(T plugin) {
        plugins.add(plugin);
    }

    static {
        //TODO 暂未支持moduleR
        registerPlugin(new FindViewAnnPlugin());
        registerPlugin(new AutoGetAnnPlugin());
        registerPlugin(new LoadSpPlugin());
        registerPlugin(new BindFragmentsAnnPlugin());
        registerPlugin(new BindFragmentAnnPlugin());
    }

    public static void bind(Activity activity) {
        bind(activity, null);
    }

    public static void bind(Activity activity, ViewModel viewModel) {
        dealAllField(activity, viewModel);
    }

    public static void bind(Fragment fragment) {
        bind(fragment, null);
    }

    public static void bind(Fragment fragment, ViewModel viewModel) {
        dealAllField(fragment, viewModel);
    }

    public static void bind(RecyclerView.ViewHolder viewHolder) {
        dealAllField(viewHolder, null);
    }

    public static void bind(Dialog object) {
        dealAllField(object, null);
    }

    private static void dealAllField(Object obj, ViewModel viewModel) {
        if (obj == null) return;
        try {
            List<Field> fields = getAllField(obj);
            for (Field field : fields) {
                for (FieldAnnBasePlugin plugin : plugins) {
                    plugin.dealField(obj, null, field);
                }
            }
            if (viewModel != null) {
                Field[] viewModelFields = viewModel.getClass().getDeclaredFields();
                for (Field field : viewModelFields) {
                    for (FieldAnnBasePlugin plugin : plugins) {
                        plugin.dealField(obj, viewModel, field);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Field> getAllField(@NonNull Object obj) {
        ArrayList<Field> result = new ArrayList<>();
        Class<?> curCls = obj.getClass();
        if (obj instanceof Activity) {
            while (curCls != null) {
                result.addAll(Arrays.asList(curCls.getDeclaredFields()));
                curCls = curCls.getSuperclass();
                if (curCls == AppCompatActivity.class) {
                    break;
                }
            }
            return result;
        } else if (obj instanceof Fragment) {
            while (curCls != null) {
                result.addAll(Arrays.asList(curCls.getDeclaredFields()));
                curCls = ((Class<?>) curCls).getSuperclass();
                if (curCls == Fragment.class) {
                    break;
                }
            }
            return result;
        } else {
            result.addAll(Arrays.asList(curCls.getDeclaredFields()));
        }
        return result;
}

    public static void setBindSpFileName(String bindSpFileName) {
        QuickBindHelper.bindSpFileName = bindSpFileName;
    }

    public static String getBindSpFileName() {
        return bindSpFileName;
    }
}

package com.wpf.app.quickbind;

import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wpf.app.quick.runtime.Unbinder;
import com.wpf.app.quickbind.plugins.AutoGetAnnPlugin;
import com.wpf.app.quickbind.plugins.BindFragmentAnnPlugin;
import com.wpf.app.quickbind.plugins.BindFragmentsAnnPlugin;
import com.wpf.app.quickbind.plugins.BindViewAnnPlugin;
import com.wpf.app.quickbind.plugins.FieldAnnBasePlugin;
import com.wpf.app.quickbind.plugins.BindSp2ViewAnnPlugin;
import com.wpf.app.quickbind.plugins.LoadSpPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王朋飞 on 2022/6/8.
 * 自动获取Activity、Fragment里Intent里的值到属性上
 * 支持 char、byte、int、float、long、short、double、String、array、list、map、Serializable、Parcelable
 * 暂不支持 Parcelable[]
 */
public class QuickBind {

    private static String bindSpFileName = "QuickViewSpBindFile";

    private static final ArrayList<FieldAnnBasePlugin> plugins = new ArrayList<>();

    public static <T extends FieldAnnBasePlugin> void registerPlugin(T plugin) {
        plugins.add(plugin);
    }

    static {
        registerPlugin(new BindViewAnnPlugin());
        registerPlugin(new BindSp2ViewAnnPlugin());
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
        bindBinder(activity, activity.getWindow().getDecorView());
    }

    public static void bind(Fragment fragment) {
        bind(fragment, null);
    }

    public static void bind(Fragment fragment, ViewModel viewModel) {
        dealAllField(fragment, viewModel);
        bindBinder(fragment, fragment.getView());
    }

    public static void bind(RecyclerView.ViewHolder viewHolder) {
        dealAllField(viewHolder, null);
    }

    public static void bind(Dialog object) {
        dealAllField(object, null);
    }

    private static void bindBinder(@NonNull Object target, @NonNull View source) {
        Class<?> targetClass = target.getClass();
        Constructor<? extends Unbinder> constructor = findBindingConstructorForClass(targetClass);

        if (constructor == null) {
            return;
        }

        //noinspection TryWithIdenticalCatches Resolves to API 19+ only type.
        try {
            constructor.newInstance(target, source);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to invoke " + constructor, e);
        } catch (InstantiationException e) {
            throw new RuntimeException("Unable to invoke " + constructor, e);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            }
            if (cause instanceof Error) {
                throw (Error) cause;
            }
            throw new RuntimeException("Unable to create binding instance.", cause);
        }
    }

    @VisibleForTesting
    static final Map<Class<?>, Constructor<? extends Unbinder>> BINDINGS = new LinkedHashMap<>();

    @Nullable
    @CheckResult
    @UiThread
    private static Constructor<? extends Unbinder> findBindingConstructorForClass(Class<?> cls) {
        Constructor<? extends Unbinder> bindingCtor = BINDINGS.get(cls);
        if (bindingCtor != null || BINDINGS.containsKey(cls)) {
            return bindingCtor;
        }
        String clsName = cls.getName();
        if (clsName.startsWith("android.") || clsName.startsWith("java.")
                || clsName.startsWith("androidx.")) {
            return null;
        }
        try {
            Class<?> bindingClass = cls.getClassLoader().loadClass(clsName + "_ViewBinding");
            //noinspection unchecked
            bindingCtor = (Constructor<? extends Unbinder>) bindingClass.getConstructor(cls, View.class);
        } catch (ClassNotFoundException e) {
            bindingCtor = findBindingConstructorForClass(cls.getSuperclass());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to find binding constructor for " + clsName, e);
        }
        BINDINGS.put(cls, bindingCtor);
        return bindingCtor;
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
        QuickBind.bindSpFileName = bindSpFileName;
    }

    public static String getBindSpFileName() {
        return bindSpFileName;
    }
}

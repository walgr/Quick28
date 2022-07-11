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
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wpf.app.quick.runtime.Databinder;
import com.wpf.app.quickbind.plugins.AutoGetAnnPlugin;
import com.wpf.app.quickbind.plugins.BindData2ViewAnnPlugin;
import com.wpf.app.quickbind.plugins.BindFragmentAnnPlugin;
import com.wpf.app.quickbind.plugins.BindFragmentsAnnPlugin;
import com.wpf.app.quickbind.plugins.BindViewAnnPlugin;
import com.wpf.app.quickbind.plugins.FieldAnnBasePlugin;
import com.wpf.app.quickbind.plugins.BindSp2ViewAnnPlugin;
import com.wpf.app.quickbind.plugins.GroupViewAnnPlugin;
import com.wpf.app.quickbind.plugins.LoadSpPlugin;
import com.wpf.app.quickbind.utils.ReflectHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
    public static final ArrayList<FieldAnnBasePlugin> bindPlugin = new ArrayList<FieldAnnBasePlugin>() {{
        add(new BindData2ViewAnnPlugin());
    }};

    public static <T extends FieldAnnBasePlugin> void registerPlugin(T plugin) {
        plugins.add(plugin);
    }

    static {
        registerPlugin(new BindViewAnnPlugin());
        registerPlugin(new GroupViewAnnPlugin());
        registerPlugin(new AutoGetAnnPlugin());
        registerPlugin(new BindSp2ViewAnnPlugin());
        registerPlugin(new LoadSpPlugin());
        registerPlugin(new BindFragmentsAnnPlugin());
        registerPlugin(new BindFragmentAnnPlugin());
        registerPlugin(new BindData2ViewAnnPlugin());
    }

    public static void bind(@NonNull Activity activity) {
        bind(activity, null);
    }

    public static void bind(@NonNull Activity activity, ViewModel viewModel) {
        bindBinder(activity, activity.getWindow().getDecorView());
        dealInPlugins(activity, viewModel);
    }

    public static void bind(@NonNull Fragment fragment) {
        bind(fragment, null);
    }

    public static void bind(@NonNull Fragment fragment, ViewModel viewModel) {
        if (fragment.getView() != null) {
            bindBinder(fragment, fragment.getView());
        }
        dealInPlugins(fragment, viewModel);
    }

    public static void bind(@NonNull RecyclerView.ViewHolder viewHolder) {
        bindBinder(viewHolder, viewHolder.itemView);
        dealInPlugins(viewHolder, null);
    }

    public static void bind(@NonNull Dialog dialog) {
        bindBinder(dialog, dialog.getWindow().getDecorView());
        dealInPlugins(dialog, null);
    }

    public static final Map<Class<?>, Databinder> BINDEDMAP = new LinkedHashMap<>();
    private static void bindBinder(@NonNull Object target, @NonNull View source) {
        Class<?> targetClass = target.getClass();
        Constructor<? extends Databinder> constructor = findBindingConstructorForClass(targetClass);

        if (constructor == null) {
            return;
        }

        //noinspection TryWithIdenticalCatches Resolves to API 19+ only type.
        try {
            BINDEDMAP.put(targetClass, (Databinder)constructor.newInstance(target, source));
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
    static final Map<Class<?>, Constructor<? extends Databinder>> BINDINGS = new LinkedHashMap<>();

    @Nullable
    @CheckResult
    @UiThread
    private static Constructor<? extends Databinder> findBindingConstructorForClass(Class<?> cls) {
        Constructor<? extends Databinder> bindingCtor = BINDINGS.get(cls);
        if (bindingCtor != null || BINDINGS.containsKey(cls)) {
            return bindingCtor;
        }
        String clsPackage = cls.getPackage().getName();
        String clsName = cls.getName();
        String clsSimpleName = cls.getSimpleName();
        if (clsName.startsWith("android.") || clsName.startsWith("java.")
                || clsName.startsWith("androidx.")) {
            return null;
        }
        try {
            Class<?> bindingClass = cls.getClassLoader().loadClass(clsPackage + ".Quick_" + clsSimpleName + "_ViewBinding");
            //noinspection unchecked
            bindingCtor = (Constructor<? extends Databinder>) bindingClass.getConstructor(cls, View.class);
        } catch (ClassNotFoundException e) {
            bindingCtor = findBindingConstructorForClass(cls.getSuperclass());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to find binding constructor for " + clsName, e);
        }
        BINDINGS.put(cls, bindingCtor);
        return bindingCtor;
    }

    public static void dealInPlugins(Object obj, ViewModel viewModel) {
        dealInPlugins(obj, viewModel, plugins);
    }

    public static void dealInPlugins(Object obj, ViewModel viewModel, ArrayList<FieldAnnBasePlugin> plugins) {
        if (obj == null) return;
        try {
            List<Field> fields = ReflectHelper.getFieldWithParent(obj);
            for (Field field : fields) {
                for (FieldAnnBasePlugin plugin : plugins) {
                    boolean result = plugin.dealField(obj, null, field);
                    if (result) {
                        break;
                    }
                }
            }
            if (viewModel != null) {
                List<Field> viewModelFields = ReflectHelper.getFieldWithParent(viewModel);
                for (Field field : viewModelFields) {
                    for (FieldAnnBasePlugin plugin : plugins) {
                        boolean result = plugin.dealField(obj, viewModel, field);
                        if (result) {
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setBindSpFileName(String bindSpFileName) {
        QuickBind.bindSpFileName = bindSpFileName;
    }

    public static String getBindSpFileName() {
        return bindSpFileName;
    }
}

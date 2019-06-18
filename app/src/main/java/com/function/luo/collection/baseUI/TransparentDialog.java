package com.function.luo.collection.baseUI;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.function.luo.collection.R;


public class TransparentDialog extends Dialog {
    private LayoutParams attr;

    public LayoutParams getAttr() {
        return attr;
    }

    public TransparentDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawableResource(
                R.mipmap.transparent_bg);
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 1f;
        window.setAttributes(lp);
        attr = window.getAttributes();
        if (attr != null) {
            attr.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
            attr.gravity = Gravity.BOTTOM;
        }
    }

    public TransparentDialog(Activity context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawableResource(
                R.mipmap.transparent_bg);
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 1f;
        window.setAttributes(lp);
        attr = window.getAttributes();
        if (attr != null) {
            attr.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
            attr.gravity = Gravity.BOTTOM;
        }
    }

    /**
     * @param context
     * @param alpha   透明度 0.0f--1f(不透明)
     * @param gravity 方向(Gravity.BOTTOM,Gravity.TOP,Gravity.LEFT,Gravity.RIGHT)
     */
    public TransparentDialog(Context context, float alpha, int gravity) {
        super(context);
        // TODO Auto-generated constructor stub
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawableResource(
                R.mipmap.transparent_bg);
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = alpha;
        window.setAttributes(lp);
        attr = window.getAttributes();
        if (attr != null) {
            attr.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
            attr.gravity = gravity;
        }
    }


}

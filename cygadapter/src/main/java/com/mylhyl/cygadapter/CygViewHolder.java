package com.mylhyl.cygadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hupei on 2016/7/12.
 */
public final class CygViewHolder {
    private Context mContext;
    private SparseArray<View> mViews;
    private View mItemView;

    public static CygViewHolder get(Context context, int resource, View convertView,
                                    ViewGroup parent) {
        if (convertView == null) {
            View view = LayoutInflater.from(context).inflate(resource, parent, false);
            return new CygViewHolder(context, view);
        }
        return (CygViewHolder) convertView.getTag();
    }

    private CygViewHolder(Context context, View itemView) {
        mContext = context;
        mViews = new SparseArray<>();
        mItemView = itemView;
        mItemView.setTag(this);
    }


    public <T extends View> T findViewById(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = mItemView.findViewById(id);
            mViews.put(id, view);
        }
        return (T) view;
    }

    public CygViewHolder setText(int viewId, int textId) {
        return setText(viewId, mContext.getString(textId));
    }

    /**
     * 设置 TextView 的显示的字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public CygViewHolder setText(int viewId, String text) {
        View view = findViewById(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
        return this;
    }

    public CygViewHolder setTextColor(int viewId, int textColor) {
        TextView view = findViewById(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public CygViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = findViewById(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    public CygViewHolder setImageResource(int viewId, int resId) {
        ImageView view = findViewById(viewId);
        view.setImageResource(resId);
        return this;
    }

    public CygViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = findViewById(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public View getItemView() {
        return mItemView;
    }
}

package com.mylhyl.cygadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hupei on 2016/7/12.
 */
public abstract class CygMultiViewTypeAdapter<T> extends CygListAdapter<T> {

    public abstract int getItemLayout(int viewType);

    public abstract int getItemViewType(T item, int position);

    public CygMultiViewTypeAdapter(Context context, List objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        int resourceId = getItemLayout(viewType);
        CygViewHolder viewHolder = CygViewHolder.get(mContext, resourceId, convertView, parent);
        onBindData(viewHolder, getItem(position), position);
        return viewHolder.getItemView();
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewType(getItem(position), position);
    }

    public static final class ItemViewType {
        private int resourceId;
        private int type;

        public ItemViewType(int resourceId, int type) {
            this.resourceId = resourceId;
            this.type = type;
        }
    }
}

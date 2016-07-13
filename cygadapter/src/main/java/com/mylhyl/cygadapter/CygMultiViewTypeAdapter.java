package com.mylhyl.cygadapter;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hupei on 2016/7/12.
 */
public abstract class CygMultiViewTypeAdapter<T> extends CygAdapter<T> {

    public abstract int getItemViewType(T item, int position);

    public abstract void onBindData(CygViewHolder viewHolder, final T item, final int position, final int viewType);

    protected SparseIntArray mItemViewTypes;

    public CygMultiViewTypeAdapter(Context context, List objects, SparseIntArray itemViewTypes) {
        super(context, 0, objects);
        mItemViewTypes = itemViewTypes;
    }

    public void addItemViewType(int viewType, int layoutId) {
        if (mItemViewTypes == null) mItemViewTypes = new SparseIntArray();
        mItemViewTypes.put(viewType, layoutId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        int layoutId = mItemViewTypes.get(viewType);
        CygViewHolder viewHolder = CygViewHolder.get(mContext, layoutId, convertView, parent);
        onBindData(viewHolder, getItem(position), position, viewType);
        return viewHolder.getItemView();
    }

    @Override
    public int getViewTypeCount() {
        return mItemViewTypes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewType(getItem(position), position);
    }

    @Override
    public final void onBindData(CygViewHolder viewHolder, T item, int position) {

    }
}

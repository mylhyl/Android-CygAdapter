package com.nylhyl.adapter.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hupei on 2017/5/22.
 */

public final class ViewHolder<T extends ViewDataBinding> {
    private final T mBinding;
    private View mItemView;

    public static ViewHolder get(LayoutInflater inflater, int resource, View convertView,
                                 ViewGroup parent) {
        if (convertView == null) {
            return new ViewHolder(DataBindingUtil.inflate(inflater, resource, parent, false));
        }
        return (ViewHolder) convertView.getTag();
    }

    public ViewHolder(T binding) {
        mBinding = binding;
        mItemView = mBinding.getRoot();
        mItemView.setTag(this);
    }

    public T getBinding() {
        return mBinding;
    }

    public View getItemView() {
        return mItemView;
    }
}

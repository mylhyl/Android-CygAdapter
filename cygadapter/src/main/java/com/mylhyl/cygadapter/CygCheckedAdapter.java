package com.mylhyl.cygadapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hupei on 2016/7/12.
 */
public abstract class CygCheckedAdapter<T> extends CygAdapter<T> {

    public void onCompoundButtonCheckedChanged(CompoundButton buttonView, boolean isChecked, int position) {

    }

    private int mCheckViewId;
    protected SparseArray<T> mSparseObjects;// 如复选框选中的对象集

    public CygCheckedAdapter(Context context, int resource, int checkViewId, List objects) {
        super(context, resource, objects);
        this.mCheckViewId = checkViewId;
        mSparseObjects = new SparseArray<>();
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        CygViewHolder viewHolder = (CygViewHolder) view.getTag();
        View checkView = viewHolder.findViewById(mCheckViewId);
        if (checkView instanceof CompoundButton) {
            CompoundButton compoundButton = (CompoundButton) checkView;
            setOnCheckedChangeListener(compoundButton, position);

            //处理滑动时的状态恢复
            if (getCheckObject(position) != null) {
                compoundButton.setChecked(true);
            } else {
                compoundButton.setChecked(false);
            }
        }
        return view;
    }

    private void setOnCheckedChangeListener(CompoundButton compoundButton, final int position) {
        //可点击且可用状态下才注册事件
        if (compoundButton.isClickable() && compoundButton.isEnabled()) {
            compoundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    toggleCheckObject(isChecked, position);
                    onCompoundButtonCheckedChanged(buttonView, isChecked, position);
                }
            });
        }
    }

    /**
     * 切换 CompoundButton 状态<br>
     * 例如：AdapterView.OnItemClickListener 中调用
     *
     * @param view
     * @param position
     */
    public final void toggleCheckObject(View view, int position) {
        CygViewHolder viewHolder = (CygViewHolder) view.getTag();
        CompoundButton compoundButton = viewHolder.findViewById(mCheckViewId);
        compoundButton.toggle();
        toggleCheckObject(compoundButton.isChecked(), position);
    }

    public final void toggleCheckObject(boolean isChecked, int position) {
        if (isChecked)
            putCheckObject(position);
        else
            removeCheckObject(position);
    }

    /**
     * 删除选中数据
     */
    public final void removeCheckObjects() {
        int size = getCheckSize();
        for (int i = 0; i < size; i++) {
            remove(mSparseObjects.valueAt(i));
        }
        clearCheckObjects();
    }

    public final int getCheckSize() {
        return mSparseObjects.size();
    }

    /**
     * 取出选中数据
     *
     * @return
     */
    public final List<T> getCheckObjects() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < mSparseObjects.size(); i++) {
            list.add(mSparseObjects.valueAt(i));
        }
        return list;
    }

    /*以下为缓存操作*/

    private final void clearCheckObjects() {
        mSparseObjects.clear();
    }

    private final void putCheckObject(int position) {
        mSparseObjects.put(position, getItem(position));
    }

    private final void removeCheckObject(int position) {
        mSparseObjects.remove(position);
    }

    private final T getCheckObject(int position) {
        return mSparseObjects.get(position);
    }
}

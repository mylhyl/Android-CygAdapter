package com.mylhyl.cygadapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
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
    public View getView(int position, View convertView, ViewGroup parent) {
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
        } else if (checkView instanceof CheckedTextView) {
            CheckedTextView checkedTextView = (CheckedTextView) checkView;
            //处理滑动时的状态恢复
            if (getCheckObject(position) != null) {
                checkedTextView.setChecked(true);
            } else {
                checkedTextView.setChecked(false);
            }
        }
        return view;
    }

    private void setOnCheckedChangeListener(CompoundButton compoundButton, final int position) {
        //可以点击、可用状态，才注册事件
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
     * @param convertView
     * @param position
     */
    public final void toggleCheckObject(View convertView, int position) {
        CygViewHolder viewHolder = (CygViewHolder) convertView.getTag();
        View viewCheck = viewHolder.findViewById(mCheckViewId);
        if (viewCheck instanceof CompoundButton) {
            CompoundButton compoundButton = (CompoundButton) viewCheck;
            compoundButton.toggle();
            toggleCheckObject(compoundButton.isChecked(), position);
        } else if (viewCheck instanceof CheckedTextView) {
            CheckedTextView checkedTextView = (CheckedTextView) viewCheck;
            checkedTextView.toggle();
            toggleCheckObject(checkedTextView.isChecked(), position);
        }
    }

    /**
     * 切换 CompoundButton 状态<br>
     * 例如：AdapterView.OnItemClickListener 中，已知 CompoundButton 的状态<br>
     * 只有当选择模式已被设置为 CHOICE_MODE_SINGLE 或 CHOICE_MODE_MULTIPLE 时, isItemChecked 结果才有效<br>
     * boolean isChecked = listView.isItemChecked(position);
     *
     * @param isChecked
     * @param position
     */
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

    /**
     * 取得选中数据大小
     *
     * @return
     */
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

    /**
     * 全选中/全不选
     *
     * @param isAll
     */
    public void toggleCheckObjectState(boolean isAll) {
        int size = getCount();
        for (int i = 0; i < size; i++) {
            toggleCheckObject(isAll, i);
        }
        notifyDataSetChanged();
    }

    /**
     * 取消选中的项
     */
    public void clearCheckObjectState() {
        int size = getCount();
        for (int i = 0; i < size; i++) {
            toggleCheckObject(false, i);
        }
        notifyDataSetChanged();
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

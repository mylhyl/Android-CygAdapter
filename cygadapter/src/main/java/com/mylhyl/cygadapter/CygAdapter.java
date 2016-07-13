package com.mylhyl.cygadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hupei on 2016/7/12.
 */
public abstract class CygAdapter<T> extends BaseAdapter implements Filterable {
    private final Object mLock = new Object();
    private ObjectFilter mFilter;
    private ArrayList<T> mOriginalValues;//原始数据
    protected List<T> mObjects;// 数据源
    protected int mCurrentCheckPosition = -1;// 当前选择的行号
    protected Context mContext;
    protected int mResource;// 视图ID

    public abstract void onBindData(CygViewHolder viewHolder, final T item, final int position);

    public CygAdapter(Context context, int resource, List<T> objects) {
        this.mContext = context;
        this.mResource = resource;
        this.mObjects = objects;
    }

    @Override
    public int getCount() {
        if (mObjects == null)
            return 0;
        return mObjects.size();
    }

    @Override
    public T getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CygViewHolder viewHolder = CygViewHolder.get(mContext, mResource, convertView, parent);
        onBindData(viewHolder, getItem(position), position);
        return viewHolder.getItemView();
    }

    /**
     * 添加单个数据对象
     *
     * @param object
     */
    public final void add(T object) {
        synchronized (mLock) {
            if (mOriginalValues != null) {
                mOriginalValues.add(object);
            } else {
                mObjects.add(object);
            }
        }
        notifyDataSetChanged();
    }

    public final void addAll(List<T> collection) {
        synchronized (mLock) {
            if (mOriginalValues != null) {
                mOriginalValues.addAll(collection);
            } else {
                mObjects.addAll(collection);
            }
        }
        notifyDataSetChanged();
    }

    public final void insert(T object, int index) {
        synchronized (mLock) {
            if (mOriginalValues != null) {
                mOriginalValues.add(index, object);
            } else {
                mObjects.add(index, object);
            }
        }
        notifyDataSetChanged();
    }

    public final void set(T object) {
        synchronized (mLock) {
            int index = mObjects.indexOf(object);
            if (mOriginalValues != null) {
                mOriginalValues.set(index, object);
            } else {
                mObjects.set(index, object);
            }
        }
        notifyDataSetChanged();
    }

    public final void set(T object, int index) {
        synchronized (mLock) {
            if (mOriginalValues != null) {
                mOriginalValues.set(index, object);
            } else {
                mObjects.set(index, object);
            }
        }
        notifyDataSetChanged();
    }

    public final void remove(T object) {
        synchronized (mLock) {
            if (mOriginalValues != null) {
                mOriginalValues.remove(object);
            } else {
                mObjects.remove(object);
            }
        }
        notifyDataSetChanged();
    }

    public final void remove(int location) {
        synchronized (mLock) {
            if (mOriginalValues != null) {
                mOriginalValues.remove(location);
            } else {
                mObjects.remove(location);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 清除数据
     *
     * @param notify true 刷新
     */
    public final void clear(boolean notify) {
        synchronized (mLock) {
            if (mOriginalValues != null) {
                mOriginalValues.clear();
            } else {
                mObjects.clear();
            }
        }
        if (notify)
            notifyDataSetChanged();
    }

    public final int getCurrentCheckPosition() {
        return mCurrentCheckPosition;
    }

    /**
     * 设置当前点击行
     *
     * @param position
     */
    public final void setCurrentCheckPosition(int position) {
        this.mCurrentCheckPosition = position;
        notifyDataSetChanged();
    }

    /**
     * 取出所有数据
     *
     * @return
     */
    public final List<T> getObjects() {
        return mObjects;
    }

    /**
     * 对数据排序
     *
     * @param comparator
     */
    public final void sort(Comparator<? super T> comparator) {
        synchronized (mLock) {
            if (mOriginalValues != null) {
                Collections.sort(mOriginalValues, comparator);
            } else {
                Collections.sort(mObjects, comparator);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ObjectFilter();
        }
        return mFilter;
    }

    class ObjectFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();

            if (mOriginalValues == null) {
                synchronized (mLock) {
                    mOriginalValues = new ArrayList<>(mObjects);
                }
            }

            if (prefix == null || prefix.length() == 0) {
                ArrayList<T> list;
                synchronized (mLock) {
                    list = new ArrayList<>(mOriginalValues);
                }
                results.values = list;
                results.count = list.size();
            } else {
                ArrayList<T> values;
                synchronized (mLock) {
                    values = new ArrayList<>(mOriginalValues);
                }

                final ArrayList<T> newValues = new ArrayList<>();
                final int count = values.size();
                String prefixString = prefix.toString().toLowerCase();
                for (int i = 0; i < count; i++) {
                    final T value = values.get(i);
                    final String valueText = value.toString().toLowerCase();
                    if (valueText.contains(prefixString)) {
                        newValues.add(value);
                    }
                }
                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mObjects = (List<T>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}

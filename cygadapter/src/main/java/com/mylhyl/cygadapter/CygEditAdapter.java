package com.mylhyl.cygadapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

/**
 * 点击 EditText 输入框时虽然弹出了输入法，但是还得再次点击 EditText 才能输入字符
 * 需要在清单文件中的当前 activity 里配置下弹出输入框禁止绘制当前屏幕的属性
 * android:windowSoftInputMode="stateAlwaysHidden|adjustPan"
 * Created by hupei on 2017/3/28.
 */
public abstract class CygEditAdapter<T> extends CygAdapter<T> {
    public abstract void onBindTextChanged(T item, String text);

    private int mEditViewId;

    public CygEditAdapter(Context context, int resource, int editViewId, List<T> objects) {
        super(context, resource, objects);
        this.mEditViewId = editViewId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CygViewHolder viewHolder = CygViewHolder.get(mContext, mResource, convertView, parent);
        final EditText editText = viewHolder.findViewById(mEditViewId);
        editText.setTag(getItem(position));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.removeTextChangedListener(this);
                T tag = (T) editText.getTag();
                onBindTextChanged(tag, s.toString());
                editText.addTextChangedListener(this);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        editText.clearFocus();
        onBindData(viewHolder, getItem(position), position);
        return viewHolder.getItemView();
    }
}

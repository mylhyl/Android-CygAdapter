package com.mylhyl.cygadapter.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.mylhyl.crlayout.app.SwipeRefreshListFragment;
import com.mylhyl.cygadapter.CygListAdapter;
import com.mylhyl.cygadapter.CygViewHolder;

import java.util.ArrayList;

/**
 * Created by hupei on 2016/7/12.
 */
public class SimpleFragment extends SwipeRefreshListFragment {
    private CygListAdapter<String> mAdapter;

    public static SimpleFragment newInstance() {
        return new SimpleFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<String> datas = new ArrayList();
        datas.add("pull add data");
        mAdapter = new CygListAdapter<String>(getContext(), android.R.layout.simple_list_item_1, datas) {
            @Override
            public void onBindData(CygViewHolder viewHolder, String item, int position) {
                TextView tv = viewHolder.findViewById(android.R.id.text1);
                tv.setText(item);
                if (position == getCurrentCheckPosition()) {
                    tv.setBackgroundColor(Color.RED);
                } else {
                    tv.setBackgroundColor(Color.WHITE);
                }
            }
        };
        setListAdapter(mAdapter);
    }

    @Override
    public void onRefresh() {
        int count = mAdapter.getCount();
        mAdapter.add("Add Data：" + count);
        setRefreshing(false);
    }

    @Override
    public void onListItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.setCurrentCheckPosition(position);
    }
}

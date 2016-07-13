package com.mylhyl.cygadapter.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mylhyl.cygadapter.CygCheckedListAdapter;
import com.mylhyl.cygadapter.CygViewHolder;

import java.util.ArrayList;

/**
 * Created by hupei on 2016/7/12.
 */
public class CheckedChangedFragment extends ListFragment {
    public static CheckedChangedFragment newInstance() {
        return new CheckedChangedFragment();
    }

    private CygCheckedListAdapter<String> mAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("CheckedChanged");
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        ArrayList<String> datas = new ArrayList();
        for (int i = 0; i < 50; i++) {
            datas.add("menu del data" + i);
        }
        mAdapter = new CygCheckedListAdapter<String>(getContext(),
                R.layout.fragment_checked_changed_item,R.id.checkBox, datas) {

            @Override
            public void onBindData(CygViewHolder viewHolder, String item, final int position) {
                viewHolder.setText(android.R.id.text1, item);
            }
        };
        setListAdapter(mAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_check, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_check_del) {
            mAdapter.removeCheckObjects();
        }
        return super.onOptionsItemSelected(item);
    }
}

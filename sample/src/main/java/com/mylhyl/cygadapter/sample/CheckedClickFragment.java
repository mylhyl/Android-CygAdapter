package com.mylhyl.cygadapter.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.mylhyl.cygadapter.CygCheckedAdapter;
import com.mylhyl.cygadapter.CygViewHolder;

import java.util.ArrayList;

/**
 * Created by hupei on 2016/7/12.
 */
public class CheckedClickFragment extends ListFragment {
    public static CheckedClickFragment newInstance() {
        return new CheckedClickFragment();
    }

    private CygCheckedAdapter<String> mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("CheckedClick");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        ArrayList<String> datas = new ArrayList();
        for (int i = 0; i < 50; i++) {
            datas.add("menu del data" + i);
        }
        mAdapter = new CygCheckedAdapter<String>(getContext(),
                R.layout.fragment_checked_click_item, R.id.checkBox, datas) {

            @Override
            public void onBindData(CygViewHolder viewHolder, String item, int position) {
                viewHolder.setText(android.R.id.text1, item);
            }
        };
        setListAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        mAdapter.toggleCheckObject(view, position);
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

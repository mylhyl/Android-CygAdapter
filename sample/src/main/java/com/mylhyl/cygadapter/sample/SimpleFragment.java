package com.mylhyl.cygadapter.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.mylhyl.crlayout.app.SwipeRefreshListFragment;
import com.mylhyl.cygadapter.CygAdapter;
import com.mylhyl.cygadapter.CygViewHolder;
import com.mylhyl.cygadapter.sample.entities.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by hupei on 2016/7/12.
 */
public class SimpleFragment extends SwipeRefreshListFragment {
    private CygAdapter<Student> mAdapter;
    private boolean sort = true;//默认降序
    private String title;

    public static SimpleFragment newInstance(String title) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(title);
        setHasOptionsMenu(true);

        ArrayList<Student> datas = new ArrayList();
        datas.add(new Student(0, "pull add data"));
        datas.add(new Student(1, "pull add data"));
        datas.add(new Student(2, "pull add data"));
        Collections.sort(datas);//排序

        mAdapter = new CygAdapter<Student>(getContext(), android.R.layout.simple_list_item_1, datas) {
            @Override
            public void onBindData(CygViewHolder viewHolder, Student item, int position) {
                TextView tv = viewHolder.findViewById(android.R.id.text1);
                tv.setText(item.name + "：" + item.age + "岁");
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
        mAdapter.add(new Student(mAdapter.getCount(), "Add Data"));
        mAdapter.add(new Student(mAdapter.getCount(), "Add Data"));
        mAdapter.add(new Student(mAdapter.getCount(), "Add Data"));
        setRefreshing(false);
    }

    @Override
    public void onListItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.setCurrentCheckPosition(position);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_simple, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_simple_sort) {
            //排序
            mAdapter.sort(new Comparator<Student>() {
                @Override
                public int compare(Student lhs, Student rhs) {
                    if (sort) return lhs.age - rhs.age;
                    else return rhs.age - lhs.age;

                }
            });
            sort = !sort;
        }
        return super.onOptionsItemSelected(item);
    }
}

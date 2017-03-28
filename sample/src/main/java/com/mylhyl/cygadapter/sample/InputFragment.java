package com.mylhyl.cygadapter.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.mylhyl.cygadapter.CygEditAdapter;
import com.mylhyl.cygadapter.CygViewHolder;
import com.mylhyl.cygadapter.sample.entities.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hupei on 2017/3/17.
 */

public class InputFragment extends ListFragment {
    private static final String TAG = "InputFragment";
    ArrayList<Student> datas;
    CygEditAdapter<Student> mAdapter;

    public static InputFragment newInstance() {
        return new InputFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        datas = new ArrayList();
        for (int i = 0; i < 50; i++) {
            datas.add(new Student(i, "测试" + i + "输入"));
        }
        mAdapter = new CygEditAdapter<Student>(getContext(), R.layout.fragment_input_item, R.id.editText, datas) {


            @Override
            public void onBindTextChanged(Student item, String text) {
                item.name = text;
            }

            @Override
            public void onBindData(CygViewHolder viewHolder, Student item, int position) {
                EditText editText = viewHolder.findViewById(R.id.editText);
                editText.setText(item.name);
            }
        };
        setListAdapter(mAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_save, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {
            List<Student> objects = mAdapter.getObjects();
            for (Student student : objects) {
                Log.i(TAG, student.name);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

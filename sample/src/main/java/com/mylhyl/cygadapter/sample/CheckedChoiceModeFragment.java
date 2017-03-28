package com.mylhyl.cygadapter.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.mylhyl.cygadapter.CygCheckedAdapter;
import com.mylhyl.cygadapter.CygViewHolder;

import java.util.ArrayList;

/**
 * ListView.CHOICE_MODE_MULTIPLE
 * 复选框单击与item单击演示
 *
 * Created by hupei on 2016/7/12.
 */
public class CheckedChoiceModeFragment extends ListFragment {
    public static CheckedChoiceModeFragment newInstance() {
        return new CheckedChoiceModeFragment();
    }

    private CygCheckedAdapter<String> mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("CheckedChoiceMode");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        //为 ListView 设置 ChoiceMode
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayList<String> datas = new ArrayList();
        for (int i = 0; i < 50; i++) {
            datas.add("menu del data" + i);
        }
        //fragment_checked_choice_item 为 item可点击，CheckBox 也可点击
        //当 CheckBox 点击时，需重写 onCompoundButtonCheckedChanged 调用 ListView.setItemChecked 改变状态
        mAdapter = new CygCheckedAdapter<String>(getContext(),
                R.layout.fragment_checked_choice_item, R.id.checkBox, datas) {

            @Override
            public void onCompoundButtonCheckedChanged(CompoundButton buttonView, boolean isChecked, int position) {
                getListView().setItemChecked(position, isChecked);
            }

            @Override
            public void onBindData(CygViewHolder viewHolder, String item, int position) {
                viewHolder.setText(android.R.id.text1, item);
            }
        };
        setListAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        //只有当选择模式已被设置为 CHOICE_MODE_SINGLE 或 CHOICE_MODE_MULTIPLE 时, isItemChecked 结果才有效。
        //必要时与 setItemChecked 一起使用(adapter中重写onCompoundButtonCheckedChanged)，如 CheckBox 也可以单击选中
        boolean checked = listView.isItemChecked(position);
        mAdapter.toggleCheckObject(checked, position);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_check, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_check_del) {
            mAdapter.removeCheckObjects();
            //清空选择状态
            getListView().clearChoices();
        }
        return super.onOptionsItemSelected(item);
    }
}

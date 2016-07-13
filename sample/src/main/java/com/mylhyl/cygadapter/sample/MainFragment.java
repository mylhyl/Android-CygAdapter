package com.mylhyl.cygadapter.sample;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.mylhyl.cygadapter.CygListAdapter;
import com.mylhyl.cygadapter.CygViewHolder;

import java.util.ArrayList;

/**
 * Created by hupei on 2016/7/12.
 */
public class MainFragment extends ListFragment {
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private OnFragmentInteractionListener mListener;
    private Uri mUri = Uri.parse("content://com.mylhyl.cygadapter/types");

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<String> datas = new ArrayList();
        datas.add("Simple");
        datas.add("MultiViewType");
        datas.add("CheckedChanged");
        datas.add("CheckedClick");
        datas.add("CheckedChoiceMode");
        setListAdapter(new CygListAdapter<String>(getContext(), android.R.layout.simple_list_item_1, datas) {
            @Override
            public void onBindData(CygViewHolder viewHolder, String item, int position) {
                viewHolder.setText(android.R.id.text1, item);
            }
        });
    }


    @Override
    public void onStart() {
        getActivity().setTitle(R.string.app_name);
        super.onStart();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Uri uri = ContentUris.withAppendedId(mUri, position);
        onButtonPressed(uri);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
}

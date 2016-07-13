package com.mylhyl.cygadapter.sample;

import android.content.Context;
import android.widget.ImageView;

import com.mylhyl.cygadapter.CygMultiViewTypeAdapter;
import com.mylhyl.cygadapter.CygViewHolder;
import com.mylhyl.cygadapter.sample.entities.ChatMsg;

import java.util.List;

/**
 * Created by hupei on 2016/7/13.
 */
public class ChatAdapter extends CygMultiViewTypeAdapter<ChatMsg> {
    private static final int VIEW_TYPE_TO_TEXT = 0;
    private static final int VIEW_TYPE_FROM_TEXT = 1;
    private static final int VIEW_TYPE_TO_IMAGE = 2;
    private static final int VIEW_TYPE_FROM_IMAGE = 3;

    public ChatAdapter(Context context, List objects) {
        super(context, objects, null);
        addItemViewType(VIEW_TYPE_TO_TEXT, R.layout.fragment_multi_view_type_to_item_text);
        addItemViewType(VIEW_TYPE_FROM_TEXT, R.layout.fragment_multi_view_type_from_item_text);
        addItemViewType(VIEW_TYPE_TO_IMAGE, R.layout.fragment_multi_view_type_to_item_image);
        addItemViewType(VIEW_TYPE_FROM_IMAGE, R.layout.fragment_multi_view_type_from_item_image);
    }

    @Override
    public int getItemViewType(ChatMsg item, int position) {
        if (item.isTo) {
            if (item.msgType == ChatMsg.MsgType.TEXT)
                return VIEW_TYPE_TO_TEXT;
            else
                return VIEW_TYPE_TO_IMAGE;
        } else {
            if (item.msgType == ChatMsg.MsgType.TEXT)
                return VIEW_TYPE_FROM_TEXT;
            else return VIEW_TYPE_FROM_IMAGE;
        }
    }

    @Override
    public void onBindData(CygViewHolder viewHolder, ChatMsg item, int position, int viewType) {
        if (viewType == VIEW_TYPE_FROM_TEXT || viewType == VIEW_TYPE_TO_TEXT) {
            viewHolder.setText(R.id.textView, item.msgContent);
        } else if(viewType == VIEW_TYPE_FROM_IMAGE || viewType == VIEW_TYPE_TO_IMAGE){
            ImageView imageView = viewHolder.findViewById(R.id.imageView1);
            imageView.setImageResource(item.msgImage);
        }
    }
}

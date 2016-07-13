package com.mylhyl.cygadapter.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.mylhyl.crlayout.app.SwipeRefreshListFragment;

import java.util.ArrayList;

/**
 * Created by hupei on 2016/7/13.
 */
public class MultiViewTypeFragment extends SwipeRefreshListFragment {
    public static MultiViewTypeFragment newInstance() {
        return new MultiViewTypeFragment();
    }

    private ArrayList<ChatMsg> datas = new ArrayList();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datas.add(new ChatMsg(true, "问到了吗？"));
        datas.add(new ChatMsg(false, "恩，还是原来的物流单号，因为是物流碰坏了"));
        datas.add(new ChatMsg(false, "所以还是原来的单号他们承担运费的"));
        datas.add(new ChatMsg(true, "那就查不到进度了"));
        datas.add(new ChatMsg(false, "可以打电话查的"));
        datas.add(new ChatMsg(true, "行吧，谢谢~"));
        datas.add(new ChatMsg(true, "那个李师傅说，要物流暂时不要送货。送货的又一直催收货，没搞懂你们怎么沟通的，明明是你们发货与物流之间的事情，硬是要我来从中间转告，你们自己搞定吧。搞了再通知我接货。"));
        datas.add(new ChatMsg(true, "给你们一周时间，不行我就申请退款了"));
        datas.add(new ChatMsg(false, "好"));
        datas.add(new ChatMsg(true, "麻烦把淘宝自动确认收货时间延长。"));
        datas.add(new ChatMsg(false, "好"));
        datas.add(new ChatMsg(false, "货收到没？"));
        datas.add(new ChatMsg(true, "急急忙忙看了下，没细看"));
        datas.add(new ChatMsg(true, "是不是确认收货？"));
        datas.add(new ChatMsg(false, "不急"));
        datas.add(new ChatMsg(true, "真不急？"));
        datas.add(new ChatMsg(false, "还好"));
        datas.add(new ChatMsg(false, R.mipmap.remind_bg));
        datas.add(new ChatMsg(true, "老实点男人更喜欢"));
        datas.add(new ChatMsg(false, "记得给我好评晒图+追评"));
        datas.add(new ChatMsg(true, "我在想怎么评论，差评吧对你不利，不差评吧又对不住自己"));
        datas.add(new ChatMsg(true, "刚才想评论的事呢，你就索要好评了"));
        datas.add(new ChatMsg(true, "产品没问题，就是物流的问题"));
        datas.add(new ChatMsg(false, "好事多磨嘛"));
        datas.add(new ChatMsg(false, "你就评论物流不好"));
        datas.add(new ChatMsg(true, "放心不会差评的"));
        datas.add(new ChatMsg(true, "毕竟处理的结果还是非常满意的，但你们改善与物流之间的沟通"));
        datas.add(new ChatMsg(true, R.mipmap.wb_pic3_peoplearound_notloggedin));
        datas.add(new ChatMsg(false, "恩恩"));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView = getSwipeRefreshLayout().getScrollView();
        listView.setDivider(null);
        listView.setSelector(null);
        listView.setStackFromBottom(true);
        setListAdapter(new ChatAdapter(getContext(), datas));
    }

    @Override
    public void onRefresh() {
        setRefreshing(false);
    }
}

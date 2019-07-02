package com.mobike.uilibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mobike.framework.event.MessageEvent;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.model.DetailItemModel;
import com.mobike.uilibrary.view.OnItemClickLitener;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * RecycleView 适配器
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/19 下午4:34
 */
public class BaseTextRecycleAdapter extends RecyclerView.Adapter<BaseTextRecycleAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<DetailItemModel> mList;
    private OnItemClickLitener onItemClickLitener;

    public BaseTextRecycleAdapter(Context context, List<DetailItemModel> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(R.layout.item_text_dialog, parent, false));

        Log.d("RecycleView", "当前MyViewHolder=" + holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String title = mList.get(position).name;
        if (!TextUtils.isEmpty(title)) {
            holder.tv.setText(title);
        }

        holder.mRlCoordinator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickLitener != null) {
                    onItemClickLitener.onItemClick(v, holder.getAdapterPosition());
                    EventBus.getDefault().post(new MessageEvent("测试事件发送"));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        RelativeLayout mRlCoordinator;


        public MyViewHolder(View view) {
            super(view);
            tv = view.findViewById(R.id.title);
            mRlCoordinator = view.findViewById(R.id.widget_dialog_coordinator);
        }
    }

}

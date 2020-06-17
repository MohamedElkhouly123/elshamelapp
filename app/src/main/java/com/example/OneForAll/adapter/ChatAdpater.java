package com.example.OneForAll.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OneForAll.R;
import com.example.OneForAll.data.model.ChatModel;
import com.example.OneForAll.data.model.ClientData;
import com.example.OneForAll.utils.Chat.ClientOneListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.OneForAll.data.local.SharedPreferencesManger.LoadUserData;


public class ChatAdpater extends RecyclerView.Adapter<ChatAdpater.ViewHolder> {
    private List<ChatModel> listData;
    private Context context;
    private ClientOneListener listener;
    private ClientData clientData;

    public ChatAdpater(List<ChatModel> listData, Context context, Activity activity) {
        this.listData = listData;
        this.context = context;
        this.listener = listener;
        clientData = LoadUserData(activity);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).
                inflate(R.layout.chat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (listData.get(position).getUserId().equals(clientData.getUser().getId())) {
            holder.sender.setVisibility(View.VISIBLE);
            holder.sender.setText(listData.get(position).getMessage());
        } else {
            holder.receiver.setVisibility(View.VISIBLE);
            holder.receiver.setText(listData.get(position).getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_receiver)
        TextView receiver;
        @BindView(R.id.tv_sender)
        TextView sender;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
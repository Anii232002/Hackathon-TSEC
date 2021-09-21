package com.Hackthon.botshop.AdapterModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Hackthon.botshop.Models.MessagesModels;
import com.Hackthon.botshop.R;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<MessagesModels> messagesModels;
    Context context;

    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<MessagesModels> messagesModels, Context context) {
        this.messagesModels = messagesModels;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       if(viewType==SENDER_VIEW_TYPE){
           View view = LayoutInflater.from(context).inflate(R.layout.sample_senders,parent,false);
           return new SendersViewHolder(view);
       }
        View view = LayoutInflater.from(context).inflate(R.layout.sample_receiver,parent,false);
        return new ReceiverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MessagesModels msgModels = messagesModels.get(position);

        if(holder.getClass() == SendersViewHolder.class){
            ((SendersViewHolder)holder).sendersMsg.setText(msgModels.getMessage());
            Date date=new Date(msgModels.getTimestamp());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
            ((SendersViewHolder)holder).sendersTime.setText(simpleDateFormat.format(date));
        }
        else{
            ((ReceiverViewHolder)holder).receiverMsg.setText(msgModels.getMessage());
            Time time=new Time(msgModels.getTimestamp());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
            ((ReceiverViewHolder)holder).receiverTime.setText(simpleDateFormat.format(time));

        }

    }

    @Override
    public int getItemCount() {
        return messagesModels.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(messagesModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else {
            return RECEIVER_VIEW_TYPE;
        }
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{

        TextView receiverMsg , receiverTime;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);

            receiverMsg = itemView.findViewById(R.id.reciever_textviewGroup);
            receiverTime = itemView.findViewById(R.id.receiverTimeGroup);

        }
    }

    public class SendersViewHolder extends RecyclerView.ViewHolder{

        TextView sendersMsg , sendersTime;

        public SendersViewHolder(@NonNull View itemView) {
            super(itemView);

            sendersMsg = itemView.findViewById(R.id.sender_textview);
            sendersTime = itemView.findViewById(R.id.sendersTimeGroup);

        }

    }

}

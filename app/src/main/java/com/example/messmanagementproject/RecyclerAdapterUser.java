package com.example.messmanagementproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterUser extends RecyclerView.Adapter<RecyclerAdapterUser.viewHolder> {
    Context context;
    ArrayList<ControlModel> arrContact;
    RecyclerAdapterUser(Context context, ArrayList<ControlModel> arrContact){
        this.context=context;
        this.arrContact=arrContact;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.column_row, parent, false);
        viewHolder viewHolder=new viewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.imgContact.setImageResource(arrContact.get(position).img);
        holder.txtname.setText(arrContact.get(position).name);
        holder.txtadd.setText(arrContact.get(position).address);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = 0;
        }


    }

    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView txtname , txtadd;
        ImageView imgContact;
        public viewHolder(View itemView){
            super(itemView );
            txtname=itemView.findViewById(R.id.txtname);
            txtadd=itemView.findViewById(R.id.txtadd);
            imgContact=itemView.findViewById(R.id.imgContact);

            itemView.findViewById(R.id.visit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i6 = new Intent(context, MainActivity4User.class);
                    context.startActivity(i6);
                }
            });
        }

    }
}



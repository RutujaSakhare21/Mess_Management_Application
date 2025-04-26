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

public class RecyclerAdapterAdmin extends RecyclerView.Adapter<RecyclerAdapterAdmin.viewHolder> {

    static Context context;
    ArrayList<ControlModel> arrContact;

    public RecyclerAdapterAdmin(Context context, ArrayList<ControlModel> arrContact) {
        this.context = context;
        this.arrContact = arrContact;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.column_row, parent, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ControlModel model = arrContact.get(position);
        holder.imgContact.setImageResource(model.img);
        holder.txtname.setText(model.name);
        holder.txtadd.setText(model.address);

        // Optional layout margin fix
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = 0;
        }
    }

    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtname, txtadd;
        ImageView imgContact;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
            txtadd = itemView.findViewById(R.id.txtadd);
            imgContact = itemView.findViewById(R.id.imgContact);

            itemView.findViewById(R.id.visit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i5 = new Intent(context, MainActivity4Admin.class);
                    context.startActivity(i5);
                }
            });
        }
    }
}

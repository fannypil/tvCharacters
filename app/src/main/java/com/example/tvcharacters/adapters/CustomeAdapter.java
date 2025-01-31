package com.example.tvcharacters.adapters;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.provider.ContactsContract;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvcharacters.R;
import com.example.tvcharacters.models.Data;

import java.util.ArrayList;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.myViewHolder> {
   private ArrayList<Data> arr;
    public void setFilteredList(ArrayList<Data> filteredList) {
        this.arr=filteredList;
        notifyDataSetChanged();
    }
    public CustomeAdapter(ArrayList<Data> arr) {
    this.arr=arr;
    }


    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView description;
        ImageView imageViewItem;
        public myViewHolder(View itemView){
            super(itemView);
            userName= itemView.findViewById(R.id.textName);
            description =itemView.findViewById(R.id.textDescription);
            imageViewItem=itemView.findViewById(R.id.imageView);
            description.setMovementMethod(new ScrollingMovementMethod());
        }
    }

    @NonNull
    @Override
    public CustomeAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent,false);
        myViewHolder myViewHolder=new myViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.myViewHolder holder, int position) {

        holder.userName.setText(arr.get(position).getName());
        holder.description.setText(arr.get(position).getDescription());
        holder.imageViewItem.setImageResource(arr.get(position).getImage());
        //holder.description.setMovementMethod(new ScrollingMovementMethod());
        holder.description.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });
        holder.itemView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

            // Inflate Custom Layout
            LayoutInflater inflater = LayoutInflater.from(v.getContext());
            View dialogView = inflater.inflate(R.layout.custom_layout_alert_dialog_box, null);

            ImageView dialogImage = dialogView.findViewById(R.id.dialog_image);
            TextView dialogName = dialogView.findViewById(R.id.dialog_name);
            TextView dialogDescription = dialogView.findViewById(R.id.dialog_description);
            Button doneButton = dialogView.findViewById(R.id.done);

            dialogName.setText(arr.get(position).getName());
            dialogImage.setImageResource(arr.get(position).getImage());
            dialogDescription.setText(arr.get(position).getDescription());

            // Connect View to dialog
            builder.setView(dialogView);
            AlertDialog dialog = builder.create();

            // Add action to Done Button
            doneButton.setOnClickListener(view -> dialog.dismiss());

            // Display Dialog
            dialog.show();
        });

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }


}

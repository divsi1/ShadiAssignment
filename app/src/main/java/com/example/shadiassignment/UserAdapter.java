package com.example.shadiassignment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shadiassignment.Model.User;
import com.example.shadiassignment.Roomdb.UserEntity;

import java.util.List;

class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersViewHolder> {


    private Context mCtx;
    private List<UserEntity> userList;
    onAcceptedOrDeclinedReceived onAcceptedOrDeclinedReceived;

    public UserAdapter(Context mCtx, List<UserEntity> userList,onAcceptedOrDeclinedReceived onAcceptedOrDeclinedReceived) {
        this.mCtx = mCtx;
        this.userList = userList;
        this.onAcceptedOrDeclinedReceived=onAcceptedOrDeclinedReceived;
    }
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.shadi_card_view, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UsersViewHolder holder, final int position) {
        UserEntity t = userList.get(position);
        holder.textViewName.setText(t.getName());
        holder.textViewDOB.setText(t.getDob());
        holder.textViewGender.setText(t.getGender());
        holder.textViewPhone.setText(t.getPhone_number());
        holder.textEmail.setText(t.getEmail());
        String s=userList.get(position).getIsAcceptedOrDeclined();

        Glide.with(mCtx).
                load(t.getPicture()).
                into(holder.imageView);

        //divya
        if(userList.get(position).getIsAcceptedOrDeclined().equals("accepted"))
        {
            holder.btnDeclined.setVisibility(View.GONE);
            holder.btnAccepted.setVisibility(View.GONE);
            holder.textViewAccepted.setVisibility(View.GONE);
            holder.textViewDeclined.setVisibility(View.GONE);
            /////
            holder.textViewAccepted.setVisibility(View.VISIBLE);
        }

        if(userList.get(position).getIsAcceptedOrDeclined().equals("declined"))
        {
            holder.btnDeclined.setVisibility(View.GONE);
            holder.btnAccepted.setVisibility(View.GONE);
            holder.textViewAccepted.setVisibility(View.GONE);
            holder.textViewDeclined.setVisibility(View.GONE);
            ///////
            holder.textViewDeclined.setVisibility(View.VISIBLE);
        }
        //divya

        if(s.equals("---"))
        {
           Log.d("no value","no value");
            holder.btnDeclined.setVisibility(View.VISIBLE);
            holder.btnAccepted.setVisibility(View.VISIBLE);
            holder.textViewDeclined.setVisibility(View.GONE);
            holder.textViewAccepted.setVisibility(View.GONE);
           holder.btnAccepted.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   userList.get(position).setIsAcceptedOrDeclined("accepted");
                   holder.btnDeclined.setVisibility(View.GONE);
                   holder.textViewDeclined.setVisibility(View.GONE);
                   holder.textViewAccepted.setVisibility(View.VISIBLE);
                   holder.btnAccepted.setVisibility(View.GONE);
                   onAcceptedOrDeclinedReceived.updatedUserEntity(userList.get(position));
               }
           });

           holder.btnDeclined.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   userList.get(position).setIsAcceptedOrDeclined("declined");
                   holder.btnAccepted.setVisibility(View.GONE);
                   holder.textViewAccepted.setVisibility(View.GONE);
                   holder.btnDeclined.setVisibility(View.GONE);
                   holder.textViewDeclined.setVisibility(View.VISIBLE);
                   onAcceptedOrDeclinedReceived.updatedUserEntity(userList.get(position));

               }
           });

        }

    }

    @Override
    public int getItemCount() {
        if(userList.size()!=0)
        {
            return userList.size();
        }
        return 0;
    }



    class UsersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textViewName,textViewGender,textViewPhone,textEmail,textViewDOB;
        Button btnAccepted,btnDeclined;
        TextView textViewAccepted,textViewDeclined;

        public UsersViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_profile_image);
            textViewName = itemView.findViewById(R.id.tv_name);
            textViewPhone = itemView.findViewById(R.id.tv_phone);
            textViewGender = itemView.findViewById(R.id.tv_gender);
            textEmail=itemView.findViewById(R.id.tv_email);
            textViewDOB=itemView.findViewById(R.id.tv_dob);
            btnAccepted=itemView.findViewById(R.id.btn_accept);
            btnDeclined=itemView.findViewById(R.id.btn_decline);
            textViewAccepted=itemView.findViewById(R.id.tv_accept);
            textViewDeclined=itemView.findViewById(R.id.tv_decline);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }
}

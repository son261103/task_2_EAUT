package com.example.bai_kiem_tra_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bai_kiem_tra_2.R;
import com.example.bai_kiem_tra_2.model.User;
import com.example.bai_kiem_tra_2.model.BMICalculator;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private List<User> userList;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public UserListAdapter(Context context, List<User> userList, OnItemClickListener listener) {
        this.context = context;
        this.userList = userList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user, listener);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void updateUserList(List<User> newUserList) {
        this.userList = newUserList;
        notifyDataSetChanged();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewDetails;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewUserName);
            textViewDetails = itemView.findViewById(R.id.textViewUserDetails);
        }

        void bind(final User user, final OnItemClickListener listener) {
            textViewName.setText(user.getName());

            float bmi = BMICalculator.calculateBMI(user.getWeight(), user.getHeight());
            String bmiStatus = BMICalculator.getHealthStatus(bmi);

            String details = String.format("Tuổi: %d, Chiều cao: %.2fm, Cân nặng: %.1fkg\nBMI: %.1f (%s)",
                    user.getAge(), user.getHeight(), user.getWeight(), bmi, bmiStatus);
            textViewDetails.setText(details);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(user);
                }
            });
        }
    }
}
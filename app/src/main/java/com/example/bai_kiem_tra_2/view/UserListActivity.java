package com.example.bai_kiem_tra_2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bai_kiem_tra_2.R;
import com.example.bai_kiem_tra_2.adapter.UserListAdapter;
import com.example.bai_kiem_tra_2.controller.UserController;
import com.example.bai_kiem_tra_2.model.User;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUsers;
    private UserListAdapter adapter;
    private UserController userController;
    private MaterialButton buttonAddNew, buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        buttonAddNew = findViewById(R.id.buttonAddNew);
        buttonBack = findViewById(R.id.buttonBack);

        userController = new UserController(this);
        userController.open();

        setupRecyclerView();
        loadUsers();

        buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupRecyclerView() {
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserListAdapter(this, null, new UserListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                showUserDetails(user);
            }
        });
        recyclerViewUsers.setAdapter(adapter);
    }

    private void loadUsers() {
        List<User> users = userController.getAllUsers();
        adapter.updateUserList(users);
    }

    private void showUserDetails(User user) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("USER_ID", user.getId());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUsers(); // Reload users when returning to this activity
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userController.close();
    }
}
package com.example.bai_kiem_tra_2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bai_kiem_tra_2.R;
import com.example.bai_kiem_tra_2.controller.UserController;
import com.example.bai_kiem_tra_2.model.BMICalculator;
import com.example.bai_kiem_tra_2.model.User;
import com.google.android.material.button.MaterialButton;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewName, textViewAge, textViewBMI, textViewStatus, textViewWarning, textViewIdealWeight;
    private MaterialButton buttonBack, buttonViewList;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize views
        textViewName = findViewById(R.id.textViewName);
        textViewAge = findViewById(R.id.textViewAge);
        textViewBMI = findViewById(R.id.textViewBMI);
        textViewStatus = findViewById(R.id.textViewStatus);
        textViewWarning = findViewById(R.id.textViewWarning);
        textViewIdealWeight = findViewById(R.id.textViewIdealWeight);
        buttonBack = findViewById(R.id.buttonBack);
        buttonViewList = findViewById(R.id.buttonViewList);

        userController = new UserController(this);
        userController.open();

        long userId = getIntent().getLongExtra("USER_ID", -1);
        if (userId != -1) {
            displayResult(userId);
        }

        // Set click listeners for buttons
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // This will close the current activity and return to the previous one
            }
        });

        buttonViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, UserListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayResult(long userId) {
        User user = userController.getUser(userId);
        if (user != null) {
            float bmi = BMICalculator.calculateBMI(user.getWeight(), user.getHeight());

            textViewName.setText("Họ tên: " + user.getName());
            textViewAge.setText("Tuổi: " + user.getAge());
            textViewBMI.setText(String.format("Chỉ số BMI: %.2f", bmi));
            textViewStatus.setText("Tình trạng: " + BMICalculator.getHealthStatus(bmi));
            textViewWarning.setText("Cảnh báo: " + BMICalculator.getHealthWarning(bmi));
            textViewIdealWeight.setText("Cân nặng lý tưởng: " +
                    BMICalculator.getIdealWeightRange(user.getHeight(), user.getGender()));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userController.close();
    }
}
package com.example.chatapp.views;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.chatapp.R;
import com.example.chatapp.viewmodel.MyViewModel;
import com.example.chatapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    MyViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        myViewModel=new ViewModelProvider(this).get(MyViewModel.class);

        ActivityLoginBinding activityLoginBinding=
                DataBindingUtil.setContentView(this, R.layout.activity_login);

        activityLoginBinding.setVModel(myViewModel);
    }
}
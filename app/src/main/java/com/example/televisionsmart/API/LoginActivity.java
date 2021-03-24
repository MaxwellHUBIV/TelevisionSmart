package com.example.televisionsmart.API;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.televisionsmart.MainActivity;
import com.example.televisionsmart.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {//Объявление переменных 
    EditText username, password;
    Button btnLogin,btnSignUp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.edemail);//Поле ввода логина 
        password = findViewById(R.id.edpass);//Поле ввода пароля 
        btnLogin = findViewById(R.id.loginbutton);//Кнопка "вход" 

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(username.getText().toString())||TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Username / Password Required", Toast.LENGTH_LONG).show();
                }else{
                    login();
                }
            }
        });

    }
    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(username.getText().toString());
        loginRequest.setPassword(password.getText().toString());
        Call<LoginResponce> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponce>()
        {
            @Override

            public void onResponse(Call<LoginResponce> call, Response<LoginResponce> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Добро пожаловать", Toast.LENGTH_LONG).show();
                    Intent intent;
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "Не правильный логин или пароль", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<LoginResponce> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Throwable"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }

        });
    }
}
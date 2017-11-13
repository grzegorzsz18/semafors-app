package com.semafors.grzegorz.semafors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    ConnectionService connectionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        connectionService = ConnectionService.getConnectionService();
    }

    public void login(View view){
        EditText login = (EditText)findViewById(R.id.inputLogin);
        EditText password = (EditText)findViewById(R.id.inputPassword);
        connectionService.login(login.getText().toString(), password.getText().toString(), this);
    }

    public void goToTheMainPage(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}

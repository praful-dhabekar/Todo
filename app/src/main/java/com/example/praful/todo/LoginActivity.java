package com.example.praful.todo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = LoginActivity.class.getSimpleName();
    private Button loginButton;
    private EditText passwordEditText;
    private EditText usernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText)findViewById(R.id.edit_text_username);
        passwordEditText = (EditText)findViewById(R.id.edit_text_password);

        loginButton = (Button)findViewById(R.id.btn_login);

        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        boolean isError = false;

        if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){

        }
        if (TextUtils.isEmpty(username)){
            usernameEditText.setError(getString(R.string.required_field));
            isError = true;
        }
        if (TextUtils.isEmpty(password)){
            passwordEditText.setError(getString(R.string.required_field));
            isError = true;
        }
        if (!isError){
            login(username, password);
        }
    }

    private void login(String username, String password) {

        AsyncTask<String, Integer, Boolean> asyncTask = new AsyncTask<String, Integer, Boolean>() {
            int i;
            @Override
            protected Boolean doInBackground(String... strings) {
                String username = strings[0];
                String password = strings[1];

                for (i =0; i<100; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(i);
                }
                return username.equals("abc") && password.equals("abc");
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                loginButton.setText("Logging..");
                Log.d(TAG, "Progress:"+ i);
            }

            @Override
            protected void onPostExecute(Boolean logged) {
                super.onPostExecute(logged);
                loginButton.setEnabled(true);
                if (logged){

                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, TodoListActivity.class);
                    startActivity(intent);
                    finish();

                }
                else {
                    Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loginButton.setEnabled(false);
            }
        };
        asyncTask.execute(username,password);
    }
}

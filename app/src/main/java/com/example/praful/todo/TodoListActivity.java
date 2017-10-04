package com.example.praful.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class TodoListActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout){

            finish();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.action_add){
            Intent intent = new Intent(getApplicationContext(), AddTodoActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK ){

                Todo todo = (Todo) data.getSerializableExtra(AddTodoActivity.TODO);
                Toast.makeText(getApplicationContext(), "Result Okay Content:"+
                        todo.content, Toast.LENGTH_LONG).show();

            }
            else if (requestCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Result Canceled!", Toast.LENGTH_SHORT).show();
            }
        }
    }


package com.android2taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText editDesc;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);
        task = (Task) getIntent().getSerializableExtra("task");
        if (task != null) {
            editTitle.setText(task.getTitle());
            editDesc.setText(task.getDesc());
        }

    }

    public void onClick(View view) {
        String title = editTitle.getText().toString().trim();
        String desc = editDesc.getText().toString().trim();
//        Intent intent = new Intent();
        if (task != null) {
            task.setTitle(title);
            task.setDesc(desc);
            App.getDatabase().taskDao().update(task);
        } else {
            task = new Task(title, desc);
            App.getDatabase().taskDao().insert(task);
        }

//        intent.putExtra("task", task);
//        setResult(RESULT_OK, intent);
        finish();

    }
}

package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import personal.nfl.permission.support.util.AbcPermission;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AbcPermission.install(this);
        readFile();
    }

    public void readFile() {
        Toast.makeText(this, "readFile", Toast.LENGTH_SHORT).show();
    }
}

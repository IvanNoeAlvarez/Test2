package com.test.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.test.test.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {
    ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("nombre",binding.editNombre.getText().toString());
        setResult(RESULT_OK, i);
        finish();
    }
}

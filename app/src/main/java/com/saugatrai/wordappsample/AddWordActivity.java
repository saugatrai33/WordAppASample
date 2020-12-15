package com.saugatrai.wordappsample;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddWordActivity extends AppCompatActivity {

    public static final String KEY_TEXT = "inputText";

    private static final String TAG = AddWordActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        final EditText inputText = findViewById(R.id.text_word);
        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = inputText.getText().toString().trim();
                Log.d(TAG, "Input Word:: " + word);
                Intent intent = new Intent();
                if (!TextUtils.isEmpty(word)) {
                    intent.putExtra(KEY_TEXT, word);
                    setResult(RESULT_OK, intent);
                } else {
                    setResult(RESULT_CANCELED, intent);
                }
                finish();
            }
        });
    }
}
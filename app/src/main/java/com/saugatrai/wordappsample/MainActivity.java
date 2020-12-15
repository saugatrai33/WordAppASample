package com.saugatrai.wordappsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    private static final int NEW_ACTIVITY_REQUEST_CODE = 1;

    private WordViewModel wordViewModel;
    private WordAdapter adapter;
    private ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView wordList = findViewById(R.id.word_list);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this);
        wordList.setLayoutManager(layoutManager);
        adapter = new WordAdapter();
        adapter.setWords(new ArrayList<Word>());
        wordList.setAdapter(adapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newTask = new Intent(MainActivity.this,
                        AddWordActivity.class);
                startActivityForResult(newTask, NEW_ACTIVITY_REQUEST_CODE);
            }
        });

        viewModelFactory = new ViewModelFactory(this);
        wordViewModel = new ViewModelProvider(this, viewModelFactory)
                .get(WordViewModel.class);

        wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter.setWords(words);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String word = data.getStringExtra(AddWordActivity.KEY_TEXT);
            Log.d(TAG, "New Word:: " + word);
            Word newWord = new Word();
            newWord.setWord(word);
            wordViewModel.insertWord(newWord);
        } else {
            Toast.makeText(this, "Word not saved",
                    Toast.LENGTH_SHORT).show();
        }
    }



}
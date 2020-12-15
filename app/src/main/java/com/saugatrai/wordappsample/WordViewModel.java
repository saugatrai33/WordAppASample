package com.saugatrai.wordappsample;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class WordViewModel extends ViewModel {

    private WordRepository repository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(@NonNull Context context) {
        repository = new WordRepository(context);
        mAllWords = repository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insertWord(Word word) {
        repository.insertWord(word);
    }

}

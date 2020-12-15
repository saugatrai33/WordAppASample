package com.saugatrai.wordappsample;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Context context) {
        WordDatabase database = WordDatabase.getInstance(context);
        mWordDao = database.wordDao();
        mAllWords = mWordDao.mAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insertWord(final Word word) {
        WordDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                mWordDao.insertWord(word);
            }
        });
    }

}

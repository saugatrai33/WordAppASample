package com.saugatrai.wordappsample;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    private static final int NUMBER_OF_THREADS = 4;
    private static volatile WordDatabase INSTANCE;
    private static final String DB_NAME = "db_word";

    public static ExecutorService executorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static WordDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            synchronized (WordDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordDatabase.class, DB_NAME)
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
package com.saugatrai.wordappsample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_word")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getWord() {
        return mWord;
    }

    public void setWord(@NonNull String mWord) {
        this.mWord = mWord;
    }
}

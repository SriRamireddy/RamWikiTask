package com.ramireddy.ramwikitask.roomdata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ramireddy.ramwikitask.model.Page;

import java.util.List;

public class WikiViewModel extends AndroidViewModel {
    private WikiRepository mRepository;

    private LiveData<List<Page>> mAllWords;
    private LiveData<Integer> rowcount;

    public WikiViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WikiRepository(application);
        mAllWords = mRepository.getmAllWords();
        rowcount=mRepository.getGetrowcount();
    }

    public LiveData<List<Page>> getAllWords() { return mAllWords; }

    public LiveData<Integer> getRowcount() {
        return rowcount;
    }

    public void insert(Page word) { mRepository.insert(word); }
}

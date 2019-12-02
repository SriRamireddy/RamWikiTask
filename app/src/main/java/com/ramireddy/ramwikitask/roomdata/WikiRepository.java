package com.ramireddy.ramwikitask.roomdata;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ramireddy.ramwikitask.model.Page;

import java.util.List;

public class WikiRepository
{

    private WikiDAO mWordDao;
    private LiveData<List<Page>> mAllWords;
    private LiveData<Integer> getrowcount;
    public WikiRepository(Application application) {
        WikiRoomDataBase db = WikiRoomDataBase.getDatabase(application);
        mWordDao = db.wikiDAO();
        mAllWords = mWordDao.getAllWords();
        getrowcount=mWordDao.getRowCount();
    }

    public LiveData<List<Page>> getmAllWords() {
        return mAllWords;
    }

    public LiveData<Integer> getGetrowcount() {
        return getrowcount;
    }

    public void insert (Page word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Page, Void, Void> {

        private WikiDAO mAsyncTaskDao;

        insertAsyncTask(WikiDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Page... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}

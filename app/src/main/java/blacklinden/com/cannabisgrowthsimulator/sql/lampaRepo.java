package blacklinden.com.cannabisgrowthsimulator.sql;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import blacklinden.com.cannabisgrowthsimulator.pojo.Lampa;

public class lampaRepo {

    private LampaDao lampaDao;
    private LiveData<List<Lampa>> lampak;



    lampaRepo(Application application){
        VegtermekRoomDatabase db = VegtermekRoomDatabase.getDatabase(application);

        lampaDao = db.lampaDao();
        lampak = lampaDao.getAll();

    }

    LiveData<List<Lampa>> getAll(){
        return lampak;
    }

    void insert (Lampa adat){
        new lampaRepo.insertAsyncTask(lampaDao).execute(adat);
    }

    void deleteAll(){
        new lampaRepo.deleteAllAsync(lampaDao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<Lampa, Void, Void> {

        private LampaDao mAsyncTaskDao;

        insertAsyncTask(LampaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Lampa... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllAsync extends AsyncTask<Lampa, Void, Void> {

        private LampaDao mAsyncTaskDao;

        deleteAllAsync(LampaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Lampa... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }


}

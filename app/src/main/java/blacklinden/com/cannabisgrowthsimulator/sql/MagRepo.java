package blacklinden.com.cannabisgrowthsimulator.sql;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import blacklinden.com.cannabisgrowthsimulator.pojo.MagEntity;

public class MagRepo {

    private MagDao magDao;
    private LiveData<List<MagEntity>> data;



    MagRepo(Application application){
        VegtermekRoomDatabase db = VegtermekRoomDatabase.getDatabase(application);

        magDao = db.magDao();
        data = magDao.getAll();

    }

    LiveData<List<MagEntity>> getAll(){
        return data;
    }

    void insert (MagEntity adat){
        new MagRepo.insertAsyncTask(magDao).execute(adat);
    }

    void deleteAll(){
        new MagRepo.deleteAllAsync(magDao).execute();
    }

    void update(String fajta,int mennyi){new MagRepo.updateAsync(magDao,fajta,mennyi).execute();}

    private static class insertAsyncTask extends AsyncTask<MagEntity, Void, Void> {

        private MagDao mAsyncTaskDao;

        insertAsyncTask(MagDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MagEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllAsync extends AsyncTask<MagEntity, Void, Void> {

        private MagDao mAsyncTaskDao;

        deleteAllAsync(MagDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MagEntity... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class updateAsync extends AsyncTask<MagEntity, Void, Void> {

        private MagDao mAsyncTaskDao;
        private String fajta;
        private int mennyi;

        updateAsync(MagDao dao,String fajta,int mennyi) {
            mAsyncTaskDao = dao;
            this.fajta=fajta;
            this.mennyi=mennyi;
        }

        @Override
        protected Void doInBackground(final MagEntity... params) {

                mAsyncTaskDao.updateMag(fajta,mennyi);

            return null;
        }
    }
}

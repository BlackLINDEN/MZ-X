package blacklinden.com.cannabisgrowthsimulator.sql;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import blacklinden.com.cannabisgrowthsimulator.pojo.Lampa;
import blacklinden.com.cannabisgrowthsimulator.pojo.NutriEntity;

public class NutriRepo {

    private NutriDao nutriDao;
    private LiveData<List<NutriEntity>> nutrik;



    NutriRepo(Application application){
        VegtermekRoomDatabase db = VegtermekRoomDatabase.getDatabase(application);

        nutriDao = db.nutriDao();
        nutrik = nutriDao.getAll();

    }

    LiveData<List<NutriEntity>> getAll(){
        return nutrik;
    }

    void insert (NutriEntity adat){
        new NutriRepo.insertAsyncTask(nutriDao).execute(adat);
    }

    void deleteAll(){
        new NutriRepo.deleteAllAsync(nutriDao).execute();
    }

    void update(String fajta,int mennyi){new NutriRepo.updateAsync(nutriDao,fajta,mennyi).execute();}

    private static class insertAsyncTask extends AsyncTask<NutriEntity, Void, Void> {

        private NutriDao mAsyncTaskDao;

        insertAsyncTask(NutriDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final NutriEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllAsync extends AsyncTask<Lampa, Void, Void> {

        private NutriDao mAsyncTaskDao;

        deleteAllAsync(NutriDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Lampa... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class updateAsync extends AsyncTask<NutriEntity, Void, Void> {

        private NutriDao mAsyncTaskDao;
        private String fajta;
        private int mennyi;

        updateAsync(NutriDao dao,String fajta,int mennyi) {
            mAsyncTaskDao = dao;
            this.fajta=fajta;
            this.mennyi=mennyi;
        }

        @Override
        protected Void doInBackground(final NutriEntity... params) {

            if(mennyi>0)
            mAsyncTaskDao.updateNute(fajta,mennyi);
            else
            mAsyncTaskDao.deleteIt(fajta);

            return null;
        }
    }

}

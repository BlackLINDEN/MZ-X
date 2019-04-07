package blacklinden.com.cannabisgrowthsimulator.sql;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import androidx.annotation.Nullable;
import blacklinden.com.cannabisgrowthsimulator.pojo.Lampa;
import blacklinden.com.cannabisgrowthsimulator.pojo.Vegtermek;

public class Repo {

       private VegtermekDao vegtermekDao;
    private LiveData<List<Vegtermek>> vegtermekek;



    Repo(Application application){
        VegtermekRoomDatabase db = VegtermekRoomDatabase.getDatabase(application);

        vegtermekDao = db.adatDao();
        vegtermekek = vegtermekDao.getAll();

    }

    LiveData<List<Vegtermek>> getAll(){
        return vegtermekek;
    }

    void insert (Vegtermek adat){
        new insertAsyncTask(vegtermekDao).execute(adat);
    }

    void deleteAll(){
        new deleteAllAsync(vegtermekDao).execute();
    }

    void update(int id,float mennyi){new updateAsync(vegtermekDao,id,mennyi).execute();}


    private static class insertAsyncTask extends AsyncTask<Vegtermek, Void, Void> {

        private VegtermekDao mAsyncTaskDao;

        insertAsyncTask(VegtermekDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Vegtermek... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllAsync extends AsyncTask<Vegtermek, Void, Void> {

        private VegtermekDao mAsyncTaskDao;

        deleteAllAsync(VegtermekDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Vegtermek... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class updateAsync extends AsyncTask<Vegtermek, Void, Void> {

        private VegtermekDao mAsyncTaskDao;
        private int id;
        private float mennyi;

        updateAsync(VegtermekDao dao,int id,float mennyi) {
            mAsyncTaskDao = dao;
            this.id=id;
            this.mennyi=mennyi;
        }

        @Override
        protected Void doInBackground(final Vegtermek... params) {
            if(mennyi>0)
                mAsyncTaskDao.updateVegtermek(id,mennyi);
            else
                mAsyncTaskDao.deleteIt(id);
            return null;
        }
    }



}

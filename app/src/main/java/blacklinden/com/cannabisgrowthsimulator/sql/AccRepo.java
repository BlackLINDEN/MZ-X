package blacklinden.com.cannabisgrowthsimulator.sql;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;
import blacklinden.com.cannabisgrowthsimulator.pojo.AccessoryEntity;


public class AccRepo {

    private AccessoryDao dao;
    private LiveData<List<AccessoryEntity>> data;



    AccRepo(Application application){
        VegtermekRoomDatabase db = VegtermekRoomDatabase.getDatabase(application);

        dao = db.accessoryDao();
        data = dao.getAll();

    }

    LiveData<List<AccessoryEntity>> getAll(){
        return data;
    }

    void insert (AccessoryEntity adat){
        new AccRepo.insertAsyncTask(dao).execute(adat);
    }

    void deleteAll(){
        new AccRepo.deleteAllAsync(dao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<AccessoryEntity, Void, Void> {

        private AccessoryDao mAsyncTaskDao;

        insertAsyncTask(AccessoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AccessoryEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllAsync extends AsyncTask<AccessoryEntity, Void, Void> {

        private AccessoryDao mAsyncTaskDao;

        deleteAllAsync(AccessoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AccessoryEntity... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

}

package blacklinden.com.cannabisgrowthsimulator.sql;

        import android.arch.persistence.db.SupportSQLiteDatabase;
        import android.arch.persistence.room.Database;
        import android.arch.persistence.room.Room;
        import android.arch.persistence.room.RoomDatabase;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.support.annotation.NonNull;

        import blacklinden.com.cannabisgrowthsimulator.pojo.AccessoryEntity;
        import blacklinden.com.cannabisgrowthsimulator.pojo.Lampa;
        import blacklinden.com.cannabisgrowthsimulator.pojo.MagEntity;
        import blacklinden.com.cannabisgrowthsimulator.pojo.NutriEntity;
        import blacklinden.com.cannabisgrowthsimulator.pojo.ScoreEntity;
        import blacklinden.com.cannabisgrowthsimulator.pojo.Vegtermek;

@Database(entities = {Vegtermek.class,Lampa.class,NutriEntity.class,AccessoryEntity.class,MagEntity.class,ScoreEntity.class}, version = 8)
abstract class VegtermekRoomDatabase extends RoomDatabase {
     abstract VegtermekDao adatDao();
     abstract LampaDao lampaDao();
     abstract NutriDao nutriDao();
     abstract AccessoryDao accessoryDao();
     abstract MagDao magDao();
     abstract ScoreDao scrDao();

    private static volatile VegtermekRoomDatabase INSTANCE;

    static VegtermekRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VegtermekRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            VegtermekRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();


                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onCreate (@NonNull SupportSQLiteDatabase db){
                    super.onCreate(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final VegtermekDao mDao;
        private final LampaDao lDao;
        private final NutriDao nDao;
        private final AccessoryDao aDao;
        private final MagDao mgDao;
        private final ScoreDao scrDao;

        PopulateDbAsync(VegtermekRoomDatabase db) {
            mDao = db.adatDao();
            lDao = db.lampaDao();
            nDao = db.nutriDao();
            aDao = db.accessoryDao();
            mgDao = db.magDao();
            scrDao = db.scrDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            //mDao.deleteAll();
            //Vegtermek vegtermek = new Vegtermek("kkk",10);
            //mDao.insert(vegtermek);
           /* vegtermek = new Vegtermek("Weed",12);
            mDao.insert(vegtermek);*/

            lDao.insert(new Lampa("a"));
            nDao.insert(new NutriEntity("BioGrow",15));
            aDao.insert(new AccessoryEntity("a"));
            mgDao.insert(new MagEntity("a",10));
            mgDao.insert(new MagEntity("b",10));
            scrDao.insert(new ScoreEntity("NOOB",10000));
            mDao.insert(new Vegtermek("weed1",30));
            return null;
        }
    }
}


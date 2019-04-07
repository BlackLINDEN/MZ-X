package blacklinden.com.cannabisgrowthsimulator.sql;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import blacklinden.com.cannabisgrowthsimulator.pojo.MagEntity;

@Dao
public interface MagDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    void insert(MagEntity entity);

    @Query("DELETE FROM mg_table")
    void deleteAll();

    @Query("SELECT * from mg_table ORDER BY fajta ASC")
    LiveData<List<MagEntity>> getAll();

    @Query("UPDATE mg_table SET mennyi = :mennyi_adat WHERE fajta = :magFajta")
    void updateMag(String magFajta,int mennyi_adat);

    @Query("DELETE FROM mg_table WHERE fajta = :magFajta")
    void deleteIt(String magFajta);


}

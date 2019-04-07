package blacklinden.com.cannabisgrowthsimulator.sql;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import blacklinden.com.cannabisgrowthsimulator.pojo.Vegtermek;

@Dao
public interface VegtermekDao {

    @Insert ( onConflict = OnConflictStrategy.REPLACE)
    void insert(Vegtermek vegtermek);

    @Query("DELETE FROM vgtrmk_table")
    void deleteAll();

    @Query("SELECT * from vgtrmk_table ORDER BY fajta ASC")
    LiveData<List<Vegtermek>> getAll();

    @Query("UPDATE vgtrmk_table SET mennyi = :mennyi_adat WHERE id = :iid")
    void updateVegtermek(int iid,float mennyi_adat);

    @Query("DELETE FROM vgtrmk_table WHERE id = :stashId")
    void deleteIt(int stashId);

}

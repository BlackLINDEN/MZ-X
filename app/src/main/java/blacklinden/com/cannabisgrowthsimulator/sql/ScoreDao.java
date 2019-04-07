package blacklinden.com.cannabisgrowthsimulator.sql;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import blacklinden.com.cannabisgrowthsimulator.pojo.ScoreEntity;

@Dao
public interface ScoreDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    void insert(ScoreEntity scoreEntity);

    @Query("SELECT * from scr_table")
    LiveData<ScoreEntity> get();

    @Query("UPDATE scr_table SET score = :score")
    void updateScore(int score);

    @Query("UPDATE scr_table SET rank = :rank")
    void updateRank(String rank);

    @Query("UPDATE scr_table SET xp = :xp")
    void updateXp(int xp);

}

package blacklinden.com.cannabisgrowthsimulator.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "scr_table")
public class ScoreEntity {


    @PrimaryKey
    @NonNull
    public String name="scr";


    @ColumnInfo
    @NonNull
    private String rank;

    @ColumnInfo(name = "xp")
    private int xp=0;

    @ColumnInfo(name="score")
    private int score;

    public ScoreEntity(@NonNull String rank, int score) {this.rank = rank; this.score=score;}

    @NonNull
    public String getRank(){return this.rank;}

    public int getScore(){return this.score;}

    public int getXp(){return this.xp;}


    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

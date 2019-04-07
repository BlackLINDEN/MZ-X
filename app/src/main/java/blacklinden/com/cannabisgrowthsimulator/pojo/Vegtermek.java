package blacklinden.com.cannabisgrowthsimulator.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "vgtrmk_table")
public class Vegtermek {


    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "fajta")
    private String fajta;

    @ColumnInfo(name="mennyi")
    private float mennyi;

    public Vegtermek(@NonNull String fajta,float mennyi) {this.fajta = fajta; this.mennyi=mennyi;}

    public String getFajta(){return this.fajta;}

    public float getMennyi(){return this.mennyi;}
}

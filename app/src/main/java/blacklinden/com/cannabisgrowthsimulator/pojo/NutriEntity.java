package blacklinden.com.cannabisgrowthsimulator.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ntr_table")
public class NutriEntity {

    @PrimaryKey
    @NonNull
    private String fajta;

    @ColumnInfo(name="mennyi")
    private int mennyi;

    public NutriEntity(@NonNull String fajta,int mennyi) { this.fajta = fajta; this.mennyi=mennyi; }

    @NonNull
    public String getFajta(){return this.fajta;}

    public int getMennyi(){ return this.mennyi;}
}

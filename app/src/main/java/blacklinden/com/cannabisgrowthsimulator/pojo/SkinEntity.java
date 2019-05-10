package blacklinden.com.cannabisgrowthsimulator.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "skn_table")
public class SkinEntity {


    @PrimaryKey
    @NonNull
    private String fajta;

    public SkinEntity(@NonNull String fajta) {this.fajta = fajta;}

    @NonNull
    public String getFajta(){return this.fajta;}

}

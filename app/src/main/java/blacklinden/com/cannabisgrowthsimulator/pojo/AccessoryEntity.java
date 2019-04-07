package blacklinden.com.cannabisgrowthsimulator.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "acc_table")
public class AccessoryEntity {

    @PrimaryKey
    @NonNull
    private String fajta;

    public AccessoryEntity(@NonNull String fajta) {this.fajta = fajta;}

    @NonNull
    public String getFajta(){return this.fajta;}
}

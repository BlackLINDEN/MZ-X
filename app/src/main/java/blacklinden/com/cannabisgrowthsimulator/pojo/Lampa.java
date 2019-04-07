package blacklinden.com.cannabisgrowthsimulator.pojo;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "lmpa_table")
public class Lampa {


    @PrimaryKey
    @NonNull
    private String fajta;

    public Lampa(@NonNull String fajta) {this.fajta = fajta;}

    @NonNull
    public String getFajta(){return this.fajta;}

}

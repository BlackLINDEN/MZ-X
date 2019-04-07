package blacklinden.com.cannabisgrowthsimulator.sql;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import androidx.annotation.NonNull;
import blacklinden.com.cannabisgrowthsimulator.pojo.MagEntity;

public class MagVM extends AndroidViewModel {

    private MagRepo repo;
    private LiveData<List<MagEntity>> data;

    public MagVM(@NonNull Application application) {
        super(application);
        repo = new MagRepo(application);
        data = repo.getAll();
    }

    public LiveData<List<MagEntity>> getAll(){
        return data;
    }

    public void insert(MagEntity adat){repo.insert(adat);}

    public void deleteAll(){repo.deleteAll();}

    public void update(String fajta,int mennyi){repo.update(fajta,mennyi);}


}

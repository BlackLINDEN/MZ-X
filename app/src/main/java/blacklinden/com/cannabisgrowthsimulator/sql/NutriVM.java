package blacklinden.com.cannabisgrowthsimulator.sql;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import androidx.annotation.NonNull;

import blacklinden.com.cannabisgrowthsimulator.pojo.NutriEntity;

public class NutriVM extends AndroidViewModel {

    private NutriRepo repo;
    private LiveData<List<NutriEntity>> data;

    public NutriVM(@NonNull Application application) {
        super(application);
        repo = new NutriRepo(application);
        data = repo.getAll();
    }

    public LiveData<List<NutriEntity>> getAll(){
        return data;
    }

    public void insert(NutriEntity adat){repo.insert(adat);}

    public void deleteAll(){repo.deleteAll();}

    public void update(String fajta,int mennyi){repo.update(fajta,mennyi);}


}

package blacklinden.com.cannabisgrowthsimulator.sql;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import androidx.annotation.NonNull;
import blacklinden.com.cannabisgrowthsimulator.pojo.Vegtermek;

public class VegtermekViewModel extends AndroidViewModel {

    private Repo repo;
    private LiveData<List<Vegtermek>> vegtermekek;

    public VegtermekViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
        vegtermekek = repo.getAll();
    }

    public LiveData<List<Vegtermek>> getAll(){
        return vegtermekek;
    }

    public void insert(Vegtermek adat){repo.insert(adat);}

    public void deleteAll(){repo.deleteAll();}

    public void update(int id,float mennyi){repo.update(id,mennyi);}

}

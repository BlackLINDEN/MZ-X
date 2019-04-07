package blacklinden.com.cannabisgrowthsimulator.sql;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import androidx.annotation.NonNull;
import blacklinden.com.cannabisgrowthsimulator.pojo.ScoreEntity;

public class ScoreVM extends AndroidViewModel {

    private ScoreRepo repo;
    private LiveData<ScoreEntity> data;

    public ScoreVM(@NonNull Application application) {
        super(application);
        repo = new ScoreRepo(application);
        data = repo.get();
    }

    public LiveData<ScoreEntity> get() {
        return data;
    }

    public void insert(ScoreEntity adat) {
        repo.insert(adat);
    }

    public void updateScore(int score) {
        repo.updateScore(score);
    }

    public void updateXP(int xp) {
        repo.updateXp(xp);
    }
}

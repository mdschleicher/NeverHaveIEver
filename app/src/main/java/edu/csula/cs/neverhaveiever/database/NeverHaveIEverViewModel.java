package edu.csula.cs.neverhaveiever.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import edu.csula.cs.neverhaveiever.models.User;


public class NeverHaveIEverViewModel extends AndroidViewModel {

    private  NeverHaveIEverRepository neverHaveIEverRepository;

    private LiveData<List<User>> mUser;

    public NeverHaveIEverViewModel (Application application) {
        super(application);
        neverHaveIEverRepository = new NeverHaveIEverRepository(application);
        mUser = neverHaveIEverRepository.getAllUsers();
    }

    public void CreateUser(String name, String photo) {
        neverHaveIEverRepository.CreateUser(name, photo);
    }

    public LiveData<List<User>> getAllUser() {
        return mUser;
    }


}

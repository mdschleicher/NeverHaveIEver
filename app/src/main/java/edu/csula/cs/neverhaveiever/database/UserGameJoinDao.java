package edu.csula.cs.neverhaveiever.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.csula.cs.neverhaveiever.models.Game;
import edu.csula.cs.neverhaveiever.models.User;
import edu.csula.cs.neverhaveiever.models.UserGameJoin;

@Dao
public interface UserGameJoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserGameJoins(UserGameJoin... userGameJoins);

    @Update
    public void updateUserGameJoins(UserGameJoin... userGameJoins);

    @Delete
    public void deleteUserGameJoins(UserGameJoin... userGameJoins);

    @Query("Select * FROM usergamejoin")
    public List<UserGameJoin> loadAllUserGameJoins();

    @Query("Select * FROM user WHERE id IN (Select userId From usergamejoin where gameId = :gameId)")
    public LiveData<List<User>> loadUserInfoForGame(int gameId);

    @Query("Select * From game Where id IN (Select gameId From usergamejoin where userId = :userId)")
    public LiveData<List<Game>> loadGameInfoForUser(int userId);
}

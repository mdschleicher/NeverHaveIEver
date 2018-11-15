package edu.csula.cs.neverhaveiever.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.csula.cs.neverhaveiever.models.Game;
import edu.csula.cs.neverhaveiever.models.User;

@Dao
public interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGame(Game... games);

    @Update
    public void updateGames(Game... games);

    @Delete
    public void deleteGames(Game... games);

    @Query("Select * FROM game")
    public List<Game> loadAllGame();
}

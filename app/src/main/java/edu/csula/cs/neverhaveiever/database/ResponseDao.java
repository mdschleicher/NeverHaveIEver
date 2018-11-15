package edu.csula.cs.neverhaveiever.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.csula.cs.neverhaveiever.models.Response;

@Dao
public interface ResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertResponse(Response... Response);

    @Update
    public void updateResponse(Response... responses);

    @Delete
    public void deleteResponse(Response... responses);

    @Query("Select * FROM response")
    public List<Response> loadAllResponse();

    @Query("Select * From response Where questionId = :questionId")
    public LiveData<List<Response>> loadResponsesForQuestion(int questionId);
}

package edu.csula.cs.neverhaveiever.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.csula.cs.neverhaveiever.models.Question;
import edu.csula.cs.neverhaveiever.models.User;

@Dao
public interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuestion(Question... questions);

    @Update
    public void updateQuestions(Question... questions);

    @Delete
    public void deleteQuestions(Question... questions);

    @Query("Select * FROM question")
    public List<Question> loadAllQuestions();

    @Query("Select * From question Where gameId = :gameId")
    public LiveData<List<Question>> loadQuestionsForGame(int gameId);
}

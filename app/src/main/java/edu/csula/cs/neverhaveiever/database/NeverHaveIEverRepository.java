package edu.csula.cs.neverhaveiever.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

import edu.csula.cs.neverhaveiever.models.Game;
import edu.csula.cs.neverhaveiever.models.Question;
import edu.csula.cs.neverhaveiever.models.Response;
import edu.csula.cs.neverhaveiever.models.User;

public class NeverHaveIEverRepository {
    private GameDao mGameDao;
    private QuestionDao mQuestionDao;
    private ResponseDao mResponseDao;
    private UserDao mUserDao;
    private UserGameJoinDao mUserGameJoinDao;

    public NeverHaveIEverRepository(Application application){
        NeverHaveIEverDatabase db = NeverHaveIEverDatabase.getDatabase(application);
        mGameDao = db.gameDao();
        mQuestionDao = db.questionDao();
        mResponseDao = db.responseDao();
        mUserDao = db.userDao();
        mUserGameJoinDao = db.userGameJoinDao();
    }

    LiveData<List<Game>> getAllGamesForUser(int userID) {
        return mUserGameJoinDao.loadGameInfoForUser(userID);
    }

    LiveData<List<User>> getAllUserForGame(int gameID) {
        return mUserGameJoinDao.loadUserInfoForGame(gameID);
    }

    LiveData<List<Question>> getAllQuestionForGame(int gameID) {
        return mQuestionDao.loadQuestionsForGame(gameID);
    }

    LiveData<List<Response>> getAllResponseForQuestion(int questionID) {
        return mResponseDao.loadResponsesForQuestion(questionID);
    }

    //Add async task here to udpate the room from the remote db
}

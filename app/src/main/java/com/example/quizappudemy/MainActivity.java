package com.example.quizappudemy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  final int USER_PROGRESS=10;
    private TextView textQuestions;
    private final String SCORE_KEY="SCORE";
    private final String INDEX_KEY="INDEX";
    private Button   btnTrue,btnWrong;
    private int MQuestionsIndex;
    private int MQuizQuestions;
    private ProgressBar mprogressBar;
    private TextView mStatsTextView;
    private int mUserScore;
    //To add all the questin in the list that why we have been use the array
    private QuizModel [] quizcollection = new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,false),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,false),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,false),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            mUserScore= savedInstanceState.getInt(SCORE_KEY);
            MQuestionsIndex=savedInstanceState.getInt(INDEX_KEY);
        }else{
            mUserScore=0;
            MQuestionsIndex=0;

        }
        textQuestions=findViewById(R.id.textQuestions);
        mprogressBar=findViewById(R.id.progressBar);
        mStatsTextView=findViewById(R.id.remainingQuestions);
        //To acess the index of the array
        QuizModel q1 = quizcollection[MQuestionsIndex];
        MQuizQuestions=q1.getmQuestions();
        textQuestions.setText(MQuizQuestions);
        btnTrue=findViewById(R.id.btnTrue);
        btnWrong=findViewById(R.id.btnFalse);
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              evaluateUserGuess(true);
                changeQuestionOnButtonClick();
            }
        });
        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateUserGuess(false);
                changeQuestionOnButtonClick();
             }
        });
    }
    //To change the question that is been next
    private void changeQuestionOnButtonClick(){
        MQuestionsIndex=(MQuestionsIndex+1)%10;
        if(MQuestionsIndex==0){
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("The quiz is finished");
            quizAlert.setMessage("Your Score is : " +mUserScore);
            quizAlert.setPositiveButton("Finish the quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  finish();
                }
            });
            quizAlert.show();
        }
        MQuizQuestions=quizcollection[MQuestionsIndex].getmQuestions();
        textQuestions.setText(MQuizQuestions);
        mprogressBar.incrementProgressBy(USER_PROGRESS);
        mStatsTextView.setText(mUserScore+"");
    }
    //To check the progress bar
    private void evaluateUserGuess(boolean userGuess){
        boolean currentQuestionAnswer = quizcollection[MQuestionsIndex].ismAnswers();
        if(currentQuestionAnswer==userGuess) {
            Toast.makeText(getApplicationContext(),R.string.correct_toast_message,Toast.LENGTH_SHORT).show();
            mUserScore= mUserScore+1;
        }
        else{
            Toast.makeText(getApplicationContext(),R.string.incoreect_toast_message,Toast.LENGTH_SHORT).show();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(MainActivity.this, "The version of the app is:"+BuildConfig.VERSION_CODE,Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_KEY,mUserScore);
        outState.putInt(INDEX_KEY,MQuestionsIndex);
    }
}
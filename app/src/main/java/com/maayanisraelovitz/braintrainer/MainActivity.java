package com.maayanisraelovitz.braintrainer;

import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    GridLayout gameBoard;
    TextView timeLeftTextView;
    TextView correctAnswersTextView;
    TextView rightWrong;
    TextView exercise;
    int left;
    int right;
    int rightAnswer;
    int numOfQuestions =0;
    int numOfRightAnswers=0;
    Random random;
    TextView answer1;
    TextView answer2;
    TextView answer3;
    TextView answer4;

    public void go(View view){
        findViewById(R.id.goButton).setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(RelativeLayout.VISIBLE);
        newGame();
    }

    public void playAgain (View view){
        numOfQuestions= 0;
        numOfRightAnswers= 0;
        rightWrong.setVisibility(View.INVISIBLE);
        findViewById(R.id.playAgainButton).setVisibility(View.INVISIBLE);
        answer1.setEnabled(true);
        answer2.setEnabled(true);
        answer3.setEnabled(true);
        answer4.setEnabled(true);
        newGame();
    }

    public void choose (View view){
        if (Integer.parseInt(view.getTag().toString())==rightAnswer) {
            rightWrong.setText("Correct!");
            numOfRightAnswers++;
        }
        else{
            rightWrong.setText("Wrong!");
        }
        numOfQuestions++;
        correctAnswersTextView.setText(Integer.toString(numOfRightAnswers)+"/"+Integer.toString(numOfQuestions));
        if (rightWrong.getVisibility()==View.INVISIBLE)
            rightWrong.setVisibility(View.VISIBLE);
        prepareQuestion();
    }

    public void prepareQuestion(){
        left = random.nextInt(20 - 1 + 1) + 1;
        right = random.nextInt(20 - 1 + 1) + 1;
        exercise.setText(left+" + "+right);
        rightAnswer = random.nextInt(4 - 1 + 1) + 1;
        int leftPlusRight = left+right;
        if (rightAnswer==1)
            answer1.setText(Integer.toString(leftPlusRight));
        else
            answer1.setText(randomAnswer(leftPlusRight));
        if (rightAnswer==2)
            answer2.setText(Integer.toString(leftPlusRight));
        else
            answer2.setText(randomAnswer(leftPlusRight));
        if (rightAnswer==3)
            answer3.setText(Integer.toString(leftPlusRight));
        else
            answer3.setText(randomAnswer(leftPlusRight));
        if (rightAnswer==4)
            answer4.setText(Integer.toString(leftPlusRight));
        else
            answer4.setText(randomAnswer(leftPlusRight));
    }

    public String randomAnswer(int rightAnswer){
        int incorrectAnswer= random.nextInt(40 - 1 + 1) + 1;
        while (incorrectAnswer == rightAnswer)
            incorrectAnswer= random.nextInt(40 - 1 + 1) + 1;
        return Integer.toString(incorrectAnswer);
    }

    public void newGame(){
        correctAnswersTextView.setText(numOfRightAnswers+"/"+numOfQuestions);
        new CountDownTimer((30*1000)+100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftTextView.setText((String.valueOf(millisUntilFinished/1000))+"s");
            }

            @Override
            public void onFinish() {
                timeLeftTextView.setText("0s");
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);
                rightWrong.setText("Your score: "+correctAnswersTextView.getText().toString());
                rightWrong.setVisibility(View.VISIBLE);
                findViewById(R.id.playAgainButton).setVisibility(View.VISIBLE);

            }
        }.start();
        prepareQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.gameLayout);
        gameBoard =(GridLayout) findViewById(R.id.answersLayout);
        timeLeftTextView = (TextView) findViewById(R.id.timeLeftTextView);
        correctAnswersTextView = (TextView) findViewById(R.id.correctAnswersTextView);
        rightWrong = (TextView) findViewById(R.id.rightWrong);
        exercise = (TextView) findViewById(R.id.exerciseTextView);
        random = new Random();
        answer1 = (TextView) findViewById(R.id.answer1);
        answer2 = (TextView) findViewById(R.id.answer2);
        answer3 = (TextView) findViewById(R.id.answer3);
        answer4 = (TextView) findViewById(R.id.answer4);
    }
}

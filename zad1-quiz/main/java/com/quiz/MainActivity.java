package com.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;

    private TextView questionTextView;

    private Question[] questions = {
            new Question(R.string.question1,false),
            new Question(R.string.question2,true),
            new Question(R.string.question3,false),
            new Question(R.string.question4,false),
            new Question(R.string.question5,true)
    };
    private int idx = 0;
    private int score = 0;
    private boolean answered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answered) checkAnswer(true);
                answered = true;
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answered) checkAnswer(false);
                answered = true;
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answered)
                {
                    idx = (idx +1)%questions.length;
                    setNextQuestion();
                }
                answered = false;
            }
        });
    }
    private void checkAnswer(boolean userAnswer)
    {
        int resultMessageId = 0;
        if(userAnswer == questions[idx].getTrueAnswer())
        {
            resultMessageId = R.string.correctAnswer;
            score++;
        }
        else
        {
            resultMessageId = R.string.wrongAnswer;
        }
        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
        if(idx == questions.length-1)
        {
            Toast.makeText(this,"Twój wynik: " + score,Toast.LENGTH_SHORT).show();
            score = 0;
        }
    }
    private void setNextQuestion()
    {
        questionTextView.setText(questions[idx].getQuestionId());
    }
}
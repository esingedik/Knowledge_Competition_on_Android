package com.example.asus.uygulama2;

import java.util.List;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivityLiterature extends Activity {
    List<QuestionActivity> quesList;
    QuestionActivity currentQ;
    TextView txtQuestion;
    int qid;
    String trueScore, falseScore;
    RadioButton rda, rdb, rdc, rdd;
    Button butAnswer,butFinish;
    String[] userAnswer = new String[10];
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Bundle b = getIntent().getExtras();
        qid = b.getInt("qid");

        DbHelperLiterature db=new DbHelperLiterature(this);
        quesList=db.getAllQuestions();
        currentQ=quesList.get(qid);

        txtQuestion = (TextView)findViewById(R.id.textView1);

        rda = (RadioButton)findViewById(R.id.radio0);
        rdb = (RadioButton)findViewById(R.id.radio1);
        rdc = (RadioButton)findViewById(R.id.radio2);
        rdd = (RadioButton)findViewById(R.id.radio3);

        butAnswer=(Button)findViewById(R.id.submit_button);
        butFinish = (Button)findViewById(R.id.finish_button);

        setQuestionView();

        butAnswer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
                RadioButton answer= (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                String correctAns = new String();
                String correctChoise = new String();

                if(currentQ.getAnswer().equals("A"))
                {
                    correctAns = currentQ.getOptionA();
                    correctChoise = "A";
                }

                else if(currentQ.getAnswer().equals("B"))
                {
                    correctAns = currentQ.getOptionB();
                    correctChoise = "B";
                }

                else if(currentQ.getAnswer().equals("C"))
                {
                    correctAns = currentQ.getOptionC();
                    correctChoise = "C";
                }

                else if(currentQ.getAnswer().equals("D"))
                {
                    correctAns = currentQ.getOptionD();
                    correctChoise = "D";
                }

                System.out.println("Dogru Cevap: " + correctAns + ", Senin Cevabın: " + answer.getText());

                if(correctAns.equals(answer.getText())) {
                    preferences = getSharedPreferences("LiteratureTrueScore", MODE_PRIVATE);
                    editor = preferences.edit();

                    trueScore = preferences.getString("LiteratureTrueScore", "");
                    trueScore = String.valueOf(Integer.parseInt(trueScore) + 1);
                    editor.putString("LiteratureTrueScore", trueScore);
                    editor.commit();

                    System.out.println("trueScore:" + trueScore + "\n");
                    userAnswer[qid] = correctChoise;
                }

                else
                {
                    preferences = getSharedPreferences("LiteratureFalseScore",MODE_PRIVATE);
                    editor = preferences.edit();

                    falseScore =  preferences.getString("LiteratureFalseScore", "");
                    falseScore = String.valueOf(Integer.parseInt(falseScore) + 1);
                    editor.putString("LiteratureFalseScore", falseScore);
                    editor.commit();

                    System.out.println("falseScore:" + falseScore + "\n");

                    if(answer.getText().equals(currentQ.getOptionA()))
                    {
                        userAnswer[qid]= "A";
                    }

                    else if(answer.getText().equals(currentQ.getOptionB()))
                    {
                        userAnswer[qid]= "B";
                    }

                    else if(answer.getText().equals(currentQ.getOptionC()))
                    {
                        userAnswer[qid]= "C";
                    }

                    else if(answer.getText().equals(currentQ.getOptionD()))
                    {
                        userAnswer[qid]= "D";
                    }
                }
                Intent animalIntent = new Intent(getApplicationContext(),LiteratureActivity.class);
                startActivity(animalIntent);
                finish();
            }
        });

        butFinish.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(getApplicationContext(),ResultActivityLiterature.class);
                startActivity(resultIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_question, menu);
        return true;
    }

    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getQuestion());
        rda.setText(currentQ.getOptionA());
        rdb.setText(currentQ.getOptionB());
        rdc.setText(currentQ.getOptionC());
        rdd.setText(currentQ.getOptionD());
    }
}
package com.example.asus.uygulama2;

/*  ESİN GEDİK
    14011501
    Mobil Programlama Dersi 2. Ödevi
*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class AnimalsActivity extends Activity {

    private RecyclerView recycler_view;
    private String [] answer_list = new String[10];
    private List<String> animals_questions;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        recycler_view.setLayoutManager(layoutManager);

        animals_questions = new ArrayList<>();

        animals_questions.add("Dünyanın en hızlı koşan kuşu hangisidir?");
        animals_questions.add("Fok balığının diğer adı nedir?");
        animals_questions.add("Geviş getirmeyen memeli hayvanların ayağındaki tek tırnağa ne isim verilir?");
        animals_questions.add("Hangi kuş başını 270 derece döndürebilir?");
        animals_questions.add("Hem karada hem suda yaşayan canlılara ne ad verilir?");
        animals_questions.add("Suda yaşayan tek hücreli canlıya ne ad verilir?");
        animals_questions.add("Zıplayamayan tek memeli hayvan hangisidir?");
        animals_questions.add("Özellikle develerin sırtında bulunan, uzun sürede su ihtiyacına karşı dayanıklı olmasına yarayan organın adı nedir?");
        animals_questions.add("Hangi hayvan kış uykusuna yatmaz?");
        animals_questions.add("Hangisi bir memelidir?");

        QuestionListAdapter adapter_items = new QuestionListAdapter(animals_questions, new CustomItemClickListener()
        {
            @Override
            public void onItemClick(View v, int position)
            {
                Log.d("position", "Tıklanan Pozisyon:" + position);
                String category = animals_questions.get(position);

                Intent quizIntent = new Intent(getApplicationContext(),QuizActivityAnimal.class);//Sorunun açılması için QuizActiviysi çağırılır.
                Bundle b = new Bundle(); //Hangi sorunun açılacağını göstermek için bundle yaratılır.
                b.putInt("qid", position); //position referans olarak verilir.
                quizIntent.putExtras(b);
                startActivity(quizIntent);
                finish();
            }
        });

        recycler_view.setHasFixedSize(true);
        recycler_view.setAdapter(adapter_items);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
    }
}
package com.example.asus.uygulama2;

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

public class LiteratureActivity extends Activity {

    private RecyclerView recycler_view;
    private List<String> literature_questions;

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

        literature_questions = new ArrayList<>();

        literature_questions.add("Keşanlı Ali Destanı hangi yazara aittir?");
        literature_questions.add("Kişi ya da toplumdaki aksaklıkları eleştirmek amacıyla yazılan şiirlere ne ad verilir?");
        literature_questions.add("Kişinin kendi yaşam öyküsünü anlattığı yazı türüne ne ad verilir?");
        literature_questions.add("Kurtuluş Savaşı'nı doğrudan işleyen ilk roman hangisidir?");
        literature_questions.add("Manas Destanı hangi millete aittir?");
        literature_questions.add("Nobel Edebiyat Ödülü’nü kazanan ilk Türk yazar kimdir?");
        literature_questions.add("Hayvanların konuşturulduğu yazılı türün adı nedir?");
        literature_questions.add("Hangisi bir şair değildir?");
        literature_questions.add("Hangisi şiir ile ilgili bir terim değildir?");
        literature_questions.add("Gazete ve dergilerin belirli köşelerinde çıkan, olayları yazarın gözüyle inceleyen, yorumlayan kısa yazı türüne ne denir?");

        QuestionListAdapter adapter_items = new QuestionListAdapter(literature_questions, new CustomItemClickListener()
        {
            @Override
            public void onItemClick(View v, int position)
            {
                Log.d("position", "Tıklanan Pozisyon:" + position);
                String category = literature_questions.get(position);

                Intent quizIntent = new Intent(getApplicationContext(),QuizActivityLiterature.class);//Sorunun açılması için QuizActiviysi çağırılır.
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
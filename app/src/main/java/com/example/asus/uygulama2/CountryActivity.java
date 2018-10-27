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

public class CountryActivity extends Activity {

    private RecyclerView recycler_view;
    private List<String> country_questions;

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

        country_questions = new ArrayList<>();

        country_questions.add("88 yıl sonra Amerika Başkanı Barack Obama'nın gittiği ülke hangisidir?");
        country_questions.add("Magna Carta hangi ülkenin kralıyla yapılmış bir sözleşmedir?");
        country_questions.add("Hangisi tarihteki Türk devletlerinden biri değildir?");
        country_questions.add("Hangi ülke Asya kıtasındadır?");
        country_questions.add("Hangisi tarihteki Rus devlet yöneticilerinden biridir?");
        country_questions.add("Priştine hangi ülkenin başkentidir?");
        country_questions.add("Birleşik Arap Emirlikleri'nin başkenti neresidir?");
        country_questions.add("Fransa ile İngiltere'yi birbirine bağlayan tünelin adı nedir?");
        country_questions.add("Dünya kahve üretiminde ilk sırada hangi ülke vardır?");
        country_questions.add("Pisa Kulesi nerededir?");

        QuestionListAdapter adapter_items = new QuestionListAdapter(country_questions, new CustomItemClickListener()
        {
            @Override
            public void onItemClick(View v, int position)
            {
                Log.d("position", "Tıklanan Pozisyon:" + position);
                String category = country_questions.get(position);
                System.out.println("position: " + position + "\n");
                Intent quizIntent = new Intent(getApplicationContext(),QuizActivityCountry.class);//Sorunun açılması için QuizActiviysi çağırılır.
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
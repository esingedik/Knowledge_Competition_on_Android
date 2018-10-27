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

public class HealthActivity extends Activity {

    private RecyclerView recycler_view;
    private List<String> health_questions;

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

        health_questions = new ArrayList<>();

        health_questions.add("Aspirinin hammaddesi nedir?");
        health_questions.add("Alkolün en fazla zarar verdiği organ hangisidir?");
        health_questions.add("Bir adı da kanbilim olan, kanın yapısı, işlevi ve hastalıklarını konu edinen tıp dalına ne ad verilir?");
        health_questions.add("Bugün bile adına tıp yemini edilen İspanyol tıp bilgini kimdir?");
        health_questions.add("Hangi kan grubu herkesten kan alabilir?");
        health_questions.add("En çok koyun ve sığırlarda görülen, deri yoluyla insanlara da bulaşan, tehlikeli bir hastalık olan, karakabarcık adı da verilen hastalığın adı nedir?");
        health_questions.add("Canlılardaki en küçük yapı birimine ne isim verilir?");
        health_questions.add("Guatr hastalığına sebep olan bez hangisidir?");
        health_questions.add("Vücudumuzdaki en küçük kemiklerin bulunduğu organ hangisidir?");
        health_questions.add("Kanın pıhtılaşmasını aşağıdakilerden hangisi sağlar?");

        QuestionListAdapter adapter_items = new QuestionListAdapter(health_questions,new CustomItemClickListener()
        {
            @Override
            public void onItemClick(View v, int position)
            {
                Log.d("position", "Tıklanan Pozisyon:" + position);
                String category = health_questions.get(position);

                Intent quizIntent = new Intent(getApplicationContext(),QuizActivityHealth.class);//Sorunun açılması için QuizActiviysi çağırılır.
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
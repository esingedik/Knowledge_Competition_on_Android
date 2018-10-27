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

public class CultureActivity extends Activity {

    private RecyclerView recycler_view;
    private List<String> culture_questions;

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

        culture_questions = new ArrayList<>();

        culture_questions.add("Yüzölçümü en küçük ilimiz hangisidir?");
        culture_questions.add("'Gözlerin gözlerime değince felaketim olurdu, ağlardım' dizelerinin sahibi ünlü şair, düşünür, romancı, gazeteci ve eleştirmen kimdir?");
        culture_questions.add("Ülkemizde Pamukkale’de görülen, bazı kireçli su kaynaklarının çevresinde biriken kireçtaşına ne ad verilir?");
        culture_questions.add("Gemi çalışanlarına ne ad verilir?");
        culture_questions.add("Cevizin yeşil kabuğuna ne ad verilir?");
        culture_questions.add("Şirketlerin ortaklarına ödedikleri kar payına ne denir?");
        culture_questions.add("Aşağıdakilerden hangisi yeterlilik anlamına gelir?");
        culture_questions.add("Herhangi bir müzik aletini ustalıkla çalanlara ne ad verilir?");
        culture_questions.add("Değişik ülkelerden birbirinin dilini bilmeyen insanların anlaşabilmelerini sağlamak amacıyla yaratılmış, yapay bir dil olan ve adını İspanyolca umut sözcüğünden almış olan dil hangisidir?");
        culture_questions.add("Fırlatıldığında geri dönen, kıvrık, ince bir ağaçtan yapılma, Avustralya yerlilerinin av için kullandığı silah nedir?");

        QuestionListAdapter adapter_items = new QuestionListAdapter(culture_questions, new CustomItemClickListener()
        {
            @Override
            public void onItemClick(View v, int position)
            {
                Log.d("position", "Tıklanan Pozisyon:" + position);
                String category = culture_questions.get(position);

                Intent quizIntent = new Intent(getApplicationContext(),QuizActivityCulture.class);//Sorunun açılması için QuizActiviysi çağırılır.
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
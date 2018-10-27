package com.example.asus.uygulama2;

/*  ESİN GEDİK
    14011501
    Mobil Programlama Dersi 2. Ödevi
*/

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends Activity {

    private RecyclerView recycler_view;
    private List<String> category_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        recycler_view.setLayoutManager(layoutManager);

        category_list = new ArrayList<>();

        category_list.add("HAYVANLAR");
        category_list.add("EDEBİYAT");
        category_list.add("SAĞLIK");
        category_list.add("GENEL KÜLTÜR");
 //       category_list.add("ÜLKELER");

        CategoryAdapter adapter_items = new CategoryAdapter(category_list, new CustomItemClickListener()
        {
            @Override
            public void onItemClick(View v, int position)
            {
                Log.d("position", "Tıklanan Pozisyon:" + position);
                String category = category_list.get(position);

//                if(position==0)
//                {
//                    Intent countryIntent = new Intent(getApplicationContext(),CountryActivity.class);
//                    startActivity(countryIntent);
//                    finish();
//                }

                if(position==0)
                {
                    Intent animalIntent = new Intent(getApplicationContext(),AnimalsActivity.class);
                    startActivity(animalIntent);
                    finish();
                }

                else if(position==1)
                {
                    Intent literatureIntent = new Intent(getApplicationContext(),LiteratureActivity.class);
                    startActivity(literatureIntent);
                    finish();
                }

                else if(position==2)
                {
                    Intent healthIntent = new Intent(getApplicationContext(),HealthActivity.class);
                    startActivity(healthIntent);
                    finish();
                }

                else if(position==3)
                {
                    Intent cultureIntent = new Intent(getApplicationContext(),CultureActivity.class);
                    startActivity(cultureIntent);
                    finish();
                }

             }
        });
        recycler_view.setHasFixedSize(true);
        recycler_view.setAdapter(adapter_items);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
    }
}
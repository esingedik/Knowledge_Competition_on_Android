package com.example.asus.uygulama2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InfoActivity extends AppCompatActivity {

    Button updateButton,beginButton;
    EditText nameEditText,surnameEditText,mailEditText,usernameEditText,passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info); // Gorsel ile activity eslestiriliyor.

        nameEditText = (EditText) findViewById(R.id.name_et);
        surnameEditText = (EditText) findViewById(R.id.surname_et);
        mailEditText = (EditText) findViewById(R.id.mail_et);
        usernameEditText = (EditText) findViewById(R.id.username_et);
        passwordEditText = (EditText) findViewById(R.id.password_et);
        updateButton = (Button) findViewById(R.id.update_b);
        beginButton = (Button) findViewById(R.id.begin_b);

        SharedPreferences preferences = getSharedPreferences("REGISTERS",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String name = preferences.getString("Name",""); //birinci parametreye değişkenin adını, ikinci parametreye ise eğer öyle bir değişken yoksa geriye döndüreceği default değeri yazıyoruz.
        String surname = preferences.getString("Surname","");
        String mail = preferences.getString("Mail","");
        String username = preferences.getString("Username","");
        String password = preferences.getString("Password","");

        nameEditText.setText(name);
        surnameEditText.setText(surname);
        mailEditText.setText(mail);
        usernameEditText.setText(username);
        passwordEditText.setText(password);

        updateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            String newName = nameEditText.getText().toString();
            String newSurname = surnameEditText.getText().toString();
            String newMail = mailEditText.getText().toString();
            String newUsername = usernameEditText.getText().toString();
            String newPassword = passwordEditText.getText().toString();

            if(newName.matches("") || newSurname.matches("") || newMail.matches("") || newUsername.matches("") || newPassword.matches(""))
            {//Veriler Boş  ise
                AlertDialog.Builder alert = new AlertDialog.Builder(InfoActivity.this);
                alert.setTitle("UYARI");
                alert.setMessage("Eksik Bilgileri Lütfen Tamamlayınız!");
                alert.setPositiveButton("Tamam", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,int which) { }
                });
                alert.show();
            }

            else
            {//veriler boş değilse kayıt işlemine geçilir
                SharedPreferences preferences = getSharedPreferences("REGISTERS",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit(); //preferences editor nesnesi referansı .prefernces nesnesine veri ekleyip cıkarmak için

                editor.putString("Name", newName); //isim değeri
                editor.putString("Surname", newSurname);
                editor.putString("Mail", newMail);
                editor.putString("Username", newUsername);
                editor.putString("Password", newPassword);

                editor.commit();//yapılan değişiklikler kaydedilmesi için editor nesnesinin commit() metodu çağırılır.
                //Değerlerimizi sharedPreferences a kaydettik.Artık bu bilgiler ile giriş yapabiliriz.

                AlertDialog.Builder info = new AlertDialog.Builder(InfoActivity.this);
                info.setTitle("BİLGİ");
                info.setMessage("Bilgileriniz Başarıyla Güncellendi!");
                info.setPositiveButton("Tamam", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,int which) { }
                });
                info.show();
            }
            }
        });

        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences preferencesCT = getSharedPreferences("CountryTrueScore",MODE_PRIVATE);
                SharedPreferences.Editor editorCT = preferencesCT.edit();
                editorCT.putString("CountryTrueScore","0");
                editorCT.commit();

                SharedPreferences preferencesCF = getSharedPreferences("CountryFalseScore",MODE_PRIVATE);
                SharedPreferences.Editor editorCF = preferencesCF.edit();
                editorCF.putString("CountryFalseScore","0");
                editorCF.commit();

                SharedPreferences preferencesAT = getSharedPreferences("AnimalTrueScore",MODE_PRIVATE);
                SharedPreferences.Editor editorAT = preferencesAT.edit();
                editorAT.putString("AnimalTrueScore","0");
                editorAT.commit();

                SharedPreferences preferencesAF = getSharedPreferences("AnimalFalseScore",MODE_PRIVATE);
                SharedPreferences.Editor editorAF = preferencesAF.edit();
                editorAF.putString("AnimalFalseScore","0");
                editorAF.commit();

                SharedPreferences preferencesLT = getSharedPreferences("LiteratureTrueScore",MODE_PRIVATE);
                SharedPreferences.Editor editorLT = preferencesLT.edit();
                editorLT.putString("LiteratureTrueScore","0");
                editorLT.commit();

                SharedPreferences preferencesLF = getSharedPreferences("LiteratureFalseScore",MODE_PRIVATE);
                SharedPreferences.Editor editorLF = preferencesLF.edit();
                editorLF.putString("LiteratureFalseScore","0");
                editorLF.commit();

                SharedPreferences preferencesHT = getSharedPreferences("HealthTrueScore",MODE_PRIVATE);
                SharedPreferences.Editor editorHT = preferencesHT.edit();
                editorHT.putString("HealthTrueScore","0");
                editorHT.commit();

                SharedPreferences preferencesHF = getSharedPreferences("HealthFalseScore",MODE_PRIVATE);
                SharedPreferences.Editor editorHF = preferencesHF.edit();
                editorHF.putString("HealthFalseScore","0");
                editorHF.commit();

                SharedPreferences preferencesGT = getSharedPreferences("GenelCultureTrueScore",MODE_PRIVATE);
                SharedPreferences.Editor editorGT = preferencesGT.edit();
                editorGT.putString("GenelCultureTrueScore","0");
                editorGT.commit();

                SharedPreferences preferencesGF = getSharedPreferences("GenelCultureFalseScore",MODE_PRIVATE);
                SharedPreferences.Editor editorGF = preferencesGF.edit();
                editorGF.putString("GenelCultureFalseScore","0");
                editorGF.commit();

                Intent category = new Intent(getApplicationContext(),CategoryActivity.class);
                startActivity(category);
                finish();
            }
        });
    }
}

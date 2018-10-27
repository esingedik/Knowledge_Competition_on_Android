package com.example.asus.uygulama2;

/*  ESİN GEDİK
    14011501
    Mobil Programlama Dersi 2. Ödevi
*/

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
    Button loginButton;
    EditText inputUsername,inputPassword;
    TextView register;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences("REGISTERS",MODE_PRIVATE);
        editor = preferences.edit(); //preferences editor nesnesi referansı .preferences nesnesine veri ekleyip cıkarmak için

        //Burda sharedPreferencas üzerine kayıtlı login değerini alıyoruz.
        //Login değeri doğru giriş yapıldığında veya kayıt olduğunda true olarak kaydedilir
        //Amacı ise kullanıcı uygulamadan cıkarken direk çıkıs demeden cıktıysa yanı direk home veya back tusuyla uygulamadan çıktıysa
        //Geri geldiğinde tekrar giriş bilgilerini istemeden anasayfaya yönlendiriyoruz
        //Bu değer ancak anasayfa üzerinde cıkış butonuna basılırsa diğer bilgiler silinmeden bu değer false yapılır
        //ve uygulamaya tekrar girildiğinde kayıt olurken kullandığı şifre ve emaili ister

        inputUsername = (EditText)findViewById(R.id.login_username);
        inputPassword = (EditText)findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.login_button);
        register = (TextView) findViewById(R.id.register_tv);

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent registerIntent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();

                if(username.matches("") || password.matches(""))
                {//bilgiler eksik   ise
                    AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                    alert.setTitle("UYARI˝");
                    alert.setMessage("Eksik Bilgileri Lütfen Tamamlayınız!");
                    alert.setPositiveButton("Tamam", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog,int which) { }
                    });
                    alert.show();
                }

                else
                {//Bilgiler doğru ise
                    String comparedUsername = preferences.getString("Username", "");//preferences objesinden kaydettiğimiz değerleri keyleri ile geri alıyoruz
                    String comparedPassword = preferences.getString("Password", "");

                    if(username.matches(comparedUsername) && password.matches(comparedPassword))
                    {//edittextlerden alınan şifre ve mail değerleri shared preferencesdan alınan değerlerle eşleşiyorsa
                        //Giriş başarılı ise sharedpreferences login değerini true yapıyoruz.
                        Intent infoIntent = new Intent(getApplicationContext(),InfoActivity.class);//Anasayfa aktivity si açılır
                        startActivity(infoIntent);
                        finish();
                    }

                    else
                    {//şifre ve mail uyuşmuyorsa hata bastırılır
                        AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                        alert.setTitle("HATA");
                        alert.setMessage("Mail veya Şifre Hatalı");
                        alert.setPositiveButton("Tamam", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog,int which) { }
                        });
                        alert.show();
                    }
                }
            }
        });
    }
}

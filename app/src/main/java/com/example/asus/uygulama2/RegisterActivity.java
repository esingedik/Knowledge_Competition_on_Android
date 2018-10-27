package com.example.asus.uygulama2;

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

public class RegisterActivity extends Activity{

    EditText newName, newSurname, newMail, newUsername, newPassword;
    TextView login;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        newName = (EditText)findViewById(R.id.new_name);
        newSurname = (EditText)findViewById(R.id.new_surname);
        newMail = (EditText)findViewById(R.id.new_mail);
        newUsername = (EditText)findViewById(R.id.new_username);
        newPassword = (EditText)findViewById(R.id.new_password);
        registerButton = (Button) findViewById(R.id.register_b);
        login = (TextView) findViewById(R.id.login_tv);

        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = newName.getText().toString();
                String surname = newSurname.getText().toString();
                String mail = newMail.getText().toString();
                String username = newUsername.getText().toString();
                String password = newPassword.getText().toString();

                if(name.matches("") || surname.matches("") || mail.matches("") || username.matches("") || password.matches(""))
                {//Veriler Boş  ise
                    AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
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

                    editor.putString("Name", name); //isim değeri
                    editor.putString("Surname", surname);
                    editor.putString("Mail", mail);
                    editor.putString("Username", username);
                    editor.putString("Password", password);

                    editor.commit();//yapılan değişiklikler kaydedilmesi için editor nesnesinin commit() metodu çağırılır.
                    //Değerlerimizi sharedPreferences a kaydettik.Artık bu bilgiler ile giriş yapabiliriz.
                    Intent i = new Intent(getApplicationContext(),InfoActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
            }
        });

    }
}

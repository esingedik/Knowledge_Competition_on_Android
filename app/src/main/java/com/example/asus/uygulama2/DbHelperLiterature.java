package com.example.asus.uygulama2;

/*  ESİN GEDİK
    14011501
    Mobil Programlama Dersi 2. Ödevi
*/

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelperLiterature extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "LiteratureQuiz";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; //correct option
    private static final String KEY_OPTA= "opta"; //option a
    private static final String KEY_OPTB= "optb"; //option b
    private static final String KEY_OPTC= "optc"; //option c
    private static final String KEY_OPTD= "optd"; //option d
    private SQLiteDatabase dbase;

    public DbHelperLiterature(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD +" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }

    private void addQuestions()
    {
        QuestionActivity q1=new QuestionActivity("Keşanlı Ali Destanı hangi yazara aittir?","Yaşar Kemal", "Haldun Taner", "Sabahattin Ali", "Aziz Nesin","B");
        this.addQuestion(q1);

        QuestionActivity q2=new QuestionActivity("Kişi ya da toplumdaki aksaklıkları eleştirmek amacıyla yazılan şiirlere ne ad verilir?","Satirik Şiir", "Didaktik Şiir", "Epik Şiir", "Pastoral Şiir","A");
        this.addQuestion(q2);

        QuestionActivity q3=new QuestionActivity("Kişinin kendi yaşam öyküsünü anlattığı yazı türüne ne ad verilir?","Hayat Hikayesi", "Biyografi", "Otobiyografi", "Anı","C");
        this.addQuestion(q3);

        QuestionActivity q4=new QuestionActivity("Kurtuluş Savaşı’nı doğrudan işleyen ilk roman nedir?","Türk’ün Ateşle İmtihanı", "Şu Çılgın Türkler", "Ateşten Gömlek", "Vatan Yahut Silistre","C");
        this.addQuestion(q4);

        QuestionActivity q5=new QuestionActivity("Manas Destanı hangi millete aittir?","Kırgızlar", "Oğuzlar", "Moğollar", "Artuklular","A");
        this.addQuestion(q5);

        QuestionActivity q6=new QuestionActivity("Nobel Edebiyat Ödülü’nü kazanan ilk Türk yazar kimdir?","Peyami Safa", "Orhan Pamuk", "Elif Şafak", "Zülfi Livaneli","B");
        this.addQuestion(q6);

        QuestionActivity q7=new QuestionActivity("Hayvanların konuşturulduğu yazılı türün adı nedir?","Tekerleme", "Fıkra", "Fabl", "Öykü","C");
        this.addQuestion(q7);

        QuestionActivity q8=new QuestionActivity("Hangisi bir şair değildir?","Atilla İlhan", "Edip Cansever", "Gülten Akın", "Osman Zeki Üngör","D");
        this.addQuestion(q8);

        QuestionActivity q9=new QuestionActivity("Hangisi şiir ile ilgili bir terim değildir?","Redif", "Ezgi", "Manzum", "Hece Ölçüsü","B");
        this.addQuestion(q9);

        QuestionActivity q10=new QuestionActivity("Gazete ve dergilerin belli köşelerinde çıkan, olayları yazarın gözüyle türlü yönlerden inceleyen, yorumlayan kısa yazı türüne ne denir?","Makale", "Röportaj", "Fıkra", "Söyleşi","A");
        this.addQuestion(q10);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }

    // Adding new question
    public void addQuestion(QuestionActivity quest)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQuestion());
        values.put(KEY_ANSWER, quest.getAnswer());
        values.put(KEY_OPTA, quest.getOptionA());
        values.put(KEY_OPTB, quest.getOptionB());
        values.put(KEY_OPTC, quest.getOptionC());
        values.put(KEY_OPTD, quest.getOptionD());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }

    public List<QuestionActivity> getAllQuestions()
    {
        List<QuestionActivity> quesList = new ArrayList<QuestionActivity>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                QuestionActivity quest = new QuestionActivity();
                quest.setID(cursor.getInt(0));
                quest.setQuestion(cursor.getString(1));
                quest.setAnswer(cursor.getString(2));
                quest.setOptionA(cursor.getString(3));
                quest.setOptionB(cursor.getString(4));
                quest.setOptionC(cursor.getString(5));
                quest.setOptionD(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}

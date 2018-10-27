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

public class DbHelperAnimals extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "AnimalQuiz";
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

    public DbHelperAnimals(Context context)
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
        QuestionActivity q1=new QuestionActivity("Dünyanın en hızlı koşan kuşu hangisidir?","Devekuşu", "Leylek", "Orman Tavuğu", "Turna","A");
        this.addQuestion(q1);

        QuestionActivity q2=new QuestionActivity("Fok balığının diğer adı nedir?","Mors", "Penguen", "Ayı Balığı", "Deniz Fili","C");
        this.addQuestion(q2);

        QuestionActivity q3=new QuestionActivity("Geviş getirmeyen memelilerin ayağındaki tek tırnağa verilen isim nedir?","Pati", "Toynak", "Pençe", "Tek Tırnak","B");
        this.addQuestion(q3);

        QuestionActivity q4=new QuestionActivity("Hangi kuş başını 270 derece döndürebilir?","Baykuş", "Flamingo", "Şahin", "Devekuşu","A");
        this.addQuestion(q4);

        QuestionActivity q5=new QuestionActivity("Hem karada hem suda yaşayan canlılara ne ad verilir?","Amfibikler", "Ökaryot", "Metamorfozlar", "Amipler","A");
        this.addQuestion(q5);

        QuestionActivity q6=new QuestionActivity("Suda yaşayan tek hücreli canlıya ne ad verilir?","Öglena", "Amip", "Paramezyum", "Mantar","B");
        this.addQuestion(q6);

        QuestionActivity q7=new QuestionActivity("Zıplayamayan tek memeli hayvan hangisidir?","Zürafa", "Gergedan", "Fil", "Yarasa","C");
        this.addQuestion(q7);

        QuestionActivity q8=new QuestionActivity("Özellikle develerin sırtında bulunan, uzun sürede su ihtiyacına karşı dayanıklı olmasına yarayan organın adı nedir?","Hendek", "Hörgüç", "Kese", "Oluk","B");
        this.addQuestion(q8);

        QuestionActivity q9=new QuestionActivity("Hangi hayvan kış uykusuna yatmaz?","Kaplumbağa", "Ayı", "Kurt", "Sincap","D");
        this.addQuestion(q9);

        QuestionActivity q10=new QuestionActivity("Hangisi bir memelidir?","Balina", "Köpekbalığı", "Penguen", "Martı","A");
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

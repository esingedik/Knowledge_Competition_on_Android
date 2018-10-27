package com.example.asus.uygulama2;

public class QuestionActivity {
    private int ID;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;

    public QuestionActivity()
    {
        ID=0;
        question="";
        optionA="";
        optionB="";
        optionC="";
        answer="";
    }

    public QuestionActivity(String question, String optionA, String optionB, String optionC, String optionD, String answer)
    {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }

    public int getID()
    {
        return ID;
    }

    public String getQuestion()
    {
        return question;
    }

    public String getOptionA()
    {
        return optionA;
    }

    public String getOptionB()
    {
        return optionB;
    }

    public String getOptionC()
    {
        return optionC;
    }

    public String getOptionD()
    {
        return optionD;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setID(int id)
    {
        ID=id;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public void setOptionA(String optionA)
    {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB)
    {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC)
    {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD)
    {
        this.optionD = optionD;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
}
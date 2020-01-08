package com.example.fitapp;


public class Quote {
    private int quoteID;                //Id of the quote in the database
    private String quoteText;           //Text of the quote


    public Quote() {}

    public Quote(int id, String quotetext) {
        this.quoteID = id;
        this.quoteText = quotetext;
    }

    //Setters and Getters
    public void setID(int id) {
        this.quoteID = id;
    }
    public int getID() {
        return this.quoteID;
    }
    public void setQuoteText(String quotetext) {
        this.quoteText = quotetext;
    }
    public String getQuoteText() {
        return this.quoteText;
    }
}

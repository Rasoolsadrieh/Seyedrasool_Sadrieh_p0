package com.revature.Rossbank.models;

public class history {
    private int id;
    private String ability;
    private int value;
    private int accountID;

    public history (int id,String ability,int accountID ,int value){
        super();
        this.id = id;
        this.ability = ability;
        this.accountID = accountID;
        this.value = value;
    }
    public history (){

    }

    public int getAccountID() {
        return accountID;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public String getAbility() {
        return ability;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return
                "ID: " + id + '\'' +
                        "| AccountID: " + accountID + '\'' +
                        "| Ability= " + ability + '\'' +
                        "| Amount= " + value + '\'';
    }

}


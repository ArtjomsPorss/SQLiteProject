package com.artjomsporss.sqliteproject.person;

/**
 * Created by User1 on 07/10/2015.
 */
public class Person {
    private int mID = -1;
    private String mName;
    private String mPhoneNum;
    private String mEmail;


    public Person() {
    }

    public Person(String mEmail, int mID, String mName, String mPhoneNum) {
        this.mEmail = mEmail;
        this.mID = mID;
        this.mName = mName;
        this.mPhoneNum = mPhoneNum;
    }


    public void setEmail(String email) {
        this.mEmail = email;
    }

    public void setID(int ID) {
        this.mID = ID;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.mPhoneNum = phoneNum;
    }

    public String getEmail() {
        return mEmail;
    }

    public int getID() {
        return mID;
    }

    public String getName() {
        return mName;
    }

    public String getPhoneNum() {
        return mPhoneNum;
    }

    @Override
    public String toString() {
        return mName;
    }
}

package com.example.doancuoiki.tim_tro_dack;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by asus on 1/16/2017.
 */



public class Message implements Serializable{
    @SerializedName("message")
    private String message;
    //contructor
    //getter/ setter
    public String getMessage(){
        return message;
    }
}

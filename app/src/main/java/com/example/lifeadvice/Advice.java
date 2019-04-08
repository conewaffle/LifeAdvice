package com.example.lifeadvice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Advice {
    @SerializedName("slip")
    @Expose
    private Slip slip;

    public Slip getSlip(){
        return slip;
    }

    public void setSlip(Slip slip){
        this.slip = slip;
    }


}

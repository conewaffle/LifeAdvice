package com.example.lifeadvice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Slip {
    @SerializedName("advice")
    @Expose
    private String advice;

    @SerializedName("slip_id")
    @Expose
    private String slipId;

    public String getAdvice(){
        return advice;
    }

    public void setAdvice(String advice){
        this.advice = advice;
    }

    public String getSlipId(){
        return slipId;
    }

    public void setSlipId(String slipdId){
        this.slipId = slipdId;
    }
}

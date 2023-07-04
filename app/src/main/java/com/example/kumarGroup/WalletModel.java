package com.example.kumarGroup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WalletModel {

    @SerializedName("attandance")
    @Expose
    private List<WalletList> attandance = null;

    public List<WalletList> getAttandance() {
        return attandance;
    }

    public void setAttandance(List<WalletList> attandance) {
        this.attandance = attandance;
    }
}

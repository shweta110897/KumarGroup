package com.example.kumarGroup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatabankMakeModel {

    @SerializedName("state")
    @Expose
    private List<State> state = null;

    public List<State> getState() {
        return state;
    }

    public void setState(List<State> state) {
        this.state = state;
    }

    public class State {

        @SerializedName("make")
        @Expose
        private String make;

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

    }

}

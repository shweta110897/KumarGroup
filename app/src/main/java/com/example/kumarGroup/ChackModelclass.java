package com.example.kumarGroup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChackModelclass {


        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("msg")
        @Expose
        private String msg;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

    }



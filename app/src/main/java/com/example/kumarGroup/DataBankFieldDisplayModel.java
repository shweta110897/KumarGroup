package com.example.kumarGroup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataBankFieldDisplayModel {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("cat_id")
        @Expose
        private String catId;
        @SerializedName("cat_list")
        @Expose
        private String catList;
        @SerializedName("make_name")
        @Expose
        private String makeName;
        @SerializedName("model_name")
        @Expose
        private String modelName;
        @SerializedName("sor_inq")
        @Expose
        private String sorInq;

        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public String getCatList() {
            return catList;
        }

        public void setCatList(String catList) {
            this.catList = catList;
        }

        public String getMakeName() {
            return makeName;
        }

        public void setMakeName(String makeName) {
            this.makeName = makeName;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public String getSorInq() {
            return sorInq;
        }

        public void setSorInq(String sorInq) {
            this.sorInq = sorInq;
        }

    }

}

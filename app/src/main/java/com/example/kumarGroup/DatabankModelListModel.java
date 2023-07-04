package com.example.kumarGroup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatabankModelListModel {

    @SerializedName("model")
    @Expose
    private List<Model> model = null;

    public List<Model> getModel() {
        return model;
    }

    public void setModel(List<Model> model) {
        this.model = model;
    }

    public class Model {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("model_name")
        @Expose
        private String modelName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

    }
}

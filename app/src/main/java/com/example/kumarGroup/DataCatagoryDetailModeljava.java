package com.example.kumarGroup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataCatagoryDetailModeljava {


    @SerializedName("cat")
    @Expose
    private List<Cat> cat = null;

    public List<Cat> getCat() {
        return cat;
    }

    public void setCat(List<Cat> cat) {
        this.cat = cat;
    }

    public class Cat {

        @SerializedName("cat_id")
        @Expose
        private String catId;
        @SerializedName("cat_name")
        @Expose
        private String catName;
        @SerializedName("fname")
        @Expose
        private String fname;
        @SerializedName("lname")
        @Expose
        private String lname;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("distric")
        @Expose
        private String distric;
        @SerializedName("tehsil")
        @Expose
        private String tehsil;
        @SerializedName("vilage")
        @Expose
        private Object vilage;
        @SerializedName("moblino")
        @Expose
        private String moblino;
        @SerializedName("whatsappno")
        @Expose
        private String whatsappno;
        @SerializedName("employee_name")
        @Expose
        private String employeeName;
        @SerializedName("desc")
        @Expose
        private String desc;
        private String PaymentType;
        private String PassingType;


        public void setVilage(Object vilage) {
            this.vilage = vilage;
        }

        public String getPaymentType() {
            return PaymentType;
        }

        public void setPaymentType(String paymentType) {
            PaymentType = paymentType;
        }

        public String getPassingType() {
            return PassingType;
        }

        public void setPassingType(String passingType) {
            PassingType = passingType;
        }

        public String getEid() {
            return eid;
        }

        public void setEid(String eid) {
            this.eid = eid;
        }

        @SerializedName("eid")
        @Expose
        private String eid;


        @SerializedName("auto_id")
        @Expose
        private String auto_id;

        public String getAuto_id() {
            return auto_id;
        }

        public void setAuto_id(String auto_id) {
            this.auto_id = auto_id;
        }



        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistric() {
            return distric;
        }

        public void setDistric(String distric) {
            this.distric = distric;
        }

        public String getTehsil() {
            return tehsil;
        }

        public void setTehsil(String tehsil) {
            this.tehsil = tehsil;
        }

        public String getVilage() {
            return (String) vilage;
        }

        public void setVilage(String vilage) {
            this.vilage = vilage;
        }


     /*   public Object getVilage() {
            return vilage;
        }

        public void setVilage(Object vilage) {
            this.vilage = vilage;
        }*/




        public String getMoblino() {
            return moblino;
        }

        public void setMoblino(String moblino) {
            this.moblino = moblino;
        }

        public String getWhatsappno() {
            return whatsappno;
        }

        public void setWhatsappno(String whatsappno) {
            this.whatsappno = whatsappno;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

    }
}









package com.example.kumarGroup.DealstageAK;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Notes_POJO {

    @SerializedName("data")
    @Expose
    private List<Cat> cat;

    public List<Cat> getCat() {
        return cat;
    }

    public void setCat(List<Cat> cat) {
        this.cat = cat;
    }

    public class Cat {

        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("emp_id")
        @Expose
        private String empId;
        @SerializedName("recent_note")
        @Expose
        private String recentNote;
        @SerializedName("follow_up")
        @Expose
        private String followUp;
        @SerializedName("date")
        @Expose
        private String date;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

        public String getRecentNote() {
            return recentNote;
        }

        public void setRecentNote(String recentNote) {
            this.recentNote = recentNote;
        }

        public String getFollowUp() {
            return followUp;
        }

        public void setFollowUp(String followUp) {
            this.followUp = followUp;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "location='" + location + '\'' +
                    ", empId='" + empId + '\'' +
                    ", recentNote='" + recentNote + '\'' +
                    ", followUp='" + followUp + '\'' +
                    ", date='" + date + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Notes_POJO{" +
                "cat=" + cat +
                '}';
    }
}

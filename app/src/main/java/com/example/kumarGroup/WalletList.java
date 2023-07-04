package com.example.kumarGroup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletList {
    private boolean expanded;

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("checkin")
    @Expose
    private String checkin;
    @SerializedName("lunch_e_time")
    @Expose
    private String lunchETime;
    @SerializedName("checkout_time")
    @Expose
    private String checkoutTime;
    @SerializedName("overtime_time")
    @Expose
    private String overtimeTime;
    @SerializedName("overtime_end_time")
    @Expose
    private String overtimeEndTime;
    @SerializedName("working_hour")
    @Expose
    private String workingHour;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("icard")
    @Expose
    private String icard;
    @SerializedName("uniform")
    @Expose
    private String uniform;
    @SerializedName("payout")
    @Expose
    private String payout;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getLunchETime() {
        return lunchETime;
    }

    public void setLunchETime(String lunchETime) {
        this.lunchETime = lunchETime;
    }

    public String getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public String getOvertimeTime() {
        return overtimeTime;
    }

    public void setOvertimeTime(String overtimeTime) {
        this.overtimeTime = overtimeTime;
    }

    public String getOvertimeEndTime() {
        return overtimeEndTime;
    }

    public void setOvertimeEndTime(String overtimeEndTime) {
        this.overtimeEndTime = overtimeEndTime;
    }

    public String getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(String workingHour) {
        this.workingHour = workingHour;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIcard() {
        return icard;
    }

    public void setIcard(String icard) {
        this.icard = icard;
    }

    public String getUniform() {
        return uniform;
    }

    public void setUniform(String uniform) {
        this.uniform = uniform;
    }

    public String getPayout() {
        return payout;
    }

    public void setPayout(String payout) {
        this.payout = payout;
    }

    public boolean isExpanded() {
        return expanded;
    }
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}

package com.railway.cablejoint.models;

import java.io.Serializable;

public class JointData implements Serializable {
    private String date;
    private String section;
    private String subSection;
    private String km;
    private String oheMast;
    private String offsetFromTrack;
    private String cableType;
    private String jointType;
    private String photoUrl;
    private double latitude;
    private double longitude;
    private String reason;
    private String staffPresent;
    private String remark;
    private int rowIndex;

    public JointData() {}

    public JointData(String date, String section, String subSection, String km, 
                     String oheMast, String offsetFromTrack, String cableType, 
                     String jointType, String photoUrl, double latitude, 
                     double longitude, String reason, String staffPresent, String remark) {
        this.date = date;
        this.section = section;
        this.subSection = subSection;
        this.km = km;
        this.oheMast = oheMast;
        this.offsetFromTrack = offsetFromTrack;
        this.cableType = cableType;
        this.jointType = jointType;
        this.photoUrl = photoUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.reason = reason;
        this.staffPresent = staffPresent;
        this.remark = remark;
    }

    // Getters and Setters
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public String getSubSection() { return subSection; }
    public void setSubSection(String subSection) { this.subSection = subSection; }

    public String getKm() { return km; }
    public void setKm(String km) { this.km = km; }

    public String getOheMast() { return oheMast; }
    public void setOheMast(String oheMast) { this.oheMast = oheMast; }

    public String getOffsetFromTrack() { return offsetFromTrack; }
    public void setOffsetFromTrack(String offsetFromTrack) { this.offsetFromTrack = offsetFromTrack; }

    public String getCableType() { return cableType; }
    public void setCableType(String cableType) { this.cableType = cableType; }

    public String getJointType() { return jointType; }
    public void setJointType(String jointType) { this.jointType = jointType; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStaffPresent() { return staffPresent; }
    public void setStaffPresent(String staffPresent) { this.staffPresent = staffPresent; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public int getRowIndex() { return rowIndex; }
    public void setRowIndex(int rowIndex) { this.rowIndex = rowIndex; }
}

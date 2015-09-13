package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/9/8 0008.
 */
public class CoachDetailBean implements Parcelable {

    /**
     * Usr_Expertise : ???
     * Usr_User_ActualName : ??
     * Usr_Height : 180
     * Usr_Shape : ???
     * Usr_UserID : 9
     * TeachingYear : 3
     * Usr_Sex : ?
     * Usr_Weight : 70.0
     */
    private String Usr_Expertise;
    private String Usr_User_ActualName;
    private int Usr_Height;
    private String Usr_Shape;
    private int Usr_UserID;
    private int TeachingYear;
    private String Usr_Sex;
    private double Usr_Weight;

    public void setUsr_Expertise(String Usr_Expertise) {
        this.Usr_Expertise = Usr_Expertise;
    }

    public void setUsr_User_ActualName(String Usr_User_ActualName) {
        this.Usr_User_ActualName = Usr_User_ActualName;
    }

    public void setUsr_Height(int Usr_Height) {
        this.Usr_Height = Usr_Height;
    }

    public void setUsr_Shape(String Usr_Shape) {
        this.Usr_Shape = Usr_Shape;
    }

    public void setUsr_UserID(int Usr_UserID) {
        this.Usr_UserID = Usr_UserID;
    }

    public void setTeachingYear(int TeachingYear) {
        this.TeachingYear = TeachingYear;
    }

    public void setUsr_Sex(String Usr_Sex) {
        this.Usr_Sex = Usr_Sex;
    }

    public void setUsr_Weight(double Usr_Weight) {
        this.Usr_Weight = Usr_Weight;
    }

    public String getUsr_Expertise() {
        return Usr_Expertise;
    }

    public String getUsr_User_ActualName() {
        return Usr_User_ActualName;
    }

    public int getUsr_Height() {
        return Usr_Height;
    }

    public String getUsr_Shape() {
        return Usr_Shape;
    }

    public int getUsr_UserID() {
        return Usr_UserID;
    }

    public int getTeachingYear() {
        return TeachingYear;
    }

    public String getUsr_Sex() {
        return Usr_Sex;
    }

    public double getUsr_Weight() {
        return Usr_Weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Usr_Expertise);
        dest.writeString(this.Usr_User_ActualName);
        dest.writeInt(this.Usr_Height);
        dest.writeString(this.Usr_Shape);
        dest.writeInt(this.Usr_UserID);
        dest.writeInt(this.TeachingYear);
        dest.writeString(this.Usr_Sex);
        dest.writeDouble(this.Usr_Weight);
    }

    public CoachDetailBean() {
    }

    protected CoachDetailBean(Parcel in) {
        this.Usr_Expertise = in.readString();
        this.Usr_User_ActualName = in.readString();
        this.Usr_Height = in.readInt();
        this.Usr_Shape = in.readString();
        this.Usr_UserID = in.readInt();
        this.TeachingYear = in.readInt();
        this.Usr_Sex = in.readString();
        this.Usr_Weight = in.readDouble();
    }

    public static final Parcelable.Creator<CoachDetailBean> CREATOR = new Parcelable.Creator<CoachDetailBean>() {
        public CoachDetailBean createFromParcel(Parcel source) {
            return new CoachDetailBean(source);
        }

        public CoachDetailBean[] newArray(int size) {
            return new CoachDetailBean[size];
        }
    };
}

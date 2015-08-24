package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015-08-24.
 */
public class UserBean implements Parcelable {


    /**
     * Usr_Sex : 男
     * Usr_Photo : http://121.40.168.164:8090/Pictures/user_default.jpg
     * Usr_UserType : 0
     * Usr_UserName : 18603826387
     * Usr_CreateDateTime : 2015-08-24 09:31:52
     * Usr_UserID : 7
     * Usr_IsEnable : 1
     * Usr_Birthday :
     * Usr_IS_Infor_Complete : 0
     * Usr_alipay_count : 0
     * Usr_DepCode :
     * Usr_MobilePhone : 18603826387
     * Usr_Job_Intension :
     * Usr_User_ActualName :
     * Usr_EmployeeID :
     * Usr_CreateUserId : 0
     * Usr_Integral : 0
     * ReportSum : 0
     * Usr_Password : 741852
     * Usr_Height : 0
     * Usr_EmailAddress :
     * Usr_address :
     */
    private String Usr_Sex;
    private String Usr_Photo;
    private int Usr_UserType;
    private String Usr_UserName;
    private String Usr_CreateDateTime;
    private int Usr_UserID;
    private int Usr_IsEnable;
    private String Usr_Birthday;
    private String Usr_IS_Infor_Complete;
    private String Usr_alipay_count;
    private String Usr_DepCode;
    private String Usr_MobilePhone;
    private String Usr_Job_Intension;
    private String Usr_User_ActualName;
    private String Usr_EmployeeID;
    private int Usr_CreateUserId;
    private String Usr_Integral;
    private int ReportSum;
    private String Usr_Password;
    private int Usr_Height;
    private String Usr_EmailAddress;
    private String Usr_address;

    public void setUsr_Sex(String Usr_Sex) {
        this.Usr_Sex = Usr_Sex;
    }

    public void setUsr_Photo(String Usr_Photo) {
        this.Usr_Photo = Usr_Photo;
    }

    public void setUsr_UserType(int Usr_UserType) {
        this.Usr_UserType = Usr_UserType;
    }

    public void setUsr_UserName(String Usr_UserName) {
        this.Usr_UserName = Usr_UserName;
    }

    public void setUsr_CreateDateTime(String Usr_CreateDateTime) {
        this.Usr_CreateDateTime = Usr_CreateDateTime;
    }

    public void setUsr_UserID(int Usr_UserID) {
        this.Usr_UserID = Usr_UserID;
    }

    public void setUsr_IsEnable(int Usr_IsEnable) {
        this.Usr_IsEnable = Usr_IsEnable;
    }

    public void setUsr_Birthday(String Usr_Birthday) {
        this.Usr_Birthday = Usr_Birthday;
    }

    public void setUsr_IS_Infor_Complete(String Usr_IS_Infor_Complete) {
        this.Usr_IS_Infor_Complete = Usr_IS_Infor_Complete;
    }

    public void setUsr_alipay_count(String Usr_alipay_count) {
        this.Usr_alipay_count = Usr_alipay_count;
    }

    public void setUsr_DepCode(String Usr_DepCode) {
        this.Usr_DepCode = Usr_DepCode;
    }

    public void setUsr_MobilePhone(String Usr_MobilePhone) {
        this.Usr_MobilePhone = Usr_MobilePhone;
    }

    public void setUsr_Job_Intension(String Usr_Job_Intension) {
        this.Usr_Job_Intension = Usr_Job_Intension;
    }

    public void setUsr_User_ActualName(String Usr_User_ActualName) {
        this.Usr_User_ActualName = Usr_User_ActualName;
    }

    public void setUsr_EmployeeID(String Usr_EmployeeID) {
        this.Usr_EmployeeID = Usr_EmployeeID;
    }

    public void setUsr_CreateUserId(int Usr_CreateUserId) {
        this.Usr_CreateUserId = Usr_CreateUserId;
    }

    public void setUsr_Integral(String Usr_Integral) {
        this.Usr_Integral = Usr_Integral;
    }

    public void setReportSum(int ReportSum) {
        this.ReportSum = ReportSum;
    }

    public void setUsr_Password(String Usr_Password) {
        this.Usr_Password = Usr_Password;
    }

    public void setUsr_Height(int Usr_Height) {
        this.Usr_Height = Usr_Height;
    }

    public void setUsr_EmailAddress(String Usr_EmailAddress) {
        this.Usr_EmailAddress = Usr_EmailAddress;
    }

    public void setUsr_address(String Usr_address) {
        this.Usr_address = Usr_address;
    }

    public String getUsr_Sex() {
        return Usr_Sex;
    }

    public String getUsr_Photo() {
        return Usr_Photo;
    }

    public int getUsr_UserType() {
        return Usr_UserType;
    }

    public String getUsr_UserName() {
        return Usr_UserName;
    }

    public String getUsr_CreateDateTime() {
        return Usr_CreateDateTime;
    }

    public int getUsr_UserID() {
        return Usr_UserID;
    }

    public int getUsr_IsEnable() {
        return Usr_IsEnable;
    }

    public String getUsr_Birthday() {
        return Usr_Birthday;
    }

    public String getUsr_IS_Infor_Complete() {
        return Usr_IS_Infor_Complete;
    }

    public String getUsr_alipay_count() {
        return Usr_alipay_count;
    }

    public String getUsr_DepCode() {
        return Usr_DepCode;
    }

    public String getUsr_MobilePhone() {
        return Usr_MobilePhone;
    }

    public String getUsr_Job_Intension() {
        return Usr_Job_Intension;
    }

    public String getUsr_User_ActualName() {
        return Usr_User_ActualName;
    }

    public String getUsr_EmployeeID() {
        return Usr_EmployeeID;
    }

    public int getUsr_CreateUserId() {
        return Usr_CreateUserId;
    }

    public String getUsr_Integral() {
        return Usr_Integral;
    }

    public int getReportSum() {
        return ReportSum;
    }

    public String getUsr_Password() {
        return Usr_Password;
    }

    public int getUsr_Height() {
        return Usr_Height;
    }

    public String getUsr_EmailAddress() {
        return Usr_EmailAddress;
    }

    public String getUsr_address() {
        return Usr_address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Usr_Sex);
        dest.writeString(this.Usr_Photo);
        dest.writeInt(this.Usr_UserType);
        dest.writeString(this.Usr_UserName);
        dest.writeString(this.Usr_CreateDateTime);
        dest.writeInt(this.Usr_UserID);
        dest.writeInt(this.Usr_IsEnable);
        dest.writeString(this.Usr_Birthday);
        dest.writeString(this.Usr_IS_Infor_Complete);
        dest.writeString(this.Usr_alipay_count);
        dest.writeString(this.Usr_DepCode);
        dest.writeString(this.Usr_MobilePhone);
        dest.writeString(this.Usr_Job_Intension);
        dest.writeString(this.Usr_User_ActualName);
        dest.writeString(this.Usr_EmployeeID);
        dest.writeInt(this.Usr_CreateUserId);
        dest.writeString(this.Usr_Integral);
        dest.writeInt(this.ReportSum);
        dest.writeString(this.Usr_Password);
        dest.writeInt(this.Usr_Height);
        dest.writeString(this.Usr_EmailAddress);
        dest.writeString(this.Usr_address);
    }

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        this.Usr_Sex = in.readString();
        this.Usr_Photo = in.readString();
        this.Usr_UserType = in.readInt();
        this.Usr_UserName = in.readString();
        this.Usr_CreateDateTime = in.readString();
        this.Usr_UserID = in.readInt();
        this.Usr_IsEnable = in.readInt();
        this.Usr_Birthday = in.readString();
        this.Usr_IS_Infor_Complete = in.readString();
        this.Usr_alipay_count = in.readString();
        this.Usr_DepCode = in.readString();
        this.Usr_MobilePhone = in.readString();
        this.Usr_Job_Intension = in.readString();
        this.Usr_User_ActualName = in.readString();
        this.Usr_EmployeeID = in.readString();
        this.Usr_CreateUserId = in.readInt();
        this.Usr_Integral = in.readString();
        this.ReportSum = in.readInt();
        this.Usr_Password = in.readString();
        this.Usr_Height = in.readInt();
        this.Usr_EmailAddress = in.readString();
        this.Usr_address = in.readString();
    }

    public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>() {
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}

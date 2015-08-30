package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/8/29 0029.
 */
public class BuyLessonBean implements Parcelable {

    /**
     * CompanyId : 3
     * course_Photo : http://121.40.168.164:8091/Pictures/2.jpg
     * JobBeginTime : 13:00:00
     * id : 25
     * JobTitle : 有氧健身
     * JobEndTime : 14:00:00
     * JobAddress : 上海市南京西路100号
     * Remark : 0
     */
    private int CompanyId;
    private String course_Photo;
    private String JobBeginTime;
    private int id;
    private String JobTitle;
    private String JobEndTime;
    private String JobAddress;
    private String Remark;

    public void setCompanyId(int CompanyId) {
        this.CompanyId = CompanyId;
    }

    public void setCourse_Photo(String course_Photo) {
        this.course_Photo = course_Photo;
    }

    public void setJobBeginTime(String JobBeginTime) {
        this.JobBeginTime = JobBeginTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJobTitle(String JobTitle) {
        this.JobTitle = JobTitle;
    }

    public void setJobEndTime(String JobEndTime) {
        this.JobEndTime = JobEndTime;
    }

    public void setJobAddress(String JobAddress) {
        this.JobAddress = JobAddress;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public String getCourse_Photo() {
        return course_Photo;
    }

    public String getJobBeginTime() {
        return JobBeginTime;
    }

    public int getId() {
        return id;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public String getJobEndTime() {
        return JobEndTime;
    }

    public String getJobAddress() {
        return JobAddress;
    }

    public String getRemark() {
        return Remark;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.CompanyId);
        dest.writeString(this.course_Photo);
        dest.writeString(this.JobBeginTime);
        dest.writeInt(this.id);
        dest.writeString(this.JobTitle);
        dest.writeString(this.JobEndTime);
        dest.writeString(this.JobAddress);
        dest.writeString(this.Remark);
    }

    public BuyLessonBean() {
    }

    protected BuyLessonBean(Parcel in) {
        this.CompanyId = in.readInt();
        this.course_Photo = in.readString();
        this.JobBeginTime = in.readString();
        this.id = in.readInt();
        this.JobTitle = in.readString();
        this.JobEndTime = in.readString();
        this.JobAddress = in.readString();
        this.Remark = in.readString();
    }

    public static final Parcelable.Creator<BuyLessonBean> CREATOR = new Parcelable.Creator<BuyLessonBean>() {
        public BuyLessonBean createFromParcel(Parcel source) {
            return new BuyLessonBean(source);
        }

        public BuyLessonBean[] newArray(int size) {
            return new BuyLessonBean[size];
        }
    };
}

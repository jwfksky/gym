package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/9/1 0001.
 */
public class PublishLessonBean implements Parcelable {

    /**
     * JobRequirements : http://211.149.244.163:8090/Pictures/6ce337b8-278c-4c24-8402-56b848ebad2d.jpg
     * Status : 1
     * CourseTime : 11:17
     * HireNum : 50
     * CompanyId : 3
     * JobContent : 对缓解肌肤沟沟壑壑好和不甘雌伏看空间
     * CourseCycle : 星期三
     * MaintenanceCycle : 56
     * Id : 617
     * JobTitle : 保护方法
     */
    private String JobRequirements;
    private String Status;
    private String CourseTime;
    private String HireNum;
    private int CompanyId;
    private String JobContent;
    private String CourseCycle;
    private String MaintenanceCycle;
    private int Id;
    private String JobTitle;

    public void setJobRequirements(String JobRequirements) {
        this.JobRequirements = JobRequirements;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setCourseTime(String CourseTime) {
        this.CourseTime = CourseTime;
    }

    public void setHireNum(String HireNum) {
        this.HireNum = HireNum;
    }

    public void setCompanyId(int CompanyId) {
        this.CompanyId = CompanyId;
    }

    public void setJobContent(String JobContent) {
        this.JobContent = JobContent;
    }

    public void setCourseCycle(String CourseCycle) {
        this.CourseCycle = CourseCycle;
    }

    public void setMaintenanceCycle(String MaintenanceCycle) {
        this.MaintenanceCycle = MaintenanceCycle;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setJobTitle(String JobTitle) {
        this.JobTitle = JobTitle;
    }

    public String getJobRequirements() {
        return JobRequirements;
    }

    public String getStatus() {
        return Status;
    }

    public String getCourseTime() {
        return CourseTime;
    }

    public String getHireNum() {
        return HireNum;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public String getJobContent() {
        return JobContent;
    }

    public String getCourseCycle() {
        return CourseCycle;
    }

    public String getMaintenanceCycle() {
        return MaintenanceCycle;
    }

    public int getId() {
        return Id;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.JobRequirements);
        dest.writeString(this.Status);
        dest.writeString(this.CourseTime);
        dest.writeString(this.HireNum);
        dest.writeInt(this.CompanyId);
        dest.writeString(this.JobContent);
        dest.writeString(this.CourseCycle);
        dest.writeString(this.MaintenanceCycle);
        dest.writeInt(this.Id);
        dest.writeString(this.JobTitle);
    }

    public PublishLessonBean() {
    }

    protected PublishLessonBean(Parcel in) {
        this.JobRequirements = in.readString();
        this.Status = in.readString();
        this.CourseTime = in.readString();
        this.HireNum = in.readString();
        this.CompanyId = in.readInt();
        this.JobContent = in.readString();
        this.CourseCycle = in.readString();
        this.MaintenanceCycle = in.readString();
        this.Id = in.readInt();
        this.JobTitle = in.readString();
    }

    public static final Parcelable.Creator<PublishLessonBean> CREATOR = new Parcelable.Creator<PublishLessonBean>() {
        public PublishLessonBean createFromParcel(Parcel source) {
            return new PublishLessonBean(source);
        }

        public PublishLessonBean[] newArray(int size) {
            return new PublishLessonBean[size];
        }
    };
}

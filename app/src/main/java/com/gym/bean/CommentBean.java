package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/9/13 0013.
 */
public class CommentBean implements Parcelable {

    /**
     * Usr_User_ActualName :
     * CompanyId : 3
     * create_time : {"Month":5,"Millisecond":0,"Year":2015,"Minute":5,"Second":36,"Hour":18,"Value":"2015-05-19 18:05:36","IsNull":false,"Day":19,"IsValidDateTime":true}
     * topic_content : 非常好非常好非常好
     * course_Photo : http://121.40.168.164:8091/Pictures/2.jpg
     * JobBeginTime : 13:30:00
     * topic_id : 15
     * Usr_Photo : http://211.149.244.163:8090/Pictures/bd34af62-2037-41cb-ab02-d8057241ea75.jpg
     * JobTitle : 朔身瑜珈1
     * JobEndTime : 15:30:00
     * JobAddress : 上海体育馆4楼109室
     * JobId : 573
     */
    private String Usr_User_ActualName;
    private int CompanyId;
    private CreateTimeEntity create_time;
    private String topic_content;
    private String course_Photo;
    private String JobBeginTime;
    private int topic_id;
    private String Usr_Photo;
    private String JobTitle;
    private String JobEndTime;
    private String JobAddress;
    private int JobId;

    public void setUsr_User_ActualName(String Usr_User_ActualName) {
        this.Usr_User_ActualName = Usr_User_ActualName;
    }

    public void setCompanyId(int CompanyId) {
        this.CompanyId = CompanyId;
    }

    public void setCreate_time(CreateTimeEntity create_time) {
        this.create_time = create_time;
    }

    public void setTopic_content(String topic_content) {
        this.topic_content = topic_content;
    }

    public void setCourse_Photo(String course_Photo) {
        this.course_Photo = course_Photo;
    }

    public void setJobBeginTime(String JobBeginTime) {
        this.JobBeginTime = JobBeginTime;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public void setUsr_Photo(String Usr_Photo) {
        this.Usr_Photo = Usr_Photo;
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

    public void setJobId(int JobId) {
        this.JobId = JobId;
    }

    public String getUsr_User_ActualName() {
        return Usr_User_ActualName;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public CreateTimeEntity getCreate_time() {
        return create_time;
    }

    public String getTopic_content() {
        return topic_content;
    }

    public String getCourse_Photo() {
        return course_Photo;
    }

    public String getJobBeginTime() {
        return JobBeginTime;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public String getUsr_Photo() {
        return Usr_Photo;
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

    public int getJobId() {
        return JobId;
    }

    public static class CreateTimeEntity implements Parcelable {

        /**
         * Month : 5
         * Millisecond : 0
         * Year : 2015
         * Minute : 5
         * Second : 36
         * Hour : 18
         * Value : 2015-05-19 18:05:36
         * IsNull : false
         * Day : 19
         * IsValidDateTime : true
         */
        private int Month;
        private int Millisecond;
        private int Year;
        private int Minute;
        private int Second;
        private int Hour;
        private String Value;
        private boolean IsNull;
        private int Day;
        private boolean IsValidDateTime;

        public void setMonth(int Month) {
            this.Month = Month;
        }

        public void setMillisecond(int Millisecond) {
            this.Millisecond = Millisecond;
        }

        public void setYear(int Year) {
            this.Year = Year;
        }

        public void setMinute(int Minute) {
            this.Minute = Minute;
        }

        public void setSecond(int Second) {
            this.Second = Second;
        }

        public void setHour(int Hour) {
            this.Hour = Hour;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }

        public void setIsNull(boolean IsNull) {
            this.IsNull = IsNull;
        }

        public void setDay(int Day) {
            this.Day = Day;
        }

        public void setIsValidDateTime(boolean IsValidDateTime) {
            this.IsValidDateTime = IsValidDateTime;
        }

        public int getMonth() {
            return Month;
        }

        public int getMillisecond() {
            return Millisecond;
        }

        public int getYear() {
            return Year;
        }

        public int getMinute() {
            return Minute;
        }

        public int getSecond() {
            return Second;
        }

        public int getHour() {
            return Hour;
        }

        public String getValue() {
            return Value;
        }

        public boolean isIsNull() {
            return IsNull;
        }

        public int getDay() {
            return Day;
        }

        public boolean isIsValidDateTime() {
            return IsValidDateTime;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.Month);
            dest.writeInt(this.Millisecond);
            dest.writeInt(this.Year);
            dest.writeInt(this.Minute);
            dest.writeInt(this.Second);
            dest.writeInt(this.Hour);
            dest.writeString(this.Value);
            dest.writeByte(IsNull ? (byte) 1 : (byte) 0);
            dest.writeInt(this.Day);
            dest.writeByte(IsValidDateTime ? (byte) 1 : (byte) 0);
        }

        public CreateTimeEntity() {
        }

        protected CreateTimeEntity(Parcel in) {
            this.Month = in.readInt();
            this.Millisecond = in.readInt();
            this.Year = in.readInt();
            this.Minute = in.readInt();
            this.Second = in.readInt();
            this.Hour = in.readInt();
            this.Value = in.readString();
            this.IsNull = in.readByte() != 0;
            this.Day = in.readInt();
            this.IsValidDateTime = in.readByte() != 0;
        }

        public static final Creator<CreateTimeEntity> CREATOR = new Creator<CreateTimeEntity>() {
            public CreateTimeEntity createFromParcel(Parcel source) {
                return new CreateTimeEntity(source);
            }

            public CreateTimeEntity[] newArray(int size) {
                return new CreateTimeEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Usr_User_ActualName);
        dest.writeInt(this.CompanyId);
        dest.writeParcelable(this.create_time, flags);
        dest.writeString(this.topic_content);
        dest.writeString(this.course_Photo);
        dest.writeString(this.JobBeginTime);
        dest.writeInt(this.topic_id);
        dest.writeString(this.Usr_Photo);
        dest.writeString(this.JobTitle);
        dest.writeString(this.JobEndTime);
        dest.writeString(this.JobAddress);
        dest.writeInt(this.JobId);
    }

    public CommentBean() {
    }

    protected CommentBean(Parcel in) {
        this.Usr_User_ActualName = in.readString();
        this.CompanyId = in.readInt();
        this.create_time = in.readParcelable(CreateTimeEntity.class.getClassLoader());
        this.topic_content = in.readString();
        this.course_Photo = in.readString();
        this.JobBeginTime = in.readString();
        this.topic_id = in.readInt();
        this.Usr_Photo = in.readString();
        this.JobTitle = in.readString();
        this.JobEndTime = in.readString();
        this.JobAddress = in.readString();
        this.JobId = in.readInt();
    }

    public static final Parcelable.Creator<CommentBean> CREATOR = new Parcelable.Creator<CommentBean>() {
        public CommentBean createFromParcel(Parcel source) {
            return new CommentBean(source);
        }

        public CommentBean[] newArray(int size) {
            return new CommentBean[size];
        }
    };
}

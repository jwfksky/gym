package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/9/1 0001.
 */
public class OrderManagerBean implements Parcelable {

    /**
     * CourseDate : 1,3,5
     * ScoreNumber : 0
     * JobTitle : 有氧健身
     * Remark : 0
     * JobTypeName : 健身
     * JobContent : 健身健身健身健身健身内容
     * CreateTime1 : {"Month":5,"Millisecond":0,"Year":2015,"Minute":47,"Second":48,"Hour":14,"Value":"2015-05-12 14:47:48","IsNull":false,"Day":12,"IsValidDateTime":true}
     * Id1 : 572
     * JobBeginTime : 13:00:00
     * Sort : 5
     * TotalScore : 0.0
     * JobId : 572
     * Status : 1
     * CompanyId : 3
     * LinkMan : 张老师
     * CreateTime : {"Month":8,"Millisecond":0,"Year":2015,"Minute":0,"Second":0,"Hour":0,"Value":"2015-08-31 00:00:00","IsNull":false,"Day":31,"IsValidDateTime":true}
     * CreateUser : admin1
     * JobEndTime : 14:00:00
     * JobRequirements : http://121.40.168.164:8091/Pictures/2.jpg
     * HireNum : 30
     * CourseRateType : 2
     * Score : 0.0
     * JobTypeId : 1
     * Treatment : 1000
     * JobStartDate : {"Month":5,"Millisecond":0,"Year":2015,"Minute":0,"Second":0,"Hour":0,"Value":"2015-05-29 00:00:00","IsNull":false,"Day":29,"IsValidDateTime":true}
     * CourseLast : 20
     * UserId : 1
     * CreateUser1 : admin1
     * Id : 42
     * LinkPhone : 1388888999
     * JobAddress : 上海市南京西路100号
     */
    private String CourseDate;
    private int ScoreNumber;
    private String JobTitle;
    private String Remark;
    private String JobTypeName;
    private String JobContent;
    private CreateTime1Entity CreateTime1;
    private int Id1;
    private String JobBeginTime;
    private int Sort;
    private double TotalScore;
    private int JobId;
    private String Status;
    private int CompanyId;
    private String LinkMan;
    private CreateTimeEntity CreateTime;
    private String CreateUser;
    private String JobEndTime;
    private String JobRequirements;
    private String HireNum;
    private String CourseRateType;
    private double Score;
    private int JobTypeId;
    private String Treatment;
    private JobStartDateEntity JobStartDate;
    private String CourseLast;
    private int UserId;
    private String CreateUser1;
    private int Id;
    private String LinkPhone;
    private String JobAddress;

    public void setCourseDate(String CourseDate) {
        this.CourseDate = CourseDate;
    }

    public void setScoreNumber(int ScoreNumber) {
        this.ScoreNumber = ScoreNumber;
    }

    public void setJobTitle(String JobTitle) {
        this.JobTitle = JobTitle;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public void setJobTypeName(String JobTypeName) {
        this.JobTypeName = JobTypeName;
    }

    public void setJobContent(String JobContent) {
        this.JobContent = JobContent;
    }

    public void setCreateTime1(CreateTime1Entity CreateTime1) {
        this.CreateTime1 = CreateTime1;
    }

    public void setId1(int Id1) {
        this.Id1 = Id1;
    }

    public void setJobBeginTime(String JobBeginTime) {
        this.JobBeginTime = JobBeginTime;
    }

    public void setSort(int Sort) {
        this.Sort = Sort;
    }

    public void setTotalScore(double TotalScore) {
        this.TotalScore = TotalScore;
    }

    public void setJobId(int JobId) {
        this.JobId = JobId;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setCompanyId(int CompanyId) {
        this.CompanyId = CompanyId;
    }

    public void setLinkMan(String LinkMan) {
        this.LinkMan = LinkMan;
    }

    public void setCreateTime(CreateTimeEntity CreateTime) {
        this.CreateTime = CreateTime;
    }

    public void setCreateUser(String CreateUser) {
        this.CreateUser = CreateUser;
    }

    public void setJobEndTime(String JobEndTime) {
        this.JobEndTime = JobEndTime;
    }

    public void setJobRequirements(String JobRequirements) {
        this.JobRequirements = JobRequirements;
    }

    public void setHireNum(String HireNum) {
        this.HireNum = HireNum;
    }

    public void setCourseRateType(String CourseRateType) {
        this.CourseRateType = CourseRateType;
    }

    public void setScore(double Score) {
        this.Score = Score;
    }

    public void setJobTypeId(int JobTypeId) {
        this.JobTypeId = JobTypeId;
    }

    public void setTreatment(String Treatment) {
        this.Treatment = Treatment;
    }

    public void setJobStartDate(JobStartDateEntity JobStartDate) {
        this.JobStartDate = JobStartDate;
    }

    public void setCourseLast(String CourseLast) {
        this.CourseLast = CourseLast;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public void setCreateUser1(String CreateUser1) {
        this.CreateUser1 = CreateUser1;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setLinkPhone(String LinkPhone) {
        this.LinkPhone = LinkPhone;
    }

    public void setJobAddress(String JobAddress) {
        this.JobAddress = JobAddress;
    }

    public String getCourseDate() {
        return CourseDate;
    }

    public int getScoreNumber() {
        return ScoreNumber;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public String getRemark() {
        return Remark;
    }

    public String getJobTypeName() {
        return JobTypeName;
    }

    public String getJobContent() {
        return JobContent;
    }

    public CreateTime1Entity getCreateTime1() {
        return CreateTime1;
    }

    public int getId1() {
        return Id1;
    }

    public String getJobBeginTime() {
        return JobBeginTime;
    }

    public int getSort() {
        return Sort;
    }

    public double getTotalScore() {
        return TotalScore;
    }

    public int getJobId() {
        return JobId;
    }

    public String getStatus() {
        return Status;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public String getLinkMan() {
        return LinkMan;
    }

    public CreateTimeEntity getCreateTime() {
        return CreateTime;
    }

    public String getCreateUser() {
        return CreateUser;
    }

    public String getJobEndTime() {
        return JobEndTime;
    }

    public String getJobRequirements() {
        return JobRequirements;
    }

    public String getHireNum() {
        return HireNum;
    }

    public String getCourseRateType() {
        return CourseRateType;
    }

    public double getScore() {
        return Score;
    }

    public int getJobTypeId() {
        return JobTypeId;
    }

    public String getTreatment() {
        return Treatment;
    }

    public JobStartDateEntity getJobStartDate() {
        return JobStartDate;
    }

    public String getCourseLast() {
        return CourseLast;
    }

    public int getUserId() {
        return UserId;
    }

    public String getCreateUser1() {
        return CreateUser1;
    }

    public int getId() {
        return Id;
    }

    public String getLinkPhone() {
        return LinkPhone;
    }

    public String getJobAddress() {
        return JobAddress;
    }

    public static class CreateTime1Entity implements Parcelable {
        /**
         * Month : 5
         * Millisecond : 0
         * Year : 2015
         * Minute : 47
         * Second : 48
         * Hour : 14
         * Value : 2015-05-12 14:47:48
         * IsNull : false
         * Day : 12
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

        public CreateTime1Entity() {
        }

        protected CreateTime1Entity(Parcel in) {
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

        public static final Parcelable.Creator<CreateTime1Entity> CREATOR = new Parcelable.Creator<CreateTime1Entity>() {
            public CreateTime1Entity createFromParcel(Parcel source) {
                return new CreateTime1Entity(source);
            }

            public CreateTime1Entity[] newArray(int size) {
                return new CreateTime1Entity[size];
            }
        };
    }

    public static class CreateTimeEntity implements Parcelable {
        /**
         * Month : 8
         * Millisecond : 0
         * Year : 2015
         * Minute : 0
         * Second : 0
         * Hour : 0
         * Value : 2015-08-31 00:00:00
         * IsNull : false
         * Day : 31
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

        public static final Parcelable.Creator<CreateTimeEntity> CREATOR = new Parcelable.Creator<CreateTimeEntity>() {
            public CreateTimeEntity createFromParcel(Parcel source) {
                return new CreateTimeEntity(source);
            }

            public CreateTimeEntity[] newArray(int size) {
                return new CreateTimeEntity[size];
            }
        };
    }

    public static class JobStartDateEntity implements Parcelable {
        /**
         * Month : 5
         * Millisecond : 0
         * Year : 2015
         * Minute : 0
         * Second : 0
         * Hour : 0
         * Value : 2015-05-29 00:00:00
         * IsNull : false
         * Day : 29
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

        public JobStartDateEntity() {
        }

        protected JobStartDateEntity(Parcel in) {
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

        public static final Creator<JobStartDateEntity> CREATOR = new Creator<JobStartDateEntity>() {
            public JobStartDateEntity createFromParcel(Parcel source) {
                return new JobStartDateEntity(source);
            }

            public JobStartDateEntity[] newArray(int size) {
                return new JobStartDateEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.CourseDate);
        dest.writeInt(this.ScoreNumber);
        dest.writeString(this.JobTitle);
        dest.writeString(this.Remark);
        dest.writeString(this.JobTypeName);
        dest.writeString(this.JobContent);
        dest.writeParcelable(this.CreateTime1, 0);
        dest.writeInt(this.Id1);
        dest.writeString(this.JobBeginTime);
        dest.writeInt(this.Sort);
        dest.writeDouble(this.TotalScore);
        dest.writeInt(this.JobId);
        dest.writeString(this.Status);
        dest.writeInt(this.CompanyId);
        dest.writeString(this.LinkMan);
        dest.writeParcelable(this.CreateTime, 0);
        dest.writeString(this.CreateUser);
        dest.writeString(this.JobEndTime);
        dest.writeString(this.JobRequirements);
        dest.writeString(this.HireNum);
        dest.writeString(this.CourseRateType);
        dest.writeDouble(this.Score);
        dest.writeInt(this.JobTypeId);
        dest.writeString(this.Treatment);
        dest.writeParcelable(this.JobStartDate, flags);
        dest.writeString(this.CourseLast);
        dest.writeInt(this.UserId);
        dest.writeString(this.CreateUser1);
        dest.writeInt(this.Id);
        dest.writeString(this.LinkPhone);
        dest.writeString(this.JobAddress);
    }

    public OrderManagerBean() {
    }

    protected OrderManagerBean(Parcel in) {
        this.CourseDate = in.readString();
        this.ScoreNumber = in.readInt();
        this.JobTitle = in.readString();
        this.Remark = in.readString();
        this.JobTypeName = in.readString();
        this.JobContent = in.readString();
        this.CreateTime1 = in.readParcelable(CreateTime1Entity.class.getClassLoader());
        this.Id1 = in.readInt();
        this.JobBeginTime = in.readString();
        this.Sort = in.readInt();
        this.TotalScore = in.readDouble();
        this.JobId = in.readInt();
        this.Status = in.readString();
        this.CompanyId = in.readInt();
        this.LinkMan = in.readString();
        this.CreateTime = in.readParcelable(CreateTimeEntity.class.getClassLoader());
        this.CreateUser = in.readString();
        this.JobEndTime = in.readString();
        this.JobRequirements = in.readString();
        this.HireNum = in.readString();
        this.CourseRateType = in.readString();
        this.Score = in.readDouble();
        this.JobTypeId = in.readInt();
        this.Treatment = in.readString();
        this.JobStartDate = in.readParcelable(JobStartDateEntity.class.getClassLoader());
        this.CourseLast = in.readString();
        this.UserId = in.readInt();
        this.CreateUser1 = in.readString();
        this.Id = in.readInt();
        this.LinkPhone = in.readString();
        this.JobAddress = in.readString();
    }

    public static final Parcelable.Creator<OrderManagerBean> CREATOR = new Parcelable.Creator<OrderManagerBean>() {
        public OrderManagerBean createFromParcel(Parcel source) {
            return new OrderManagerBean(source);
        }

        public OrderManagerBean[] newArray(int size) {
            return new OrderManagerBean[size];
        }
    };
}

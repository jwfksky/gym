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
     * FF_Name : NSR健身馆
     * Remark : 0
     * JobTypeName : 健身
     * JobContent : 健身健身健身健身健身内容
     * CreateTime1 : 2015-05-12 14:47:48
     * Id1 : 572
     * JobBeginTime : 13:00:00
     * Sort : 5
     * TotalScore : 0.0
     * JobId : 572
     * Status : 1
     * CompanyId : 3
     * LinkMan : 张老师
     * CreateTime : {"Month":9,"Millisecond":0,"Year":2015,"Minute":0,"Second":0,"Hour":0,"Value":"2015-09-09 00:00:00","IsNull":false,"Day":9,"IsValidDateTime":true}
     * FF_City : 上海市
     * CreateUser : admin1
     * Quantity : 5
     * JobEndTime : 14:00:00
     * JobRequirements : http://121.40.168.164:8091/Pictures/2.jpg
     * HireNum : 30
     * CourseRateType : 2
     * Score : 0.0
     * JobTypeId : 1
     * Treatment : 1000.0
     * JobStartDate : 2015-05-29
     * CourseLast : 20
     * FF_Phone : 110
     * Price : 5000.0
     * UserId : 1
     * CreateUser1 : admin1
     * Id : 52
     * FFID : 2
     * LinkPhone : 1388888999
     * JobAddress : 上海市南京西路100号
     * FF_Address : 上海市浦东国际机场
     */
    private String CourseDate;
    private int ScoreNumber;
    private String JobTitle;
    private String FF_Name;
    private String Remark;
    private String JobTypeName;
    private String JobContent;
    private String CreateTime1;
    private int Id1;
    private String JobBeginTime;
    private int Sort;
    private double TotalScore;
    private int JobId;
    private String Status;
    private int CompanyId;
    private String LinkMan;
    private CreateTimeEntity CreateTime;
    private String FF_City;
    private String CreateUser;
    private int Quantity;
    private String JobEndTime;
    private String JobRequirements;
    private String HireNum;
    private String CourseRateType;
    private double Score;
    private int JobTypeId;
    private double Treatment;
    private String JobStartDate;
    private String CourseLast;
    private String FF_Phone;
    private double Price;
    private int UserId;
    private String CreateUser1;
    private int Id;
    private int FFID;
    private String LinkPhone;
    private String JobAddress;
    private String FF_Address;

    public void setCourseDate(String CourseDate) {
        this.CourseDate = CourseDate;
    }

    public void setScoreNumber(int ScoreNumber) {
        this.ScoreNumber = ScoreNumber;
    }

    public void setJobTitle(String JobTitle) {
        this.JobTitle = JobTitle;
    }

    public void setFF_Name(String FF_Name) {
        this.FF_Name = FF_Name;
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

    public void setCreateTime1(String CreateTime1) {
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

    public void setFF_City(String FF_City) {
        this.FF_City = FF_City;
    }

    public void setCreateUser(String CreateUser) {
        this.CreateUser = CreateUser;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
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

    public void setTreatment(double Treatment) {
        this.Treatment = Treatment;
    }

    public void setJobStartDate(String JobStartDate) {
        this.JobStartDate = JobStartDate;
    }

    public void setCourseLast(String CourseLast) {
        this.CourseLast = CourseLast;
    }

    public void setFF_Phone(String FF_Phone) {
        this.FF_Phone = FF_Phone;
    }

    public void setPrice(double Price) {
        this.Price = Price;
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

    public void setFFID(int FFID) {
        this.FFID = FFID;
    }

    public void setLinkPhone(String LinkPhone) {
        this.LinkPhone = LinkPhone;
    }

    public void setJobAddress(String JobAddress) {
        this.JobAddress = JobAddress;
    }

    public void setFF_Address(String FF_Address) {
        this.FF_Address = FF_Address;
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

    public String getFF_Name() {
        return FF_Name;
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

    public String getCreateTime1() {
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

    public String getFF_City() {
        return FF_City;
    }

    public String getCreateUser() {
        return CreateUser;
    }

    public int getQuantity() {
        return Quantity;
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

    public double getTreatment() {
        return Treatment;
    }

    public String getJobStartDate() {
        return JobStartDate;
    }

    public String getCourseLast() {
        return CourseLast;
    }

    public String getFF_Phone() {
        return FF_Phone;
    }

    public double getPrice() {
        return Price;
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

    public int getFFID() {
        return FFID;
    }

    public String getLinkPhone() {
        return LinkPhone;
    }

    public String getJobAddress() {
        return JobAddress;
    }

    public String getFF_Address() {
        return FF_Address;
    }

    public static class CreateTimeEntity implements Parcelable {

        /**
         * Month : 9
         * Millisecond : 0
         * Year : 2015
         * Minute : 0
         * Second : 0
         * Hour : 0
         * Value : 2015-09-09 00:00:00
         * IsNull : false
         * Day : 9
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
        dest.writeString(this.CourseDate);
        dest.writeInt(this.ScoreNumber);
        dest.writeString(this.JobTitle);
        dest.writeString(this.FF_Name);
        dest.writeString(this.Remark);
        dest.writeString(this.JobTypeName);
        dest.writeString(this.JobContent);
        dest.writeString(this.CreateTime1);
        dest.writeInt(this.Id1);
        dest.writeString(this.JobBeginTime);
        dest.writeInt(this.Sort);
        dest.writeDouble(this.TotalScore);
        dest.writeInt(this.JobId);
        dest.writeString(this.Status);
        dest.writeInt(this.CompanyId);
        dest.writeString(this.LinkMan);
        dest.writeParcelable(this.CreateTime, flags);
        dest.writeString(this.FF_City);
        dest.writeString(this.CreateUser);
        dest.writeInt(this.Quantity);
        dest.writeString(this.JobEndTime);
        dest.writeString(this.JobRequirements);
        dest.writeString(this.HireNum);
        dest.writeString(this.CourseRateType);
        dest.writeDouble(this.Score);
        dest.writeInt(this.JobTypeId);
        dest.writeDouble(this.Treatment);
        dest.writeString(this.JobStartDate);
        dest.writeString(this.CourseLast);
        dest.writeString(this.FF_Phone);
        dest.writeDouble(this.Price);
        dest.writeInt(this.UserId);
        dest.writeString(this.CreateUser1);
        dest.writeInt(this.Id);
        dest.writeInt(this.FFID);
        dest.writeString(this.LinkPhone);
        dest.writeString(this.JobAddress);
        dest.writeString(this.FF_Address);
    }

    public OrderManagerBean() {
    }

    protected OrderManagerBean(Parcel in) {
        this.CourseDate = in.readString();
        this.ScoreNumber = in.readInt();
        this.JobTitle = in.readString();
        this.FF_Name = in.readString();
        this.Remark = in.readString();
        this.JobTypeName = in.readString();
        this.JobContent = in.readString();
        this.CreateTime1 = in.readString();
        this.Id1 = in.readInt();
        this.JobBeginTime = in.readString();
        this.Sort = in.readInt();
        this.TotalScore = in.readDouble();
        this.JobId = in.readInt();
        this.Status = in.readString();
        this.CompanyId = in.readInt();
        this.LinkMan = in.readString();
        this.CreateTime = in.readParcelable(CreateTimeEntity.class.getClassLoader());
        this.FF_City = in.readString();
        this.CreateUser = in.readString();
        this.Quantity = in.readInt();
        this.JobEndTime = in.readString();
        this.JobRequirements = in.readString();
        this.HireNum = in.readString();
        this.CourseRateType = in.readString();
        this.Score = in.readDouble();
        this.JobTypeId = in.readInt();
        this.Treatment = in.readDouble();
        this.JobStartDate = in.readString();
        this.CourseLast = in.readString();
        this.FF_Phone = in.readString();
        this.Price = in.readDouble();
        this.UserId = in.readInt();
        this.CreateUser1 = in.readString();
        this.Id = in.readInt();
        this.FFID = in.readInt();
        this.LinkPhone = in.readString();
        this.JobAddress = in.readString();
        this.FF_Address = in.readString();
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

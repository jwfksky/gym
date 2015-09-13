package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/9/8 0008.
 */
public class CoachBean implements Parcelable {

    /**
     * Usr_Height : 180
     * Usr_MobilePhone : 1890000000
     * Usr_UserName : ??
     * TeachingYear : 3
     * ScoreNumber : 1
     * Usr_Weight : 70.0
     * Usr_Integral : 0
     * Usr_Expertise : ???
     * Usr_Password : 111111
     * Usr_UserID : 9
     * Usr_Sex : ?
     * Usr_EmailAddress : 657344348@qq.com
     * Usr_EmployeeID :
     * TotalScore : 4.0
     * Usr_Photo : http://121.40.168.164:8090/Pictures/user_default.jpg
     * Distance : 0.0
     * Usr_User_ActualName : ??
     * Usr_Birthday : {"Month":1,"Millisecond":0,"Year":1991,"Minute":0,"Second":0,"Hour":0,"Value":"1991-01-01 00:00:00","IsNull":false,"Day":1,"IsValidDateTime":true}
     * Usr_IS_Infor_Complete : 0
     * Usr_IsCheck : 0
     * Score : 4.0
     * Usr_Shape : ???
     * Usr_IsEnable : 1
     * Signature : ???
     * Usr_UserAlia : ??
     * Usr_UserType : 2
     * Usr_CreateDateTime : {"Month":8,"Millisecond":0,"Year":2015,"Minute":26,"Second":47,"Hour":12,"Value":"2015-08-24 12:26:47","IsNull":false,"Day":24,"IsValidDateTime":true}
     * Location : ???
     */
    private int Usr_Height;
    private String Usr_MobilePhone;
    private String Usr_UserName;
    private int TeachingYear;
    private int ScoreNumber;
    private double Usr_Weight;
    private int Usr_Integral;
    private String Usr_Expertise;
    private String Usr_Password;
    private int Usr_UserID;
    private String Usr_Sex;
    private String Usr_EmailAddress;
    private String Usr_EmployeeID;
    private double TotalScore;
    private String Usr_Photo;
    private double Distance;
    private String Usr_User_ActualName;
    private UsrBirthdayEntity Usr_Birthday;
    private int Usr_IS_Infor_Complete;
    private String Usr_IsCheck;
    private double Score;
    private String Usr_Shape;
    private int Usr_IsEnable;
    private String Signature;
    private String Usr_UserAlia;
    private int Usr_UserType;
    private UsrCreateDateTimeEntity Usr_CreateDateTime;
    private String Location;

    public void setUsr_Height(int Usr_Height) {
        this.Usr_Height = Usr_Height;
    }

    public void setUsr_MobilePhone(String Usr_MobilePhone) {
        this.Usr_MobilePhone = Usr_MobilePhone;
    }

    public void setUsr_UserName(String Usr_UserName) {
        this.Usr_UserName = Usr_UserName;
    }

    public void setTeachingYear(int TeachingYear) {
        this.TeachingYear = TeachingYear;
    }

    public void setScoreNumber(int ScoreNumber) {
        this.ScoreNumber = ScoreNumber;
    }

    public void setUsr_Weight(double Usr_Weight) {
        this.Usr_Weight = Usr_Weight;
    }

    public void setUsr_Integral(int Usr_Integral) {
        this.Usr_Integral = Usr_Integral;
    }

    public void setUsr_Expertise(String Usr_Expertise) {
        this.Usr_Expertise = Usr_Expertise;
    }

    public void setUsr_Password(String Usr_Password) {
        this.Usr_Password = Usr_Password;
    }

    public void setUsr_UserID(int Usr_UserID) {
        this.Usr_UserID = Usr_UserID;
    }

    public void setUsr_Sex(String Usr_Sex) {
        this.Usr_Sex = Usr_Sex;
    }

    public void setUsr_EmailAddress(String Usr_EmailAddress) {
        this.Usr_EmailAddress = Usr_EmailAddress;
    }

    public void setUsr_EmployeeID(String Usr_EmployeeID) {
        this.Usr_EmployeeID = Usr_EmployeeID;
    }

    public void setTotalScore(double TotalScore) {
        this.TotalScore = TotalScore;
    }

    public void setUsr_Photo(String Usr_Photo) {
        this.Usr_Photo = Usr_Photo;
    }

    public void setDistance(double Distance) {
        this.Distance = Distance;
    }

    public void setUsr_User_ActualName(String Usr_User_ActualName) {
        this.Usr_User_ActualName = Usr_User_ActualName;
    }

    public void setUsr_Birthday(UsrBirthdayEntity Usr_Birthday) {
        this.Usr_Birthday = Usr_Birthday;
    }

    public void setUsr_IS_Infor_Complete(int Usr_IS_Infor_Complete) {
        this.Usr_IS_Infor_Complete = Usr_IS_Infor_Complete;
    }

    public void setUsr_IsCheck(String Usr_IsCheck) {
        this.Usr_IsCheck = Usr_IsCheck;
    }

    public void setScore(double Score) {
        this.Score = Score;
    }

    public void setUsr_Shape(String Usr_Shape) {
        this.Usr_Shape = Usr_Shape;
    }

    public void setUsr_IsEnable(int Usr_IsEnable) {
        this.Usr_IsEnable = Usr_IsEnable;
    }

    public void setSignature(String Signature) {
        this.Signature = Signature;
    }

    public void setUsr_UserAlia(String Usr_UserAlia) {
        this.Usr_UserAlia = Usr_UserAlia;
    }

    public void setUsr_UserType(int Usr_UserType) {
        this.Usr_UserType = Usr_UserType;
    }

    public void setUsr_CreateDateTime(UsrCreateDateTimeEntity Usr_CreateDateTime) {
        this.Usr_CreateDateTime = Usr_CreateDateTime;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public int getUsr_Height() {
        return Usr_Height;
    }

    public String getUsr_MobilePhone() {
        return Usr_MobilePhone;
    }

    public String getUsr_UserName() {
        return Usr_UserName;
    }

    public int getTeachingYear() {
        return TeachingYear;
    }

    public int getScoreNumber() {
        return ScoreNumber;
    }

    public double getUsr_Weight() {
        return Usr_Weight;
    }

    public int getUsr_Integral() {
        return Usr_Integral;
    }

    public String getUsr_Expertise() {
        return Usr_Expertise;
    }

    public String getUsr_Password() {
        return Usr_Password;
    }

    public int getUsr_UserID() {
        return Usr_UserID;
    }

    public String getUsr_Sex() {
        return Usr_Sex;
    }

    public String getUsr_EmailAddress() {
        return Usr_EmailAddress;
    }

    public String getUsr_EmployeeID() {
        return Usr_EmployeeID;
    }

    public double getTotalScore() {
        return TotalScore;
    }

    public String getUsr_Photo() {
        return Usr_Photo;
    }

    public double getDistance() {
        return Distance;
    }

    public String getUsr_User_ActualName() {
        return Usr_User_ActualName;
    }

    public UsrBirthdayEntity getUsr_Birthday() {
        return Usr_Birthday;
    }

    public int getUsr_IS_Infor_Complete() {
        return Usr_IS_Infor_Complete;
    }

    public String getUsr_IsCheck() {
        return Usr_IsCheck;
    }

    public double getScore() {
        return Score;
    }

    public String getUsr_Shape() {
        return Usr_Shape;
    }

    public int getUsr_IsEnable() {
        return Usr_IsEnable;
    }

    public String getSignature() {
        return Signature;
    }

    public String getUsr_UserAlia() {
        return Usr_UserAlia;
    }

    public int getUsr_UserType() {
        return Usr_UserType;
    }

    public UsrCreateDateTimeEntity getUsr_CreateDateTime() {
        return Usr_CreateDateTime;
    }

    public String getLocation() {
        return Location;
    }

    public static class UsrBirthdayEntity implements Parcelable {

        /**
         * Month : 1
         * Millisecond : 0
         * Year : 1991
         * Minute : 0
         * Second : 0
         * Hour : 0
         * Value : 1991-01-01 00:00:00
         * IsNull : false
         * Day : 1
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

        public UsrBirthdayEntity() {
        }

        protected UsrBirthdayEntity(Parcel in) {
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

        public static final Parcelable.Creator<UsrBirthdayEntity> CREATOR = new Parcelable.Creator<UsrBirthdayEntity>() {
            public UsrBirthdayEntity createFromParcel(Parcel source) {
                return new UsrBirthdayEntity(source);
            }

            public UsrBirthdayEntity[] newArray(int size) {
                return new UsrBirthdayEntity[size];
            }
        };
    }

    public static class UsrCreateDateTimeEntity implements Parcelable {

        /**
         * Month : 8
         * Millisecond : 0
         * Year : 2015
         * Minute : 26
         * Second : 47
         * Hour : 12
         * Value : 2015-08-24 12:26:47
         * IsNull : false
         * Day : 24
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

        public UsrCreateDateTimeEntity() {
        }

        protected UsrCreateDateTimeEntity(Parcel in) {
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

        public static final Creator<UsrCreateDateTimeEntity> CREATOR = new Creator<UsrCreateDateTimeEntity>() {
            public UsrCreateDateTimeEntity createFromParcel(Parcel source) {
                return new UsrCreateDateTimeEntity(source);
            }

            public UsrCreateDateTimeEntity[] newArray(int size) {
                return new UsrCreateDateTimeEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Usr_Height);
        dest.writeString(this.Usr_MobilePhone);
        dest.writeString(this.Usr_UserName);
        dest.writeInt(this.TeachingYear);
        dest.writeInt(this.ScoreNumber);
        dest.writeDouble(this.Usr_Weight);
        dest.writeInt(this.Usr_Integral);
        dest.writeString(this.Usr_Expertise);
        dest.writeString(this.Usr_Password);
        dest.writeInt(this.Usr_UserID);
        dest.writeString(this.Usr_Sex);
        dest.writeString(this.Usr_EmailAddress);
        dest.writeString(this.Usr_EmployeeID);
        dest.writeDouble(this.TotalScore);
        dest.writeString(this.Usr_Photo);
        dest.writeDouble(this.Distance);
        dest.writeString(this.Usr_User_ActualName);
        dest.writeParcelable(this.Usr_Birthday, 0);
        dest.writeInt(this.Usr_IS_Infor_Complete);
        dest.writeString(this.Usr_IsCheck);
        dest.writeDouble(this.Score);
        dest.writeString(this.Usr_Shape);
        dest.writeInt(this.Usr_IsEnable);
        dest.writeString(this.Signature);
        dest.writeString(this.Usr_UserAlia);
        dest.writeInt(this.Usr_UserType);
        dest.writeParcelable(this.Usr_CreateDateTime, flags);
        dest.writeString(this.Location);
    }

    public CoachBean() {
    }

    protected CoachBean(Parcel in) {
        this.Usr_Height = in.readInt();
        this.Usr_MobilePhone = in.readString();
        this.Usr_UserName = in.readString();
        this.TeachingYear = in.readInt();
        this.ScoreNumber = in.readInt();
        this.Usr_Weight = in.readDouble();
        this.Usr_Integral = in.readInt();
        this.Usr_Expertise = in.readString();
        this.Usr_Password = in.readString();
        this.Usr_UserID = in.readInt();
        this.Usr_Sex = in.readString();
        this.Usr_EmailAddress = in.readString();
        this.Usr_EmployeeID = in.readString();
        this.TotalScore = in.readDouble();
        this.Usr_Photo = in.readString();
        this.Distance = in.readDouble();
        this.Usr_User_ActualName = in.readString();
        this.Usr_Birthday = in.readParcelable(UsrBirthdayEntity.class.getClassLoader());
        this.Usr_IS_Infor_Complete = in.readInt();
        this.Usr_IsCheck = in.readString();
        this.Score = in.readDouble();
        this.Usr_Shape = in.readString();
        this.Usr_IsEnable = in.readInt();
        this.Signature = in.readString();
        this.Usr_UserAlia = in.readString();
        this.Usr_UserType = in.readInt();
        this.Usr_CreateDateTime = in.readParcelable(UsrCreateDateTimeEntity.class.getClassLoader());
        this.Location = in.readString();
    }

    public static final Parcelable.Creator<CoachBean> CREATOR = new Parcelable.Creator<CoachBean>() {
        public CoachBean createFromParcel(Parcel source) {
            return new CoachBean(source);
        }

        public CoachBean[] newArray(int size) {
            return new CoachBean[size];
        }
    };
}

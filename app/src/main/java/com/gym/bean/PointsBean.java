package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/8/30 0030.
 */
public class PointsBean implements Parcelable {

    /**
     * date : {"Month":5,"Millisecond":0,"Year":2015,"Minute":59,"Second":14,"Hour":20,"Value":"2015-05-19 20:59:14","IsNull":false,"Day":19,"IsValidDateTime":true}
     * course_Name : 注册
     * integral : 50
     */
    private DateEntity date;
    private String course_Name;
    private int integral;

    public void setDate(DateEntity date) {
        this.date = date;
    }

    public void setCourse_Name(String course_Name) {
        this.course_Name = course_Name;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public DateEntity getDate() {
        return date;
    }

    public String getCourse_Name() {
        return course_Name;
    }

    public int getIntegral() {
        return integral;
    }

    public static class DateEntity implements Parcelable {
        /**
         * Month : 5
         * Millisecond : 0
         * Year : 2015
         * Minute : 59
         * Second : 14
         * Hour : 20
         * Value : 2015-05-19 20:59:14
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

        public DateEntity() {
        }

        protected DateEntity(Parcel in) {
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

        public static final Parcelable.Creator<DateEntity> CREATOR = new Parcelable.Creator<DateEntity>() {
            public DateEntity createFromParcel(Parcel source) {
                return new DateEntity(source);
            }

            public DateEntity[] newArray(int size) {
                return new DateEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.date, 0);
        dest.writeString(this.course_Name);
        dest.writeInt(this.integral);
    }

    public PointsBean() {
    }

    protected PointsBean(Parcel in) {
        this.date = in.readParcelable(DateEntity.class.getClassLoader());
        this.course_Name = in.readString();
        this.integral = in.readInt();
    }

    public static final Parcelable.Creator<PointsBean> CREATOR = new Parcelable.Creator<PointsBean>() {
        public PointsBean createFromParcel(Parcel source) {
            return new PointsBean(source);
        }

        public PointsBean[] newArray(int size) {
            return new PointsBean[size];
        }
    };
}

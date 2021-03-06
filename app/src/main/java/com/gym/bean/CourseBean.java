package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015-08-26.
 */
public class CourseBean implements Parcelable {

    /**
     * create_time : {"Month":8,"Millisecond":0,"Year":2015,"Minute":0,"Second":0,"Hour":0,"Value":"2015-08-28 00:00:00","IsNull":false,"Day":28,"IsValidDateTime":true}
     * p_e_last : 555
     * IBM : 55.0
     * calorie : 55
     * weight : 555.0
     * photoPath1 : http://211.149.244.163:8090/Pictures/9f32ea4a-20bc-4cfe-8ecc-6f2f1bc7d7ed.jpg
     * id : 30
     */
    private CreateTimeEntity create_time;
    private int p_e_last;
    private double IBM;
    private int calorie;
    private double weight;
    private String photoPath1;
    private int id;

    public void setCreate_time(CreateTimeEntity create_time) {
        this.create_time = create_time;
    }

    public void setP_e_last(int p_e_last) {
        this.p_e_last = p_e_last;
    }

    public void setIBM(double IBM) {
        this.IBM = IBM;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setPhotoPath1(String photoPath1) {
        this.photoPath1 = photoPath1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CreateTimeEntity getCreate_time() {
        return create_time;
    }

    public int getP_e_last() {
        return p_e_last;
    }

    public double getIBM() {
        return IBM;
    }

    public int getCalorie() {
        return calorie;
    }

    public double getWeight() {
        return weight;
    }

    public String getPhotoPath1() {
        return photoPath1;
    }

    public int getId() {
        return id;
    }

    public static class CreateTimeEntity implements Parcelable {

        /**
         * Month : 8
         * Millisecond : 0
         * Year : 2015
         * Minute : 0
         * Second : 0
         * Hour : 0
         * Value : 2015-08-28 00:00:00
         * IsNull : false
         * Day : 28
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
        dest.writeParcelable(this.create_time, flags);
        dest.writeInt(this.p_e_last);
        dest.writeDouble(this.IBM);
        dest.writeInt(this.calorie);
        dest.writeDouble(this.weight);
        dest.writeString(this.photoPath1);
        dest.writeInt(this.id);
    }

    public CourseBean() {
    }

    protected CourseBean(Parcel in) {
        this.create_time = in.readParcelable(CreateTimeEntity.class.getClassLoader());
        this.p_e_last = in.readInt();
        this.IBM = in.readDouble();
        this.calorie = in.readInt();
        this.weight = in.readDouble();
        this.photoPath1 = in.readString();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<CourseBean> CREATOR = new Parcelable.Creator<CourseBean>() {
        public CourseBean createFromParcel(Parcel source) {
            return new CourseBean(source);
        }

        public CourseBean[] newArray(int size) {
            return new CourseBean[size];
        }
    };
}

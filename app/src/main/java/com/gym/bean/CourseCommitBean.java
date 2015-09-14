package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/9/11 0011.
 */
public class CourseCommitBean implements Parcelable {

    /**
     * createdByName : admin1
     * createdAt : {"Month":5,"Millisecond":0,"Year":2015,"Minute":49,"Second":26,"Hour":21,"Value":"2015-05-13 21:49:26","IsNull":false,"Day":13,"IsValidDateTime":true}
     * topicId : 573
     * isDeleted : 0
     * id : 1
     * replyContent : 非常好，非常好，非常好，非常好，
     * createdById : 1
     */
    private String createdByName;
    private CreatedAtEntity createdAt;
    private int topicId;
    private int isDeleted;
    private int id;
    private String replyContent;
    private int createdById;

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public void setCreatedAt(CreatedAtEntity createdAt) {
        this.createdAt = createdAt;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public CreatedAtEntity getCreatedAt() {
        return createdAt;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public int getId() {
        return id;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public int getCreatedById() {
        return createdById;
    }

    public static class CreatedAtEntity implements Parcelable {

        /**
         * Month : 5
         * Millisecond : 0
         * Year : 2015
         * Minute : 49
         * Second : 26
         * Hour : 21
         * Value : 2015-05-13 21:49:26
         * IsNull : false
         * Day : 13
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

        public CreatedAtEntity() {
        }

        protected CreatedAtEntity(Parcel in) {
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

        public static final Creator<CreatedAtEntity> CREATOR = new Creator<CreatedAtEntity>() {
            public CreatedAtEntity createFromParcel(Parcel source) {
                return new CreatedAtEntity(source);
            }

            public CreatedAtEntity[] newArray(int size) {
                return new CreatedAtEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createdByName);
        dest.writeParcelable(this.createdAt, flags);
        dest.writeInt(this.topicId);
        dest.writeInt(this.isDeleted);
        dest.writeInt(this.id);
        dest.writeString(this.replyContent);
        dest.writeInt(this.createdById);
    }

    public CourseCommitBean() {
    }

    protected CourseCommitBean(Parcel in) {
        this.createdByName = in.readString();
        this.createdAt = in.readParcelable(CreatedAtEntity.class.getClassLoader());
        this.topicId = in.readInt();
        this.isDeleted = in.readInt();
        this.id = in.readInt();
        this.replyContent = in.readString();
        this.createdById = in.readInt();
    }

    public static final Parcelable.Creator<CourseCommitBean> CREATOR = new Parcelable.Creator<CourseCommitBean>() {
        public CourseCommitBean createFromParcel(Parcel source) {
            return new CourseCommitBean(source);
        }

        public CourseCommitBean[] newArray(int size) {
            return new CourseCommitBean[size];
        }
    };
}

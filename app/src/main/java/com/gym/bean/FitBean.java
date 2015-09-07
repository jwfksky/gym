package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2015/9/2 0002.
 */
public class FitBean implements Parcelable {

    /**
     * result : 1
     * msg : 查询到相关数据
     * data : [{"JobRequirements":"http://121.40.168.164:8091/Pictures/2.jpg","CompanyId":3,"CreateTime":{"Month":5,"Millisecond":0,"Year":2015,"Minute":47,"Second":48,"Hour":14,"Value":"2015-05-12 14:47:48","IsNull":false,"Day":12,"IsValidDateTime":true},"Sort":5,"Id":572,"Usr_Photo":"http://211.149.244.163:8090/Pictures/bd34af62-2037-41cb-ab02-d8057241ea75.jpg","JobTitle":"有氧健身","JobTypeName":"健身","Remark":" "},{"JobRequirements":"http://121.40.168.164:8091/Pictures/2.jpg","CompanyId":3,"CreateTime":{"Month":5,"Millisecond":0,"Year":2015,"Minute":28,"Second":16,"Hour":17,"Value":"2015-05-20 17:28:16","IsNull":false,"Day":20,"IsValidDateTime":true},"Sort":1,"Id":580,"Usr_Photo":"http://211.149.244.163:8090/Pictures/bd34af62-2037-41cb-ab02-d8057241ea75.jpg","JobTitle":"体操","Remark":" "},{"JobRequirements":"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","CompanyId":0,"CreateTime":{"Month":5,"Millisecond":0,"Year":2015,"Minute":8,"Second":56,"Hour":18,"Value":"2015-05-25 18:08:56","IsNull":false,"Day":25,"IsValidDateTime":true},"Id":592,"JobTitle":"(null),(null)","Remark":" "},{"JobRequirements":"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","CompanyId":0,"CreateTime":{"Month":5,"Millisecond":0,"Year":2015,"Minute":48,"Second":23,"Hour":11,"Value":"2015-05-25 11:48:23","IsNull":false,"Day":25,"IsValidDateTime":true},"Id":591,"JobTitle":"(null),(null)","Remark":" "},{"JobRequirements":"http://211.149.244.163:8090/Pictures/716520da-4363-4eb0-8081-d73125f69460.jpg","CompanyId":3,"Id":616,"Usr_Photo":"http://211.149.244.163:8090/Pictures/bd34af62-2037-41cb-ab02-d8057241ea75.jpg","JobTitle":"知识","Remark":" "},{"JobRequirements":"http://211.149.244.163:8090/Pictures/6ce337b8-278c-4c24-8402-56b848ebad2d.jpg","CompanyId":3,"Id":617,"Usr_Photo":"http://211.149.244.163:8090/Pictures/bd34af62-2037-41cb-ab02-d8057241ea75.jpg","JobTitle":"保护方法","Remark":" "}]
     * totalPageCount : 4
     */
    private int result;
    private String msg;
    private List<DataEntity> data;
    private int totalPageCount;

    public void setResult(int result) {
        this.result = result;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public static class DataEntity implements Parcelable {
        /**
         * JobRequirements : http://121.40.168.164:8091/Pictures/2.jpg
         * CompanyId : 3
         * CreateTime : {"Month":5,"Millisecond":0,"Year":2015,"Minute":47,"Second":48,"Hour":14,"Value":"2015-05-12 14:47:48","IsNull":false,"Day":12,"IsValidDateTime":true}
         * Sort : 5
         * Id : 572
         * Usr_Photo : http://211.149.244.163:8090/Pictures/bd34af62-2037-41cb-ab02-d8057241ea75.jpg
         * JobTitle : 有氧健身
         * JobTypeName : 健身
         * Remark :
         */

        private String JobRequirements;
        private int CompanyId;
        private CreateTimeEntity CreateTime;
        private int Sort;
        private int Id;
        private String Usr_Photo;
        private String JobTitle;
        private String JobTypeName;
        private String Remark;

        public void setJobRequirements(String JobRequirements) {
            this.JobRequirements = JobRequirements;
        }

        public void setCompanyId(int CompanyId) {
            this.CompanyId = CompanyId;
        }

        public void setCreateTime(CreateTimeEntity CreateTime) {
            this.CreateTime = CreateTime;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public void setUsr_Photo(String Usr_Photo) {
            this.Usr_Photo = Usr_Photo;
        }

        public void setJobTitle(String JobTitle) {
            this.JobTitle = JobTitle;
        }

        public void setJobTypeName(String JobTypeName) {
            this.JobTypeName = JobTypeName;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getJobRequirements() {
            return JobRequirements;
        }

        public int getCompanyId() {
            return CompanyId;
        }

        public CreateTimeEntity getCreateTime() {
            return CreateTime;
        }

        public int getSort() {
            return Sort;
        }

        public int getId() {
            return Id;
        }

        public String getUsr_Photo() {
            return Usr_Photo;
        }

        public String getJobTitle() {
            return JobTitle;
        }

        public String getJobTypeName() {
            return JobTypeName;
        }

        public String getRemark() {
            return Remark;
        }

        public static class CreateTimeEntity implements Parcelable {

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
            dest.writeString(this.JobRequirements);
            dest.writeInt(this.CompanyId);
            dest.writeParcelable(this.CreateTime, flags);
            dest.writeInt(this.Sort);
            dest.writeInt(this.Id);
            dest.writeString(this.Usr_Photo);
            dest.writeString(this.JobTitle);
            dest.writeString(this.JobTypeName);
            dest.writeString(this.Remark);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.JobRequirements = in.readString();
            this.CompanyId = in.readInt();
            this.CreateTime = in.readParcelable(CreateTimeEntity.class.getClassLoader());
            this.Sort = in.readInt();
            this.Id = in.readInt();
            this.Usr_Photo = in.readString();
            this.JobTitle = in.readString();
            this.JobTypeName = in.readString();
            this.Remark = in.readString();
        }

        public static final Parcelable.Creator<DataEntity> CREATOR = new Parcelable.Creator<DataEntity>() {
            public DataEntity createFromParcel(Parcel source) {
                return new DataEntity(source);
            }

            public DataEntity[] newArray(int size) {
                return new DataEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.result);
        dest.writeString(this.msg);
        dest.writeTypedList(data);
        dest.writeInt(this.totalPageCount);
    }

    public FitBean() {
    }

    protected FitBean(Parcel in) {
        this.result = in.readInt();
        this.msg = in.readString();
        this.data = in.createTypedArrayList(DataEntity.CREATOR);
        this.totalPageCount = in.readInt();
    }

    public static final Parcelable.Creator<FitBean> CREATOR = new Parcelable.Creator<FitBean>() {
        public FitBean createFromParcel(Parcel source) {
            return new FitBean(source);
        }

        public FitBean[] newArray(int size) {
            return new FitBean[size];
        }
    };
}

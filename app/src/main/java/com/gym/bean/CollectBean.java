package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/31 0031.
 */
public class CollectBean implements Parcelable {

    /**
     * result : 1
     * msg : 查询到相关数据
     * data : [{"Usr_UserName":"张三","ID":1}]
     * totalPageCount : 0
     */
    private int result;
    private String msg;
    private ArrayList<DataEntity> data;
    private int totalPageCount;

    public void setResult(int result) {
        this.result = result;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(ArrayList<DataEntity> data) {
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
         * Usr_UserName : 张三
         * ID : 1
         */
        private String Usr_UserName;
        private int ID;

        public void setUsr_UserName(String Usr_UserName) {
            this.Usr_UserName = Usr_UserName;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getUsr_UserName() {
            return Usr_UserName;
        }

        public int getID() {
            return ID;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.Usr_UserName);
            dest.writeInt(this.ID);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.Usr_UserName = in.readString();
            this.ID = in.readInt();
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

    public CollectBean() {
    }

    protected CollectBean(Parcel in) {
        this.result = in.readInt();
        this.msg = in.readString();
        this.data = in.createTypedArrayList(DataEntity.CREATOR);
        this.totalPageCount = in.readInt();
    }

    public static final Parcelable.Creator<CollectBean> CREATOR = new Parcelable.Creator<CollectBean>() {
        public CollectBean createFromParcel(Parcel source) {
            return new CollectBean(source);
        }

        public CollectBean[] newArray(int size) {
            return new CollectBean[size];
        }
    };
}

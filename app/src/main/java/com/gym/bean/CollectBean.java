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

    public CollectBean() {
    }

    protected CollectBean(Parcel in) {
        this.Usr_UserName = in.readString();
        this.ID = in.readInt();
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

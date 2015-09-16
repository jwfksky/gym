package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/8/31 0031.
 */
public class CollectBean implements Parcelable {


    /**
     * Usr_UserName : admin3
     * ID : 1
     * Usr_Photo : http://211.149.244.163:8090/Pictures/bd34af62-2037-41cb-ab02-d8057241ea75.jpg
     */
    private String Usr_UserName;
    private int ID;
    private String Usr_Photo;

    public void setUsr_UserName(String Usr_UserName) {
        this.Usr_UserName = Usr_UserName;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUsr_Photo(String Usr_Photo) {
        this.Usr_Photo = Usr_Photo;
    }

    public String getUsr_UserName() {
        return Usr_UserName;
    }

    public int getID() {
        return ID;
    }

    public String getUsr_Photo() {
        return Usr_Photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Usr_UserName);
        dest.writeInt(this.ID);
        dest.writeString(this.Usr_Photo);
    }

    public CollectBean() {
    }

    protected CollectBean(Parcel in) {
        this.Usr_UserName = in.readString();
        this.ID = in.readInt();
        this.Usr_Photo = in.readString();
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

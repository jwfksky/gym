package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/9/8 0008.
 */
public class CityBean implements Parcelable {

    /**
     * province : 云南省
     * city : 景洪市
     */
    private String province;
    private String city;

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.province);
        dest.writeString(this.city);
    }

    public CityBean() {
    }

    protected CityBean(Parcel in) {
        this.province = in.readString();
        this.city = in.readString();
    }

    public static final Parcelable.Creator<CityBean> CREATOR = new Parcelable.Creator<CityBean>() {
        public CityBean createFromParcel(Parcel source) {
            return new CityBean(source);
        }

        public CityBean[] newArray(int size) {
            return new CityBean[size];
        }
    };
}

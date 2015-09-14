package com.gym.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/9/11 0011.
 */
public class SpaceDetailBean implements Parcelable {

    /**
     * PeopleNumber : 50
     * EncoreNumber : 1
     * Category : 舞蹈
     * Address : 上海市虹桥火车站
     * InstrumentType : 1
     * InstrumentNumber : 1
     * EquipmentNewAndOld : 全新
     * ScoreNumber : 0
     * City : 上海市
     * ShowerRoom : 1
     * Score : 0.0
     * SeparateRoom : 1
     * OtherSettings : 1
     * Price : 100.0
     * Phone : 110
     * AreaCovered : 1.0
     * CoachNumber : 1
     * WardrobeNumber : 1
     * id : 3
     * NozzleNumber : 1
     * SaunaRoom : 11
     * TotalScore : 0.0
     * GymName : JSD健身馆
     * Create_Time : 00:00:00
     */
    private int PeopleNumber;
    private int EncoreNumber;
    private String Category;
    private String Address;
    private String InstrumentType;
    private int InstrumentNumber;
    private String EquipmentNewAndOld;
    private int ScoreNumber;
    private String City;
    private int ShowerRoom;
    private double Score;
    private int SeparateRoom;
    private String OtherSettings;
    private double Price;
    private String Phone;
    private double AreaCovered;
    private int CoachNumber;
    private int WardrobeNumber;
    private int id;
    private int NozzleNumber;
    private int SaunaRoom;
    private double TotalScore;
    private String GymName;
    private String Create_Time;

    public void setPeopleNumber(int PeopleNumber) {
        this.PeopleNumber = PeopleNumber;
    }

    public void setEncoreNumber(int EncoreNumber) {
        this.EncoreNumber = EncoreNumber;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setInstrumentType(String InstrumentType) {
        this.InstrumentType = InstrumentType;
    }

    public void setInstrumentNumber(int InstrumentNumber) {
        this.InstrumentNumber = InstrumentNumber;
    }

    public void setEquipmentNewAndOld(String EquipmentNewAndOld) {
        this.EquipmentNewAndOld = EquipmentNewAndOld;
    }

    public void setScoreNumber(int ScoreNumber) {
        this.ScoreNumber = ScoreNumber;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setShowerRoom(int ShowerRoom) {
        this.ShowerRoom = ShowerRoom;
    }

    public void setScore(double Score) {
        this.Score = Score;
    }

    public void setSeparateRoom(int SeparateRoom) {
        this.SeparateRoom = SeparateRoom;
    }

    public void setOtherSettings(String OtherSettings) {
        this.OtherSettings = OtherSettings;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setAreaCovered(double AreaCovered) {
        this.AreaCovered = AreaCovered;
    }

    public void setCoachNumber(int CoachNumber) {
        this.CoachNumber = CoachNumber;
    }

    public void setWardrobeNumber(int WardrobeNumber) {
        this.WardrobeNumber = WardrobeNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNozzleNumber(int NozzleNumber) {
        this.NozzleNumber = NozzleNumber;
    }

    public void setSaunaRoom(int SaunaRoom) {
        this.SaunaRoom = SaunaRoom;
    }

    public void setTotalScore(double TotalScore) {
        this.TotalScore = TotalScore;
    }

    public void setGymName(String GymName) {
        this.GymName = GymName;
    }

    public void setCreate_Time(String Create_Time) {
        this.Create_Time = Create_Time;
    }

    public int getPeopleNumber() {
        return PeopleNumber;
    }

    public int getEncoreNumber() {
        return EncoreNumber;
    }

    public String getCategory() {
        return Category;
    }

    public String getAddress() {
        return Address;
    }

    public String getInstrumentType() {
        return InstrumentType;
    }

    public int getInstrumentNumber() {
        return InstrumentNumber;
    }

    public String getEquipmentNewAndOld() {
        return EquipmentNewAndOld;
    }

    public int getScoreNumber() {
        return ScoreNumber;
    }

    public String getCity() {
        return City;
    }

    public int getShowerRoom() {
        return ShowerRoom;
    }

    public double getScore() {
        return Score;
    }

    public int getSeparateRoom() {
        return SeparateRoom;
    }

    public String getOtherSettings() {
        return OtherSettings;
    }

    public double getPrice() {
        return Price;
    }

    public String getPhone() {
        return Phone;
    }

    public double getAreaCovered() {
        return AreaCovered;
    }

    public int getCoachNumber() {
        return CoachNumber;
    }

    public int getWardrobeNumber() {
        return WardrobeNumber;
    }

    public int getId() {
        return id;
    }

    public int getNozzleNumber() {
        return NozzleNumber;
    }

    public int getSaunaRoom() {
        return SaunaRoom;
    }

    public double getTotalScore() {
        return TotalScore;
    }

    public String getGymName() {
        return GymName;
    }

    public String getCreate_Time() {
        return Create_Time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.PeopleNumber);
        dest.writeInt(this.EncoreNumber);
        dest.writeString(this.Category);
        dest.writeString(this.Address);
        dest.writeString(this.InstrumentType);
        dest.writeInt(this.InstrumentNumber);
        dest.writeString(this.EquipmentNewAndOld);
        dest.writeInt(this.ScoreNumber);
        dest.writeString(this.City);
        dest.writeInt(this.ShowerRoom);
        dest.writeDouble(this.Score);
        dest.writeInt(this.SeparateRoom);
        dest.writeString(this.OtherSettings);
        dest.writeDouble(this.Price);
        dest.writeString(this.Phone);
        dest.writeDouble(this.AreaCovered);
        dest.writeInt(this.CoachNumber);
        dest.writeInt(this.WardrobeNumber);
        dest.writeInt(this.id);
        dest.writeInt(this.NozzleNumber);
        dest.writeInt(this.SaunaRoom);
        dest.writeDouble(this.TotalScore);
        dest.writeString(this.GymName);
        dest.writeString(this.Create_Time);
    }

    public SpaceDetailBean() {
    }

    protected SpaceDetailBean(Parcel in) {
        this.PeopleNumber = in.readInt();
        this.EncoreNumber = in.readInt();
        this.Category = in.readString();
        this.Address = in.readString();
        this.InstrumentType = in.readString();
        this.InstrumentNumber = in.readInt();
        this.EquipmentNewAndOld = in.readString();
        this.ScoreNumber = in.readInt();
        this.City = in.readString();
        this.ShowerRoom = in.readInt();
        this.Score = in.readDouble();
        this.SeparateRoom = in.readInt();
        this.OtherSettings = in.readString();
        this.Price = in.readDouble();
        this.Phone = in.readString();
        this.AreaCovered = in.readDouble();
        this.CoachNumber = in.readInt();
        this.WardrobeNumber = in.readInt();
        this.id = in.readInt();
        this.NozzleNumber = in.readInt();
        this.SaunaRoom = in.readInt();
        this.TotalScore = in.readDouble();
        this.GymName = in.readString();
        this.Create_Time = in.readString();
    }

    public static final Parcelable.Creator<SpaceDetailBean> CREATOR = new Parcelable.Creator<SpaceDetailBean>() {
        public SpaceDetailBean createFromParcel(Parcel source) {
            return new SpaceDetailBean(source);
        }

        public SpaceDetailBean[] newArray(int size) {
            return new SpaceDetailBean[size];
        }
    };
}

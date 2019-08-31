package com.example.week3_day3;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

public class Character implements Parcelable {

    private String descriptor;
    private String race;
    private String cclass;
    private String whatDo;
    private String total;

    public Character() {
        Random rand = new Random();
        int n = rand.nextInt(4);
        switch (n + 1) {
            case 1:
                this.descriptor = "Conceited ";
                break;
            case 2:
                this.descriptor = "Bitchy ";
                break;
            case 3:
                this.descriptor = "Suspicious ";
                break;
            case 4:
                this.descriptor = "Sensitive ";
        }
        int x = rand.nextInt(5);
        switch (x + 1) {
            case 1:
                this.race = "human ";
                break;
            case 2:
                this.race = "elf ";
                break;
            case 3:
                this.race = "tiefling ";
                break;
            case 4:
                this.race = "half-orc ";
                break;
            case 5:
                this.race = "dwarf ";
        }
        int y = rand.nextInt(6);
        switch (y + 1) {
            case 1:
                this.cclass="ranger, ";
                break;
            case 2:
                this.cclass="bard, ";
                break;
            case 3:
                this.cclass="monk, ";
                break;
            case 4:
                this.cclass="paladin, ";
                break;
            case 5:
                this.cclass="fighter, ";
                break;
            case 6:
                this.cclass="wizard, ";
        }
        int z = rand.nextInt(4);
        switch (z + 1) {
            case 1:
                this.whatDo ="who fights for species equality.";
                break;
            case 2:
                this.whatDo="who wants to prove they're no longer that goody kid.";
                break;
            case 3:
                this.whatDo="who was apprenticed to a failed alchemist.";
                break;
            case 4:
                this.whatDo="who has a twin that constantly impersonates them.";
        }
    }

    protected Character(Parcel in) {
        descriptor = in.readString();
        race = in.readString();
        cclass = in.readString();
        whatDo = in.readString();
        total = in.readString();
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(descriptor);
        parcel.writeString(race);
        parcel.writeString(cclass);
        parcel.writeString(whatDo);
        parcel.writeString(total);
    }

    public String getCclass() {
        return cclass;
    }

    public void setCclass(String cclass) {
        this.cclass = cclass;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getWhatDo() {
        return whatDo;
    }

    public void setWhatDo(String whatDo) {
        this.whatDo = whatDo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}

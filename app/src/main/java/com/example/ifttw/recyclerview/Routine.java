package com.example.ifttw.recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class Routine implements Parcelable {
    //private int id;
    private String functionality;
    private String condition;
    private String action;

    public Routine(){
    }

    public Routine( String functionality, String condition, String action) {
        //this.id = id;
        this.functionality = functionality;
        this.condition = condition;
        this.action = action;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getFunctionality() {
        return functionality;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(this.id);
        dest.writeString(this.functionality);
        dest.writeString(this.condition);
        dest.writeString(this.action);
    }

    protected Routine(Parcel in) {
//        this.id = in.readInt();
        this.functionality = in.readString();
        this.condition = in.readString();
        this.action = in.readString();
    }

    public static final Parcelable.Creator<Routine> CREATOR = new Parcelable.Creator<Routine>() {
        @Override
        public Routine createFromParcel(Parcel source) {
            return new Routine(source);
        }

        @Override
        public Routine[] newArray(int size) {
            return new Routine[size];
        }
    };
}

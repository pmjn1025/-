package com.example.recyclerviewtest1_3;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.TextView;

public class Person implements Parcelable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String age;
    private String price;
    private int image;

    protected Person(Parcel in) {
        image = in.readInt();
    }

    public Person(int image, String name, String day, String time) {
        this.image =image;
        this.name = name;
        this.age = day;
        this.price = time;

    }

    public Person() {

    }

    public Person(String toString, String toString1, String toString2) {

    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public static Creator<Person> getCREATOR() {
        return CREATOR;
    }
}

package com.mobike.android_architecture.kotlin.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Parcelable序列化反序列化过程 适合内存级别
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-07 20:06
 */
public class ItemModel implements Parcelable {

    public String title_display; // 标题
    public String pict_url;  // 图片
    public double reserve_price_str; // 原价文案
    public double zk_final_price_str; //  券后价文案

    protected ItemModel(Parcel in) {
        title_display = in.readString();
        pict_url = in.readString();
        reserve_price_str = in.readDouble();
        zk_final_price_str = in.readDouble();
    }

    /**
     * 序列化
     *
     * @param dest  Parcel
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title_display);
        dest.writeString(pict_url);
        dest.writeDouble(reserve_price_str);
        dest.writeDouble(zk_final_price_str);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 反序列化
     */
    public static final Creator<ItemModel> CREATOR = new Creator<ItemModel>() {
        @Override
        public ItemModel createFromParcel(Parcel in) {
            return new ItemModel(in);
        }

        @Override
        public ItemModel[] newArray(int size) {
            return new ItemModel[size];
        }


    };
}

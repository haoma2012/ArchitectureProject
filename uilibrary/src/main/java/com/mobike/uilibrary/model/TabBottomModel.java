package com.mobike.uilibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-30 02:13
 */
public class TabBottomModel implements Parcelable {

    public String tabName;
    public int position;

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 序列过程writeToParcel写入
     * @param out
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(position);
        out.writeString(tabName);

    }

    /**
     * 反序列化过程 CREATOR 来完成
     * 通过 Parcel 的一系列read方法来完成
     */
    public static final Creator<TabBottomModel> CREATOR = new Creator<TabBottomModel>() {
        @Override
        public TabBottomModel createFromParcel(Parcel in) {
            return new TabBottomModel(in);
        }

        @Override
        public TabBottomModel[] newArray(int size) {
            return new TabBottomModel[size];
        }
    };

    protected TabBottomModel(Parcel in) {
        position = in.readInt();
        tabName = in.readString();
    }


}

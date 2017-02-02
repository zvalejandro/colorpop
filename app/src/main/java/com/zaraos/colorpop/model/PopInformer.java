package com.zaraos.colorpop.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * Created by alejandrozaraos on 01/02/17.
 */

public class PopInformer implements Parcelable {

    private Integer width;
    private Integer height;
    private Integer windowLeft;
    private Integer windowTop;
    private Integer circleColor;
    private Integer pageColor;
    private Integer startPointX;
    private Integer startPointY;
    private Integer statusBarHeight;
    private Boolean isBehindStatusBar = false;

    public PopInformer() {
    }

    public PopInformer(View view) {
        setView(view);
    }

    public void setView(View view) {
        if (view == null)
            return;

        setWidth(view.getWidth());
        setHeight(view.getHeight());

        int[] locationOnScreen = {0, 0};
        view.getLocationOnScreen(locationOnScreen);
        setWindowLeft(locationOnScreen[0]);
        setWindowTop(locationOnScreen[1]);
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWindowLeft() {
        return windowLeft;
    }

    public void setWindowLeft(Integer windowLeft) {
        this.windowLeft = windowLeft;
    }

    public Integer getWindowTop() {
        return windowTop;
    }

    public void setWindowTop(Integer windowTop) {
        this.windowTop = windowTop;
    }

    public Integer getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(Integer circleColor) {
        this.circleColor = circleColor;
    }

    public Integer getPageColor() {
        return pageColor;
    }

    public void setPageColor(Integer pageColor) {
        this.pageColor = pageColor;
    }

    public Integer getStartPointX() {
        return startPointX;
    }

    public void setStartPointX(Integer startPointX) {
        this.startPointX = startPointX;
    }

    public Integer getStartPointY() {
        return startPointY;
    }

    public void setStartPointY(Integer startPointY) {
        this.startPointY = startPointY;
    }

    public Integer getStatusBarHeight() {
        return statusBarHeight;
    }

    public void setStatusBarHeight(Integer statusBarHeight) {
        this.statusBarHeight = statusBarHeight;
    }

    public Boolean isBehindStatusBar() {
        return isBehindStatusBar;
    }

    public void setBehindStatusBar(Boolean behindStatusBar) {
        isBehindStatusBar = behindStatusBar;
    }

    protected PopInformer(Parcel in) {
        width = in.readInt();
        height = in.readInt();
        windowLeft = in.readInt();
        windowTop = in.readInt();
        circleColor = in.readInt();
        pageColor = in.readInt();
        startPointX = in.readInt();
        startPointY = in.readInt();
        statusBarHeight = in.readInt();
        isBehindStatusBar = in.readByte() != 0;
    }

    public static final Parcelable.Creator<PopInformer> CREATOR = new Parcelable.Creator<PopInformer>() {
        @Override
        public PopInformer createFromParcel(Parcel in) {
            return new PopInformer(in);
        }

        @Override
        public PopInformer[] newArray(int size) {
            return new PopInformer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        writeInteger(dest, width);
        writeInteger(dest, height);
        writeInteger(dest, windowLeft);
        writeInteger(dest, windowTop);
        writeInteger(dest, circleColor);
        writeInteger(dest, pageColor);
        writeInteger(dest, startPointX);
        writeInteger(dest, startPointY);
        writeInteger(dest, statusBarHeight);
        dest.writeByte((byte) (isBehindStatusBar ? 1 : 0));
    }

    private void writeInteger(Parcel dest, Integer res){
        if(res != null)
            dest.writeInt(res);
    }
}

package com.jph.takephoto.model;

import java.io.Serializable;

import me.shaohui.advancedluban.Luban;

/**
 * Luban配置类
 * Author: crazycodeboy
 * Date: 2016/11/5 0007 20:10
 * Version:4.0.0
 * 技术博文：http://www.devio.org/
 * GitHub:https://github.com/crazycodeboy
 * Eamil:crazycodeboy@gmail.com
 */
public class LuBanOptions implements Serializable{
    /**
     * 压缩到的最大大小，单位B
     */
    private int maxSize;
    private int maxHeight;
    private int maxWidth;
    private int gear= Luban.CUSTOM_GEAR;
    private LuBanOptions(){}

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    public static class Builder{
        private LuBanOptions options;

        public Builder() {
            options=new LuBanOptions();
        }

        public Builder setMaxSize(int maxSize) {
            options.setMaxSize(maxSize);
            return this;
        }

        public Builder setMaxHeight(int maxHeight) {
            options.setMaxHeight(maxHeight);
            return this;
        }

        public Builder setMaxWidth(int maxWidth) {
            options.setMaxWidth(maxWidth);
            return this;
        }

        public Builder setGear(int gear) {
            options.setGear(gear);
            return this;
        }
        public LuBanOptions create(){
            return options;
        }
    }
}

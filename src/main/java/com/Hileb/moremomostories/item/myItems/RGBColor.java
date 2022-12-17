package com.Hileb.moremomostories.item.myItems;

public class RGBColor {
    public final int color;
    public RGBColor(int index){
        color=index;
    }
    public RGBColor(int redIn,int greenIn,int blueIn){
        color=(redIn*256*256)+(greenIn*256)+(blueIn);
    }
    public int getColor(){
        return color;
    }
    public int getBlue(){
        return color%256;
    }
    public int getGreen(){
        return (color%(256*256)-getBlue())/256;
    }
    public int getRed(){
        return (color-getGreen()-getBlue())/(256*256);
    }
}

package com.innodroid.complextransitiondemo;

public class ImageSource {
    public static int CategorySize = 10;
    public static String[] Categories = new String[] { "animals", "food", "business", "sports" };
    public static int ImageCount = Categories.length * CategorySize;

    public static String getThumbnailAtPosition(int position) {
        return "http://lorempixel.com/200/200/" + Categories[position / CategorySize] + "/" + (position % CategorySize + 1);
    }

    public static String getImageAtPosition(int position) {
        return "http://lorempixel.com/600/300/" + Categories[position / CategorySize] + "/" + (position % CategorySize + 1);
    }
}

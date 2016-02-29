package com.example.danny.geometryhelper;

public class ImaginaryNumber {

    private double realNum;
    private double imageNum;

    public ImaginaryNumber(double real, double image){
        realNum = real;
        imageNum = image;
    }

    public double getImageNum() {return imageNum;}

    public double getRealNum(){return realNum;}

    public String toString(){

        int real = (int) realNum;
        int image = (int) imageNum;

        if((real == realNum) && (image == imageNum))
            if(image < 0)
                return real + " - " + -image + "i";
            else
                return real + " + " + image + "i";

        if(imageNum < 0)
            return realNum + " - " + -imageNum + "i";
        else
            return realNum + " + " + imageNum + "i";

    }

}

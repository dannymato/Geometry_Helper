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

        return ((real==realNum) && (image == imageNum)) ?
                ((image < 0) ? real + " - " + -image + "i":real + " + " + image + "i")
                :((imageNum < 0) ? realNum + " - " + -imageNum + "i":realNum + " + " + imageNum + "i");

    }

}

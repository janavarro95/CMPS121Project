package Utilities.Math;

public class Random {

    public static int getRandomExclusive(int lower, int upper){
        java.util.Random r = new java.util.Random();
        int Result = r.nextInt(upper-lower) + lower;
        return  Result;
    }
}

package cpny.korc.korcapp.helpers;

/**
 * Created by Arquia on 12/03/2018.
 */

public  class StringHelper
{
    public static String padLeft(String textToPad, int positions, char charToPad){
        return String.format("%1$" + positions + "s", textToPad).replace(' ', charToPad);
    }
}
package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Паша on 21.05.2016.
 */
public class ConsoleHelpe {


    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static void writeMessage(String line){
        System.out.println(line);
    }
}

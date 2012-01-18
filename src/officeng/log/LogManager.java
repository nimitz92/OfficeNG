package officeng.log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class LogManager {
    private static String path = "logs/";

    public static void log(String message){
        Date dt = new Date();
        java.sql.Date now = new java.sql.Date(dt.getTime());
        String str = "["+dt.toString()+"] "+message+"\r\n";
        try {
            FileOutputStream fw = new FileOutputStream(path+now.toString()+".txt", true);
            fw.write(str.getBytes());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<String> getLogs(){
        Date dt = new Date();
        java.sql.Date now = new java.sql.Date(dt.getTime());
        ArrayList<String> list = new ArrayList<String>();
		try {
            FileInputStream fw = new FileInputStream(path+now.toString()+".txt");
            Scanner sc = new Scanner(fw);
            while(sc.hasNext()){
                list.add(sc.nextLine());
            }
            sc.close();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}

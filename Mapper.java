package com.mycompany.mpwordcount;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author kimji
 */
public class Mapper {
    
    public static String fileLocation = "\\workshop.txt";
    private ArrayList<String> a = new ArrayList<String>();
    
    public ArrayList<String> getList(){
        return this.a;
    }
    
    public void load(){
        try{
            FileReader f = new FileReader(fileLocation);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()){
                String line = s.nextLine();
                String[] words = line.split("\\s");
                for (String word : words) {
                    if (!word.isEmpty()){
                        word = word.toLowerCase();
                        word = word.replace(",","");
                        word = word.replace(".","");
                        word = word.replace(";","");
                        word = word.replace("\"","");
                        word = word.replace("?","");
                        word = word.replace("(","");
                        word = word.replace(")","");
                        word = word.replace(":","");
                        word = word.replace("'s","");
                        a.add(word);
                    }
                }
            }
            f.close();
        }catch (IOException ex1){
            System.out.println("An error occured.");
            ex1.printStackTrace();
        }
    }
    
}

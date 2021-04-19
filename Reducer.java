package com.mycompany.mpwordcount;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import static java.util.stream.Collectors.toMap;

public class Reducer {
 private Hashtable<String, Integer> map = new Hashtable<String, Integer>();
 private Map<String, Integer> sorted;

 public Hashtable<String, Integer> getMap(){
     return map;
 }
 
 public void setMap(Hashtable<String, Integer> map){
     this.map=map;
 }
 
 public void sort(){     
     sorted = map
        .entrySet()
        .stream()
        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        .collect(
            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                LinkedHashMap::new));   
 }
 
 public void save(){ 
    try {
         FileWriter f = new FileWriter("\\result.txt");
         for (Entry<String, Integer> entry : sorted.entrySet()) {
             f.write(entry.getKey() + ": " + entry.getValue() + "\n");
         }
         f.close();
         System.out.println("Successfully wrote to the file.");
       } catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
       }

}
 
 public void reduce(ArrayList<String> words){
     
     for (int i=0; i<words.size(); i++) {
         String word = words.get(i);
         if (map.containsKey(word)) { //check if map hashtable contains the word
             int count = map.get(word); //get the count in that word
             count++;
             map.put(word, count);
         } else {
             map.put(word, 1);
         }
     }     
}
 
 public static void main(String[] args) {
     
     Mapper m = new Mapper();
     m.load();
     Reducer r = new Reducer();
     ArrayList<String> a = m.getList();
     r.reduce(a);
     r.sort();
     r.save();
 }
}

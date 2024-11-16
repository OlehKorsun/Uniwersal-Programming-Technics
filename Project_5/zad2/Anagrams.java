package zad2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Anagrams {
    ArrayList<String> list; // all words
    public Anagrams(String str){
        list = new ArrayList<>();
        ArrayList<String> tmp = new ArrayList<>();
        try{
            List<String> tmp2 = Files.readAllLines(Paths.get(str));
            for(String el2 : tmp2){
                tmp.addAll(Arrays.asList(el2.split(" ")));
            }
            list.addAll(tmp);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public List<List<String>> getSortedByAnQty(){

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(String el : list){
            char[] tabl = el.toCharArray();
            Arrays.sort(tabl);
            if(!map.containsKey(Arrays.toString(tabl))){
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(el);
                map.put(Arrays.toString(tabl), tmp);
            }
            else {
                map.get(Arrays.toString(tabl)).add(el);
            }
        }

        ArrayList<List<String>> tmp = new ArrayList<>();
        for(Map.Entry<String, ArrayList<String>> entry : map.entrySet()){
            tmp.add(entry.getValue());
        }

        tmp.sort((list1, list2) -> Integer.compare(list2.size(), list1.size()));

        return tmp;
    }

    public String getAnagramsFor(String word){
        StringBuilder builder = new StringBuilder();
        builder.append(word+": [");
        char[] tabl = word.toCharArray();
        Arrays.sort(tabl);
        for(String el : list){
            if(!el.equals(word)){
                char[] tmp = el.toCharArray();
                Arrays.sort(tmp);
                if(Arrays.toString(tabl).equals(Arrays.toString(tmp))){
                    builder.append(el+", ");
                }
            }
        }

        return (builder.toString().length() == word.length()+3) ? (builder.toString()+"]") : (builder.toString().substring(0, builder.length()-2)+"]");
    }
}  

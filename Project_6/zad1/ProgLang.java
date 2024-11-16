package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {
    ArrayList<ArrayList<String>> list;

    public ProgLang(String nazwaPliku){
        list = new ArrayList<>();
        try{
            List<String> date = Files.readAllLines(Paths.get(nazwaPliku));
            for(int i = 0; i<date.size(); ++i){
                String[] tmp = date.get(i).split("\\s");
                list.add(new ArrayList<>());
                list.get(i).addAll(Arrays.asList(tmp));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Map<String, ArrayList<String>> getLangsMap(){
        LinkedHashMap<String, ArrayList<String>> tmp = new LinkedHashMap<>();
        for(ArrayList<String> el : list){
            if(!tmp.containsKey(el.get(0))){
                tmp.put(el.get(0), new ArrayList<>());
                for(int i = 1; i<el.size(); ++i)
                    if(!tmp.get(el.get(0)).contains(el.get(i)))
                        tmp.get(el.get(0)).add(el.get(i));
            } else {
                for(int i = 1; i<el.size(); ++i)
                    if(!tmp.get(el.get(0)).contains(el.get(i)))
                        tmp.get(el.get(0)).add(el.get(i));
            }
        }
        return tmp;


    }

    public Map<String, ArrayList<String>> getProgsMap(){
        LinkedHashMap<String, ArrayList<String>> tmp = new LinkedHashMap<>();

        for(int i = 0; i<list.size(); ++i){
            for(int j = 1; j<list.get(i).size(); ++j){
                if(!tmp.containsKey(list.get(i).get(j))){
                    tmp.put(list.get(i).get(j), new ArrayList<>());
                    tmp.get(list.get(i).get(j)).add(list.get(i).get(0));
                } else {
                    if(!tmp.get(list.get(i).get(j)).contains(list.get(i).get(0))){
                        tmp.get(list.get(i).get(j)).add(list.get(i).get(0));
                    }
                }
            }
        }

        return tmp;
    }

    public Map<String, ArrayList<String>> getLangsMapSortedByNumOfProgs(){
//        LinkedHashMap<String, ArrayList<String>> tmp = new LinkedHashMap<>();
//
//        List<Map.Entry<String, ArrayList<String>>> list = new ArrayList<>(this.getLangsMap().entrySet());
//        list.sort((a, b) -> {
//            int compare = Integer.compare(b.getValue().size(), a.getValue().size());
//            return compare == 0 ? b.getKey().compareTo(a.getKey()) : compare;
//        });
//
//        for(Map.Entry<String, ArrayList<String>> el : list){
//            tmp.put(el.getKey(), el.getValue());
//        }

        Map<String, ArrayList<String>> tmp = sorted(this.getLangsMap(), (a, b)-> {
            int compare = Integer.compare(b.getValue().size(), a.getValue().size());
            return compare==0 ? a.getKey().compareTo(b.getKey()) : compare;
        });

        return tmp;
    }

    public Map<String, ArrayList<String>> getProgsMapSortedByNumOfLangs(){
//        LinkedHashMap<String, ArrayList<String>> tmp = new LinkedHashMap<>();
//
//        List<Map.Entry<String, ArrayList<String>>> lista = new ArrayList<>(this.getProgsMap().entrySet());
//        lista.sort((a, b) -> {
//            int compare = Integer.compare(b.getValue().size(), a.getValue().size());
//            return compare == 0 ? a.getKey().compareTo(b.getKey()) : compare;
//        });
//
//        for(Map.Entry<String, ArrayList<String>> el : lista){
//            tmp.put(el.getKey(), el.getValue());
//        }

        Map<String, ArrayList<String>> tmp = sorted(this.getProgsMap(), (a, b) -> {
            int compare = Integer.compare(b.getValue().size(), a.getValue().size());
            return compare==0 ? a.getKey().compareTo(b.getKey()) : compare;
        });

        return tmp;
    }

    public Map<String, ArrayList<String>> getProgsMapForNumOfLangsGreaterThan(int n){
//        LinkedHashMap<String, ArrayList<String>> tmp = new LinkedHashMap<>();
//        this.getProgsMap().forEach((str, lista) -> {
//            if(lista.size()>n){
//                tmp.put(str, lista);
//            }
//        });

        Map<String, ArrayList<String>> tmp = filtered(this.getProgsMap(), (a) -> {
            return a.getValue().size() > n;
        });

        return tmp;
    }

    public <K, V> Map<K, V> sorted(Map<K, V> map, Comparator<Map.Entry<K, V>> fun){

        return map.entrySet().stream().sorted(fun).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a, b) -> a,
                LinkedHashMap::new
                ));
    }

    public <K, V> Map<K, V> filtered(Map<K, V> map, Predicate<Map.Entry<K, V>> pred){

        return map.entrySet().stream().filter(pred).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a, b) -> a,
                LinkedHashMap::new
        ));
    }
}
package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList <G> extends ArrayList<G>{

    public XList(G... g){     // dla wielu argumentow oraz dla tablicy
        for(G el : g){
            this.add(el);
        }
    }

    public XList(Collection<G> collection){   // dla kolekcji
        this.addAll(collection);
    }


    public static<G> XList of(G... g){  // dla wielu argumentow oraz dla tablicy
        return new XList(g);
    }

    public static <G> XList of(Collection<G> collection){   // dla kolekcji
        return new XList(collection);
    }



    public static<G> XList charsOf(String str){
        ArrayList<Character> tmp = new ArrayList();
        for(char el : str.toCharArray()){
            tmp.add(el);
        }

        return new XList(tmp);
    }

    public static<G> XList tokensOf(String str, String sep){
        return new XList<>(Arrays.asList(str.split(sep)));
    }

    public static<G> XList tokensOf(String str){
        return new XList<>(Arrays.asList(str.split("\\s+")));
    }


    public XList union(Collection<G> collection){
        List<G> tmp = new ArrayList<>(this);
        for(G el : collection){
            tmp.add(el);
        }
        return new XList(tmp);
    }

    public XList union(Object[] tabl){
        List<G> tmp = new ArrayList<>(this);
        for(Object el : tabl){
            tmp.add((G)el);
        }
        return new XList(tmp);
    }


    public XList diff(Collection<G> collection){
        ArrayList<G> tmp = new ArrayList<>();

        for(int i = 0; i<this.size(); ++i){
            if(!collection.contains(this.get(i))){
                tmp.add(this.get(i));
            }
        }

        return new XList(tmp);
    }

    public XList unique(){
        return new XList<>(this.stream().distinct().collect(Collectors.toList()));
    }

    public XList<List<G>> combine(){
        // {{"a", "X", "1"}, {"b", "X", "1"}, {"a", "Y", "1"}, {"b", "Y", "1"}, {"a", "Z", "1"}, {"b", "Z", "1"}, {"a", "X", "2"}, {"b", "X", "2"}, {"a", "Y", "2"}, {"b", "Y", "2"}, {"a", "Z", "2"}, {"b", "Z", "2"}};
        XList<List<G>> tmp = new XList<>();
        if(this.isEmpty()){
            return tmp;
        }
        List<List<G>> lists = this.stream()
                .map(el -> (List<G>) el)
                .collect(Collectors.toList());
        collect(lists, new XList<>(), lists.size()-1, tmp);
        return new XList(tmp);
    }


    public void collect(List<List<G>> lista, List<G> current, int k, XList<List<G>> result){
        if (k < 0) {
            result.add(new XList<>(current));
            return;
        }
        for (G element : lista.get(k)) {
            current.add(0,element);
            collect(lista,  current,k - 1, result);
            current.remove(0);
        }
    }

    public <U>XList<U> collect(Function<G, U> fun){
        ArrayList<U> tmp = new ArrayList<>();
        for(G el : this){
            tmp.add(fun.apply(el));
        }
        return new XList<>(tmp);
    }

    public String join(String sep){
        return this.stream().map(Object::toString).collect(Collectors.joining(sep));
    }
    public String join(){
        return this.stream().map(Object::toString).collect(Collectors.joining(""));
    }

    public<U> void forEachWithIndex(BiConsumer<G, Integer> consumer ){
        for (int i = 0; i < this.size(); i++) {
            consumer.accept(this.get(i), i);
        }
    }
}
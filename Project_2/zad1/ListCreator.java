package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T, S extends Predicate<T>, K extends Function<T, T>> {
    List<T> lista;
    public ListCreator(List<T> lista){
        this.lista = lista;
    }

    static <T, S extends Predicate<T>, K extends Function<T, T>>ListCreator<T, S, K> collectFrom(List<T> list){
        return new ListCreator<T, S, K>(list);
    }

    public ListCreator<T, S, K> when(S s){
        List<T> tmp = new ArrayList<T>();
        for(T t : lista){
            if(s.test(t)){
                tmp.add(t);
            }
        }
        lista = tmp;
        return this;
    }

    public List<T> mapEvery(K k){
        List<T> tmp = new ArrayList<T>();
        for(T t : lista){
            tmp.add(k.apply(t));
        }
        return tmp;
    }
}

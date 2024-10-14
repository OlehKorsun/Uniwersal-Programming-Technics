/**
 *
 *  @author Korsun Oleh S30911
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.List;

public class ListCreator<S, T> { // Uwaga: klasa musi być sparametrtyzowana
    private List<S> list;
    public ListCreator(List<S> list){
        this.list = list;
    }
    static <S, T> ListCreator<S, T> collectFrom(List<S> list){
        return new ListCreator<S, T>(list);
    }

    ListCreator<S, T> when(Selector<S> selector){
        List<S> listTmp = new ArrayList<S>();
        for(int i = 0; i<list.size(); i++){
            S a = list.get(i);
            if(selector.select(a)){
                listTmp.add(a);
            }
        }
        this.list=listTmp;
        return this;
    }

    List<T> mapEvery(Mapper<S, T> mapper){
        List<T> list2 = new ArrayList<T>();
        for(S s : list){
            list2.add(mapper.map(s));
        }
        return list2;
    }
}  

package zad2;

import java.util.function.Function;

public class InputConverter<S> {
    S s;
    public InputConverter(S s){
        this.s = s;
    }
    public <T> T convertBy(Function... f){
        Object t = s;
        for(Function fun : f){
            t = fun.apply(t);
        }
        return (T)t;
    }
}
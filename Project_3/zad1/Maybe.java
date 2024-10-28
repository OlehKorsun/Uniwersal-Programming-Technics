package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class Maybe<T> {
    T t;
    private Maybe(T t){
        this.t = t;
    }
    private Maybe(){
        this.t=null;
    }

    public static <T> Maybe<T> of(T t){
        return new Maybe<>(t);
    }

    public void ifPresent(Consumer<T> cons){
        if(t != null){
            cons.accept(t);
        }
    }

    public <S>Maybe<S> map(Function<T, S> func){
        if(!isPresent())
            return new Maybe<>();
        else{
            return new Maybe<>(func.apply(t));
        }
    }

    public T get(){
        if(t==null)
            throw new NoSuchElementException("maybe is empty");
        else
             return t;
    }

    public boolean isPresent(){
        return t!=null;
    }

    public T orElse(T defVal){
        if(t==null)
            return defVal;
        else
            return t;
    }

    public Maybe<T> filter(Predicate<T> pred){
        if(t==null)
            return this;
        else if(!pred.test(t))
            return new Maybe<>();
        else
            return this;
    }

    public String toString(){
        if(t!=null){
            return "Maybe has value " + t;
        }
        else {
            return "Maybe is empty";
        }
    }
}

/**
 *
 *  @author Korsun Oleh
 *
 */

package zad1;


public interface Mapper<S, T> { // Uwaga: interfejs musi być sparametrtyzowany
    public T map(S s);
}  

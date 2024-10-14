/**
 *
 *  @author Korsun Oleh S30911
 *
 */

package zad1;


public interface Mapper<S, T> { // Uwaga: interfejs musi być sparametrtyzowany
    public T map(S s);
}  

/**
 *
 *  @author Korsun Oleh S30911
 *
 */

package zad2;

import java.util.Arrays;/*<-- niezbędne importy */
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream()
            .filter( s -> s.split(" ")[0].equals("WAW"))
            .map( s -> "to " + s.split(" ")[1] + " - price in PLN: " + Integer.parseInt(s.split(" ")[2])*ratePLNvsEUR)
            .collect(Collectors.toList());
    /*<-- tu należy dopisać fragment
     * przy czym nie wolno używać żadnych własnych klas, jak np. ListCreator
     * ani też żadnych własnych interfejsów
     * Podpowiedź: należy użyć strumieni
     */

    for (String r : result) System.out.println(r);
  }
}

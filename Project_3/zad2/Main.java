package zad2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {

    Function<String, List<String>> flines = s -> {
        try {
           return Files.readAllLines(Paths.get(s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    Function<List<String>, String> join = s -> {
      return String.join(" ", s);
    };

    Function<String, List<Integer>> collectInts = s -> {

      Pattern pattern = Pattern.compile("\\d+");
      Matcher matcher = pattern.matcher(s);

      List<Integer> integers = new ArrayList<>();

      while(matcher.find()){
        integers.add(Integer.parseInt(matcher.group()));
      }
      return integers;

    };

    Function<List<Integer>, Integer> sum = i -> {
      int tmp = 0;
      for(int a : i)
        tmp+=a;
      return tmp;
    };

    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}

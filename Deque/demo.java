import java.util.HashMap;
import java.util.Map;

import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

public class demo {
  public static void main(String[] args) {

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    for (int i = 0; i < 10 ; i++) {
      map.put(i, i + 1);
    }

    for (Integer k: map.keySet()) {
       System.out.println(k);
    }
    
  }

 

}
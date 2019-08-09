package utils.stringparsing;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class ServerParsing {
  public static Pair<String, String> parsePropertyWithDescription(String stringToParse, String seperator) {
    String[] parsedString = stringToParse.split(seperator, 2);
    if (parsedString.length != 2) {
      throw new IllegalArgumentException(stringToParse + " is declared incorrectly. It requires a " + seperator + ".");
    }
    String serverUrl = parsedString[0].replaceAll("\\s+", "");
    String serverDescription = parsedString[1].trim();

    Pair<String, String> pair = new ImmutablePair<>(serverUrl, serverDescription);
    return pair;
  }
}
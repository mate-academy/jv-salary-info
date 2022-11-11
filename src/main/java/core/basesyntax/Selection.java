package core.basesyntax;

import static java.lang.Integer.parseInt;

public class Selection {

    public final int selection(String[] arrayInfo, String[] nameArray, int counter) {
        int generalNumber = 0;
        for (String s : arrayInfo) {
            if (s.contains(nameArray[counter])) {

                generalNumber += parseInt(s.replaceAll("[^0-9]", ""));
            }
        }
        return generalNumber;
    }
}

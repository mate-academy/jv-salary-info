package core.basesyntax.services;

import java.util.Arrays;

public class ArrayService {
    private final String[] dataString;

    public ArrayService(String[] dataString) {
        this.dataString = dataString;
    }

    public int indexByValue(String value) {
        return Arrays.asList(dataString).indexOf(value);
    }
}

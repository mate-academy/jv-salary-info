package core.basesyntax.suppliers;

public class NameSupplier {
    private static final int NAME_INDEX = 1;

    public String getName(String data) {
        return data.split(" ")[NAME_INDEX];
    }
}

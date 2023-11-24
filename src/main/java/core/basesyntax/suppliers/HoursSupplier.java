package core.basesyntax.suppliers;

public class HoursSupplier {
    private static final int WORKING_HOURS_INDEX = 2;

    public int getWorkingHours(String data) {
        return Integer.parseInt(data.split(" ")[WORKING_HOURS_INDEX]);
    }
}

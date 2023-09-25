package core.basesyntax;

public class CheckNotNull {
    public void checkNotNull(String[] names, String[] data, String dateFrom, String dateTo) {
        if ((names == null || data == null
                || dateFrom == null || dateTo == null)
                || names.length == 0 || data.length == 0
                || dateFrom.isEmpty() || dateTo.isEmpty()) {
            throw new EmptyStringException("Empty String Not Allowed");
        }
    }
}

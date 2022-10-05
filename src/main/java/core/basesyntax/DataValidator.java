package core.basesyntax;

public class DataValidator {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void validate(String[] names, String[] data, String dateFrom, String dateTo) {
        if (dateFrom == null || dateTo == null) {
            throw new DataValidationException("Input data is incorrect: "
            + LINE_SEPARATOR + "Date From and Date To cannot be null");
        }
        if (names.length == 0 || data.length == 0) {
            throw new DataValidationException("Input data is incorrect: "
                    + LINE_SEPARATOR + "Names and Data arrays cannot be empty");
        }
        for (String name : names) {
            if (name == null || name.length() == 0) {
                throw new DataValidationException("Input data is incorrect: "
                        + LINE_SEPARATOR + "Elements in array Names cannot be null or empty");
            }
        }
        for (String datum : data) {
            if (datum == null || datum.length() == 0) {
                throw new DataValidationException("Input data is incorrect: "
                        + LINE_SEPARATOR + "Elements in array Data cannot be null or empty");
            }
        }
    }
}

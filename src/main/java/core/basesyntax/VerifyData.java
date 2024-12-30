package core.basesyntax;

public class VerifyData {
    public void verification(String date) {
        FormatDataException exception = new FormatDataException("format of data is wrong");

        String[] parts = date.split(" ");

        if (parts.length != 4) {
            throw exception;
        }

        if (!SimpleDate.isValidDate(parts[0])) {
            throw exception;
        }

        if (!parts[2].matches("\\d+") || !parts[3].matches("\\d+")) {
            throw exception;
        }
    }
}

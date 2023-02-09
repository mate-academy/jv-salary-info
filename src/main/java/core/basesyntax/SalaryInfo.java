package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salariesInfo = new int[names.length];
        StringBuilder result = new StringBuilder()
                .append("Report for period " + dateFrom + " - " + dateTo + System.lineSeparator());

        for (int i = 0; i < data.length; i++) {
            String[] currentRow = data[i].split("\\s+");
            if (isDateInRange(currentRow[0], dateFrom, dateTo)) {
                int salaryIndex = getIndex(currentRow[1], names);
                salariesInfo[salaryIndex] += Integer.valueOf(currentRow[2])
                        * Integer.valueOf(currentRow[3]);
            }
        }

        for (int i = 0; i < names.length; i++) {
            result.append(names[i] + " - " + salariesInfo[i] + System.lineSeparator());
        }

        return result.toString().trim();
    }

    private boolean isDateInRange(String dateToCheckString, String dateFromString,
                                  String dateToString) {
        LocalDate dateFrom = null;
        LocalDate dateTo = null;
        LocalDate dateToCheck = null;

        try {
            dateFrom = LocalDate.parse(dateFromString, FORMATTER);
            dateTo = LocalDate.parse(dateToString, FORMATTER);
            dateToCheck = LocalDate.parse(dateToCheckString, FORMATTER);
        } catch (DateTimeParseException exc) {
            System.out.println("Error while parsing date");
        }

        return dateFrom == null || dateTo == null || dateToCheck == null ? false :
                dateToCheck.isAfter(dateFrom.minusDays(1))
                        && dateToCheck.isBefore(dateTo.plusDays(1));
    }

    private int getIndex(String element, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (element.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }
}

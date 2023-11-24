package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_COLUMN = 0;
    public static final int NAME_COLUMN = 1;
    public static final int HOURS_COLUMN = 2;
    public static final int SALARY_COLUMN = 3;
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private LocalDate dateFromParsed;
    private LocalDate dateToParsed;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        dateFromParsed = LocalDate.parse(dateFrom, FORMATTER);
        dateToParsed = LocalDate.parse(dateTo, FORMATTER);
        int[] salariesInfo = new int[names.length];
        StringBuilder result = new StringBuilder()
                .append("Report for period " + dateFrom + " - " + dateTo + System.lineSeparator());
        for (int i = 0; i < data.length; i++) {
            String[] currentRow = data[i].split("\\s+");
            if (isDateInRange(currentRow[DATE_COLUMN])) {
                int salaryIndex = getIndex(currentRow[NAME_COLUMN], names);
                salariesInfo[salaryIndex] += Integer.valueOf(currentRow[HOURS_COLUMN])
                        * Integer.valueOf(currentRow[SALARY_COLUMN]);
            }
        }
        for (int i = 0; i < names.length; i++) {
            result.append(names[i] + " - " + salariesInfo[i] + System.lineSeparator());
        }
        return result.toString().trim();
    }

    private boolean isDateInRange(String dateToCheckString) {
        LocalDate dateToCheck = LocalDate.parse(dateToCheckString, FORMATTER);
        return dateToCheck.isAfter(dateFromParsed.minusDays(1))
                        && dateToCheck.isBefore(dateToParsed.plusDays(1));
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

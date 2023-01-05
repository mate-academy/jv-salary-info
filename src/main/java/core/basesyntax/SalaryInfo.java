package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String DASH = " - ";
    public static final String REGEX = " ";
    public static final String INTRO = "Report for period ";
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOURS_INDEX = 2;
    public static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate dateLast = LocalDate.parse(dateTo, FORMATTER);
        final LocalDate dateFirst = LocalDate.parse(dateFrom, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();
        int[] income = new int[names.length];
        String[] dataRow;
        stringBuilder.append(INTRO + dateFrom + DASH + dateTo + System.lineSeparator());
        for (int j = 0; j < names.length; j++) {
        int salary = 0;
            for (int i = 0; i < data.length; i++) {
                dataRow = data[i].split(REGEX);
                if ((dataRow[NAME_INDEX].equals(names[j]))
                        && (LocalDate.parse(dataRow[DATE_INDEX], FORMATTER).isAfter(dateFirst)
                        || LocalDate.parse(dataRow[DATE_INDEX], FORMATTER).isEqual(dateFirst))
                        && (LocalDate.parse(dataRow[DATE_INDEX], FORMATTER).isBefore(dateLast)
                        || LocalDate.parse(dataRow[DATE_INDEX], FORMATTER).isEqual(dateLast))) {
                    income[j] += Integer.parseInt(dataRow[HOURS_INDEX])
                        * Integer.parseInt(dataRow[INCOME_INDEX]);
                }
            }
            stringBuilder.append(names[j] + DASH + income[j]);
            if (j == names.length - 1) {
                break;
            }
            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
}

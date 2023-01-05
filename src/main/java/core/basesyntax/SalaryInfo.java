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
        String[] dataRow;
        stringBuilder.append(INTRO).append(dateFrom).append(DASH).append(dateTo);
        for (int j = 0; j < names.length; j++) {
            stringBuilder.append(System.lineSeparator());
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                dataRow = data[i].split(REGEX);
                LocalDate dateData = LocalDate.parse(dataRow[DATE_INDEX], FORMATTER);
                if (((dataRow[NAME_INDEX].equals(names[j]))
                        && (dateData.isAfter(dateFirst)
                        || dateData.isEqual(dateFirst))
                        && (dateData.isBefore(dateLast)
                        || dateData.isEqual(dateLast)))) {
                    salary += Integer.parseInt(dataRow[HOURS_INDEX])
                        * Integer.parseInt(dataRow[INCOME_INDEX]);
                }
            }
            stringBuilder.append(names[j]).append(DASH).append(salary);
        }
        return stringBuilder.toString();
    }
}

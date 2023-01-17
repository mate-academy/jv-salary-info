package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String STRING_FROM_TITLE_LINE = "Report for period";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = stringBuilder.append(STRING_FROM_TITLE_LINE)
                .append(" " + dateFrom + " - " + dateTo);

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            stringBuilder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ");
            for (int j = 0; j < data.length; j++) {
                String[] valuesFromData = data[j].split(" ");
                LocalDate dateToCheck = LocalDate.parse(valuesFromData[DATE_INDEX], DATE_FORMATTER);
                LocalDate dateStart = LocalDate.parse(dateFrom, DATE_FORMATTER);
                LocalDate dateEnd = LocalDate.parse(dateTo, DATE_FORMATTER);

                if (dateToCheck.isAfter(dateStart)
                        && (dateToCheck.isBefore(dateEnd) || dateToCheck.isEqual(dateEnd))
                        && valuesFromData[NAME_INDEX].equals(names[i])) {
                    salary += Integer.parseInt(valuesFromData[WORKING_HOUR_INDEX])
                            * Integer.parseInt(valuesFromData[INCOME_INDEX]);
                }
            }
            stringBuilder.append(salary);
        }
        return stringBuilder.toString();
    }
}

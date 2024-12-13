package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int INCOME = 3;
    private static final int DAY = 1;
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromDateFormat = changeDateFormat(dateFrom);
        LocalDate dateToDateFormat = changeDateFormat(dateTo);
        int[] salary = new int[names.length];

        for (String dataRow : data) {
            String[] dataInfo = dataRow.split(" ");
            if (changeDateFormat(dataInfo[DATE]).isAfter(dateFromDateFormat.minusDays(DAY))
                    && changeDateFormat(dataInfo[DATE]).isBefore(dateToDateFormat.plusDays(DAY))) {
                for (int i = 0; i < names.length; i++) {
                    if (Objects.equals(names[i], dataInfo[NAME])) {
                        salary[i] += Integer.parseInt(dataInfo[HOURS])
                                * Integer.parseInt(dataInfo[INCOME]);
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo + System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            if (i != names.length - 1) {
                stringBuilder.append(names[i]).append(" - ").append(salary[i])
                        .append(System.lineSeparator());
            } else {
                stringBuilder.append(names[i]).append(" - ").append(salary[i]);
            }
        }

        return stringBuilder.toString();
    }

    public LocalDate changeDateFormat(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(stringDate, formatter);
    }
}

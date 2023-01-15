package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATA = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_TIME_WORK = 2;
    private static final int INDEX_OF_SALARY = 3;
    private int salary = 0;

    public String getSalaryInfo(String[]names, String[]data, String dateFrom, String dateTo) {
        LocalDate dateFromFormat = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFormat = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            for (int k = 0; k < data.length; k++) {
                String[] workingDay = data[k].split(" ");
                LocalDate newData = LocalDate.parse(workingDay[INDEX_OF_DATA], FORMATTER);
                String name = workingDay[INDEX_OF_NAME];

                if ((newData.isAfter(dateFromFormat) || newData.isEqual(dateFromFormat))
                        && (newData.isBefore(dateToFormat) || newData.isEqual(dateToFormat))
                        && name.equals(names[i])) {
                    salary += Integer.parseInt(workingDay[INDEX_OF_TIME_WORK])
                            * Integer.parseInt(workingDay[INDEX_OF_SALARY]);
                }
            }
            builder.append(names[i]).append(" - ").append(salary);
            if (i != names.length - 1) {
                builder.append(System.lineSeparator());
            }
            salary = 0;
        }
        return builder.toString();
    }
}

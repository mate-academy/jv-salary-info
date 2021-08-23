package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int RATE = 3;
    private static final int HOURS = 2;
    private static final int NAME = 1;
    private static final int DATE = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0, salary = 0; i < names.length; i++, salary = 0) {
            for (int dataElement = 0; dataElement < data.length; dataElement++) {
                String[] dataArray = data[dataElement].split(" ");
                if (names[i].equals(dataArray[NAME])
                        && (LocalDate.parse(dataArray[DATE], DATE_FORMAT).
                        isAfter(LocalDate.parse(dateFrom, DATE_FORMAT))
                        || LocalDate.parse(dataArray[DATE], DATE_FORMAT)
                        .isEqual(LocalDate.parse(dateFrom, DATE_FORMAT)))
                        && (LocalDate.parse(dataArray[DATE], DATE_FORMAT).
                        isBefore(LocalDate.parse(dateTo, DATE_FORMAT))
                        || LocalDate.parse(dataArray[DATE], DATE_FORMAT)
                        .isEqual(LocalDate.parse(dateTo, DATE_FORMAT)))) {
                    salary += Integer.parseInt(dataArray[HOURS])
                            * Integer.parseInt(dataArray[RATE]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}

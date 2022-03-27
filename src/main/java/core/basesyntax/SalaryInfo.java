package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int START_POINT = 0;
    private static final int END_POINT = 10;
    private static final int START_POINT_CHECKING = 11;
    private static final int PLUS_TO_INDEX = 1;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        fromDate = fromDate.minusDays(1);
        toDate = toDate.plusDays(1);
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        int salary = 0;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                int index = data[j].indexOf(" ", START_POINT_CHECKING);
                int index2 = data[j].indexOf(" ", index + 1);
                LocalDate check = LocalDate.parse(data[j].substring(START_POINT, END_POINT),
                        formatter);
                if (names[i].equals(data[j].substring(START_POINT_CHECKING, index))
                        && check.isAfter(fromDate) && check.isBefore(toDate)) {
                    salary += Integer.parseInt(data[j].substring(index + PLUS_TO_INDEX, index2))
                            * Integer.parseInt(data[j].substring(index2 + PLUS_TO_INDEX));
                }
            }
            sb.append(names[i])
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());
            salary *= 0;
        }
        return sb.toString().trim();
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator());
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate beforeDate = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int count = 0;
            for (String info : data) {
                String[] container = info.split(" ");
                LocalDate dateOfWork = LocalDate.parse(container[0], FORMATTER);
                if (container[1].equals(name) && dateOfWork.isAfter(fromDate)
                        && dateOfWork.isBefore(beforeDate.plusDays(1))) {
                    count += Integer.valueOf(container[2]) * Integer.valueOf(container[3]);
                }
            }
            if (names[names.length - 1].equals(name) && names.length > 1) {
                result.append(name + " - " + count);
                return result.toString();
            } else if (names.length == 1) {
                result.append(name + " - " + count);
                return result.toString();
            }
            result.append(name + " - " + count + System.lineSeparator());
        }
        return result.toString();
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int DATEINDEX = 0;
    static final int NAMEINDEX = 1;
    static final int HOURSPOSITION = 2;
    static final int SALARYPOSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int salarySum = 0;
            for (String datum : data) {
                String[] splittedDate = datum.split(" ");
                if (isValidDate(name, dateFrom, splittedDate,
                        DATEINDEX, dateTo, FORMATTER)) {
                    int hours = Integer.parseInt(splittedDate[HOURSPOSITION]);
                    int salary = Integer.parseInt(splittedDate[SALARYPOSITION]);
                    salarySum += (hours * salary);
                }
            }
            stringBuilder.append(name).append(" - ").append(salarySum)
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }

    public boolean isValidDate(String name, String dateFrom, String[] splittedDate,
                               int DATEINDEX, String dateTo, DateTimeFormatter FORMATTER) {
        if (name.equals(splittedDate[NAMEINDEX])
                && !LocalDate.parse(splittedDate[DATEINDEX], FORMATTER)
                .isBefore(LocalDate.parse(dateFrom, FORMATTER))
                && !LocalDate.parse(splittedDate[DATEINDEX], FORMATTER)
                .isAfter(LocalDate.parse(dateTo, FORMATTER))) {
            return true;
        } else {
            return false;
        }
    }
}

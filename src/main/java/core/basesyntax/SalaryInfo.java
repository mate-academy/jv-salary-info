package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOURSPOSITION = 2;
    static final int SALARYPOSITION = 3;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int salarySum = 0;
            for (String datum : data) {
                String[] splittedDate = datum.split(" ");
                if (isValidDate(name, dateFrom, splittedDate,
                        DATE_INDEX, dateTo, formatter)) {
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
                               int dateindex, String dateTo, DateTimeFormatter formatter) {
        if (name.equals(splittedDate[NAME_INDEX])
                && !LocalDate.parse(splittedDate[dateindex], formatter)
                .isBefore(LocalDate.parse(dateFrom, formatter))
                && !LocalDate.parse(splittedDate[dateindex], formatter)
                .isAfter(LocalDate.parse(dateTo, formatter))) {
            return true;
        } else {
            return false;
        }
    }
}

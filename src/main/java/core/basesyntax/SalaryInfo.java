package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String namesEmployees : names) {
            int salary = 0;
            salaryInfo.append(System.lineSeparator()).append(namesEmployees).append(" - ");
            for (int d = 0; d < data.length; d++) {
                String[] period = data[d].split(" ");
                LocalDate dateNow = LocalDate.parse(period[0], FORMATTER);
                if (namesEmployees.equals(period[1]) && dateNow.isAfter(dateFromDate)
                        && dateNow.isBefore(dateToDate.plusDays(1))) {
                    salary = salary + (Integer.parseInt(period[2]) * Integer.parseInt(period[3]));
                }
            }
            salaryInfo.append(salary);
        }
        return salaryInfo.toString();
    }
}

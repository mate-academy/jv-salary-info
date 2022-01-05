package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToDate = LocalDate.parse(dateTo, formatter);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String namesEmployees : names) {
            int salary = 0;
            salaryInfo.append(System.lineSeparator()).append(namesEmployees).append(" - ");
            for (int d = 0; d < data.length; d++) {
                String[] period = data[d].split(" ");
                LocalDate dateNow = LocalDate.parse(period[0], formatter);
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

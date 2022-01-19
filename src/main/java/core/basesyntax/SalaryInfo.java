package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromD = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate dateToD = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String namesS : names) {
            int salary = 0;
            result.append(System.lineSeparator()).append(namesS).append(" - ");
            for (int d = 0; d < data.length; d++) {
                String[] datePeriod = data[d].split(" ");
                LocalDate dateNow = LocalDate.parse(datePeriod[0], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                if (namesS.equals(datePeriod[1]) && dateNow.isAfter(dateFromD)
                        && dateNow.isBefore(dateToD.plusDays(1))) {
                    salary = salary + (Integer.parseInt(datePeriod[2]) * Integer.parseInt(datePeriod[3]));
                }
            }
            result.append(salary);
        }
        return result.toString();
    }
}
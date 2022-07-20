package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] arrayLine = line.split(" ");
                LocalDate workday = getLocalDate(arrayLine[0]);
                if ((getLocalDate(dateFrom).isBefore(workday)
                        || getLocalDate(dateFrom).isEqual(workday))
                        && (getLocalDate(dateTo).isAfter(workday)
                        || getLocalDate(dateTo).isEqual(workday))) {
                    if (name.equals(arrayLine[1])) {
                        salary = salary + parseInt(arrayLine[3])
                                * parseInt(arrayLine[2]);
                    }
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }

    public LocalDate getLocalDate(String dateString) {
        LocalDate localDate = LocalDate
                .parse(dateString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return localDate;
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATA = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary;
        StringBuilder salaryInfo = new StringBuilder("Report for period ");
        salaryInfo.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATA);
        LocalDate datefrom = LocalDate.parse(dateFrom, formatter);
        LocalDate dateto = LocalDate.parse(dateTo, formatter);
        for (String name:names) {
            salary = 0;
            for (String fullData : data) {
                String[] splitByWhiteSpace = fullData.split(" ");
                LocalDate currentDate = LocalDate.parse(splitByWhiteSpace[0], formatter);
                if ((name.equals(splitByWhiteSpace[1]))
                        && ((currentDate.isAfter(datefrom)
                        && currentDate.isBefore(dateto))
                        || (currentDate.isEqual(datefrom))
                        || (currentDate.isEqual(dateto)))) {
                    salary = salary + Integer.parseInt(splitByWhiteSpace[2])
                                * Integer.parseInt(splitByWhiteSpace[3]);
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return salaryInfo.toString();
    }
}

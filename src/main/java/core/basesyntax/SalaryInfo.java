package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo =
                new StringBuilder("Report for period " + dateFrom + " - "
                        + dateTo + System.lineSeparator());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateF = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateT = LocalDate.parse(dateTo, dateTimeFormatter);
        for (String name : names) {
            int sumOfSalary = 0;
            for (String datum : data) {
                String[] parseDatum = datum.split(" ");
                int salary = 0;
                for (String datum1 : data) {
                    String[] parseDatum1 = datum1.split(" ");
                    LocalDate tempDate = LocalDate.parse(parseDatum[0], dateTimeFormatter);
                    LocalDate tempDate1 = LocalDate.parse(parseDatum1[0], dateTimeFormatter);
                    if ((tempDate.isAfter(dateF) && tempDate.isBefore(dateT)
                            || tempDate.isEqual(dateF) || tempDate.isEqual(dateT))
                            && tempDate.isEqual(tempDate1)) {
                        if (name.equals(parseDatum[1])) {
                            salary += name.equals(parseDatum1[1])
                                    ? Integer.parseInt(parseDatum1[2])
                                    * Integer.parseInt(parseDatum1[3])
                                    : 0;
                        }
                    }
                }
                sumOfSalary += salary;
            }
            salaryInfo.append(name).append(" - ").append(sumOfSalary)
                    .append(name.equals(names[names.length - 1]) ? "" : System.lineSeparator());
        }
        return salaryInfo.toString();
    }
}

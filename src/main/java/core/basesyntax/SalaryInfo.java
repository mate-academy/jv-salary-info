package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary = 0;
        String[] info;
        StringBuilder employeeSalary = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name: names) {
            for (String dataPerDay: data) {
                info = dataPerDay.split(" ");

                if (name.equals(info[1])
                        && dateConvert(info[0]).compareTo(dateConvert(dateFrom)) >= 0
                        && dateConvert(info[0]).compareTo(dateConvert(dateTo)) <= 0) {
                    salary += Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
                }
            }
            employeeSalary.append(System.lineSeparator())
                    .append(name).append(" - ").append(salary);
            salary = 0;
        }

        return employeeSalary.toString();
    }

    private LocalDate dateConvert(String dateString) {
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println(dateString + " is not parsable!");
            throw e;
        }
    }
}

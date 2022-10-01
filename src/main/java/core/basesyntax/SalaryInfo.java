package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startWorkDate = LocalDate.parse(dateFrom, FORMATTER);
        // converted from string dateFrom date to Date format
        LocalDate finishWorkDate = LocalDate.parse(dateTo, FORMATTER);
        // converted from string dateTo date to Date format
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                    + dateFrom + " - "
                    + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String allData : data) {
                String[] dataInArray = allData.split(" ");
                LocalDate certainDay = LocalDate.parse(dataInArray[0], FORMATTER);
                // converted from string date in data to Date
                int salaryPerDay = Integer.parseInt(dataInArray[2])
                            * Integer.parseInt(dataInArray[3]);

                // product of hours and pay
                if (startWorkDate.getDayOfYear() < certainDay.getDayOfYear()
                        && certainDay.getDayOfYear() <= finishWorkDate.getDayOfYear()) {
                    if (dataInArray[1].equals(name)) {
                        salary += salaryPerDay;
                    }
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salary);
        }
        return salaryInfo.toString();
    }
}

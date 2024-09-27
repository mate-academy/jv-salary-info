package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        int userValues = 4;

        String[] arrayOfData = new String[userValues];
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                arrayOfData = line.split(" ");
                LocalDate currentDate = LocalDate.parse(arrayOfData[0], FORMATTER);
                if ((name.equals(arrayOfData[1])) && (currentDate.isAfter(localDateFrom)
                        || currentDate.isEqual(localDateFrom))
                        && (currentDate.isEqual(localDateTo)
                        || currentDate.isBefore(localDateTo))) {
                    salary += (Integer.parseInt(arrayOfData[3]) * Integer.parseInt(arrayOfData[2]));
                }
            }
            report.append('\n').append(name).append(" - ").append(salary);
        }

        return report.toString();
    }
}

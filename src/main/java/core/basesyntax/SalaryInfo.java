package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder report = new StringBuilder();
        report.append("Report for period " + localDateFrom.format(FORMATTER) + " - "
                + localDateTo.format(FORMATTER));

        for (String name : names) {
            report.append(System.lineSeparator());
            int salary = 0;
            for (String element : data) {
                String[] parsedData = element.split(" ");

                LocalDate workingDate = LocalDate.parse(parsedData[0], FORMATTER);
                int workingHours = Integer.valueOf(parsedData[2]);
                int ratePerHour = Integer.valueOf(parsedData[3]);

                if (parsedData[1].equals(name) && (
                        (workingDate.isAfter(localDateFrom) && workingDate.isBefore(localDateTo))
                        || workingDate.isEqual(localDateFrom)
                        || workingDate.isEqual(localDateTo))) {
                    salary += workingHours * ratePerHour;
                }
            }
            report.append(name + " - " + salary);
        }
        return report.toString();
    }
}

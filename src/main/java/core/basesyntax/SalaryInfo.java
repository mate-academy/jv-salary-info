package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder("Report for period ");
        String[] personalInfo;
        LocalDate personalDate;
        int personalSalary = 0;

        if (startDate.isAfter(endDate)) {
            startDate = endDate;
            endDate = LocalDate.parse(dateFrom, formatter);
            result.append(dateTo)
                    .append(" - ")
                    .append(dateFrom);
        } else {
            result.append(dateFrom)
                    .append(" - ")
                    .append(dateTo);
        }
        for (String name : names) {
            for (String datum : data) {
                personalInfo = datum.split("\\s");
                if (!name.equals(personalInfo[1])) {
                    continue;
                }
                personalDate = LocalDate.parse(personalInfo[0], formatter);
                if (!personalDate.isBefore(startDate)
                        && !personalDate.isAfter(endDate)) {
                    personalSalary += Integer.parseInt(personalInfo[2])
                            * Integer.parseInt(personalInfo[3]);
                }
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(personalSalary);
            personalSalary = 0;
        }
        return result.toString();
    }
}

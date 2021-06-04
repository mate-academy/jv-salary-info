package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int WHICH_DATE = 0;
    private static final int HOURS = 2;
    private static final int SALARY_PER_HOUR = 3;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        LocalDate datFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate datTo = LocalDate.parse(dateTo, formatter);
        result.append("Report for period ")
                .append(formatter.format(datFrom))
                .append(" - ")
                .append(formatter.format(datTo));
        for (String name : names) {
            int salaryCounter = 0;
            for (String date : data) {
                if (date.contains(name)) {
                    String[] arrayOfDate = date.split(" ");
                    LocalDate datIs = LocalDate.parse(arrayOfDate[WHICH_DATE], formatter);
                    if ((datIs.isEqual(datFrom) || datIs.isAfter(datFrom))
                            && (datIs.isEqual(datTo) || datIs.isBefore(datTo))) {
                        salaryCounter += (Integer.parseInt(arrayOfDate[HOURS])
                                * Integer.parseInt(arrayOfDate[SALARY_PER_HOUR]));
                    }
                }
            }
            result.append("\n")
                    .append(name)
                    .append(" - ")
                    .append(salaryCounter);
        }
        return result.toString();
    }
}

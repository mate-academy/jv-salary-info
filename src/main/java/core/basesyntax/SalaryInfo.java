package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int TIME_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        int sumSalary = 0;

        for (String name: names) {
            for (String dataElements: data) {
                String[] words = dataElements.split(" ");
                LocalDate workDate = LocalDate.parse(words[DATE_INDEX], formatter);
                if ((words[NAME_INDEX].equals(name)) && (workDate.isAfter(localDateFrom)
                        || workDate.isEqual(localDateFrom))
                        && (workDate.isBefore(localDateTo)
                        || workDate.isEqual(localDateTo))) {
                    sumSalary += (Integer.parseInt(words[TIME_INDEX])
                            * Integer.parseInt(words[RATE_INDEX]));
                }
            }
            result.append(System.lineSeparator());
            result.append(name).append(" - ");
            result.append(sumSalary);
            sumSalary = 0;
        }
        return result.toString();
    }
}


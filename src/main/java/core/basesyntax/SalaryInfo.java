package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        if (fromDate != null && toDate != null && names != null && data != null) {
            for (String name : names) {
                int salary = 0;
                for (String dataFromData : data) {
                    String[] dataEach = dataFromData.split(" ");
                    LocalDate dataDate = LocalDate.parse(dataEach[DATE_INDEX], FORMATTER);
                    if (dataEach[NAME_INDEX].equals(name) && !dataDate.isBefore(fromDate)
                            && !dataDate.isAfter(toDate)) {
                        salary += Integer.parseInt(dataEach[HOURS_INDEX])
                                * Integer.parseInt(dataEach[RATE_INDEX]);
                    }
                }
                result.append(name).append(" - ").append(salary);
                if (!name.equals(names[names.length - 1])) {
                    result.append(System.lineSeparator());
                }
            }
        }
        return result.toString();
    }
}

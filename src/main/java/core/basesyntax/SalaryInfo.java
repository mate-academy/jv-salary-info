package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int TIME_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate endDate = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder result = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        int sumSalary;

        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator());
            result.append(names[i]).append(" - ");
            sumSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] recordData = data[j].split(" ");
                LocalDate workDate = LocalDate.parse(recordData[DATE_INDEX], dateTimeFormatter);
                String name = recordData [NAME_INDEX];
                if (name.equals(names[i]) && workDate.compareTo(endDate) <= 0 && workDate.compareTo(startDate) >= 0) {
                        sumSalary += Integer.parseInt(recordData[RATE_INDEX])
                                * Integer.parseInt(recordData[TIME_INDEX]);
                }
            }
            result.append(sumSalary);
        }
        return result.toString();
    }
}

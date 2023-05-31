package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int NUMBER_OF_HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] splitData = data[j].split(" ");
                LocalDate startDate = LocalDate.parse(dateFrom, formatter);
                LocalDate endDate = LocalDate.parse(dateTo, formatter);
                LocalDate currentDate = LocalDate.parse(splitData[DATE], formatter);
                if (splitData[NAME].equals(names[i])
                        && (currentDate.isAfter(startDate) || currentDate.equals(startDate))
                        && (currentDate.isBefore(endDate) || currentDate.equals(endDate))) {
                    salary += Integer.parseInt(splitData[NUMBER_OF_HOURS])
                            * Integer.parseInt(splitData[INCOME_PER_HOUR]);
                }
            }
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
        }
        return result.toString();
    }
}

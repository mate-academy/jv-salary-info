package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final int dataIndex = 0;
    private final int nameIndex = 1;
    private final int hoursIndex = 2;
    private final int rateIndex = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary = 0;
        LocalDate forDateFrom = LocalDate.parse(dateFrom,formatter);
        LocalDate forDateTo = LocalDate.parse(dateTo,formatter);

        StringBuilder resultSalary = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name : names) {
            for (String dataMassive : data) {
                String[] splitDateInfo = dataMassive.split(" ");
                String entryDate = splitDateInfo[dataIndex];
                String entryName = splitDateInfo[nameIndex];
                int hoursWorked = Integer.parseInt(splitDateInfo[hoursIndex]);
                int hourlyRate = Integer.parseInt(splitDateInfo[rateIndex]);

                LocalDate entryLocalDate = LocalDate.parse(entryDate,formatter);

                if (entryName.equals(name)
                        && entryLocalDate.isAfter(forDateFrom.minusDays(1))
                        && entryLocalDate.isBefore(forDateTo.plusDays(1))) {
                    salary += hourlyRate * hoursWorked;
                }
            }
            resultSalary.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            salary = 0;
        }
        return resultSalary.toString();
    }
}

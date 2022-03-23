package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = System.lineSeparator();
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS_WORKED = 2;
    private static final int INDEX_OF_HOURLY_RATE = 3;
    private LocalDate startDate;
    private LocalDate endDate;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        startDate = LocalDate.parse(dateFrom, FORMATTER);
        endDate = LocalDate.parse(dateTo, FORMATTER);
        int salary;
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            salary = 0;
            for (String entry : data) {
                Entry parsedEntry = parseEntry(entry);
                if (isEligible(parsedEntry, name)) {
                    salary += parsedEntry.getMoneyEarned();
                }
            }
            builder.append(SEPARATOR).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }

    private Entry parseEntry(String entry) {
        String[] splittedData = entry.split(" ");
        LocalDate date = LocalDate.parse(splittedData[INDEX_OF_DATE], FORMATTER);
        String name = splittedData[INDEX_OF_NAME];
        int hoursWorked = Integer.parseInt(splittedData[INDEX_OF_HOURS_WORKED]);
        int hourlyRate = Integer.parseInt(splittedData[INDEX_OF_HOURLY_RATE]);
        return new Entry(date, name, hoursWorked, hourlyRate);
    }

    private boolean isWithinDateRange(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return !(date.isBefore(dateFrom) || date.isAfter(dateTo));
    }

    private boolean isEligible(Entry entry, String name) {
        return entry.getName().equals(name)
             & isWithinDateRange(entry.getDate(), startDate, endDate);
    }
}

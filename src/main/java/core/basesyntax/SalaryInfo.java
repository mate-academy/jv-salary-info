package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int HOURLY_WAGE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = stringToLocalDate(dateFrom);
        LocalDate localDateTo = stringToLocalDate(dateTo);
        StringBuilder output = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String personData : data) {
                if (personData.contains(name)) {
                    salary += calculateIncome(personData, localDateFrom, localDateTo);
                }
            }
            output.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return output.toString();
    }

    public int calculateIncome(String personData, LocalDate localDateFrom, LocalDate localDateTo) {
        String[] personDataArray = personData.split(" ");
        LocalDate currentLocalDate = stringToLocalDate(personDataArray[DATE_INDEX]);
        int workingHours = Integer.parseInt(personDataArray[WORKING_HOURS_INDEX]);
        int hourlyWage = Integer.parseInt(personDataArray[HOURLY_WAGE_INDEX]);
        return (!currentLocalDate.isBefore(localDateFrom) && !currentLocalDate.isAfter(localDateTo))
                ? workingHours * hourlyWage
                : 0;
    }

    public LocalDate stringToLocalDate(String input) {
        return LocalDate.parse(input, FORMATTER);
    }
}

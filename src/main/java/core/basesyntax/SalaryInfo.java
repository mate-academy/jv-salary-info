package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int SALARY_INDEX = 2;
    private static final int SALARY_MULTIPLIER_INDEX = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder(String.format("Report for period %s - %s",
                dateFrom, dateTo));

        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, formatter);

        String[] foundData;
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                foundData = line.split(" ");
                LocalDate parsedFoundDate = LocalDate.parse(foundData[DATE_INDEX], formatter);
                if (compareDates(parsedDateFrom, parsedFoundDate, parsedDateTo)) {
                    if (foundData[NAME_INDEX].equals(name)) {
                        salary += Integer.parseInt(foundData[SALARY_INDEX])
                                * Integer.parseInt(foundData[SALARY_MULTIPLIER_INDEX]);
                    }
                }
            }
            report.append(String.format("%s%s - %d",
                    System.lineSeparator(), name, salary));
        }

        return report.toString();
    }

    public boolean compareDates(LocalDate dateFrom, LocalDate dataDate, LocalDate dateTo) {
        boolean compareResult = dateFrom.compareTo(dataDate) <= 0
                && dateTo.compareTo(dataDate) >= 0;

        return compareResult;
    }
}

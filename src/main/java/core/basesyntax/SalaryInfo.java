package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = " ";
    private static final int DATE_IND = 0;
    private static final int NAME_IND = 1;
    private static final int SALARY_IND = 2;
    private static final int HOURS_IND = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = parseDate(dateFrom);
        LocalDate lastDate = parseDate(dateTo);

        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salarySum = 0;
            for (String line : data) {
                String[] dataLine = line.split(SEPARATOR);
                if (name.equals(dataLine[NAME_IND])
                        && isNeededDate(dataLine[DATE_IND], startDate, lastDate)) {
                    salarySum += Integer.parseInt(dataLine[SALARY_IND])
                            * Integer.parseInt(dataLine[HOURS_IND]);
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name).append(" - ").append(salarySum);
        }
        return stringBuilder.toString();
    }

    private boolean isNeededDate(String currentDate, LocalDate dateFrom, LocalDate dateTo) {
        LocalDate checkedDate = parseDate(currentDate);
        return !dateFrom.isAfter(checkedDate) && !dateTo.isBefore(checkedDate);

    }

    private LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, formatter);
    }
}

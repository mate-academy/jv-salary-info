package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOUR_POSITION = 2;
    private static final int INCOME_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate secondDate = LocalDate.parse(dateTo, FORMATTER);
        String[] splitData;
        for (String name : names) {
            int fullSalary = 0;
            for (String dataByName : data) {
                splitData = dataByName.split(" ");
                LocalDate actualDate = LocalDate.parse(splitData[DATE_POSITION], FORMATTER);
                if (!actualDate.isAfter(secondDate) && !actualDate.isBefore(firstDate)
                        && name.equals(splitData[NAME_POSITION])) {
                    fullSalary += Integer.parseInt(splitData[HOUR_POSITION])
                            * Integer.parseInt(splitData[INCOME_POSITION]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(fullSalary);
        }
        return report.toString();
    }
}

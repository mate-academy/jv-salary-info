package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int WORKING_HOURS = 2;
    private static final int WAGE_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate tillDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            int workerWage = 0;
            for (String workerData : data) {
                String[] workerInfo = workerData.split(" ");
                LocalDate workingDate = LocalDate.parse(workerInfo[DATE], DATE_FORMATTER);
                if ((workingDate.isAfter(fromDate) && workingDate.isBefore(tillDate)
                        || workingDate.equals(fromDate) || workingDate.equals(tillDate))
                        && names[i].equals(workerInfo[NAME])) {
                    int wagePerHour = Integer.parseInt(workerInfo[WAGE_PER_HOUR]);
                    int workingHours = Integer.parseInt(workerInfo[WORKING_HOURS]);
                    workerWage = workerWage + (wagePerHour * workingHours);
                }
            }
            reportBuilder = (i < (names.length - 1))
                    ? reportBuilder.append(names[i]).append(" - ")
                    .append(workerWage).append(System.lineSeparator())
                    : reportBuilder.append(names[i]).append(" - ").append(workerWage);
        }
        return reportBuilder.toString();
    }
}


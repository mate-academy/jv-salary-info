package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int DATE = 0;
    static final int NAME = 1;
    static final int WORKING_HOURS = 2;
    static final int WAGE_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate tillDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            int workerWage = 0;
            for (String dt : data) {
                String[] workerInfo = dt.split(" ");
                LocalDate workingDate = LocalDate.parse(workerInfo[DATE], DATE_FORMATTER);
                if ((workingDate.isAfter(fromDate) && workingDate.isBefore(tillDate)
                        || workingDate.equals(fromDate) || workingDate.equals(tillDate))
                        && names[i].equals(workerInfo[NAME])) {
                    int wagePerHour = Integer.parseInt(workerInfo[WAGE_PER_HOUR]);
                    int workingHours = Integer.parseInt(workerInfo[WORKING_HOURS]);
                    workerWage = workerWage + (wagePerHour * workingHours);
                }
            }
            if (i == (names.length - 1)) {
                builder.append(names[i]).append(" - ")
                        .append(workerWage);
            } else {
                builder.append(names[i]).append(" - ")
                        .append(workerWage).append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}


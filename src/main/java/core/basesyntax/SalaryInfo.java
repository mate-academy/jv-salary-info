package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate tillDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int workerWage = 0;
            for (String dt : data) {
                String[] workerInfo = dt.split(" ");
                LocalDate localDate = LocalDate.parse(workerInfo[0], FORMATTER);
                if ((localDate.isAfter(fromDate) && localDate.isBefore(tillDate) || localDate.equals(fromDate)
                        || localDate.equals(tillDate)) && name.equals(workerInfo[1])) {
                    int wagePerHour = Integer.parseInt(workerInfo[3]);
                    int workingHours = Integer.parseInt(workerInfo[2]);
                    workerWage = workerWage + (wagePerHour * workingHours);
                }
            }
            builder.append(name).append(" - ")
                    .append(workerWage).append(System.lineSeparator());
        }
        return builder.toString();
    }
}


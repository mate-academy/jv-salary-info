package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate tillDate = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            int workerWage = 0;
            for (String dt : data) {
                String[] workerInfo = dt.split(" ");
                LocalDate localDate = LocalDate.parse(workerInfo[0], formatter);
                if ((localDate.isAfter(fromDate) && localDate.isBefore(tillDate)
                        || localDate.equals(fromDate)
                        || localDate.equals(tillDate))
                        && names[i].equals(workerInfo[1])) {
                    int wagePerHour = Integer.parseInt(workerInfo[3]);
                    int workingHours = Integer.parseInt(workerInfo[2]);
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


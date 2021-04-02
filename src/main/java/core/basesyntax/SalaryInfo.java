package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n");

        for (String name : names) {
            int salary = 0;
            for (String workerData : data) {
                if (workerData.contains(name)) {
                    String[] dataArray = workerData.split(" ");
                    LocalDate workerDate = LocalDate.parse(dataArray[0], FORMATTER);

                    if (workerDate.isAfter(localDateFrom.minusDays(1))
                            && workerDate.isBefore(localDateTo.plusDays(1))) {
                        salary += Integer.parseInt(dataArray[3])
                                * Integer.parseInt(dataArray[2]);
                    }
                }
            }
            builder.append(name).append(" - ").append(salary).append("\n");
        }
        String result = builder.toString().strip();
        return result;
    }
}

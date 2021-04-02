package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder workerSalary =
                new StringBuilder();
        workerSalary.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append("\n");
        LocalDate dateStart =
                LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinish = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int totalSalary = 0;
            for (String datum : data) {
                if (datum.contains(name)) {
                    String takeDate = datum.split(" ")[0];
                    LocalDate dateWorker = LocalDate.parse(takeDate, FORMATTER);
                    if (dateWorker.compareTo(dateStart) >= 0
                            && dateWorker.compareTo(dateFinish) <= 0) {
                        String timeWorker = datum.split(" ")[2];
                        String priceForOneHour = datum.split(" ")[3];
                        totalSalary += Integer.parseInt(timeWorker)
                                * Integer.parseInt(priceForOneHour);
                    }
                }
            }
            workerSalary.append(name).append(" - ").append(totalSalary).append("\n");
        }
        return workerSalary.toString().trim();
    }
}

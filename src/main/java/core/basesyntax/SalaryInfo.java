package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder workersSalary = new StringBuilder();
        workersSalary.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinish = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int totalSalary = 0;
            for (String datum : data) {
                String[] splitData = datum.split(" ");
                String takeDate = splitData[DATE_INDEX];
                if (splitData[NAME_INDEX].equals(name)) {
                    LocalDate dateWorker = LocalDate.parse(takeDate, FORMATTER);
                    if ((dateStart.isBefore(dateWorker)
                            || dateStart.isEqual(dateWorker))
                            && (dateWorker.isBefore(dateFinish)
                            || dateFinish.isEqual(dateWorker))) {
                        String timeWorker = splitData[HOURS_INDEX];
                        String priceForOneHour = splitData[RATE_INDEX];
                        totalSalary += Integer.parseInt(timeWorker)
                                * Integer.parseInt(priceForOneHour);
                    }
                }
            }
            workersSalary.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return workersSalary.toString();
    }
}

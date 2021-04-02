package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinish = LocalDate.parse(dateTo, FORMATTER);
        int[] salary = new int[names.length];
        for (String name : names) {
            for (int i = 0; i < data.length; i++) {
                if (name.contains(data[i])) {
                    String takeDate = data[i].split(" ")[0];
                    LocalDate dateWorker = LocalDate.parse(takeDate, FORMATTER);
                    if ((!dateWorker.isBefore(dateStart)) && (dateWorker.isBefore(dateFinish))) {
                        String timeWorker = data[i].split(" ")[2];
                        String priceForOneHour = data[i].split(" ")[3];
                        salary[i] += Integer.parseInt(timeWorker)
                                * Integer.parseInt(priceForOneHour);
                    }
                }
            }
        }
        System.out.println("Repost for period " + dateStart + "-" + dateFrom);
        StringBuilder workerSalary = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            workerSalary.append(names[i]);
            workerSalary.append(" - ");
            workerSalary.append(salary[i]);
            workerSalary.append("\n");
        }
        return workerSalary.toString();
    }
}

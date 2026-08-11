package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate secondDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                    .append(System.lineSeparator());
        for (String worker : names) {
            int workerSalary = 0;
            for (String userData : data) {
                String[] everyStringData = userData.split(" ");
                LocalDate workDate = LocalDate.parse(everyStringData[0], FORMATTER);
                String name = everyStringData[1];
                int amountOfHours = Integer.parseInt(everyStringData[2]);
                int payPerHour = Integer.parseInt(everyStringData[3]);
                if (name.equals(worker) && !workDate.isBefore(firstDate)
                            && !workDate.isAfter(secondDate)) {
                    workerSalary += amountOfHours * payPerHour;
                }
            }
            salaryInfo.append(worker).append(" - ").append(workerSalary)
                        .append(System.lineSeparator());;
        }
        return salaryInfo.toString().trim();
    }
}

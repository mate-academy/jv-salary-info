package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        int[] getSalary = getSalaryForEachEmployee(names, data, dateFrom, dateTo);
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n");
        int counter = 0;
        for (String name: names) {
            result.append(name).append(" - ").append(getSalary[counter]).append("\n");
            counter++;
        }
        return result.toString().trim();
    }

    public int[] getSalaryForEachEmployee(String[] names, String[] data,
                                          String dateFrom, String dateTo) {
        int[] totalSalary = new int[names.length];
        LocalDate dateStart = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateFinish = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        for (int i = 0; i < names.length; i++) {
            for (String datas: data) {
                String[] splittedInfo = datas.split(" ");
                String nameEmloyee = splittedInfo[1];
                int workTime = Integer.parseInt(splittedInfo[2]);
                int salaryPerHoure = Integer.parseInt(splittedInfo[3]);
                int payForDay = workTime * salaryPerHoure;
                LocalDate currentDate = LocalDate.parse(splittedInfo[0], DATE_TIME_FORMATTER);
                if (nameEmloyee.equals(names[i])
                        && currentDate.isAfter(dateStart.minusDays(1))
                        && currentDate.isBefore(dateFinish.plusDays(1))) {
                    totalSalary[i] += payForDay;
                }
            }
        }
        return totalSalary;
    }
}



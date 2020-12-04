package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        int[] getSalary = getSalaryForEach(names, data, dateFrom, dateTo);
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n");
        int counter = 0;
        for (String name : names) {
            result.append(name).append(" - ");
            result.append(getSalary[counter]).append("\n");
            counter++;
        }
        return result.toString().trim();
    }

    public int[] getSalaryForEach(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] totalySalaryEmployee = new int[names.length];
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinish = LocalDate.parse(dateTo, FORMATTER);
        for (int i = 0; i < names.length; i++) {
            for (String info : data) {
                String[] splitInformation = info.split(" ");
                String nameEmployee = splitInformation[1];
                int workedTime = Integer.parseInt(splitInformation[2]);
                int salaryPerHour = Integer.parseInt(splitInformation[3]);
                int salaryForDay = salaryPerHour * workedTime;
                LocalDate currentDay = LocalDate.parse(splitInformation[0], FORMATTER);
                if (nameEmployee.equals(names[i])
                        && currentDay.isAfter(dateStart.minusDays(1))
                        && currentDay.isBefore(dateFinish.plusDays(1))) {
                    totalySalaryEmployee[i] += salaryForDay;
                }
            }
        }
        return totalySalaryEmployee;
    }
}

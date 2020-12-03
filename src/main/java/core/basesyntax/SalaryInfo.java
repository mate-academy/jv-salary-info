package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        String [] getSalary = getSalaryForEach(data,dateFrom,dateTo);
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

    public String[] getSalaryForEach(String[] data, String dateFrom, String dateTo) {
        int salaryAndrew = 0;
        int salaryJohn = 0;
        int salaryKate = 0;
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinish = LocalDate.parse(dateTo, FORMATTER);
        for (String info : data) {
            String[] splitInformation = info.split(" ");
            int salaryPerHour = Integer.parseInt(splitInformation[2]);
            int workedTime = Integer.parseInt(splitInformation[3]);
            int salaryForDay = salaryPerHour * workedTime;
            LocalDate currentDay = LocalDate.parse(splitInformation[0], FORMATTER);
            if (splitInformation[1].equals("Andrew")
                    && currentDay.isAfter(dateStart.minusDays(1))
                    && currentDay.isBefore(dateFinish.plusDays(1))) {
                salaryAndrew += salaryForDay;
            } else if (splitInformation[1].equals("John")
                    && currentDay.isAfter(dateStart.minusDays(1))
                    && currentDay.isBefore(dateFinish.plusDays(1))) {
                salaryJohn += salaryForDay;
            } else if (splitInformation[1].equals("Kate")
                    && currentDay.isAfter(dateStart.minusDays(1))
                    && currentDay.isBefore(dateFinish.plusDays(1))) {
                salaryKate += salaryForDay;
            }
        }
        return new String[]{String.valueOf(salaryJohn),
                String.valueOf(salaryAndrew),
                String.valueOf(salaryKate)};
    }
}

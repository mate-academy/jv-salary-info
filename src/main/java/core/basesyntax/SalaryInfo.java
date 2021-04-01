package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateFormat =
            DateTimeFormatter.ofPattern(("d.MM.y"));

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom,dateFormat);
        LocalDate finishDate = LocalDate.parse(dateTo,dateFormat);
        StringBuilder result = new StringBuilder();

        result.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        int[] salary = new int[names.length];
        while (!startDate.equals(finishDate.plusDays(1))) {
            for (String dateOfWork : data) {
                String[] info = dateOfWork.split(" ");
                String dateFromData = info[0];
                String nameFromData = info[1];
                int hoursFromData = Integer.parseInt(info[2]);
                int incomePerHour = Integer.parseInt(info[3]);
                if (startDate.equals(LocalDate.parse(dateFromData,dateFormat))) {
                    for (int i = 0; i < names.length; i++) {
                        if (nameFromData.equals(names[i])) {
                            salary[i] += hoursFromData * incomePerHour;
                        }
                    }
                }
            }
            startDate = startDate.plusDays(1);
        }
        for (int i = 0; i < names.length; i++) {
            result.append("\n").append(names[i]).append(" - ").append(salary[i]);
        }
        return result.toString();
    }
}

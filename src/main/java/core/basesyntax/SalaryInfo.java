package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        int index = 0;

        for (int i = 0; i < data.length; i++) {
            int hours = Integer.parseInt(data[i].split("\\s")[2]);
            int perHour = Integer.parseInt(data[i].split("\\s")[3]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);

            // Parse dates from strings

            LocalDate salaryDate = LocalDate.parse(data[i].split("\\s")[0], formatter);
            LocalDate startDate = LocalDate.parse(dateFrom.replace("-", "."), formatter);
            LocalDate endDate = LocalDate.parse(dateTo.replace("-", "."), formatter);

            int salary = hours * perHour;
            String name = data[i].split("\\s")[1];
            for (int j = 0; j < names.length; j++) {
                if (names[j].equals(name)) {
                    index = j;
                    break;
                }
            }
            if (salaryDate.equals(startDate)
                    || salaryDate.equals(endDate)
                    || (salaryDate.isAfter(startDate)
                    && salaryDate.isBefore(endDate))) {
                salaries[index] += salary;
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append("\n");
        for (int i = 0;i < names.length; i++) {
            builder.append(names[i]).append(" - ").append(salaries[i]).append("\n");
        }
        return builder.delete(builder.length() - 1, builder.length()).toString();
    }
}

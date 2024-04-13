package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final int dataDate = 0;
        final int dataName = 1;
        final int dataHours = 2;
        final int dataIncome = 3;
        LocalDate salaryDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate salaryDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder();
        int[] salaries = new int[names.length];

        for (int i = 0; i < data.length; i++) {
            String[] tempData = data[i].split(" ");
            LocalDate currentDate = LocalDate.parse(tempData[dataDate], formatter);

            if (currentDate.isAfter(salaryDateFrom.minusDays(1))
                    && currentDate.isBefore(salaryDateTo.plusDays(1))) {
                String name = tempData[dataName];
                int hours = Integer.parseInt(tempData[dataHours]);
                int incomePerHour = Integer.parseInt(tempData[dataIncome]);
                int index = 0;

                for (int j = 0; j < names.length; j++) {
                    if (name.equals(names[j])) {
                        index = j;
                    }
                }
                salaries[index] += hours * incomePerHour;
            }
        }
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append("\n")
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }

        return builder.toString();
    }
}

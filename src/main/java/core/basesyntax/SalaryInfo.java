package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, formatter);

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");

        for (int i = 0; i < names.length; i++) {
            String searchedName = names[i];
            int salary = 0;

            for (int j = 0; j < data.length; j++) {
                String[] dividedData = data[j].split(" ");
                LocalDate currentDate = LocalDate.parse(dividedData[0], formatter);
                String currentName = dividedData[1];
                int currentMultiplier = Integer.valueOf(dividedData[2]);
                int currentAmount = Integer.valueOf(dividedData[3]);

                if (searchedName.equals(currentName)
                        && currentDate.compareTo(dateFromFormatted) >= 0
                        && currentDate.compareTo(dateToFormatted) <= 0) {
                    int currentSalary = currentMultiplier * currentAmount;
                    salary = currentSalary + salary;
                }
            }
            builder.append(names[i])
                    .append(" - ")
                    .append(salary)
                    .append("\n");
        }
        return builder.toString();
    }
}

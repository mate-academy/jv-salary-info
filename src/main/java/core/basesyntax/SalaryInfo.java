package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo
                + "\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            int totalSalary = 0;


            for (String records : data) {
                String[] dataParts = records.split(" ");
                LocalDate currentDate = LocalDate.parse(dataParts[0], formatter);
                String currentName = dataParts[1];
                int currentHours = Integer.parseInt(dataParts[2]);
                int currentRate = Integer.parseInt(dataParts[3]);
                int salary = currentHours * currentRate;

                if (name.equals(currentName)
                        && !currentDate.isBefore(from)
                        && !currentDate.isAfter(to)) {
                    totalSalary += salary;
                }

            }
            result.append(name)
                    .append(" - ")
                    .append(totalSalary)
                    .append("\n");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

}

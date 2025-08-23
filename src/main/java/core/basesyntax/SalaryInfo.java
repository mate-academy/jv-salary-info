package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder finalReport = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + "\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateF = LocalDate.parse(dateFrom, formatter);
        LocalDate dateT = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            int salary = 0;

            for (String employee : data) {
                String[] infoDivided = employee.split(" ");
                LocalDate period = LocalDate.parse(infoDivided[0], formatter);
                if (name.equals(infoDivided[1])) {

                    int hours = Integer.parseInt(infoDivided[2]);
                    int hourlyRate = Integer.parseInt(infoDivided[3]);

                    if (!dateT.isBefore(period) && !dateF.isAfter(period)) {
                        salary += hours * hourlyRate;
                    }
                }
            }
            finalReport.append(name).append(" - ").append(salary).append("\n");
        }
        finalReport.setLength(finalReport.length() - 1);

        return finalReport.toString();
    }
}

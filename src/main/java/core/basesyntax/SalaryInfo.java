package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryReportBuilder = new StringBuilder();
        int[] salary = new int[names.length];
        LocalDate dateFromFormated = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToFormated = LocalDate.parse(dateTo, formatter);
        salaryReportBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            for (String dataEntry : data) {
                String[] dataSplit = dataEntry.split(" ");
                LocalDate localDate = LocalDate.parse(dataSplit[0], formatter);
                if (!localDate.isBefore(dateFromFormated) && !localDate.isAfter(dateToFormated)) {
                    if (names[i].equals(dataSplit[1])) {
                        int hoursOfWork = Integer.parseInt(dataSplit[2]);
                        int incomePerHour = Integer.parseInt(dataSplit[3]);
                        salary[i] = salary[i] + (hoursOfWork * incomePerHour);
                    }
                }
            }
            salaryReportBuilder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }
        return salaryReportBuilder.toString();
    }

}


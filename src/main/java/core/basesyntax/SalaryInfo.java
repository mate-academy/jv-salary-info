package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws DateTimeException {
        LocalDate startDate;
        LocalDate endDate;
        LocalDate dateInArray;
        StringBuilder salaryInfo = new StringBuilder();
        try {
            startDate = LocalDate.parse(dateFrom, FORMATTER);
            endDate = LocalDate.parse(dateTo, FORMATTER);
        } catch (DateTimeException e) {
            throw new DateTimeException("Cannot parse date. Format of date should be"
                    + "dd.mm.yyyy");
        }
        salaryInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                try {
                    dateInArray = LocalDate.parse(data[j].split(" ")[0], FORMATTER);
                } catch (DateTimeException e) {
                    throw new DateTimeException("Cannot parse date from data."
                            + "Format of date should be dd.mm.yyyy");
                } finally {
                    dateInArray = LocalDate.parse(data[j].split(" ")[0], FORMATTER);
                    String nameFromData = data[j].split(" ")[1];
                    int hours = Integer.parseInt(data[j].split(" ")[2]);
                    int salaryPerHour = Integer.parseInt(data[j].split(" ")[3]);

                    if (nameFromData.equals(names[i])
                            && (dateInArray.isAfter(startDate) || dateInArray.equals(startDate))
                            && (dateInArray.isBefore(endDate) || dateInArray.equals(endDate))) {
                        salary += hours * salaryPerHour;
                    }
                }
            }
            salaryInfo.append(names[i]).append(" - ").append(salary)
                    .append(System.lineSeparator());
        }
        return salaryInfo.toString().trim();
    }
}

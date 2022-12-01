package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int WORKING_HOURS_POSITION = 2;
    private static final int SALARY_PER_HOUR_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate lastDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name :
                names) {
            int salaryCounter = 0;
            for (String line :
                    data) {
                String [] arrayOfData = line.split(" ");
                LocalDate currentDate = LocalDate.parse(arrayOfData[DATE_POSITION], DATE_FORMATTER);
                if (arrayOfData[NAME_POSITION].equals(name)
                        && ((currentDate.isAfter(firstDate)) || (currentDate.isEqual(firstDate)))
                        && (currentDate.isBefore(lastDate) || currentDate.isEqual(lastDate))) {
                    salaryCounter = salaryCounter
                            + Integer.parseInt(arrayOfData[WORKING_HOURS_POSITION])
                                    * Integer.parseInt(arrayOfData[SALARY_PER_HOUR_POSITION]);
                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salaryCounter);
        }
        return report.toString();
    }
}

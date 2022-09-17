package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int DAY_INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDay = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDay = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int sallary = 0;
            for (String dataline : data) {
                String[] parsedData = dataline.split(" ");
                LocalDate currentDate = LocalDate.parse(parsedData[DATE_INDEX], FORMATTER);
                if (parsedData[NAME_INDEX].equals(name) 
                        && currentDate.isAfter(fromDay.minusDays(1))
                        && currentDate.isBefore(toDay.plusDays(1))) {
                    sallary += (Integer.parseInt(parsedData[HOURS_INDEX])
                            * Integer.parseInt(parsedData[DAY_INCOME_INDEX]));
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(sallary);
        }
        return report.toString();
    }
}

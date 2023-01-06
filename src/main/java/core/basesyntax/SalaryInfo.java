package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate beginDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        LocalDate particularDate;
        int hours;
        int salaryHour;
        int salary;
        String[] separateData;
        for (String name: names) {
            salary = 0;
            for (String dataSet: data) {
                separateData = dataSet.split(" ");
                particularDate = LocalDate.parse(separateData[DATE_INDEX], FORMATTER);
                hours = Integer.parseInt(separateData[HOURS_INDEX]);
                salaryHour = Integer.parseInt(separateData[PER_HOUR_INDEX]);
                if (name.equals(separateData[NAME_INDEX]) && ((particularDate.isAfter(beginDate)
                        || particularDate.equals(beginDate)) && ((particularDate.isBefore(endDate)
                        || particularDate.equals(endDate))))) {
                    salary += hours * salaryHour;
                }
            }
            result.append(System.lineSeparator())
                    .append(name).append(" - ").append(salary);
        }

        return result.toString();
    }
}

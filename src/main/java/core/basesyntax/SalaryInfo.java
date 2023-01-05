package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private LocalDate beginDate;
    private LocalDate endDate;
    private LocalDate particularDate;
    private int hours;
    private int salaryHour;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        int salary;
        beginDate = LocalDate.parse(dateFrom, FORMATTER);
        endDate = LocalDate.parse(dateTo, FORMATTER);
        String[] separateData;
        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (int j = 0; j < data.length; j++) {
                separateData = data[j].split(" ");
                particularDate = LocalDate.parse(separateData[DATE_INDEX], FORMATTER);
                hours = Integer.parseInt(separateData[HOURS_INDEX]);
                salaryHour = Integer.parseInt(separateData[PER_HOUR_INDEX]);
                if (names[i].equals(separateData[NAME_INDEX]) && ((particularDate.isAfter(beginDate)
                        || particularDate.equals(beginDate)) && ((particularDate.isBefore(endDate)
                        || particularDate.equals(endDate))))) {
                    salary += hours * salaryHour;
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + builder;
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            int totalSalary = getTotalSalary(0,name,data,dateFrom,dateTo);
            reportBuilder.append(System.lineSeparator()).append(name);
            reportBuilder.append(" - ").append(totalSalary);
        }
        return reportBuilder.toString();
    }

    public int getTotalSalary(int totalSalary,String name, String[] data,
                              String dateFrom, String dateTo) {
        for (String line: data) {
            String[] splittedLine = line.split(" ");
            if (isValidDate(splittedLine[DATE_INDEX], dateFrom, dateTo)
                    & name.equals(splittedLine[NAME_INDEX])) {
                int hours = Integer.parseInt(splittedLine[HOURS_INDEX]);
                int hoursSalary = Integer.parseInt(splittedLine[INCOME_PER_HOUR_INDEX]);
                totalSalary += hoursSalary * hours;
            }
        }
        return totalSalary;
    }

    public boolean isValidDate(String checkedDate, String dateFrom, String dateTo) {
        return LocalDate.parse(dateFrom,FORMATTER).isEqual(LocalDate.parse(checkedDate,FORMATTER))
                | LocalDate.parse(dateTo,FORMATTER).isEqual(LocalDate.parse(checkedDate, FORMATTER))
                | LocalDate.parse(dateFrom,FORMATTER)
                .isBefore(LocalDate.parse(checkedDate,FORMATTER))
                & LocalDate.parse(dateTo,FORMATTER)
                .isAfter(LocalDate.parse(checkedDate, FORMATTER));
    }
}

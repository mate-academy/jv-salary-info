package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOUR_INDEX = 2;
    static final int SUM_INDEX = 3;
    static final String DATE_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate dateStart = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, dateTimeFormatter);
        int sumSalary;
        String[] salaryData;
        LocalDate dateOfSalary;
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            sumSalary = 0;
            for (String datum : data) {
                salaryData = datum.split(" ");
                dateOfSalary = LocalDate.parse(salaryData[DATE_INDEX], dateTimeFormatter);
                if (name.equals(salaryData[NAME_INDEX])) {
                    if (dateStart.compareTo(dateOfSalary) <= 0
                            && dateEnd.compareTo(dateOfSalary) >= 0) {
                        sumSalary += Integer.parseInt(salaryData[HOUR_INDEX])
                                * Integer.parseInt(salaryData[SUM_INDEX]);
                    }
                }
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(sumSalary);
        }
        return result.toString();
    }
}

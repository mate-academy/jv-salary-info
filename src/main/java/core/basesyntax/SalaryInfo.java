package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOUR_INDEX = 2;
    static final int SUM_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] sumSalary = new int[names.length];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateStart = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, dateTimeFormatter);
        for (String datum : data) {
            String[] salaryData = datum.split(" ");
            LocalDate salaryDate = LocalDate.parse(salaryData[DATE_INDEX], dateTimeFormatter);
            for (int j = 0; j < names.length; j++) {
                if (names[j].equals(salaryData[NAME_INDEX])) {
                    if (salaryDate.compareTo(dateStart) >= 0
                            && salaryDate.compareTo(dateEnd) <= 0) {
                        sumSalary[j] += Integer.parseInt(salaryData[HOUR_INDEX])
                                * Integer.parseInt(salaryData[SUM_INDEX]);
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(sumSalary[i]);
        }
        return result.toString();
    }
}

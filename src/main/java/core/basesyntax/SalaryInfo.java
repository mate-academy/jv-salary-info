package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INITIALIZOR = 0;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY = 3;
    private LocalDate dateOfEmployeeLD = null;
    private LocalDate dateFromLD = null;
    private LocalDate dateToLD = null;

    public String getSalaryInfo(String[] names,
                                String[] data,
                                String dateFrom,
                                String dateTo) {
        StringBuilder stb = new StringBuilder();
        StringBuilder stb2 = new StringBuilder();
        dateFromLD = LocalDate.parse(dateFrom, FORMATTER).minusDays(1);
        dateToLD = LocalDate.parse(dateTo, FORMATTER).plusDays(1);
        int [] salaryNumbers = new int [names.length];
        for (int i = INITIALIZOR; i < names.length; i++) {
            for (int j = INITIALIZOR; j < data.length; j++) {
                dateOfEmployeeLD = LocalDate.parse(data[j].split(" ")[0], FORMATTER);
                if ((names[i].equals(data[j].split(" ")[INDEX_OF_NAME]))
                        && ((dateOfEmployeeLD.isAfter(dateFromLD))
                                && dateOfEmployeeLD.isBefore(dateToLD))) {
                    salaryNumbers[i] += Integer.parseInt(data[j].split(" ")[INDEX_OF_HOURS])
                            * Integer.parseInt(data[j].split(" ")[INDEX_OF_SALARY]);
                }
            }
            stb2.append(System.lineSeparator())
                    .append(names[i])
                    .append(" ")
                    .append("-")
                    .append(" ")
                    .append(salaryNumbers[i]);
        }
        stb.append("Report for period ")
                .append(dateFrom)
                .append(" ")
                .append("-")
                .append(" ")
                .append(dateTo)
                .append(stb2);
        return stb.toString();
    }
}

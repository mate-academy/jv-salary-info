package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOUR_INDEX = 2;
    public static final int SALARY_INDEX = 3;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sallaryOfEmploye = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate dateBegin = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateAfter = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int totalSallary = 0;
            for (String day : data) {
                String[] splittedUserData = day.split(" ");
                if ((LocalDate.parse(splittedUserData[DATE_INDEX], FORMATTER).equals(dateBegin)
                        || LocalDate.parse(splittedUserData[DATE_INDEX], FORMATTER)
                        .isAfter(dateBegin))
                        && (LocalDate.parse(splittedUserData[DATE_INDEX], FORMATTER)
                        .equals(dateAfter)
                        || LocalDate.parse(splittedUserData[DATE_INDEX], FORMATTER)
                            .isBefore(dateAfter))
                        && name.equals(splittedUserData[NAME_INDEX])) {
                    int hours = Integer.parseInt(splittedUserData[HOUR_INDEX]);
                    int salary = Integer.parseInt(splittedUserData[SALARY_INDEX]);
                    totalSallary = totalSallary + (hours * salary);
                }
            }
            sallaryOfEmploye.append(System.lineSeparator())
                    .append(name).append(" - ")
                    .append(totalSallary);
        }
        return sallaryOfEmploye.toString();
    }
}

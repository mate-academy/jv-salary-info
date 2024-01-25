package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private Scanner scanner;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        int moneyEarnedByPerson;
        for (String name : names) {
            moneyEarnedByPerson = 0;
            for (String dataEntry : data) {
                scanner = new Scanner(dataEntry);
                if (isDateWithinRange(scanner.next(), dateFrom, dateTo)
                        && name.equals(scanner.next())) {
                    moneyEarnedByPerson += scanner.nextInt()
                            * scanner.nextInt();
                }
            }
            stringBuilder.append(name).append(" - ").append(moneyEarnedByPerson);
            if (!name.equals(names[names.length - 1])) {
                stringBuilder.append(System.lineSeparator());
            }
        }

        return stringBuilder.toString();
    }

    private boolean isDateWithinRange(String dateString,
                                      String dateFromString, String dateToString) {
        LocalDate date = LocalDate.parse(dateString, FORMATTER);
        LocalDate dateFrom = LocalDate.parse(dateFromString, FORMATTER);
        LocalDate dateTo = LocalDate.parse(dateToString, FORMATTER);

        return (date.isEqual(dateFrom) || date.isAfter(dateFrom))
                && (date.isEqual(dateTo) || date.isBefore(dateTo));
    }

}

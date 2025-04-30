package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private Scanner scanner;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        LocalDate dateFromObject = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToObject = LocalDate.parse(dateTo, FORMATTER);
        int moneyEarnedByPerson;
        for (String name : names) {
            moneyEarnedByPerson = 0;
            for (String dataEntry : data) {
                scanner = new Scanner(dataEntry);
                if (isDateWithinRange(scanner.next(), dateFromObject, dateToObject)
                        && name.equals(scanner.next())) {
                    moneyEarnedByPerson += scanner.nextInt()
                            * scanner.nextInt();
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ")
                    .append(moneyEarnedByPerson);
        }

        return stringBuilder.toString();
    }

    private boolean isDateWithinRange(String dateString, LocalDate dateFrom, LocalDate dateTo) {
        LocalDate date = LocalDate.parse(dateString, FORMATTER);

        return (date.isEqual(dateFrom) || date.isAfter(dateFrom))
                && (date.isEqual(dateTo) || date.isBefore(dateTo));
    }

}

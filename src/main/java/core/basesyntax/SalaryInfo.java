package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryStatement = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        try {
            LocalDate dateFromParsed = LocalDate.parse(dateFrom, DATE_FORMATTER);
            LocalDate dateToParsed = LocalDate.parse(dateTo, DATE_FORMATTER);
            for (String name : names) {
                int earnedMoney = 0;
                for (String info : data) {
                    String[] splitData = info.split(" ");
                    LocalDate workingDate = LocalDate.parse(splitData[0], DATE_FORMATTER);
                    String currentName = splitData[1];
                    int daysAmount = Integer.parseInt(splitData[2]);
                    int salaryPerDay = Integer.parseInt(splitData[3]);
                    if (currentName.equals(name) && !workingDate.isAfter(dateToParsed)
                            && !workingDate.isBefore(dateFromParsed)) {
                        earnedMoney += daysAmount * salaryPerDay;
                    }
                }
                salaryStatement.append(System.lineSeparator()).append(name)
                        .append(" - ").append(earnedMoney);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Date is not parsable");
        } catch (NumberFormatException e) {
            System.out.println("Numbers are not parsable");
        }
        return salaryStatement.toString();
    }
}

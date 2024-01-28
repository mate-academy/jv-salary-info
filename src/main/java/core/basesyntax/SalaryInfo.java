package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
            LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Report for period ")
                    .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
            for (String name : names) {
                int salary = 0;
                for (String information : data) {
                    String[] informationArray = information.split(" ");
                    try {
                        LocalDate workDate = LocalDate
                                .parse(informationArray[DATE_INDEX], FORMATTER);
                        int hours = Integer.parseInt(informationArray[HOUR_INDEX]);
                        int incomePerHour = Integer.parseInt(informationArray[INCOME_INDEX]);
                        if (name.equals(informationArray[NAME_INDEX])
                                && (workDate.isAfter(startDate) || workDate.isEqual(startDate))
                                && (workDate.isBefore(endDate) || workDate.isEqual(endDate))) {
                            salary += hours * incomePerHour;
                        }
                    } catch (DateTimeParseException e) {
                        return "Exception during date parsing";
                    } catch (NumberFormatException e) {
                        return "Exception during int parsing";
                    }
                }
                stringBuilder.append(name).append(" - ").append(salary);
                if (!name.equals(names[names.length - 1])) {
                    stringBuilder.append(System.lineSeparator());
                }
            }
            return stringBuilder.toString();
        } catch (DateTimeParseException e) {
            return "Exception during date parsing";
        }
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        int[] wages = new int[names.length];
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        LocalDate compareDate;
        String withoutName;
        int hours;
        int money;
        int wage = 0;
        int i = 0;
        for (String name : names) {
            for (String shift : data) {
                compareDate = LocalDate.parse(shift.substring(0, 10), DATE_TIME_FORMATTER);
                if ((fromDate.isBefore(compareDate) || fromDate.isEqual(compareDate))
                        && (toDate.isAfter(compareDate) || toDate.isEqual(compareDate))) {
                    if (shift.contains(name)) {
                        withoutName = shift.substring(name.length() + 12).trim();
                        hours = Integer.parseInt(withoutName.substring(0, withoutName.indexOf(" ")));
                        money = Integer.parseInt(withoutName
                                .substring(withoutName.indexOf(" ")).trim());
                        wage += hours * money;
                    }
                }
            }
            wages[i] = wage;
            wage = 0;
            i++;
        }
            StringBuilder output = new StringBuilder();
            output.append("Report for period ").append(dateFrom).append(" - ")
                    .append(dateTo);
            for (int j = 0; j < names.length; j++) {
                output.append(System.lineSeparator()).append(names[j]).append(" - ").append(wages[j]);
            }
            return output.toString();
        }
    }


package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] accSalary = new int[names.length];

        for (String salaryItem : data) {
            String[] salaryItemArray = salaryItem.split(" ");
            String date = salaryItemArray[DATE_INDEX];
            String name = salaryItemArray[NAME_INDEX];
            int hours = Integer.parseInt(salaryItemArray[HOURS_INDEX]);
            int salary = Integer.parseInt(salaryItemArray[SALARY_INDEX]);
            int nameIndex = -1;
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(name)) {
                    nameIndex = i;
                    break;
                }
            }
            if (nameIndex != -1 && isBetweenInclusive(date, dateFrom, dateTo)) {
                accSalary[nameIndex] += hours * salary;
            }
        }

        StringBuilder sb = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (int i = 0; i < names.length; i++) {
            sb.append(System.lineSeparator());
            sb.append(names[i]);
            sb.append(" - ");
            sb.append(accSalary[i]);
        }

        return sb.toString();
    }

    private boolean isBetweenInclusive(String date, String dateFrom, String dateTo) {
        LocalDate localDate = LocalDate.parse(date, DATE_FORMATTER);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        return !localDate.isBefore(localDateFrom) && !localDate.isAfter(localDateTo);
    }
}

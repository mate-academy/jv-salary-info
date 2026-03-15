package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] accSalary = new int[names.length];

        for (String salaryItem : data) {
            String[] salaryItemArray = salaryItem.split(" ");
            String date = salaryItemArray[0];
            String name = salaryItemArray[1];
            int hours = Integer.parseInt(salaryItemArray[2]);
            int salary = Integer.parseInt(salaryItemArray[3]);
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
            sb.append("\n");
            sb.append(names[i]);
            sb.append(" - ");
            sb.append(accSalary[i]);
        }

        return sb.toString();
    }

    private boolean isBetweenInclusive(String date, String dateFrom, String dateTo) {
        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        return !localDate.isBefore(localDateFrom) && !localDate.isAfter(localDateTo);
    }
}

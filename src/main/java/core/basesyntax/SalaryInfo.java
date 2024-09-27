package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom);
        stringBuilder.append(" - ");
        stringBuilder.append(dateTo);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] str = data[j].split(" "); //0 - date, 1 - name, 2*3 - salary
                if (names[i].equals(str[1])) {
                    LocalDate myDate = LocalDate.parse(str[0], formatter);
                    LocalDate myDateFrom = LocalDate.parse(dateFrom, formatter);
                    LocalDate myDateTo = LocalDate.parse(dateTo, formatter);

                    if (myDate.isAfter(myDateFrom) && myDate.isBefore(myDateTo)
                            || myDate.isEqual(myDateFrom) || myDate.isEqual(myDateTo)) {
                        salary += Integer.valueOf(str[2]) * Integer.valueOf(str[3]);
                    }
                }
            }
            stringBuilder.append("\n").append(names[i]).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }

    private int dateToCompareFormate(String date) {
        return Integer.valueOf(date.substring(3, 5) + date.substring(0, 2));
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);
        int[] totalSalary = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            String[] splitDate = data[i].split(" ");
            LocalDate actualDate = LocalDate.parse(splitDate[0], dateFormatter);
            if (actualDate.isEqual(fromDate) || actualDate.isEqual(toDate)
                    || (actualDate.isAfter(fromDate) && actualDate.isBefore(toDate))) {
                for (int j = 0; j < names.length; j++) {
                    if (splitDate[1].equals(names[j])) {
                        totalSalary[j] += Integer.parseInt(splitDate[2])
                                * Integer.parseInt(splitDate[3]);
                        break;
                    }
                }
            }

        }
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator());
            result.append(names[i]).append(" - ").append(totalSalary[i]);
        }
        return result.toString();
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String pattern = "dd.MM.yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate date1 = LocalDate.parse(dateFrom, formatter);
        LocalDate date2 = LocalDate.parse(dateTo, formatter);

        String result;
        result = "Report for period " + dateFrom + " - " + dateTo;
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] splitData = data[i].split(" ");
                LocalDate date3 = LocalDate.parse(splitData[0], formatter);
                boolean bool1 = date1.isBefore(date3);
                boolean bool2 = date1.equals(date3);
                boolean bool3 = date2.isAfter(date3);
                boolean bool4 = date2.equals(date3);
                boolean nameCheck = name.equals(splitData[1]);
                if ((bool1 || bool2) && (bool3 || bool4) && nameCheck) {
                    salary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            result = result + "\r\n" + name + " - " + salary;
        }
        return result;
    }
}

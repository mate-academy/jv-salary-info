package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        String salaryStr = "";

        for (int j = 0, k = names.length; j < names.length; j++) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] strArr = data[i].split(" ");
                String name = strArr[1];
                if (names[j].equals(name)) {
                    LocalDate date = LocalDate.parse(strArr[0], formatter);
                    int workingHours = Integer.parseInt(strArr[2]);
                    int incomPerHour = Integer.parseInt(strArr[3]);
                    if ((date.isAfter(from) || date.isEqual(from))
                            && (date.isBefore(to) || date.isEqual(to))) {
                        salary += workingHours * incomPerHour;
                    }
                }
            }

            if (j == k - 1) {
                salaryStr += names[j] + " - " + salary;
            } else {
                salaryStr += names[j] + " - " + salary + System.lineSeparator();
            }
        }
        salaryStr = "Report for period " + from.format(formatter)
                + " - " + to.format(formatter) + System.lineSeparator()
                + salaryStr;

        return salaryStr;
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        StringBuilder report = new StringBuilder("Report for period " + from.format(formatter)
                + " - " + to.format(formatter));

        for (int j = 0; j < names.length; j++) {
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
            report.append(System.lineSeparator()).append(names[j]).append(" - ").append(salary);
        }
        return report.toString();
    }
}

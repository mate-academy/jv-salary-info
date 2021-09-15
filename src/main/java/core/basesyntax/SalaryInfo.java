package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateF = LocalDate.parse(dateFrom,formatter);
        LocalDate dateT = LocalDate.parse(dateTo,formatter);

        for (int i = 0; i < names.length; i++) {
            int fullSalary = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[j] == null) {
                    continue;
                }
                String[] words = data[j].split(" ",0);
                LocalDate date = LocalDate.parse(words[0],formatter);
                String checkedName = words[1];
                int hours = Integer.parseInt(words[2]);
                int salaryPerHour = Integer.parseInt(words[3]);
                int salary = hours * salaryPerHour;
                boolean a = !date.isBefore(dateF);
                boolean b = !date.isAfter(dateT);
                if (checkedName.equals(names[i])
                            && !date.isBefore(dateF) && !date.isAfter(dateT)) {
                    fullSalary = salary + fullSalary;
                }
                if (j == data.length - 1) {
                    StringBuilder resName = new StringBuilder(names[i] + " - " + fullSalary);
                    report.append(System.lineSeparator());
                    report.append(resName);
                }
            }
        }
        return report.toString();
    }
}

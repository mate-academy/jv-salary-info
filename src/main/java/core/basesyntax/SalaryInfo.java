package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom,formatter);
        LocalDate to = LocalDate.parse(dateTo,formatter);

        for (int i = 0; i < names.length; i++) {
            int fullSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] words = data[j].split(" ",0);
                LocalDate date = LocalDate.parse(words[0],formatter);
                String checkedName = words[1];
                int hours = Integer.parseInt(words[2]);
                int salaryPerHour = Integer.parseInt(words[3]);
                int salary = hours * salaryPerHour;
                if (checkedName.equals(names[i])
                            && !date.isBefore(from) && !date.isAfter(to)) {
                    fullSalary = salary + fullSalary;
                }
            }
            StringBuilder resName = new StringBuilder(names[i] + " - " + fullSalary);
            report.append(System.lineSeparator());
            report.append(resName);
        }
        return report.toString();
    }
}

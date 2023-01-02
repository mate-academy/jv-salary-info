package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate dt1 = LocalDate.parse(dateFrom, dtf);
        LocalDate dt2 = LocalDate.parse(dateTo, dtf);
        StringBuilder rezalt = new StringBuilder();
        int sumSalary;
        LocalDate workDate;
        String name;
        int hours;
        int salaryHour;
        rezalt.append("Report for period ").append(dateFrom);
        rezalt.append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            rezalt.append(System.lineSeparator());
            rezalt.append(names[i]).append(" - ");
            sumSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] words = data[j].split(" ");
                workDate = LocalDate.parse(words[0], dtf);
                name = words[1];
                hours = Integer.parseInt(words[2]);
                salaryHour = Integer.parseInt(words[3]);
                if (name.equals(names[i])) {
                    if (workDate.compareTo(dt2) <= 0 && workDate.compareTo(dt1) >= 0) {
                        sumSalary += salaryHour * hours;
                    }
                }
            }
            rezalt.append(sumSalary);
        }
        return rezalt.toString();
    }
}


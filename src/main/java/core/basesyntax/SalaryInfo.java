package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        LocalDate startDate = LocalDate.parse(dateFrom);
        LocalDate endDate = LocalDate.parse(dateTo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        salaryInfo.append("Report for period ");
        salaryInfo.append(dateFrom);
        salaryInfo.append(" - ");
        salaryInfo.append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] lines = datum.split(" ");
                LocalDate date = LocalDate.parse(lines[0]);
                String nameUser = lines[1];
                if (date.isAfter(startDate) && date.isBefore(endDate)
                        && name.equals(nameUser)) {
                    salary += Integer.parseInt(lines[2]) * Integer.parseInt(lines[3]);
                } else if (date.isEqual(startDate) && name.equals(nameUser)
                        || date.isEqual(endDate) && name.equals(nameUser)) {
                    salary += Integer.parseInt(lines[2]) * Integer.parseInt(lines[3]);
                }
            }
            salaryInfo.append(System.lineSeparator());
            salaryInfo.append(name);
            salaryInfo.append(" - ");
            salaryInfo.append(salary);
        }
        return salaryInfo.toString();
    }
}

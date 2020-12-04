package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate startWork = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate finishWork = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int employeesSalary = 0;
            for (String currentData : data) {
                String[] dateResult = currentData.split(" ");
                LocalDate localDate = LocalDate.parse(dateResult[0], DATE_FORMAT);
                if (dateResult[1].equals(name) && ((localDate.isAfter(startWork)
                        && localDate.isBefore(finishWork)) || localDate.isEqual(startWork)
                        || localDate.isEqual(finishWork))) {
                    employeesSalary += Integer.parseInt(dateResult[2])
                            * Integer.parseInt(dateResult[3]);
                }
            }
            report.append("\n").append(name).append(" - ").append(employeesSalary);
        }
        return report.toString();
    }
}

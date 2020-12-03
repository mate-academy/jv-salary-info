package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate formattedDateFrom = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate formattedDateTo = LocalDate.parse(dateTo, DATE_FORMAT);

        StringBuilder stringBuilder = new StringBuilder();

        for (String employeeData : data) {
            LocalDate employeeWorkDate = LocalDate
                    .parse(employeeData.substring(0, 10), DATE_FORMAT);
            if (!employeeWorkDate.isBefore(formattedDateFrom)
                    && !employeeWorkDate.isAfter(formattedDateTo)) {
                stringBuilder.append(employeeData.substring(11)).append(",");
            }
        }

        String[] dateMatches = stringBuilder.toString().split(",");
        stringBuilder.setLength(0);
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int personalSalary = 0;
            for (String employee : dateMatches) {
                String[] employeeData = employee.split(" ");
                if (employeeData[0].equals(name)) {
                    personalSalary += Integer.parseInt(employeeData[1])
                            * Integer.parseInt(employeeData[2]);
                }
            }
            stringBuilder.append("\n").append(name).append(" - ").append(personalSalary);
        }
        return stringBuilder.toString();
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder calculatedReport = new StringBuilder();
        calculatedReport.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        try {
            LocalDate from = LocalDate.parse(dateFrom, formatter);
            LocalDate to = LocalDate.parse(dateTo, formatter);
            for (String name : names) {
                int salary = 0;
                for (String info : data) {
                    String[] dataSplit = info.split(" ");
                    LocalDate currentDate = LocalDate.parse(dataSplit[0], formatter);
                    if (name.equals(dataSplit[1]) && currentDate.compareTo(to) <= 0
                            && currentDate.compareTo(from) >= 0) {
                        salary += Integer.parseInt(dataSplit[2]) * Integer.parseInt(dataSplit[3]);
                    }
                }
                calculatedReport.append(System.lineSeparator()).append(name).append(" - ")
                        .append(salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Wrong date format", e);
        }
        return calculatedReport.toString();
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final int dateOfPay = 10;
    private final int hoursWorked = 2;
    private final int salaryPerHour = 3;
    private final int dayToAdd = 1;
    private StringBuilder sb = new StringBuilder();
    private int salary = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateLast = LocalDate.parse(dateTo, formatter);
        String[] resultToReport = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (String dataArr : data) {
                if (dataArr.contains(names[i])) {
                    LocalDate currentDate = LocalDate
                               .parse(dataArr.substring(0, dateOfPay), formatter);
                    sb.delete(0, sb.length());
                    if (dateLast.plusDays(dayToAdd).isAfter(currentDate)
                            && dateStart.minusDays(dayToAdd).isBefore(currentDate)) {
                        String[] arr = dataArr.split(" ");
                        salary += Integer.parseInt(arr[hoursWorked])
                                * Integer.parseInt(arr[salaryPerHour]);
                        resultToReport[i] = sb.append(names[i])
                                .append(" - ").append(salary).toString();
                    } else {
                        resultToReport[i] = sb.append(names[i])
                                .append(" - ").append(salary).toString();
                    }
                }
            }
        }

        String report = "Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator()
                + resultToReport[0]
                + System.lineSeparator()
                + resultToReport[1]
                + System.lineSeparator()
                + resultToReport[2];

        return report;
    }
}

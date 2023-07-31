package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String[] report;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] resultReport = new String[names.length];
        report = new String[names.length];

        LocalDate dateStart = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        StringBuilder stringBuilderFull = new StringBuilder();
        StringBuilder stringBuilderEmpty = new StringBuilder();
        StringBuilder builder = new StringBuilder();

        stringBuilderFull.append("Report for period " + dateFrom + " - " + dateTo);
        stringBuilderEmpty.append("Report for period " + dateFrom + " - " + dateTo);

        for (int i = 0; i < names.length; i++) {
            builder.append(names[i]).append(" ").append("0");
            report[i] = builder.toString();
            builder.setLength(0);
            int resultSalary = 0;
            for (int j = 0; j < data.length; j++) {
                LocalDate currentDate = LocalDate.parse(data[j].split(" ")[0], DATE_TIME_FORMATTER);
                if (currentDate.compareTo(dateStart) > 0
                        && currentDate.compareTo(dateEnd) < 0
                        || currentDate.equals(dateStart)
                        || currentDate.equals(dateEnd)) {
                    if (data[j].contains(names[i])) {
                        String dataEmployee = data[j].substring(data[j].indexOf(names[i]));
                        String[] hourSalary = dataEmployee.split(" ");
                        int hour = Integer.parseInt(hourSalary[1]);
                        int salaryValue = Integer.parseInt(hourSalary[2]);
                        resultSalary = hour * salaryValue;
                        Integer salary = Integer.parseInt(report[i].split(" ")[1]);
                        resultSalary += salary;

                        builder.append(names[i]).append(" ").append(resultSalary);
                        resultReport[i] = builder.toString();
                        builder.setLength(0);

                        builder.append(names[i]).append(" ").append(resultSalary);
                        report[i] = builder.toString();
                        builder.setLength(0);
                    }
                }
            }
            stringBuilderFull.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(resultSalary);

            stringBuilderEmpty.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(0);
        }

        if (resultReport == null || resultReport.length == 0) {
            return stringBuilderEmpty.toString();
        }

        return stringBuilderFull.toString();
    }
}

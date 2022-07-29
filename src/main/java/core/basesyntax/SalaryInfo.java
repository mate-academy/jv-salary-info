package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final DateConverter DATE_CONVERTOR = new DateConverter();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate fromDate = DATE_CONVERTOR.convert(dateFrom);
        LocalDate toDate = DATE_CONVERTOR.convert(dateTo);

        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataElement : data) {
                String[] dataItem = dataElement.split(" ");
                final String employeeName = dataItem[1];
                final int hoursWorked = Integer.parseInt(dataItem[2]);
                final int ratePerHour = Integer.parseInt(dataItem[3]);
                LocalDate dateElement = DATE_CONVERTOR.convert(dataItem[0]);
                if (name.equals(employeeName)
                        && (dateElement.isAfter(fromDate)
                        && dateElement.isBefore(toDate)
                        || dateElement.equals(fromDate)
                        || dateElement.equals(toDate))) {
                    salary += hoursWorked * ratePerHour;
                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return report.toString();
    }
}

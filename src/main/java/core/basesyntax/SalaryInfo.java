package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final DateConvertor DATE_CONVERTOR = new DateConvertor();
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = DATE_CONVERTOR.convert(dateFrom);
        LocalDate toDate = DATE_CONVERTOR.convert(dateTo);
        StringBuilder salaryList = new StringBuilder();
        salaryList.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String elementOfData : data) {
                String[] dataItem = elementOfData.split(" ");
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
            salaryList.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return salaryList.toString();
    }
}

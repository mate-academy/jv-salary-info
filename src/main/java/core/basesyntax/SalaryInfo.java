package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final DateConvertor CONVERTOR = new DateConvertor();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = CONVERTOR.convert(dateFrom);
        LocalDate toDate = CONVERTOR.convert(dateTo);
        StringBuilder salaryList = new StringBuilder();
        salaryList.append("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String elementOfData : data) {
                String[] dataItem = elementOfData.split(" ");
                LocalDate dateElement = CONVERTOR.convert(dataItem[0]);
                if (name.equals(dataItem[1])
                        && (dateElement.isAfter(fromDate)
                        && dateElement.isBefore(toDate)
                        || dateElement.equals(fromDate)
                        || dateElement.equals(toDate))) {
                    salary += Integer.parseInt(dataItem[2]) * Integer.parseInt(dataItem[3]);
                }

            }
            salaryList.append(System.lineSeparator() + name + " - " + salary);
        }

        return salaryList.toString();
    }
}

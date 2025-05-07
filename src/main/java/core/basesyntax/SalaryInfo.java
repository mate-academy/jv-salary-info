package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        SalaryInfo salaryInfo = new SalaryInfo();
        String[] names = new String[] {"John", "Andrew", "Kate"};
        String[] data = new String[] {"26.04.2019 John 4 50", "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100", "22.04.2019 Kate 9 100", "25.06.2019 John 11 50",
                "26.04.2019 Andrew 3 150", "13.02.2019 John 7 100", "26.04.2019 Kate 9 100"};
        String dateFrom = "01.04.2019";
        String dateTo = "30.04.2019";
        System.out.println(salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo));
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocalDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateToLocalDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        String[] splittedData;

        String separator = System.lineSeparator();
        StringBuilder stringBuilderReport = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + separator);

        for (int i = 0; i < names.length; i++) {
            int sumSalaryForEmployee = 0;
            for (String dataElement: data) {
                splittedData = dataElement.split(" ");
                LocalDate splittedDate = LocalDate.parse(splittedData[0], DATE_TIME_FORMATTER);
                if (dateFromLocalDate.compareTo(splittedDate) <= 0
                        && dateToLocalDate.compareTo(splittedDate) >= 0
                        && splittedData[1].equals(names[i])) {
                    sumSalaryForEmployee += Integer.parseInt(splittedData[2])
                                    * Integer.parseInt(splittedData[3]);
                }
            }
            stringBuilderReport.append(names[i]).append(" - ").append(sumSalaryForEmployee)
                    .append(separator);
        }
        return stringBuilderReport.toString().trim();
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final static int DATE = 0;
    private final static int NAME = 1;
    private final static int NUMBER_OF_HOURS = 2;
    private final static int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            report.append(System.lineSeparator());
            report.append(name).append(" - ");
            int Salary = 0;
            for (String dates : data) {
                String[] arrayOfData = dates.split(" ");
                LocalDate firstDate = LocalDate.parse(dateFrom, formatter);
                LocalDate secondDate = LocalDate.parse(dateTo, formatter);
                LocalDate currentDate = LocalDate.parse(arrayOfData[DATE], formatter);
                if (name.equals(arrayOfData[NAME])
                        && ((currentDate.isAfter(firstDate)) || currentDate.isEqual(firstDate))
                        && ((currentDate.isBefore(secondDate)) || currentDate.isEqual(secondDate))){
                    Salary += Integer.valueOf(arrayOfData[NUMBER_OF_HOURS])
                            * Integer.valueOf(arrayOfData[INCOME_PER_HOUR]);
                }
            }
            report.append(Salary);
        }
        return report.toString();
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo
                + System.lineSeparator());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.UK);
        LocalDate startDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate endDate = LocalDate.parse(dateTo, dateTimeFormatter);

        for (String nameEmployye: names) {
            int salaryEmployye = 0;
            for (String dataInfo: data) {
                String[] dataDivide = dataInfo.split(" ");
                LocalDate parseDividedDate = LocalDate.parse(dataDivide[0], dateTimeFormatter);
                if (startDate.isBefore(parseDividedDate)
                        && !startDate.isAfter(endDate)
                        && !parseDividedDate.isAfter(endDate)
                        && dataDivide[1].equals(nameEmployye)) {

                    salaryEmployye += Integer.parseInt(dataDivide[2])
                            * Integer.parseInt(dataDivide[3]);
                }
            }
            salaryInfo.append(nameEmployye)
                    .append(" - ")
                    .append(salaryEmployye)
                    .append(System.lineSeparator());
        }
        return salaryInfo.toString().trim();
    }
}

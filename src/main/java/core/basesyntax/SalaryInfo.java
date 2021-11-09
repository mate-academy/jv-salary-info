package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private DateTimeFormatter dfStandart = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder output = new StringBuilder("Report for period ");
        output.append(dateFrom);
        output.append(" - ");
        output.append(dateTo);

        LocalDate startDate = LocalDate.parse(dateFrom, dfStandart);
        LocalDate endDate = LocalDate.parse(dateTo, dfStandart);

        for (String name : names) {
            output.append(System.lineSeparator());
            output.append(" - ");
            int tempSalary = 0;
            for (String dt : data) {
                String[] tempData = dt.split(" ");
                if (name.equals(tempData[1])) {
                    LocalDate currentDate = LocalDate.parse(tempData[0], dfStandart);
                    if (!currentDate.isAfter(endDate) && !currentDate.isBefore(startDate)) {
                        tempSalary += Integer.parseInt(tempData[2]) * Integer.parseInt(tempData[3]);
                    }
                }
            }
            output.append(tempSalary);
        }
        output.append(System.lineSeparator());
        return output.toString();
    }
}

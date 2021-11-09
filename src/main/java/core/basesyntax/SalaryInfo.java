package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws NumberFormatException, DateTimeException {

        StringBuilder output = new StringBuilder("Report for period ");
        output.append(dateFrom);
        output.append(" - ");
        output.append(dateTo);

        DateTimeFormatter dfStandart = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, dfStandart);
        LocalDate endDate = LocalDate.parse(dateTo, dfStandart);

        for (String name : names) {
            output.append(System.lineSeparator());
            StringBuilder tempLine = new StringBuilder(name);
            tempLine.append(" - ");
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
            tempLine.append(tempSalary);
            output.append(tempLine);
        }
        output.append("\n");
        return output.toString();
    }
}

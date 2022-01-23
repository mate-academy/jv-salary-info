package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateF = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateT = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder dataResult = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            dataResult.append(System.lineSeparator()).append(name + " - ");
            int salarySum = 0;
            for (String dataCount : data) {
                LocalDate currentDate = LocalDate.parse(dataCount.split(" ")[0], dateTimeFormatter);
                String currentName = dataCount.split(" ")[1];
                if (currentName.equals(name) && (dateF.isBefore(currentDate)
                        && dateT.isAfter(currentDate)
                        || dateF.isEqual(currentDate)
                        || dateT.isEqual(currentDate))) {
                    salarySum += Integer.parseInt(dataCount.split(" ")[3])
                        * Integer.parseInt(dataCount.split(" ")[2]);
                }
            }
            dataResult.append(salarySum);
        }
        return dataResult.toString();
    }
}

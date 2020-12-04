package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE);
        StringBuilder informationAboutSalary = new StringBuilder();
        informationAboutSalary.append("Report for period ").append(dateFrom)
                              .append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] delimiterData = data[j].split(" ");
                LocalDate workDate = LocalDate.parse(delimiterData[0], DATE);
                if (delimiterData[1].equals(names[i])
                        && (workDate.isBefore(localDateTo) || workDate.isEqual(localDateTo))
                        && (workDate.isAfter(localDateFrom) || workDate.isEqual(localDateFrom))) {
                    salary += (Integer.valueOf(delimiterData[2])
                            * Integer.valueOf(delimiterData[3]));
                }
            }
            informationAboutSalary.append("\n").append(names[i]).append(" - ").append(salary);
        }
        return informationAboutSalary.toString();
    }
}

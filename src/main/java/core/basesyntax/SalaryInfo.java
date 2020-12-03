package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE);
        StringBuilder informationAboutSalary = new StringBuilder();
        informationAboutSalary.append("Report for period "
                            + dateFrom
                            + " - "
                            + dateTo
                            + "\n");
        int[] salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] delimiterData = data[j].split(" ");
                LocalDate workDate = LocalDate.parse(delimiterData[0], DATE);
                if (delimiterData[1].equals(names[i])
                        && (workDate.isBefore(localDateTo) || workDate.isEqual(localDateTo))
                        && (workDate.isAfter(localDateFrom) || workDate.isEqual(localDateFrom))) {
                    salary[i] += (Integer.valueOf(delimiterData[2])
                                  * Integer.valueOf(delimiterData[3]));
                }
            }
            informationAboutSalary.append(names[i] + " - " + salary[i] + "\n");
        }

        return informationAboutSalary.toString().trim();

    }
}

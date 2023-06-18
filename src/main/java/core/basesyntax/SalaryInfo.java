package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(" - ");
            int salary = 0;
            for (String dataElement : data) {
                String[] dataPerDataElementArray = dataElement.split(" ");
                LocalDate localData = LocalDate.parse(dataPerDataElementArray[0], formatter);
                if (localData.isAfter(localDateFrom) && localData.isBefore(localDateTo)
                        ||
                        localData.isEqual(localDateFrom)
                        ||
                        localData.isEqual(localDateTo)) {
                    int income = Integer.parseInt(dataPerDataElementArray[2])
                            *
                            Integer.parseInt(dataPerDataElementArray[3]);
                    if (name.equals(dataPerDataElementArray[1])) {
                        salary += income;
                    }

                }

            }
            builder.append(salary);
        }
        return builder.toString();
    }
}

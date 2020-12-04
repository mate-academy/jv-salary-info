package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom).append(" - ").append(dateTo);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate workDateFrom = LocalDate.parse(dateFrom,formatter);
        LocalDate workDateTo = LocalDate.parse(dateTo,formatter);

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] personalData = data[j].split(" ");
                if (names[i].equals(personalData[1])) {
                    LocalDate workDate = LocalDate.parse(personalData[0], formatter);

                    if ((workDate.isAfter(workDateFrom) && workDate.isBefore(workDateTo))
                            || workDate.isEqual(workDateFrom) || workDate.isEqual(workDateTo)) {
                        salary += Integer.valueOf(personalData[2])
                                * Integer.valueOf(personalData[3]);
                    }
                }

            }
            stringBuilder.append("\n").append(names[i]).append(" - ").append(salary);

        }

        return stringBuilder.toString();
    }
}


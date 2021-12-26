package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] partsData = data[i].split(" ");

                LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
                LocalDate dateData = LocalDate.parse(partsData[0], formatter);
                LocalDate dateEnd = LocalDate.parse(dateTo, formatter);

                if (name.equals(partsData[1])) {
                    if ((dateStart.isBefore(dateData)) || (dateStart.isEqual(dateData))) {
                        if ((dateEnd.isAfter(dateData)) || (dateEnd.isEqual(dateData))) {
                            if (partsData[1].equals(name)) {
                                int hours = Integer.parseInt(partsData[2]);
                                int rate = Integer.parseInt(partsData[3]);
                                salary += hours * rate;
                            }
                        }
                    }
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + salaryInfo;
    }
}

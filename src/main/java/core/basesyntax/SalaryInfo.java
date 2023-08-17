package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        String[][] salary = new String[names.length][2];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateStart = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, dateTimeFormatter);

        for (int i = 0; i < names.length; i++) {
            salary[i][0] = names[i];
            salary[i][1] = "0";
        }

        for (String datum : data) {
            String[] temp = datum.split(" ");
            LocalDate dateTemp = LocalDate.parse(temp[0], dateTimeFormatter);

            if (!dateTemp.isBefore(dateStart) && !dateTemp.isAfter(dateEnd)) {
                for (int j = 0; j < salary.length; j++) {
                    if (temp[1].equals(salary[j][0])) {
                        salary[j][1] = String.valueOf(Integer.parseInt(salary[j][1])
                                + Integer.parseInt(temp[2])
                                * Integer.parseInt(temp[3]));
                    }
                }
            }
        }

        for (String[] finalSalary : salary) {
            result.append(finalSalary[0]).append(" - ").append(finalSalary[1]).append('\n');
        }

        return "Report for period " + dateFrom + " - " + dateTo + '\n' + result;
    }
}

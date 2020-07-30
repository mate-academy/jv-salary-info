package core.basesyntax;

import core.basesyntax.IllegalDateParametersException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_PAR = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        if (LocalDate.parse(dateFrom, DATE_PAR).isAfter(LocalDate.parse(dateTo, DATE_PAR))) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder resultInfo = new StringBuilder();
        resultInfo.append("Отчёт за период ").append(dateFrom).append(" - ").append(dateTo);
        for (int j = 0; j < names.length; j++) {
            int salary = 0;
            for (String oneElementOfData : data) {
                String[] row = oneElementOfData.split(" ");
                if (row[1].equals(names[j]) && LocalDate.parse(row[0], DATE_PAR)
                        .compareTo(LocalDate.parse(dateFrom, DATE_PAR)) >= 0
                        && LocalDate.parse(row[0], DATE_PAR)
                        .compareTo(LocalDate.parse(dateTo, DATE_PAR)) <= 0) {
                    salary += Integer.parseInt(row[2]) * Integer.parseInt(row[3]);

                }
            }
            resultInfo.append("\n").append(names[j]).append(" - ").append(salary);
        }
        return resultInfo.toString();
    }
}

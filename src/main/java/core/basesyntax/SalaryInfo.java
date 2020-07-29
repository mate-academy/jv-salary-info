package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        LocalDate startDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate stopDate = LocalDate.parse(dateTo, dateFormatter);

        if (startDate.isAfter(stopDate)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder response = new StringBuilder("Отчёт за период ");
        response.append(dateFrom).append(" - ").append(dateTo).append("\n");
        for (String name : names) {
            int salary = 0;
            for (String currentData : data) {
                String[] strData = currentData.split(" ");
                LocalDate currentDate = LocalDate.parse(strData[0], dateFormatter);
                if (name.equals(strData[1])
                        && (currentDate.isAfter(startDate) || currentDate.isEqual(startDate))
                        && (currentDate.isBefore(stopDate) || currentDate.isEqual(stopDate))) {
                    salary += Integer.parseInt(strData[2]) * Integer.parseInt(strData[3]);
                }
            }
            response.append(name).append(" - ").append(salary).append("\n");
        }
        return response.deleteCharAt(response.length() - 1).toString();
    }
}

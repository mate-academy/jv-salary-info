package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String[] data;
    private String dateFrom;
    private String dateTo;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        this.data = data;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        StringBuilder result = new StringBuilder();

        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(getSalarySumEmployee(name));
        }
        return result.toString();
    }

    private int getSalarySumEmployee(String name) {
        LocalDate dateFromFormat = getFormatData(dateFrom);
        LocalDate dateToFormat = getFormatData(dateTo);
        int salarySumEmployee = 0;

        for (String datas : data) {
            String[] splitData = datas.split(" ");
            LocalDate dateWorkEmployee = getFormatData(splitData[0]);
            String nameWorkEmployee = splitData[1];
            int salaryPerHours = Integer.parseInt(splitData[3]);
            int workHours = Integer.parseInt(splitData[2]);

            if (dateWorkEmployee.isAfter(dateFromFormat) && dateWorkEmployee.isBefore(dateToFormat)
                    || dateWorkEmployee.isEqual(dateFromFormat)
                    || dateWorkEmployee.isEqual(dateToFormat)) {
                if (name.equals(nameWorkEmployee)) {
                    salarySumEmployee += salaryPerHours * workHours;
                }
            }
        }
        return salarySumEmployee;
    }

    private LocalDate getFormatData(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}

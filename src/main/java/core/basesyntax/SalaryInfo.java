package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private String[] names;
    private String[] data;
    private String dateFrom;
    private String dateTo;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        this.names = names;
        this.data = data;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;

        return getDrawResultSalaries();
    }

    private String getDrawResultSalaries() {
        StringBuilder result = new StringBuilder();

        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            result.append(names[i]).append(" - ")
                    .append(getSalarySumEmployee()[i]);
            if (i != names.length - 1) {
                result.append(System.lineSeparator());
            }
        }
        return result.toString();
    }

    private int[] getSalarySumEmployee() {
        LocalDate dateFromFormat = getFormatData(dateFrom);
        LocalDate dateToFormat = getFormatData(dateTo);
        int[] salarySumEmployee = initializeArray(names);

        for (String datas : data) {
            String[] splitData = datas.split(" ");
            LocalDate dateWorkEmployee = getFormatData(splitData[0]);
            String nameWorkEmployee = splitData[1];
            int salaryPerHours = Integer.parseInt(splitData[3]);
            int workHours = Integer.parseInt(splitData[2]);

            if (dateWorkEmployee.isAfter(dateFromFormat) && dateWorkEmployee.isBefore(dateToFormat)
                    || dateWorkEmployee.isEqual(dateFromFormat)
                    || dateWorkEmployee.isEqual(dateToFormat)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(nameWorkEmployee)) {
                        salarySumEmployee[i] += salaryPerHours * workHours;
                    }
                }
            }
        }
        return salarySumEmployee;
    }

    private LocalDate getFormatData(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try {
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            System.out.println("Error parsing the date: " + e.getMessage());
            return null;
        }
    }

    private int[] initializeArray(String[] count) {
        int[] initializeArray = new int[count.length];
        for (int i = 0; i < count.length; i++) {
            initializeArray[i] = 0;
        }
        return initializeArray;
    }
}

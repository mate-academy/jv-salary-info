package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int NUMBER_IN_LINE_DATE = 10;
    private static final int NUMBER_IN_LINE_NAME = 11;
    private static final int NUMBER_IN_LINE_WORK_HOURS = 12;
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DATE_FROM = "01.04.2019";
    private static final String DATE_TO = "30.04.2019";
    private static final LocalDate DATE_FROM_WITH_FORMATTER
            = LocalDate.parse(DATE_FROM, DATE_TIME_FORMATTER);
    private static final LocalDate DATE_TO_WITH_FORMATTER
            = LocalDate.parse(DATE_TO, DATE_TIME_FORMATTER);

    public String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        String salaryInfo = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).toString();

        String loopLine = "";
        int salaryAmount = 0;

        for (int i = 0; i < names.length; i++) {
            salaryAmount = 0;
            loopLine = "";
            for (int j = 0; j < data.length; j++) {

                if (dataName(names[i],data[j]) && isDataDate(dataDate(data[j]))) {
                    salaryAmount =
                            salaryCalculator(salaryAmount, dataHoursSalary(data[j]),
                                    dataWorkHours(data[j], names[i].length()));
                }
            }
            loopLine = new StringBuilder()
                    .append("\n")
                    .append(names[i])
                    .append(" - ")
                    .append(salaryAmount)
                    .toString();
            salaryInfo = loopCalculation(salaryInfo,loopLine);
        }

        return salaryInfo;
    }

    public int salaryCalculator(int salaryExist, String hours, String salaryAmount) {
        return salaryExist + Integer.parseInt(hours) * Integer.parseInt(salaryAmount);
    }

    public String dataDate(String data) {
        return new StringBuilder(data).substring(0,NUMBER_IN_LINE_DATE);
    }

    public boolean isDataDate(String data) {
        return LocalDate.parse(data,DATE_TIME_FORMATTER)
                .isAfter(DATE_FROM_WITH_FORMATTER)
                && LocalDate.parse(data,DATE_TIME_FORMATTER)
                .isBefore(DATE_TO_WITH_FORMATTER);
    }

    public boolean dataName(String name, String data) {
        return new StringBuilder(data)
                .substring(NUMBER_IN_LINE_NAME,name.length()
                        + NUMBER_IN_LINE_NAME).equals(name);
    }

    public String dataWorkHours(String data, int nameSize) {
        return new StringBuilder(data).substring(nameSize
                + NUMBER_IN_LINE_WORK_HOURS,data.lastIndexOf(" "));
    }

    public String dataHoursSalary(String data) {
        return new StringBuilder(data)
                .substring(data.lastIndexOf(" ") + 1,data.length());
    }

    public String loopCalculation(String addTo, String loopData) {
        return new StringBuilder()
                .append(addTo)
                .append(loopData)
                .toString();
    }
}

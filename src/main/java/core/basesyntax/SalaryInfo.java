package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String HEAD_MESSAGE = "Report for period ";
    private static final String IOEXEPTION_MESSAGE = "We have IOExeption";
    private StringBuilder totalSalaryInfo = new StringBuilder();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        totalSalaryInfo.delete(0, totalSalaryInfo.length());
        totalSalaryInfo.append(HEAD_MESSAGE + dateFrom + " - " + dateTo);
        for (String name : names) {
            int usersSalary = 0;
            if (name == null || name.equals("")) {
                continue;
            }
            for (int i = 0; i < data.length; i++) {
                try {
                    String[] dataParse = data[i].split("\\s");
                    LocalDate dataParseDate =
                            LocalDate.parse(dataParse[0], DateTimeFormatter.ofPattern(DATE_FORMAT));
                    LocalDate dateFromDat =
                            LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern(DATE_FORMAT));
                    LocalDate dateToDate =
                            LocalDate.parse(dateTo, DateTimeFormatter.ofPattern(DATE_FORMAT));
                    if ((dataParseDate.isAfter(dateFromDat)
                            || dataParseDate.equals(dateFromDat))
                            && (dataParseDate.isBefore(dateToDate)
                            || dataParseDate.equals(dateToDate))
                            && name.equals(dataParse[1])) {
                        usersSalary += Integer.valueOf(dataParse[2])
                                * Integer.valueOf(dataParse[3]);
                    }
                } catch (Exception e) {
                    System.out.println(IOEXEPTION_MESSAGE);
                }
            }
            totalSalaryInfo.append(System.lineSeparator() + name + " - " + usersSalary);
        }
        return totalSalaryInfo.toString();
    }
}

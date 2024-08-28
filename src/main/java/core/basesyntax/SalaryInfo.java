package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[][] salaryByName = new String[names.length][2];
        for (int i = 0; i < names.length; i++) {
            salaryByName[i][0] = names[i];
            salaryByName[i][1] = "0";
        }
        String[][] splitedData = splitData(data);
        for (int i = 0; i < splitedData.length; i++) {
            if (dayCheck(splitedData[i][0], dateFrom, dateTo)) {
                for (int j = 0; j < names.length; j++) {
                    if (splitedData[i][1].equals(salaryByName[j][0])) {
                        try {
                            salaryByName[j][1] =
                                    String.valueOf((Integer.parseInt(salaryByName[j][1])
                                    + (Integer.parseInt(splitedData[i][2])
                                    * Integer.parseInt(splitedData[i][3]))));
                        } catch (NumberFormatException ex) {
                            System.out.println("Error parsing string to number: "
                                    + ex.getMessage());
                        }
                    }
                }
            }
        }
        return getRezultToString(salaryByName, dateFrom, dateTo);
    }

    private String getRezultToString(String[][] salaryByName, String dateFrom, String dateTo) {
        StringBuilder rezultMessage = new StringBuilder();
        rezultMessage.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < salaryByName.length; i++) {
            rezultMessage.append(salaryByName[i][0]).append(" - ")
                    .append(salaryByName[i][1]).append(System.lineSeparator());
        }
        return rezultMessage.toString();
    }

    private boolean dayCheck(String dateToCheck, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDay = null;
        LocalDate endDay = null;
        LocalDate checkDate = null;
        try {
            startDay = LocalDate.parse(dateFrom, formatter);
            endDay = LocalDate.parse(dateTo, formatter);
            checkDate = LocalDate.parse(dateToCheck, formatter);
        } catch (DateTimeParseException ex) {
            System.out.println("Error parsing date: " + ex.getMessage());
        }
        if ((checkDate.isAfter(startDay) && checkDate.isBefore(endDay))
                || (checkDate.isEqual(startDay) || checkDate.isEqual(endDay))) {
            return true;
        } else {
            return false;
        }
    }

    private String[][] splitData(String[] data) {
        String[][] splitedData = new String[data.length][4];
        for (int i = 0; i < data.length; i++) {
            splitedData[i] = data[i].split(" ");
        }
        return splitedData;
    }
}

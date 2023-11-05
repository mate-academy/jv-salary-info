package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateStart;
        LocalDate dateEnd;
        try {
            dateStart = LocalDate.parse(dateFrom, formatter);
            dateEnd = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            System.out.printf("%s is not parsable!%n", dateFrom);
            System.out.printf("%s is not parsable!%n", dateTo);
            throw e;
        }
        int[] totalSalaryPerWorker = new int[names.length];
        for (String dataLine : data) {
            String[] splitDataArray = dataLine.split(" ");
            LocalDate workDate;
            try {
                workDate = LocalDate.parse(splitDataArray[0], formatter);
            } catch (DateTimeParseException e) {
                System.out.printf("%s is not parsable!%n", splitDataArray[0]);
                throw e;
            }
            String workerName = splitDataArray[1];
            int workerTime = Integer.parseInt(splitDataArray[2]);
            int workerSalary = Integer.parseInt(splitDataArray[3]);
            if (!workDate.isBefore(dateStart) && !workDate.isAfter(dateEnd)) {
                for (int i = 0; i < names.length; i++) {
                    if (workerName.equals(names[i])) {
                        totalSalaryPerWorker[i] += workerTime * workerSalary;
                    }
                }
            }
        }
        StringBuilder totalReport = new StringBuilder();
        totalReport.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            totalReport
                    .append(names[i])
                    .append(" - ")
                    .append(totalSalaryPerWorker[i])
                    .append(System.lineSeparator());
        }
        return totalReport.toString();
    }
}

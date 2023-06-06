package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, DTF);
        LocalDate dateFinish = LocalDate.parse(dateTo, DTF);
        int salary = 0;
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            for (String dat : data) {
                String[] separateValue = dat.split(" ");
                LocalDate dateOfWork = LocalDate.parse(separateValue[0], DTF);
                String nameFromData = separateValue[1];
                int workingHour = Integer.parseInt(separateValue[2]);
                int income = Integer.parseInt(separateValue[3]);
                if ((dateOfWork.isAfter(dateStart) || dateOfWork.isEqual(dateStart))
                        && (dateOfWork.isBefore(dateFinish) || dateOfWork.isEqual(dateFinish))
                        && (nameFromData.equals(name))) {
                    salary += workingHour * income;
                }
            }
            builder.append(System.lineSeparator());
            builder.append(name).append(" - ").append(salary);
            salary = 0;
        }
        return builder.toString();
    }
}

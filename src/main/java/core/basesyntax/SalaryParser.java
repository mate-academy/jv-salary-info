package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryParser {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public SalaryParser() {
    }

    public WorkerSalary[] parseSalaryData(String[] data) {
        WorkerSalary[] arrayOfWorkers = new WorkerSalary[data.length];
        for (int i = 0; i < data.length; i++) {
            String[] recordArray = data[i].split(" ");
            LocalDate convertedDate = convertToLocalDate(recordArray[0]);
            String name = recordArray[1];
            int hours = Integer.parseInt(recordArray[2]);
            int rate = Integer.parseInt(recordArray[3]);
            arrayOfWorkers[i] = new WorkerSalary(convertedDate, name, hours, rate);
        }
        return arrayOfWorkers;
    }

    public LocalDate convertToLocalDate(String date) {
        return LocalDate.parse(date, formatter);
    }
}

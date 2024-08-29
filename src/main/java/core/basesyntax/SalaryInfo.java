package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private EmploeesSalaryDetails[] emploeesSalaryDetails;
    private EmploeesSalary[] emploeesSalaries;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        emploeesSalaryDetails = new EmploeesSalaryDetails[data.length];
        initEmploeesSalaryDetails(data);
        emploeesSalaries = new EmploeesSalary[names.length];
        initEmploeesSalary(names);
        LocalDate startDay = null;
        LocalDate endDay = null;
        try {
            startDay = LocalDate.parse(dateFrom, formatter);
            endDay = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
        salaryCalculator(startDay, endDay);
        return formatSalaryReport(dateFrom, dateTo);
    }

    private void salaryCalculator(LocalDate dateFrom, LocalDate dateTo) {
        for (int i = 0; i < emploeesSalaryDetails.length; i++) {
            if (isDateWithinRange(emploeesSalaryDetails[i].getWorkingDay(), dateFrom, dateTo)) {
                for (int j = 0; j < emploeesSalaries.length; j++) {
                    if (emploeesSalaries[j].getEmploeesName().equals(
                            emploeesSalaryDetails[i].getEmploeeName())) {
                        emploeesSalaries[j].setEarnedSalary(
                                  emploeesSalaries[j].getEarnedSalary()
                                + emploeesSalaryDetails[i].getSalaryPerHour()
                                * emploeesSalaryDetails[i].getHourCount());
                        break;
                    }
                }
            }
        }
    }

    private void initEmploeesSalary(String[] names) {
        for (int i = 0; i < names.length; i++) {
            emploeesSalaries[i] = new EmploeesSalary();
            emploeesSalaries[i].setEmploeesName(names[i]);
            emploeesSalaries[i].setEarnedSalary(0);
        }
    }

    private void initEmploeesSalaryDetails(String[] data) {
        String[] parsedData;
        for (int i = 0; i < data.length; i++) {
            emploeesSalaryDetails[i] = new EmploeesSalaryDetails();
            parsedData = data[i].split(" ");
            try {
                emploeesSalaryDetails[i].setWorkingDay(LocalDate.parse(parsedData[0], formatter));
                emploeesSalaryDetails[i].setEmploeeName(parsedData[1]);
                emploeesSalaryDetails[i].setHourCount(Integer.parseInt(parsedData[2]));
                emploeesSalaryDetails[i].setSalaryPerHour(Integer.parseInt(parsedData[3]));
            } catch (DateTimeParseException ex) {
                throw new DateTimeParseException("Error parsing date: " + parsedData[0],
                        parsedData[0], ex.getErrorIndex());
            } catch (NumberFormatException ex) {
                throw new NumberFormatException("Error parsing number: " + data[i]);
            }
        }
    }

    private boolean isDateWithinRange(LocalDate dateToCheck, LocalDate dateFrom, LocalDate dateTo) {
        if ((dateToCheck.isAfter(dateFrom) && dateToCheck.isBefore(dateTo))
                || (dateToCheck.isEqual(dateFrom) || dateToCheck.isEqual(dateTo))) {
            return true;
        } else {
            return false;
        }
    }

    private String formatSalaryReport(String dateFrom, String dateTo) {
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < emploeesSalaries.length; i++) {
            resultMessage.append(emploeesSalaries[i].getEmploeesName()).append(" - ")
                    .append(emploeesSalaries[i].getEarnedSalary());
            if (i < emploeesSalaries.length - 1) {
                resultMessage.append(System.lineSeparator());
            }
        }
        return resultMessage.toString();
    }
}

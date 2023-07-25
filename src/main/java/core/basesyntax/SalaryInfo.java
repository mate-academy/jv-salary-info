package core.basesyntax;

public class SalaryInfo {
    private static int salary;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] dateToSplit = dateTo.split("[.]");
        String[] dateFromSplit = dateFrom.split("[.]");
        int dateToKod = Integer.parseInt(dateToSplit[2] + dateToSplit[1] + dateToSplit[0]);
        int dateFromKod = Integer.parseInt(dateFromSplit[2] + dateFromSplit[1] + dateFromSplit[0]);
        StringBuilder reportSalary = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo);
        for (String name : names) {
            for (String informPerson : data) {
                String[] splitData = informPerson.split(" ");
                String[] splDate = splitData[0].split("[.]");
                int dateWorkKod = Integer.parseInt(splDate[2] + splDate[1] + splDate[0]);
                if (name.equals(splitData[1]) && dateToKod >= dateWorkKod
                        && dateWorkKod >= dateFromKod) {
                    salary = salary
                            + Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            reportSalary.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            salary = 0;
        }
        return reportSalary.toString();
    }
}

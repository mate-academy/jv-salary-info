package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateRangeValidator dateRangeValidator = new DateRangeValidator(dateFrom, dateTo);
        StringBuilder finalList = new StringBuilder();
        finalList.append("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String nameInList : data) {
                String [] splitData = nameInList.split(" ");
                if (name.equals(splitData[1]) && dateRangeValidator.isWithinRange(splitData[0])) {
                    salary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            finalList.append(System.lineSeparator() + name + " - " + salary);
        }
        return finalList.toString();
    }
}

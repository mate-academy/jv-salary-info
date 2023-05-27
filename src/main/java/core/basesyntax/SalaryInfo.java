package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int i = 0;
        int lengthArrayNames = names.length;
        int[] salary = new int[lengthArrayNames];
        DataRangeValidator dates = new DataRangeValidator();
        dates.dateRangeValidator(dateFrom, dateTo);

        for (String name : names) {
            for (String employeeData : data) {
                String[] employee = employeeData.split(" ");
                if (name.equals(employee[1])) {
                    if (dates.isWithinRange(employee[0])) {
                        salary[i] += Integer.parseInt(employee[2]) * Integer.parseInt(employee[3]);
                    }
                }
            }
            i++;
        }
        StringBuilder reportString = new StringBuilder("Report for period ");
        reportString.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        for (int j = 0; j < lengthArrayNames; j++) {
            reportString.append(names[j]).append(" - ").append(salary[j])
                    .append(System.lineSeparator());
        }

        reportString.deleteCharAt(reportString.length() - 1);
        return reportString.toString();
    }
}

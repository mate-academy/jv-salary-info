package core.basesyntax;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name: names) {
            salaryInfo.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(getEmployeeSalary(data, name, dateFrom, dateTo));
        }
        return salaryInfo.toString();
    }

    private String getEmployeeSalary(String[] data, String name, String dateFrom, String dateTo) {
        int result = 0;
        for (int i = 0; i < data.length; i++) {
            String[] oneData = data[i].split(" ");
            if (oneData[1].equals(name) && isDateCorrect(dateFrom, dateTo, oneData[0])) {
                result += Integer.valueOf(oneData[2]) * Integer.valueOf(oneData[3]);
            }
        }
        return Integer.toString(result);
    }

    private boolean isDateCorrect(String dateFrom, String dateTo, String actualDate) {
        return getDateValue(dateTo) >= getDateValue(actualDate)
                && getDateValue(actualDate) >= getDateValue(dateFrom);
    }

    private int getDateValue(String date) {
        return Integer.valueOf(date.split("\\.")[2]
                + date.split("\\.")[1]
                + date.split("\\.")[0]);
    }
}

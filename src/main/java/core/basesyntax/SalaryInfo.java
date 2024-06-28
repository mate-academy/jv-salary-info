package core.basesyntax;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");
        for (int i = 0; i < names.length; i++) {
            User user = new User(names[i]);
            for (int j = 0; j < data.length; j++) {
                DataSalary dataSalary = new Formatter().stringToDataSalary(data[j]);
                if (dataSalary.getName().equals(user.getName())
                        && dataSalary.getDate()
                        .isBefore(new Formatter().stringToLocalDate(dateTo).plusDays(1))
                        && dataSalary.getDate()
                        .isAfter(new Formatter().stringToLocalDate(dateFrom).minusDays(1))) {
                    user.addAmountSalary(
                            dataSalary.getHoursQuantity() * dataSalary.getSalaryPreHour());
                }
            }
            builder
                    .append(user.getName())
                    .append(" - ")
                    .append(user.getSalary());
            if (i != names.length - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}

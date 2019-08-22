package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    /**
     * Реализуйте метод getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * <p>
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     * <p>
     * names:
     * Сергей
     * Андрей
     * София
     * <p>
     * data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * <p>
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (parseDate(dateFrom).isAfter(parseDate(dateTo))) {
            return null;
        }
        StringBuilder resultText = new StringBuilder();
        resultText.append("Отчёт за период ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");
        int salaryCounter = 0;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i]) && checkDateRange(data[j], dateFrom, dateTo)) {
                    int workedHours = Integer.parseInt(data[j].split(" ")[2]);
                    int salaryPerHour = Integer.parseInt(data[j].split(" ")[3]);
                    salaryCounter += workedHours * salaryPerHour;
                }
            }
            resultText.append(names[i]).append(" - ").append(salaryCounter).append("\n");
            salaryCounter = 0;
        }
        return resultText.toString();
    }

    private static LocalDate parseDate(String date) {
        String[] splittedDate = date.split("\\.");
        int year = Integer.parseInt(splittedDate[2]);
        int month = Integer.parseInt(splittedDate[1]);
        int day = Integer.parseInt(splittedDate[0]);
        return LocalDate.of(year, month, day);
    }

    private static boolean checkDateRange(String row, String dateFrom, String dateTo) {
        LocalDate rowDate = parseDate(row.split(" ")[0]);
        LocalDate start = parseDate(dateFrom);
        LocalDate end = parseDate(dateTo);
        return (!rowDate.isBefore(start) && !rowDate.isAfter(end));
    }
}

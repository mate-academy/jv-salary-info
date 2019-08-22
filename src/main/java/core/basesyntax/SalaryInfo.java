package core.basesyntax;

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
     * </p>
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
     * </p>
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder allSalary = new StringBuilder();
        int salary1 = 0;
        int salary2 = 0;
        int salary3 = 0;
        int minData = Integer.parseInt(dateFrom.replaceAll("[^0-9]", ""));
        int maxData = Integer.parseInt(dateTo.replaceAll("[^0-9]", ""));

        if (minData > maxData) {
            return null;
        }

        for (String s: data) {
            String[] buffer = s.split(" ");
            if (Integer.parseInt(buffer[0].replaceAll("[^0-9]","")) >= minData
                    && Integer.parseInt(buffer[0].replaceAll("[^0-9]","")) <= maxData) {

                if (buffer[1].equals(names[0])) {
                    salary1 += (Integer.parseInt(buffer[buffer.length - 2]))
                            * (Integer.parseInt(buffer[buffer.length - 1]));
                }
                if (buffer[1].equals(names[1])) {
                    salary2 += (Integer.parseInt(buffer[buffer.length - 2]))
                            * (Integer.parseInt(buffer[buffer.length - 1]));
                }
                if (buffer[1].equals(names[2])) {
                    salary3 += (Integer.parseInt(buffer[buffer.length - 2]))
                            * (Integer.parseInt(buffer[buffer.length - 1]));
                }
            }
        }

        return allSalary.append("Отчёт за период " + dateFrom + " - " + dateTo + "\n")
                .append("Сергей - " + salary1 + "\n").append("Андрей - " + salary2 + "\n")
                .append("София - " + salary3 + "\n").toString();
    }
}

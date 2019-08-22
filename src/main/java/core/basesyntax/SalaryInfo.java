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
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     * names:
     * Сергей
     * Андрей
     * София
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
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Отчёт за период " + dateFrom
                + " - " + dateTo + "\n");
        String[] parsedDateFrom = dateFrom.split("\\D");
        String[] parsedDateTo = dateTo.split("\\D");

        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] parsedData = data[i].split("\\s");
                String[] parsedDate = parsedData[0].split("\\D");
                if (Integer.parseInt(parsedDateFrom[0]) >= Integer.parseInt(parsedDate[0])) {
                    return null;
                }
                if (Integer.parseInt(parsedDateFrom[0]) <= Integer.parseInt(parsedDate[0])
                        && Integer.parseInt(parsedDate[0]) <= Integer.parseInt(parsedDateTo[0])
                        && name.equals(parsedData[1])) {
                    salary += (Integer.parseInt(parsedData[2]) * Integer.parseInt(parsedData[3]));
                }
            }
            stringBuilder.append(name + " - " + salary + "\n");
        }

        return stringBuilder.toString();
    }
}

package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать класс-ошибку IllegalDateParametersException и сделать так, чтобы
     * метод getSalaryInfo выбрасывал IllegalDateParametersException,
     * если dateFrom > dateTo, с сообщнием "Wrong parameters"</p>
     *
     * <p>Пример ввода: date from = 01.04.2019 date to = 30.04.2019</p>
     *
     * <p>names:
     * Сергей
     * Андрей
     * София</p>
     *
     * <p>data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateFrom = LocalDate.parse(dateFrom,dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo,dateTimeFormatter);
        if (localDateTo.isBefore(localDateFrom)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        StringBuilder results =  new StringBuilder("Отчёт за период "
                + localDateFrom.format(dateTimeFormatter) + " - "
                + localDateTo.format(dateTimeFormatter) + "\n");
        for (int i = 0; i < names.length; i++) {
            Employee employee = new Employee(names[i]);
            for (int j = 0; j < data.length; j++) {
                String[] localData = data[j].split(" ");
                LocalDate currentDate = LocalDate.parse(localData[0],dateTimeFormatter);
                if (employee.getName().equals(localData[1])
                        && currentDate.isAfter(localDateFrom.minusDays(1))
                        && currentDate.isBefore(localDateTo.plusDays(1))) {
                    employee.setValue(Integer.valueOf(localData[2])
                            * Integer.valueOf(localData[3]));
                }
            }
            results = results.append(employee.getName() + " - " + employee.getValue() + "\n");
        }
        return results.toString();
    }
}


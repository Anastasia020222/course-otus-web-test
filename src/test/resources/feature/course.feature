#language: ru

Функционал: Главная страница

  Предыстория: Открытие браузера
    Дано Я открываю браузер "chrome"
    Пусть Открыта главная страница

  Сценарий: Поиск курса "Специализация Java-разработчик"
    Если Кликнуть по курсу "Специализация Java-разработчик"
    Тогда Откроется страница курса
    И На странице курса заголовок соответствует названию курса "Java-разработчик"


  Сценарий: Поиск курса старующего после указанной даты
    Тогда Найти курсы, которые стартуют после "30 января"


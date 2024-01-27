#language: ru

Функционал: Страница подготовительных курсов

  Предыстория: Открытие браузера
    Дано Я открываю браузер "chrome"

  Структура сценария: Поиск курса, у которого <price> стоимость
    Когда Открыта страница подготовительных курсов "/online"
    Тогда Найти курс, у которого <price> стоимость

    Примеры:
      | price |
      | "max" |
      | "min" |
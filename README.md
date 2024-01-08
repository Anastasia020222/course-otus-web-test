# Курс Java QA Engineer
- В проекте использовалась java 18+ и maven

# Запуск тестов
- Запуск браузера:  
Для запуска тестов в нужном браузере (Chrome, Firefox или Opera)
нужно в файле `local.properties` в поле `browser` прописать 
нужный браузер (chrome, firefox или opera).
По умолчанию стоит chrome.


# Запуск checkstyle
- Запустить проверку стилей командой  
`mvn checkstyle:checkstyle`

# Запуск Opera
Более новые версии selenium (выше 4.4) не поддерживают оперу. Поэтому в проекте
использовался selenium 4.4.
Для того, чтобы запустить оперу, нужно скачать драйвер оперы по адресу:
https://github.com/operasoftware/operachromiumdriver/releases и указать путь
 к драйверу в файле `WebDriverFactory` в методе `createOperaDriver()`
#Тестовое задание для стажера на позицию «Автотестирование Java»
Тесты запускаются командой 
>mvn clean test -DsuiteXMLFile=bolozovskiiTask.xml
исполненной в директории проекта.
Для построения тестового отчёта необходимо в директории
с проектом выполнить команду 
>mvn allure:serve
Файл config.json содержит имя пользователя, должность, должность для изменения и базовый URL.
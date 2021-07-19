Автотестирование REST API на Spring MVC + Boot
==============================================
15 ак. ч., 5 дней.

Архитектура приложения
----------------------
![structure](https://www.planttext.com/api/plantuml/svg/VLBBQiCm4BpxAvRad7n1y27ngJIGDgKNauCihnqHMHAocc9A_hsI7vBiq8AHnUpixkneAqqQX4fBOL2KoNI1JGf6DfIO92c98GLf4R8zhB-lp620ZcyJH6MZf41X87_5cj3k7LjpHdrd52yJoTPtxGf2Rwx9Jm8m84El7W28IfsLPg8-VXvy2KVeZo6nxGbZuYpLzV_0Hd5YS9OzOxqeSjBopahK_v3AWlvHQlmDObP4ASxi84mAg92OBuqhhssXJicM6Qcx2-nsL9POey0oJM7DMNqrjIQTpyiFzbMqvDo9j2-JJxvwnWZqmQYqZmSCDfOJJUqf4JPmeg4zwRcXPSPEHnq3w6GzBSN8V2JXN1oHNLbXBH18dRCdAVdi5MAblQ0bksSjGISgsuTkuoPjjoa4kvUHM7i41tkNaRdJRapxZTjk1VZzjgY_kq0auP7y0W00)
<details>
<summary>puml</summary>

```puml
@startuml
frame frontend
frontend -> tomcat

database DB #white
database MQ #white
component [LegacyRestService] #white

frame backend {
  frame tomcat {
    component [SpringMVC] #white
    
    frame spring {
      component [RestTemplate] #white
      component [JpaProvider] #white
      component [JdbcTemplate] #white
      
      frame "application feature" {
        component [Repository] <<codegened>> #lightgray
        [Controller] -> [Service]
        Service -> [Repository]
        Repository --> JpaProvider
        JpaProvider --> JdbcTemplate
        
        Service --> RestTemplate
        RestTemplate -> LegacyRestService
      }
    }
    
    component [DbConnectionPool] #white
    JdbcTemplate --> DbConnectionPool
    DbConnectionPool -> DB
    
    spring ..> Controller
    spring ..> Service
    spring ..> Repository
    spring ..> JpaProvider
  }
  
  tomcat -> SpringMVC
  SpringMVC -> Controller
}


@enduml
```
</details>

Тестирование Spring Core приложения
-----------------------------------
- [ ] Структура теста
- [ ] Именования
- [ ] Проверки
- [ ] Покрытие
- [ ] [Sprint Testing Framework](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html)

Тест-дублеры
------------
- [ ] Тест-дублеры: графы дублеров

Тестирование Spring Boot приложения
-----------------------------------
- [ ] Тестовые и production профили

Тестирование Spring Boot REST API
---------------------------------
- [ ] Структура автоматизированного теста на Spring MVC Test
- [ ] [Чем Spring Boot помогает в тестировании](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-testing)

Особенности тестовой конфигурации
---------------------------------
- [ ] Родительские и дочерние контексты
- [ ] [Кеширование контекста](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/testing.html#testcontext-ctx-management-caching)

Тест-дублирование окружения приложения: внешние сервисы
-------------------------------------------------------
- [ ] Тест-дублеры внешних web- и REST-сервисов
- [ ] Контейнеризация окружения при тестировании приложения

Тест-дублирование окружения приложения: СУБД
--------------------------------------------
- [ ] Фейки СУБД
- [ ] [Утилиты работы с JDBC](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/testing.html#integration-testing-support-jdbc)
- [ ] [Управление транзакциями](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/testing.html#testcontext-tx-annotation-demo)

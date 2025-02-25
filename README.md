Task Management System (API) — это простая система управления задачами, разработанная с использованием Java и Spring. Система позволяет пользователям создавать, редактировать, удалять и просматривать задачи, а также управлять ими с помощью аутентификации и авторизации через JWT токены.
> **Важно:** Некоторые функции находятся в разработке. Пожалуйста, следите за обновлениями.
## Функциональные возможности
- [x] Аутентификация и авторизация:
      - Поддержка аутентификации пользователей по email и паролю.
      - JWT токены для доступа к API.
      - Ролевая система: администраторы и пользователи.
- [x]  Основной функционал управления задачами:
      - Создание, редактирование, удаление и просмотр задач.
      - Каждая задача содержит заголовок, описание, статус, приоритет, комментарии, автора и исполнителя.
      - Администраторы могут управлять всеми задачами.
      - Пользователи могут управлять только своими задачами, если указаны как исполнители.
- [x] Фильтрация и пагинация:
      - API позволяет получать задачи конкретного автора или исполнителя с поддержкой фильтрации и пагинации.
- [ ] Обработка ошибок: ![In Development]
      - Корректная обработка ошибок с понятными сообщениями.
      - Валидация входящих данных.
- [ ] Документация: ![In Development]
      - API описан с помощью OpenAPI и Swagger.
      - Swagger UI для удобного взаимодействия с API.

Технологии:
Java 17+,
Spring Boot,
Spring Security,
Spring Data,
PostgreSQL,
Docker Compose,
Swagger                        

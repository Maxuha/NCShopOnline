# NCShopOnline
1. Выполнить docker build -t oracle/weblogic .
2. Выполнить docker-compose up
3. Выполнить docker exec -it [CONTAINER-ID-POSTRES] bash
4. Скопировать домен БД 
3. Перейти в http://localhost:7001/console
4. Создать data source: 
  1) Services -> Data Source -> New -> Generic Data Source
  2) JNDI Name - 'NCShopOnlineJNDIDB'
  3) Database Type - Other
  4) Next -> Next -> Next
  5) Database User Name - postgres
  6) Password - parolyaNet0
  7) Next
  8) Driver Class Name - org.postgresql.Driver
  9) URL - jdbc:postgresql://[ДОМЕН БД]:5432/ncshoponlinedb
  10) Проверить подлкючаеться ли - Test Configuration
  11) Next -> галочка AdminServer -> finish
5. Переходит по адресу - http://localhost:7001/index.html

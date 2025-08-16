# demo-spring-jpa

초간단 Spring Boot + Maven + JPA(H2) 샘플

## 실행
1) IntelliJ Community에서 `Open`으로 이 폴더 열기 → Trust Project
2) 우측 Maven 탭에서 `demo-spring-jpa` → `Plugins` → `spring-boot` → `spring-boot:run` 더블클릭
   또는 상단 실행 버튼으로 `DemoApplication` 실행

> Java 17 이상 필요합니다.

포트는 디폴트 8080 ..충돌시 
Application.properties
server.port=8089 여기를 기준으로 한다.

## 테스트
- 목록: `http://localhost:8080/posts`
- 추가: 
POWER SHELL에서 아래를 카피하여 붙여넣고 엔터.
```
'{"title":"fast","content":"start"}' | Set-Content -NoNewline body.json
curl.exe -v -X POST "http://localhost:8089/posts" -H "Content-Type: application/json" --data-binary "@body.json"
```

## H2 콘솔
- `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`)

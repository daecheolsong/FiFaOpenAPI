## FifaOnline Open API 사용해보기


### 🔨 어플리케이션 설정

---
## build.gradle

```groovy
plugins {
    id 'org.springframework.boot' version '2.7.3'
    id 'io.spring.dependency-management' version '1.0.13.RELEASE'
    id 'java'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation('org.springframework.boot:spring-boot-starter-thymeleaf')
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'

    implementation('org.springframework.boot:spring-boot-starter-data-jpa')
    runtimeOnly('com.h2database:h2')
    implementation('com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.5.6')
    implementation('org.springframework.boot:spring-boot-starter-batch')
}

tasks.named('test') {
    useJUnitPlatform()
}
```

## application.properties

```properties

spring.mvc.view.prefix=classpath:/templates/
spring.mvc.view.suffix=.html

spring.devtools.livereload.enabled=true
spring.thymeleaf.check-template-location=true


spring.datasource.url = jdbc:h2:tcp://localhost/~/fifa
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.hikari.data-source-properties.rewriteBatchedStatements :true

spring.jpa.hibernate.ddl-auto=create

# data jpa batch insert 
spring.jpa.properties.hibernate.jdbc.batch_size : 10000
spring.jpa.properties.hibernate.jdbc.order_inserts: true
spring.jpa.properties.hibernate.jdbc.order_updates: true

```


### 📑 진행상황

----

- 유저 닉네임으로 유저 정보 조회
  - `/api/user/nickname`
  
- 선수 고유 식별자 메타데이터 조회
  - `/api/players`
  
- 선수 고유 식별자 메타데이터 db에 저장
  - `/api/players`  조회 제거
  - ``` dto -> entity -> saveAll```
  
- 매치 종류, 시즌 아이디 메타데이터 db에 저장
  - `matchTypeDto, SeasonIdDto` - `entity` - `save`
  

- Spring 5.0 이상부터 restTemplate `Deprecated`
  - Spring webFlux 에서 제공하는 WebClient 사용권장
    - ApiClient.class API 요청`HttpClient` 모듈 `restTemplate` - `WebClient` 로 변경
  
  
- 선수 포지션 종류 조회
  - `/api/position`
  

- 등급 종류 조회
  - `/api/division`


- 유저 ID 로 MatchID 조회
  - `/api/user/matches/{accessId}/{matchtype}`


- MatchID로 MatchResult 조회
  - `/api/match/result/{matchid}`
    - `MatchResultDto`
    
    ```java 
    public class MatchResultDto {
    String matchId;
    String matchDate;
    String matchType;
    List<MatchInfoDto> matchInfo;
    }
    ```
    - `MatchInfoDto`
  
    ```java
    @Data
    public class MatchInfoDto {
    private String accessId;  
    private String nickname;
    private MatchDetailDto matchDetail;
    private ShootDto shoot;
    private List<ShootDetailDto> shootDetail;
    private PassDto pass;
    private DefenceDto defence;
    private List<PlayerDto> player;
    }
    ```
  

### ❗ 이슈 

---


- 선수 메타데이터 저장시 insert 할 데이터가 많아 저장하는데 시간이 너무 걸림
  - jpa 만을 이용하여 insert 할시 쿼리가 data 1개당 1나씩 날라가므로 성능저하
    - jpa batch insert 를 이용하여 성능 향상


- Dto 에 `null` 이 들어가는 현상
  - dto 변수명과 json 변수명이 동일해야함.
    - matchType -> matchtype
  

- WebClient 에서 uriBuilderFactory() -> baseUrl() 설정후 get 요청시 uri(String uri)를 쓰면 java.net.Exception 발생
    - 혹시나 해서 Webclient 코드를 찾아보니 `baseUrl()`은 `UriBuilderFactory` 와 함께 쓰면 baseUrl이 무시된다는 걸 찾을수 있었다. 
      ```
      Note: this method is mutually exclusive with uriBuilderFactory(UriBuilderFactory). If both are used, the baseUrl value provided here will be ignored.
      ```
```java
 @Bean
    public WebClient webClient() {
        // api key encoding 
         // ..
        return WebClient.builder()
                .uriBuilderFactory(factory) <- key 인코딩떄문에 사전에 uriBuilder 를 만들었다.
               // .baseUrl() <- baseUrl 의 값이 무시된다.
                .....
        
                .build();
    }

```
- MatchResultDto 를 받아오는데, JSON 형태가 복잡하여 JSON to Object 하는 과정이 복잡한줄 알았는데, JSON 형태에 맞게 Dto 를 잘설계하면 알아서 `httpMessageConveter` 변환해줌
  - Json Data
    ```
    { k:v 
      k:v
      ....
      matchInfo:[{
        k:v
        ...
        shootDetail:[{
          k:v
          ...
          }
          ...
          ]
        ]
        matchDetail:[{
          k:v
          ...
          }
          ...
        ]
      }
      ...
      ]
    }
    
    ```
    대충 이런형태.. 들여쓰기 참고하면서 `{}` 는 `Object` `[]` 는 `List` 로 판단하고 `MatchResultDto`를 만들어 실행해보았는데 잘 전환이 되었다.
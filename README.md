# 🎮 왓유게: What's your game?
>### 당신의 게임은 무엇인가요?  
>새로운 게임을 시작하고 싶지만, 어떤 게임을 해야 할지 모르는 사람들을 위한 게임 리뷰 공간 🎁  
>http://whatsyg.com

</br>

## 1. 제작 기간 & 참여 인원
- 2022.02.03 ~ 2022.04.06
- 개인 프로젝트

</br>

## 2. 사용 기술
#### `Front-end`
  - Thymeleaf
#### `Back-end`
  - Java 11
  - Spring Boot 2.6.3
  - Gradle
  - Spring Data JPA
  - MySQL 8.0
  - Lombok
  - Spring Security
  - OAuth2.0
#### `Dev-ops`
  - AWS (EC2, RDS, S3, CodeDeploy)
  - Travis CI

</br>

## 3. ERD 설계
![erd](https://user-images.githubusercontent.com/92259017/162871014-868072b6-0ce6-4b7b-b49e-8f78f72695ae.png)

</br>

## 4. 구현 기능
- 회원가입, 로그인, 소셜 로그인
- 리뷰 등록, 수정, 삭제, 추천
- 게임 찜하기

</br>

## 5. 핵심 기능
서비스의 핵심 기능은 리뷰 기능입니다.  
사용자는 이용 경험이 있는 게임에 리뷰를 등록, 수정, 삭제할 수 있고,  
다른 사용자의 리뷰를 추천할 수 있습니다.  

<details>
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">

### 5.1. 전체 흐름
프로젝트는 Model2 구조 기반의 MVC 패턴으로 개발하였으며,  
객체 지향적인 프로그래밍을 위해 서버 처리 과정을 Controller, Service, Repository로 분리하여 작업하였습니다.
  
![image](https://user-images.githubusercontent.com/92259017/156873527-466abdae-ae0d-4b6a-9207-7baa0a7976ad.png)
  
### 5.2. 사용자 요청
  ![image](https://user-images.githubusercontent.com/92259017/162882567-99cb2aa0-c5a6-462f-a1e1-d8cca77b59b6.png)
- **입력 체크** :pushpin: [코드 확인](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/resources/templates/game-details.html#L234)
  - 사용자가 작성한 리뷰를 등록하기 전에 별점과 내용이 입력되었는지 확인합니다.
  
- **Ajax 비동기 요청** :pushpin: [코드 확인](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/resources/templates/game-details.html#L244)
  - 사용자는 한 게임에 리뷰를 하나만 작성할 수 있으므로 리뷰 작성 여부를 확인하는 요청을 보내고,  
  성공적으로 처리되었을 경우 리뷰를 등록하는 POST 요청을 비동기로 날립니다.

### 5.3. Controller
![image](https://user-images.githubusercontent.com/92259017/162882621-0ef3add0-b694-4cf0-bcdd-5031faea99a6.png)
  
- **요청 처리, 결과 응답** :pushpin: [코드 확인](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/java/com/example/whatsyourgame/controller/ReviewController.java#L31)
  - Controller에서는 화면단에서 넘어온 요청을 받고, Service 계층에 로직 처리를 위임합니다.
  - Service 계층에서 넘어온 로직 처리 결과를 화면단에 응답해줍니다.

### 5.4. Service
![image](https://user-images.githubusercontent.com/92259017/162882650-f98b2c30-f2ef-490f-a643-02c273f1ed87.png)
  
- **리뷰 작성 여부 확인** :pushpin: [코드 확인](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/java/com/example/whatsyourgame/service/ReviewService.java#L36)
  - 요청 시 전달받은 game_id와 user_id를 조건으로 하여 Review를 조회하고,  
  기존 등록된 리뷰 여부에 따라 로직 처리 결과를 전달합니다.

- **리뷰 등록 처리** :pushpin: [코드 확인](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/java/com/example/whatsyourgame/service/ReviewService.java#L28)
  - 추천수, created_at, updated_at 등의 기본값을 설정해주고 리뷰를 등록합니다.

![image](https://user-images.githubusercontent.com/92259017/162882677-a130abb9-a863-4093-ae9f-a3a125dd5a6a.png)

- **리뷰 수정** :pushpin: [코드 확인](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/java/com/example/whatsyourgame/entity/Review.java#L49)
  - DB에 update 쿼리를 날리지 않고, Entity에 구현한 update 메서드를 호출하여 객체의 값을 변경합니다.

### 5.5. Repository
![image](https://user-images.githubusercontent.com/92259017/162882706-09dddce4-73ba-4d82-b5cc-e54acda5fbf7.png)
  
- **리뷰 저장** :pushpin: [코드 확인](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/java/com/example/whatsyourgame/service/ReviewService.java#L32)
  - 작성 여부가 확인된 리뷰는 DB에 저장합니다.
  - 저장된 리뷰는 다시 Repository - Service - Controller를 거쳐 화면단에 송출됩니다.

- 기본적인 CRUD 기능은 Spring Data JPA를 활용하여 처리합니다.:pushpin: [코드 확인](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/java/com/example/whatsyourgame/repository/ReviewRepository.java#L11)
  
</div>
</details>

</br>

## 6. 트러블 슈팅
<details>
<summary>SpringBootApplication 종료 시 발생하는 오류</summary>
<div markdown="1">

- 에러 메시지
  - Execution failed for task ':server-basic-test:BasicTestApplication.main()'.  
  \> Build cancelled while executing task ':server-basic-test:BasicTestApplication.main()'  
  \* Try:  
  Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.  
- `gradle` 대신 `IntelliJ`를 사용해서 빌드하고 실행하도록 설정을 변경하여 해결

</div>
</details>
  
<details>
<summary>dependencies 추가 시 compile 키워드 build 오류 문제</summary>
<div markdown="1">

- 에러 메시지
  - No candidates found for method call compile
- dependencies 추가 시 `compile`을 `implementation`으로 수정하여 해결
- `gradle 3.0`부터는 `compile`키워드가 `deprecated`됨

</div>
</details>
  
<details>
<summary>css, js 파일에 대한 권한이 허용되지 않아 적용이 안되는 문제</summary>
<div markdown="1">

- 정적 파일에 대한 security 권한을 허용하여 해결
  - `.antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**", "/sass/**").permitAll()`

</div>
</details>
  
<details>
<summary>security 구성 custom 시 configure(http) 호출 오류</summary>
<div markdown="1">
  
- 에러 메시지
  - java.lang.IllegalStateException: Can't configure anyRequest after itself
- WebSecurityConfigurerAdapter 클래스의 configure() 메서드를 오버라이딩하며 자동 생성된 `super.configure(http)` 메서드를 삭제하여 해결

</div>
</details>

<details>
<summary>BCryptPasswordEncoder 암호화된 패스워드 비교</summary>
<div markdown="1">

- 패스워드 인코더의 matches() 메서드 사용하여 해결
- `getPasswordEncoder().matches(getPasswordEncoder().encode(user.getPassword()), actUser.getPassword());`

</div>
</details>

<details>
<summary>세션 저장소를 DB로 변경할 때 발생하는 문제</summary>
<div markdown="1">

- DB에 spring session 관련 테이블 생성하여 해결
- [spring github의 session schema.sql 파일 참고](https://github.com/spring-projects/spring-session/blob/master/spring-session-jdbc/src/main/resources/org/springframework/session/jdbc/schema-mysql.sql) 
  
</div>
</details>

<details>
<summary>JPQL 쿼리 파라미터 name 지정 오류</summary>
<div markdown="1">

- 에러 메시지
  - org.springframework.dao.InvalidDataAccessApiUsageException: For queries with named parameters you need to use provide names for method parameters. Use @Param for query method parameters, or when on Java 8+ use the javac flag -parameters.;
- Preferences(Command + ,) > Build, Execution, Deployment > Compiler > Java Compiler 에서  
  `Additional command line parameters` 부분에 `-parameters` 추가하여 해결

</div>
</details>
  
<details>
<summary>FetchType 쿼리 실행시점 지정 문제</summary>
<div markdown="1">

- 즉시 로딩이 필요하지 않은 컬럼의 경우, 호출할 때 쿼리를 생성하도록 FetchType을 `LAZY` 타입으로 지정
  
</div>
</details>
  
</br>

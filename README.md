# ๐ฎ ์์ ๊ฒ: What's your game?
>### ๋น์ ์ ๊ฒ์์ ๋ฌด์์ธ๊ฐ์?  
>์๋ก์ด ๊ฒ์์ ์์ํ๊ณ  ์ถ์ง๋ง, ์ด๋ค ๊ฒ์์ ํด์ผ ํ ์ง ๋ชจ๋ฅด๋ ์ฌ๋๋ค์ ์ํ ๊ฒ์ ๋ฆฌ๋ทฐ ๊ณต๊ฐ ๐  
>http://whatsyg.com

</br>

## 1. ์ ์ ๊ธฐ๊ฐ & ์ฐธ์ฌ ์ธ์
- 2022.02.03 ~ 2022.04.06
- ๊ฐ์ธ ํ๋ก์ ํธ

</br>

## 2. ์ฌ์ฉ ๊ธฐ์ 
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

## 3. ERD ์ค๊ณ
![erd](https://user-images.githubusercontent.com/92259017/162871014-868072b6-0ce6-4b7b-b49e-8f78f72695ae.png)

</br>

## 4. ๊ตฌํ ๊ธฐ๋ฅ
- ํ์๊ฐ์, ๋ก๊ทธ์ธ, ์์ ๋ก๊ทธ์ธ
- ๋ฆฌ๋ทฐ ๋ฑ๋ก, ์์ , ์ญ์ , ์ถ์ฒ
- ๊ฒ์ ์ฐํ๊ธฐ

</br>

## 5. ํต์ฌ ๊ธฐ๋ฅ
์๋น์ค์ ํต์ฌ ๊ธฐ๋ฅ์ ๋ฆฌ๋ทฐ ๊ธฐ๋ฅ์๋๋ค.  
์ฌ์ฉ์๋ ์ด์ฉ ๊ฒฝํ์ด ์๋ ๊ฒ์์ ๋ฆฌ๋ทฐ๋ฅผ ๋ฑ๋ก, ์์ , ์ญ์ ํ  ์ ์๊ณ ,  
๋ค๋ฅธ ์ฌ์ฉ์์ ๋ฆฌ๋ทฐ๋ฅผ ์ถ์ฒํ  ์ ์์ต๋๋ค.  

<details>
<summary><b>ํต์ฌ ๊ธฐ๋ฅ ์ค๋ช ํผ์น๊ธฐ</b></summary>
<div markdown="1">

### 5.1. ์ ์ฒด ํ๋ฆ
ํ๋ก์ ํธ๋ Model2 ๊ตฌ์กฐ ๊ธฐ๋ฐ์ MVC ํจํด์ผ๋ก ๊ฐ๋ฐํ์์ผ๋ฉฐ,  
๊ฐ์ฒด ์งํฅ์ ์ธ ํ๋ก๊ทธ๋๋ฐ์ ์ํด ์๋ฒ ์ฒ๋ฆฌ ๊ณผ์ ์ Controller, Service, Repository๋ก ๋ถ๋ฆฌํ์ฌ ์์ํ์์ต๋๋ค.
  
![image](https://user-images.githubusercontent.com/92259017/156873527-466abdae-ae0d-4b6a-9207-7baa0a7976ad.png)
  
### 5.2. ์ฌ์ฉ์ ์์ฒญ
  ![image](https://user-images.githubusercontent.com/92259017/162882567-99cb2aa0-c5a6-462f-a1e1-d8cca77b59b6.png)
- **์๋ ฅ ์ฒดํฌ** :pushpin: [์ฝ๋ ํ์ธ](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/resources/templates/game-details.html#L234)
  - ์ฌ์ฉ์๊ฐ ์์ฑํ ๋ฆฌ๋ทฐ๋ฅผ ๋ฑ๋กํ๊ธฐ ์ ์ ๋ณ์ ๊ณผ ๋ด์ฉ์ด ์๋ ฅ๋์๋์ง ํ์ธํฉ๋๋ค.
  
- **Ajax ๋น๋๊ธฐ ์์ฒญ** :pushpin: [์ฝ๋ ํ์ธ](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/resources/templates/game-details.html#L244)
  - ์ฌ์ฉ์๋ ํ ๊ฒ์์ ๋ฆฌ๋ทฐ๋ฅผ ํ๋๋ง ์์ฑํ  ์ ์์ผ๋ฏ๋ก ๋ฆฌ๋ทฐ ์์ฑ ์ฌ๋ถ๋ฅผ ํ์ธํ๋ ์์ฒญ์ ๋ณด๋ด๊ณ ,  
  ์ฑ๊ณต์ ์ผ๋ก ์ฒ๋ฆฌ๋์์ ๊ฒฝ์ฐ ๋ฆฌ๋ทฐ๋ฅผ ๋ฑ๋กํ๋ POST ์์ฒญ์ ๋น๋๊ธฐ๋ก ๋ ๋ฆฝ๋๋ค.

### 5.3. Controller
![image](https://user-images.githubusercontent.com/92259017/162882621-0ef3add0-b694-4cf0-bcdd-5031faea99a6.png)
  
- **์์ฒญ ์ฒ๋ฆฌ, ๊ฒฐ๊ณผ ์๋ต** :pushpin: [์ฝ๋ ํ์ธ](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/java/com/example/whatsyourgame/controller/ReviewController.java#L31)
  - Controller์์๋ ํ๋ฉด๋จ์์ ๋์ด์จ ์์ฒญ์ ๋ฐ๊ณ , Service ๊ณ์ธต์ ๋ก์ง ์ฒ๋ฆฌ๋ฅผ ์์ํฉ๋๋ค.
  - Service ๊ณ์ธต์์ ๋์ด์จ ๋ก์ง ์ฒ๋ฆฌ ๊ฒฐ๊ณผ๋ฅผ ํ๋ฉด๋จ์ ์๋ตํด์ค๋๋ค.

### 5.4. Service
![image](https://user-images.githubusercontent.com/92259017/162882650-f98b2c30-f2ef-490f-a643-02c273f1ed87.png)
  
- **๋ฆฌ๋ทฐ ์์ฑ ์ฌ๋ถ ํ์ธ** :pushpin: [์ฝ๋ ํ์ธ](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/java/com/example/whatsyourgame/service/ReviewService.java#L36)
  - ์์ฒญ ์ ์ ๋ฌ๋ฐ์ game_id์ user_id๋ฅผ ์กฐ๊ฑด์ผ๋ก ํ์ฌ Review๋ฅผ ์กฐํํ๊ณ ,  
  ๊ธฐ์กด ๋ฑ๋ก๋ ๋ฆฌ๋ทฐ ์ฌ๋ถ์ ๋ฐ๋ผ ๋ก์ง ์ฒ๋ฆฌ ๊ฒฐ๊ณผ๋ฅผ ์ ๋ฌํฉ๋๋ค.

- **๋ฆฌ๋ทฐ ๋ฑ๋ก ์ฒ๋ฆฌ** :pushpin: [์ฝ๋ ํ์ธ](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/java/com/example/whatsyourgame/service/ReviewService.java#L28)
  - ์ถ์ฒ์, created_at, updated_at ๋ฑ์ ๊ธฐ๋ณธ๊ฐ์ ์ค์ ํด์ฃผ๊ณ  ๋ฆฌ๋ทฐ๋ฅผ ๋ฑ๋กํฉ๋๋ค.

![image](https://user-images.githubusercontent.com/92259017/162882677-a130abb9-a863-4093-ae9f-a3a125dd5a6a.png)

- **๋ฆฌ๋ทฐ ์์ ** :pushpin: [์ฝ๋ ํ์ธ](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/java/com/example/whatsyourgame/entity/Review.java#L49)
  - DB์ update ์ฟผ๋ฆฌ๋ฅผ ๋ ๋ฆฌ์ง ์๊ณ , Entity์ ๊ตฌํํ update ๋ฉ์๋๋ฅผ ํธ์ถํ์ฌ ๊ฐ์ฒด์ ๊ฐ์ ๋ณ๊ฒฝํฉ๋๋ค.

### 5.5. Repository
![image](https://user-images.githubusercontent.com/92259017/162882706-09dddce4-73ba-4d82-b5cc-e54acda5fbf7.png)
  
- **๋ฆฌ๋ทฐ ์ ์ฅ** :pushpin: [์ฝ๋ ํ์ธ](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/java/com/example/whatsyourgame/service/ReviewService.java#L32)
  - ์์ฑ ์ฌ๋ถ๊ฐ ํ์ธ๋ ๋ฆฌ๋ทฐ๋ DB์ ์ ์ฅํฉ๋๋ค.
  - ์ ์ฅ๋ ๋ฆฌ๋ทฐ๋ ๋ค์ Repository - Service - Controller๋ฅผ ๊ฑฐ์ณ ํ๋ฉด๋จ์ ์ก์ถ๋ฉ๋๋ค.

- ๊ธฐ๋ณธ์ ์ธ CRUD ๊ธฐ๋ฅ์ Spring Data JPA๋ฅผ ํ์ฉํ์ฌ ์ฒ๋ฆฌํฉ๋๋ค.:pushpin: [์ฝ๋ ํ์ธ](https://github.com/heewonim131/whats-your-game/blob/07407a6819c6e70f7ebc65736e6491b9c186c6a0/src/main/java/com/example/whatsyourgame/repository/ReviewRepository.java#L11)
  
</div>
</details>

</br>

## 6. ํธ๋ฌ๋ธ ์ํ
<details>
<summary>SpringBootApplication ์ข๋ฃ ์ ๋ฐ์ํ๋ ์ค๋ฅ</summary>
<div markdown="1">

- ์๋ฌ ๋ฉ์์ง
  - Execution failed for task ':server-basic-test:BasicTestApplication.main()'.  
  \> Build cancelled while executing task ':server-basic-test:BasicTestApplication.main()'  
  \* Try:  
  Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.  
- `gradle` ๋์  `IntelliJ`๋ฅผ ์ฌ์ฉํด์ ๋น๋ํ๊ณ  ์คํํ๋๋ก ์ค์ ์ ๋ณ๊ฒฝํ์ฌ ํด๊ฒฐ

</div>
</details>
  
<details>
<summary>dependencies ์ถ๊ฐ ์ compile ํค์๋ build ์ค๋ฅ ๋ฌธ์ </summary>
<div markdown="1">

- ์๋ฌ ๋ฉ์์ง
  - No candidates found for method call compile
- dependencies ์ถ๊ฐ ์ `compile`์ `implementation`์ผ๋ก ์์ ํ์ฌ ํด๊ฒฐ
- `gradle 3.0`๋ถํฐ๋ `compile`ํค์๋๊ฐ `deprecated`๋จ

</div>
</details>
  
<details>
<summary>css, js ํ์ผ์ ๋ํ ๊ถํ์ด ํ์ฉ๋์ง ์์ ์ ์ฉ์ด ์๋๋ ๋ฌธ์ </summary>
<div markdown="1">

- ์ ์  ํ์ผ์ ๋ํ security ๊ถํ์ ํ์ฉํ์ฌ ํด๊ฒฐ
  - `.antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**", "/sass/**").permitAll()`

</div>
</details>
  
<details>
<summary>security ๊ตฌ์ฑ custom ์ configure(http) ํธ์ถ ์ค๋ฅ</summary>
<div markdown="1">
  
- ์๋ฌ ๋ฉ์์ง
  - java.lang.IllegalStateException: Can't configure anyRequest after itself
- WebSecurityConfigurerAdapter ํด๋์ค์ configure() ๋ฉ์๋๋ฅผ ์ค๋ฒ๋ผ์ด๋ฉํ๋ฉฐ ์๋ ์์ฑ๋ `super.configure(http)` ๋ฉ์๋๋ฅผ ์ญ์ ํ์ฌ ํด๊ฒฐ

</div>
</details>

<details>
<summary>BCryptPasswordEncoder ์ํธํ๋ ํจ์ค์๋ ๋น๊ต</summary>
<div markdown="1">

- ํจ์ค์๋ ์ธ์ฝ๋์ matches() ๋ฉ์๋ ์ฌ์ฉํ์ฌ ํด๊ฒฐ
- `getPasswordEncoder().matches(getPasswordEncoder().encode(user.getPassword()), actUser.getPassword());`

</div>
</details>

<details>
<summary>์ธ์ ์ ์ฅ์๋ฅผ DB๋ก ๋ณ๊ฒฝํ  ๋ ๋ฐ์ํ๋ ๋ฌธ์ </summary>
<div markdown="1">

- DB์ spring session ๊ด๋ จ ํ์ด๋ธ ์์ฑํ์ฌ ํด๊ฒฐ
- [spring github์ session schema.sql ํ์ผ ์ฐธ๊ณ ](https://github.com/spring-projects/spring-session/blob/master/spring-session-jdbc/src/main/resources/org/springframework/session/jdbc/schema-mysql.sql) 
  
</div>
</details>

<details>
<summary>JPQL ์ฟผ๋ฆฌ ํ๋ผ๋ฏธํฐ name ์ง์  ์ค๋ฅ</summary>
<div markdown="1">

- ์๋ฌ ๋ฉ์์ง
  - org.springframework.dao.InvalidDataAccessApiUsageException: For queries with named parameters you need to use provide names for method parameters. Use @Param for query method parameters, or when on Java 8+ use the javac flag -parameters.;
- Preferences(Command + ,) > Build, Execution, Deployment > Compiler > Java Compiler ์์  
  `Additional command line parameters` ๋ถ๋ถ์ `-parameters` ์ถ๊ฐํ์ฌ ํด๊ฒฐ

</div>
</details>
  
<details>
<summary>FetchType ์ฟผ๋ฆฌ ์คํ์์  ์ง์  ๋ฌธ์ </summary>
<div markdown="1">

- ์ฆ์ ๋ก๋ฉ์ด ํ์ํ์ง ์์ ์ปฌ๋ผ์ ๊ฒฝ์ฐ, ํธ์ถํ  ๋ ์ฟผ๋ฆฌ๋ฅผ ์์ฑํ๋๋ก FetchType์ `LAZY` ํ์์ผ๋ก ์ง์ 
  
</div>
</details>
  
</br>

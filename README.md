# Springboot 게시판
>웹 포트폴리오의 기본이라고 하는 게시판 프로젝트 입니다.

![image](https://user-images.githubusercontent.com/103357002/195012500-62b6200a-b088-445e-95e9-b56b2a1d511a.png)




## 목차
___




* [프로젝트 소개](#프로젝트-소개)
* [프로젝트 기능](#프로젝트-기능)
* [사용기술](#사용기술)
* [실행화면](#실행화면)
* [DB 설계](#DB-설계)
* [프로젝트 보완사항](#프로젝트-보완사항)
* [후기](#후기)


## 프로젝트 소개

개시판 프로젝트를 시작하게 된 이유는 단순히 취업하려면 게시판프로젝트에서 CRUD정도만 할 줄 안다는 말을 들어서 시작하게 되었습니다.
SpringBoot에 대해 아는 것 하나 없이 순전히 독학으로 만들어낸 개인 프로젝트 입니다.


## 프로젝트 기능

* 게시판 - DRUD,조회수,페이징,검색기능
* 사용자 - Security 회원가입 및 로그인,회원정보 수정,회원가입시 중복검사
* 댓글 - CRUD기능


## 사용기술

* 백엔드
  * 주요 프레임워크 / 라이브러리
    * Java 11
    * SpringBoot 2.6.7
    * JPA
    * Spring Security
    
  * Build Tool
    * Gradle 7.5

  * DataBase
    * MySQL 8.0.30
    
* 프론트엔드
  * Html/Css
  * JavaScript
  * Mustache
  * Bootstrap 5.1.3



## DB 설계

**1. 게시글**
|제목|데이터 타입|조건|설명|
|------|---|---|---|
|id|Long|PK|고유번호|
|title|varchar|NOT NULL|제목|
|content|varchar|NOT NULL|내용|
|nickName|varchar|NOT NULL|닉네임|
|timestamp|varchar|NOT NULL|생성일|
|view|int|NOT NULL|조회수|


**2. 사용자**
|제목|데이터 타입|조건|설명|
|------|---|---|---|
|Id|Long|PK|고유번호|
|username|varchar|NOT NULL / UNIQUE|아이디|
|nickname|varchar|NOT NULL / UNIQUE|닉네임|
|password|varchar|NOT NULL|비밀번호|
|email|varchar|NOT NULL / UNIQUE|이메일|
|create_date|varchar|NOT NULL|생성시간|
|modified_date|varchar|NOT NULL|수정시간|


**3. 댓글**
|제목|데이터 타입|조건|설명|
|------|---|---|---|
|Id|Long|PK|고유번호|
|comment|varchar|NOT NULL|내용|
|createdDate|varchar|NOT NULL|생성시간|
|modifiedDate|varchar|NOT NULL|수정시간|
|article|varchar|NOT NULL / UNIQUE|게시글 번호|
|user|varchar|NOT NULL|작성자 번호|
  
  
## 실행화면

### **게시판**
**1. 게시글 전체 목록**
![image](https://user-images.githubusercontent.com/103357002/195012956-4f8791e6-5c5c-4d83-b9aa-c28544c22251.png)
___

**2. 게시글 등록**
![image](https://user-images.githubusercontent.com/103357002/195013065-6b1ac999-8f81-419c-8fc1-05c525a7fda3.png)
___

**3. 게시글 조회**
![image](https://user-images.githubusercontent.com/103357002/195013145-71955ea2-1a5f-4190-bc2f-f03afa917b5e.png)
___

**3. 게시글 수정**


### **사용자**
**1. 회원가입 화면**
![image](https://user-images.githubusercontent.com/103357002/195012594-b71f6404-c979-4047-aa9b-30b415ef54bd.png)
![image](https://user-images.githubusercontent.com/103357002/195012634-57ae393e-3d3d-4348-801d-801de3379ead.png)

___

**2. 로그인 화면**
![image](https://user-images.githubusercontent.com/103357002/195012734-51b704bc-35bf-480f-9b20-f12f5e801387.png)
![image](https://user-images.githubusercontent.com/103357002/195012775-dcc282f6-449f-4cda-937a-ae07d9f3ae6a.png)


### **댓글**






## 프로젝트 보완사항
처음에 "분석 및 설계" 체계적으로 진행되지 않아 중간에 수정사항이 생길때마다 여러개의 코드를 수정해야되는 경우가 많았다
또한 그에 관해 개인적으로 코드가 굉장히 수정할 점이 많은 것 같다. 특히 똑같은 내용의 코드가 중복되는 경우가 있다
ui도 굉장히 허접하다 특히 header부분에 로그인을 하였을 경우 순서가 불편하다
생각만 해놓고 구현 못한 기능또한 많다 예를 들어 아이디,비밀번호 찾기와 OAuth2 구글,네이버 로그인이 있다
DB를 설계하는데 join을 사용해야 되는 구간에 사용하지 못하였다

## 후기
혼자 독학하면 진행한 프로젝트이므로 아쉬운 부분도 많으며 개선해야할 점도 많다.
시작하기 전에는 저는 게시판이라는 것을 굉장히 얕보고 있었습니다.
하지만 프로젝트를 진행하게 되면서 사람들이 왜 게시판을 한번 만들어봐라 라고 하였는지 알게 되었습니다.
아직 타인에게 당당하게 보여줄만한 코드는 아니라고 생각하지만 현제의 제 자신이 만들 수 있는 절대 대충하지 않았다고 생각합니다.

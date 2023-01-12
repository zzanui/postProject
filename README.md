# Springboot 게시판
>웹 포트폴리오의 기본이라고 하는 게시판 프로젝트 입니다.

![image](https://user-images.githubusercontent.com/103357002/195012500-62b6200a-b088-445e-95e9-b56b2a1d511a.png)




## 목차
___




* [프로젝트 소개](#프로젝트-소개)
* [프로젝트 기능](#프로젝트-기능)
* [사용기술](#사용기술)
* [DB설계](#DB설계)
* [실행화면](#실행화면)
* [프로젝트 보완사항](#프로젝트-보완사항)
* [후기](#후기)


## 프로젝트 소개
첫면접을 보러간 회사에 지금생각하면 아무것도 모르는상태로 면접을 보러가 SPRING,JPA,API에 대해 알게 되어 일단 관련된 프로젝트를 한번 만들어 보자라는 생각을 가지고
먼저 취업한 동기들에게 개시판 프로젝트를 한번 해보는건 어떻겠냐고 하는 말을 듣고 시작하게 되었습니다.
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


## DB설계

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
  <details>
<summary>
게시판
</summary>


**1. 게시글 전체 목록 화면**
![image](https://user-images.githubusercontent.com/103357002/195012956-4f8791e6-5c5c-4d83-b9aa-c28544c22251.png)
  모든 게시글을 조회 가능합니다. 
___

**2. 게시글 등록 화면**
![image](https://user-images.githubusercontent.com/103357002/195013065-6b1ac999-8f81-419c-8fc1-05c525a7fda3.png)
  로그인 한 사용자만 게시글을 작성할 수 있습니다. 또한 작성 후 redirect합니다.
___

**3. 게시글 조회 화면**
![image](https://user-images.githubusercontent.com/103357002/195013145-71955ea2-1a5f-4190-bc2f-f03afa917b5e.png)
  본인이 작성한 글만 수정 및 삭제가 가능합니다.
___

**4. 게시글 수정 화면**
![image](https://user-images.githubusercontent.com/103357002/195135264-bdc80b6b-d324-4322-82a3-294f43c5572d.png)
  
  제목과 내용만 수정할 수 있게 하고 수정 후 redirect 합니다.
___

**5. 게시글 검색 화면**
![image](https://user-images.githubusercontent.com/103357002/195135609-05e3904b-eb80-4464-af98-62b02b083535.png)
![image](https://user-images.githubusercontent.com/103357002/195135684-c6a537ef-cf91-4003-8d73-7a725a4e9041.png)
  
  삭제시 작성자 본인만 삭제가 가능하면 삭제 후 redirect합니다.
___

**6. 페이징 화면**   
![image](https://user-images.githubusercontent.com/103357002/195136016-0bee0253-9de5-422a-95de-a8a957f1c32d.png)
![image](https://user-images.githubusercontent.com/103357002/195136068-41e738e9-714e-44ec-8bcc-313830fa43bd.png)
  
  게시글의 갯수가 10개 이상일 경우 다음페이지로 넘어가서 조회가 가능합니다.

</details>

  <details>
<summary>
사용자
</summary>

**1. 회원가입 화면**  
![image](https://user-images.githubusercontent.com/103357002/195012594-b71f6404-c979-4047-aa9b-30b415ef54bd.png)
![image](https://user-images.githubusercontent.com/103357002/195012634-57ae393e-3d3d-4348-801d-801de3379ead.png)
  회원가입 시 중복확인을 진행하며 완료시 회원 정보를 저장하고 로그인 화면으로 이동합니다.

___

**2. 로그인 화면**  
![image](https://user-images.githubusercontent.com/103357002/195012734-51b704bc-35bf-480f-9b20-f12f5e801387.png)
![image](https://user-images.githubusercontent.com/103357002/195012775-dcc282f6-449f-4cda-937a-ae07d9f3ae6a.png)
  
  로그인 시도시 로그인이 실패하였을경우 다음과 같이 알림이 나옵니다.
</details>

  <details>
<summary>
댓글
</summary>

**1. 미로그인 댓글화면**  
![image](https://user-images.githubusercontent.com/103357002/195136366-1ec78693-2b38-4c08-9361-0b70f61e80d2.png)
  
  미로그인상태일 경우 로그인을 하여야 댓글 작성이 가능합니다.
___

**2. 로그인 댓글화면**  
![image](https://user-images.githubusercontent.com/103357002/195136552-4b2135b3-3f49-4db7-bfdc-1e8dd946e5f0.png)
___

**3. 댓글수정 화면**  
![image](https://user-images.githubusercontent.com/103357002/195136649-92d42a7d-31c6-4ccb-b014-215147ae84c2.png)  
![image](https://user-images.githubusercontent.com/103357002/195136824-6f432e2c-2d2c-46b6-af01-8e3e751887e0.png)
  댓글을 작성한 본인만 수정이 가능합니다
___

**4. 댓글삭제 화면**  
![image](https://user-images.githubusercontent.com/103357002/195137044-25062121-314d-4b5f-a1ee-24f8a1a73ab5.png)
  댓글을 작성한 본인만 삭제가 가능합니다

</details>

## 프로젝트 보완사항
처음에 "분석 및 설계" 체계적으로 진행되지 않아 중간에 수정사항이 생길때마다 여러개의 코드를 수정해야되는 경우가 많았습니다.
또한 그에 관해 개인적으로 코드가 굉장히 수정할 점이 많은 것 같다. 특히 똑같은 내용의 코드가 중복되는 경우가 있다
ui도 굉장히 허접하다 특히 header부분에 로그인을 하였을 경우 순서가 불편하다
생각만 해놓고 구현 못한 기능또한 많다 예를 들어 아이디,비밀번호 찾기와 OAuth2 구글,네이버 로그인이 있다
또한 DB를 설계하는데 join을 사용해야 되는 구간에 사용하지 못하였다

## 후기
혼자 독학하면 진행한 프로젝트이므로 아쉬운 부분도 많으며 개선해야할 많다고 느끼게 되었는데
시작하기 전에는 저는 게시판이라는 것을 굉장히 얕보고 있었습니다.
하지만 프로젝트를 진행하게 되면서 사람들이 왜 게시판을 한번 만들어봐라 라고 하였는지 알게 되었습니다.
한가지 에러에서 수일을 소모하여 해결을 한다던지 모르는 지식이 필요하여 새롭게 공부하여 적용해나가던지 하면서 하나하나 
나에게 맞게 코드를 수정해가며 작성을 하였지만 
아직 타인에게 당당하게 보여줄만한 코드는 아니라고 생각하지만 이것이 내 현재 수준임을 자각하고
이번 프로젝트로 어떤 부분이 부족한지 알게 되는 계기가 되었다고 생각하며
더욱 발전해가야겠습니다

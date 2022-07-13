# 둥글개 둥글개

> **KH 정보 교육원**에서 진행한 세미 프로젝트입니다. 
**둥글개 둥글개**는 반려동물 150만 시대로 펫케어 시장이 고부가가치 산업으로 떠오르면서 
애견 유치원 수요도 증가하는 추세로 애견 유치원에서 제공하는 서비스와 여러가지 정보를 견주들이 온라인상에서 편리하게 확인할 수 있고, 
웹 사이트를 통해 견주들이 원하는 서비스를 취향에 맞게 신청할 수 있으며 선생님과 견주들과의 원활한 소통을 지원하는 **애견 유치원 웹사이트** 입니다.
> 

## ✍️ **목차**

1. 개발 환경
2. 프로젝트 설계 주안점
3. 구현 기능
4. 팀원 및 주 담당 기능
5. ERD
6. 프로젝트 화면

## ⚙️ **개발 환경**

- **Language**
    - JAVA 8
    - JavaScript
- **Database**
    - Oracle 11g
- **Web Server & WAS**
    - Apache-Tomcat 9.0.60
- **Library & API**
    - jQuery 3.6.0
    - Gson, Json
    - mail.jar
    - Google Auth
    - Google SMTP
    - i'mport api
    - Smart Editor api
    - CK Editor api
    - KaKao Maps api
- **Skill Stack**
    - JSP
    - Servlet
    - Ajax
- **Collaboration Tools**
    - GitHub
    - Notion
    - Bootstrap

## ✅ **프로젝트 설계 주안점**

- 다양한 공공 API를 이용 
- 사용자 편의성 증대를 위한 UI 설계 
- 기본 C,R,U,D를 활용하여 완성도 높은 프로그램 개발
- 특정 서비스에 대한 회원/비회원의 차별성 
- 유사 프로그램의 세밀한 분석을 통한 차별화 기능에 중점 
- 서비스 간 원활한 호환

## 🛠️ **구현 기능**

**1. 회원 가입**

- 구글 SMTP를 이용한 이메일 인증 서비스
- Ajax를 이용한 아이디 중복확인 기능

**2. 로그인**

- 구글 API를 이용한 구글 로그인 서비스
- 사용자의 선택에 따른 아이디 저장 기능

**3. 강아지 입학 신청**

- Ajax를 이용한 입학가능여부조회 기능 및 자동 반 배정 시스템
- 입학정원에 따른 대기 여부 확인

**4. 결제**

- i'mport 결제 API를 통한 뼈다귀 포인트 결제

**5. 회원 정보 관리**

- 마이 페이지를 통한 회원의 정보 수정 및 회원 탈퇴

**6. QnA 게시판**

- CKEditor를 이용한 회원의 문의사항 등록, 수정, 삭제 기능
- 회원의 선택에 따른 비밀글 설정
- Ajax를 이용한 관리자 댓글 관리
- 댓글 여부에 따른 답변/미답변 표시 기능

**7. 공지사항**

- 관리자의 공징사항 등록, 수정, 삭제 기능
- 제목/작성자별 특정 키워드 검색 시스템

**8. 온라인 알림장 게시판**

- SmartEditor를 이용한 관리자의 게시글 등록, 수정, 삭제 기능
- 썸네일 이미지 설정 및 컨텐트 내 이미지 삽입 기능
- Ajax를 통한 댓글 관리
- 실시간 조회수 및 댓글 개수 표시 기능

**9. 통학버스**

- 카카오 지도 api를 통한 승차장 상세 확인
- 잔여석에 따른 통학버스 예약 신청 시스템
- 회원 및 관리자의 통학 버스 예약관리 기능

**10. 간식 관리**

- 회원의 보유 포인트를 이용한 간식 구매 서비스
- 관리자의 간식 등록, 수정, 삭제 기능

**11. 관리자 마이 페이지**

- 회원의 간식 구매내역 및 통학버스 이용내역 확인 기능

## 👥 **팀원 및 주 담당 기능**
- 👧🏻 김지수(팀장) : 회원 관리와 입학 신청 및 결제
- 👧🏻 권영아 : QnA 게시판 및 공지사항 관리
- 👦🏻 김희헌 : 알림장 게시판 및 통학 버스 관리
- 👦🏻 임현빈 : 관리자 페이지 및 간식 관리

## 🏗️ **ERD**
![SemiProject (1)](https://user-images.githubusercontent.com/81502408/178646055-201be986-6a29-4841-88bf-a370e12b2925.png)

## 🖥️ **서비스 화면**

### [메인페이지]
![홈](https://user-images.githubusercontent.com/81502408/178648495-72357770-4692-4e0c-a4fb-2cdb1c5a3851.JPG)
### [회원가입]
![회원가입](https://user-images.githubusercontent.com/81502408/178647254-379556de-2492-4af4-a86a-b05c86c8bbd3.JPG)
### [로그인]
![로그인](https://user-images.githubusercontent.com/81502408/178647266-432995e8-6f59-4eb0-9580-7a4053e72637.JPG)
### [아이디/비밀번호 찾기]
![아이디비밀번호찾기](https://user-images.githubusercontent.com/81502408/178647270-cf3f5f74-1ac7-4fcf-bca9-0239a26784b8.JPG)
### [회원 마이페이지]
![마이페이지](https://user-images.githubusercontent.com/81502408/178647285-62192c93-6c52-4d32-8ad4-7ed456705994.JPG)
### [강아지 입학신청]
![입학신청](https://user-images.githubusercontent.com/81502408/178647291-1ca0df6f-211f-4070-ba8d-620cfbb04b43.JPG)
### [결제(뼈다귀 충전)]
![결제](https://user-images.githubusercontent.com/81502408/178647300-b57b289d-34c2-4563-8acb-5da3c4defaad.JPG)
### [공지사항 - 목록조회]
![공지사항목록](https://user-images.githubusercontent.com/81502408/178647324-32fdd32d-45be-4c7e-b6d4-486c0df1ebf7.JPG)
### [공지사항 - 작성]
![공지사항작성](https://user-images.githubusercontent.com/81502408/178647351-1c992809-3a2c-4217-b9f2-36dc0824669e.JPG)
### [공지사항 - 상세조회]
![공지사항상세](https://user-images.githubusercontent.com/81502408/178647354-3accbe85-374a-4a2d-b882-d74cbb9b8335.JPG)
### [QNA - 목록조회]
![qna](https://user-images.githubusercontent.com/81502408/178647367-b5b6f2c4-f2e4-4f05-82c7-1e098ea1152c.JPG)
### [QNA - 작성]
![qna작성](https://user-images.githubusercontent.com/81502408/178647370-c68d2520-6ff2-43bb-a14d-9f5b0d7bf38d.JPG)
### [QNA - 상세조회]
![qna상세](https://user-images.githubusercontent.com/81502408/178647373-d401bc6f-f9ad-4493-b89e-77e295d9d059.JPG)
### [알림장 - 목록조회]
![알림장](https://user-images.githubusercontent.com/81502408/178647459-e675fe17-11ac-4687-8dae-42d3a46ced1a.JPG)
### [알림장 - 등록]
![알림장등록](https://user-images.githubusercontent.com/81502408/178647465-411e1f68-c054-4529-984b-e4cff5649c2b.JPG)
### [알림장 - 상세조회]
![알림장상세](https://user-images.githubusercontent.com/81502408/178647469-bbf3717f-ba65-451f-a64c-5fcbee5a7d39.JPG)
### [통학버스 - 신청]
![통학버스신청](https://user-images.githubusercontent.com/81502408/178647477-d98d81c8-1e70-4532-ac9c-62c0808c89ef.JPG)
### [통학버스 - 나의 예약내역 조회]
![통학버스예약내역](https://user-images.githubusercontent.com/81502408/178647480-294f28f6-28e3-4435-9676-e505d714835a.JPG)
### [간식관리 - 목록조회]
![간식목록](https://user-images.githubusercontent.com/81502408/178647489-a8778366-e35a-4fe8-b086-2cb7a360a12c.JPG)
### [간식관리 - 등록]
![간식등록](https://user-images.githubusercontent.com/81502408/178647495-02cf01b1-b1ca-4473-a3d7-9205ce9f6831.JPG)
### [간식관리 - 상세조회]
![간식상세](https://user-images.githubusercontent.com/81502408/178647498-4161633a-c8a8-4726-b430-e9d60cdccbbf.JPG)
### [관리자 - 마이페이지]
![관리자페이지](https://user-images.githubusercontent.com/81502408/178647501-3192ddb7-02e7-43ef-aaba-2850cda203af.JPG)
### [관리자 - 회원 간식 구매내역조회]
![회원간식구매내역](https://user-images.githubusercontent.com/81502408/178647528-ff1cd45b-dff7-4b99-9fb2-58da6b5ffc55.JPG)
### [관리자 - 회원 정보조회]
![회원정보](https://user-images.githubusercontent.com/81502408/178647530-0f8f27d7-1ecc-4999-80ff-173db41a0f48.JPG)

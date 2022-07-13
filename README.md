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
    - Google SMTP
    - IMPORT api
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
![2asy work (2)](https://user-images.githubusercontent.com/81502408/178409279-fe8c4bd0-1a6f-4b3c-b303-8faf187f1a39.png)

## 🖥️ **서비스 화면**

### [회원가입]

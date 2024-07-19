<h1 align="center">🏥간호사 차트 관리 프로그램🚑</h1>
<h2 align="center">Java Swing, Awt Package — GUI Project</h2>

<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/0eb04cc1-21a2-40b2-b4d2-09c929ebd082" />
</div>

<br>

## 프로젝트 개요

> 간호사의 친절한 환자 응대는 환자의 재방문 의사를 높임과 동시에 병원의 이익을 높이는 중요한 요소입니다.<br>
간호사가 환자에게 친근감을 표할 수 있는 방법 중 하나는 차트 프로그램의 기록을 활용하는 데에 있습니다.<br>
**간호사 차트 관리 프로그램은, 이러한 차트 프로그램에 기록되는 정보의 유형과 그 정보의 배치 방식의 궁금증에서 시작하였습니다.**
>

<br>

## 개발 기간

> 2024.04.11 ~ 2024.04.21

<br>

## 개발 인원

> 아이디어 공유 및 개별 개발 진행.
> | 우승훈 | 장채윤 |
> | :------: | :------: |
> | [<img src="https://github.com/user-attachments/assets/1adb797b-7b40-4eee-bb6b-246e9488dcce" width="175" height="175"> <br> @bluewhale36](http://github.com/bluewhale36) | [@guncat-02](http://github.com/guncat-02) |

<br>

## 개발 환경

> | BE | DBMS | Driver | Tool |
> | :---: | :---: | :---: | :---: |
> | <img src="https://img.shields.io/badge/Java-F80000?style=flat&logoColor=white"/> | <img src="https://img.shields.io/badge/Oracle-F80000?style=flat&logo=oracle&logoColor=white"/> | <img src="https://img.shields.io/badge/JDBC-F80000?style=flat&logoColor=white"/> | <img src="https://img.shields.io/badge/Eclipse IDE-2C2255?style=flat&logo=eclipseide&logoColor=white"/> |

<br>

## 주요 기능

### Java : JDK 1.8
- javax.swing package를 이용하여 그래픽 기반의 유저 인터페이스 구현.
  - 상속을 통해 컨테이너 객체를 상속 받은 클래스에 컴포넌트 객체를 배치하여 GUI 구성.
  - 컴포넌트 객체 배치를 위해 Layout 개념 이해.
- awt.event package를 이용하여 GUI상에서 발생한 Event 처리.
  - ActionListener 등 인터페이스 구현을 통해 method 재정의하여 특정 Event 처리 코드 구현.
- DB Connection을 얻는 클래스를 별도 분리하여 DAO 클래스에서의 사용성 확장.
### Oracle : oracle-xe-11gR2
- trigger

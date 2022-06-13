# GitHub Issue Tracker
그룹 프로젝트#5

# 💡 그라운드 룰
-


# **🛠 브랜치 전략** 
- git flow 전략 
  - feature branch를 통한 개발이 끝나면 PR을 통해 develop branch에 merge한다.
```
team-08
    |
    |--> dev-xx
    |      |
    |      |--> feature...
```
- team-08 : 메인 배포 브랜치
- dev-be : BE 브랜치
- dev-ios : IOS 브랜치
- `feat-be/{featrue_name}-{issue_number}`: 백엔드 기능별 브랜치
- `feat-ios/{featrue_name}-{issue_number}`: ios 기능별 브랜치
- 주차별 개발 목표를 각자 milestone으로 설정한다.

# **🔥 Commit Rule**

| 타입     | 설명                                                       |
| -------- | ---------------------------------------------------------- |
| feat     | 기능 추가, 구현                                            |
| fix      | 버그 수정                                                  |
| rename   | 파일, 폴더 명 변경                                         |
| remove   | 파일, 폴더 삭제                                            |
| move     | 파일, 폴더 위치 변경                                       |
| design   | CSS 등 사용자 UI 디자인 변경                               |
| refactor | 리팩토링                                                   |
| comment  | 필요한 주석 추가 변경 삭제                                 |
| chore    | 빌드 테스트 업데이트, 패키지 매니저 설정 등 -> 코드 변경 x |
| docs     | 문서 수정 (ex. readme)                                     |
| test     | 테스트 코드                                    |

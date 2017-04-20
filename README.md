# homework
## 인스타그램 이미지뷰어
![2](./2.png)![3](./3.png)
#### Instagram ID를 검색하여 해당 사용자가 게시한 글들의 이미지를 보여준다.
#### 기본적으로 상하로 스크롤 되며 이미지 선택 시, 좌우 스크롤 상세화면이 나타난다.

## 지원자명
### 이상혁
##  개발환경
### OS : Window 10
### IDE : Android Studio 2.3.1
### OpenSouce : retrofit2 / picasso / retrofit2:converter-gson
## 회고
### 1. 여유부리다 완성도가 떨어지는 작업이 된것 같아 아쉬운 마음이 크다.
### 2. SVN만 사용하다 git을 사용하다보니 모르는 기능이 많았다. 이론적 공부와 실사용은 다르다는걸 다시 느꼈다.

## 향후 기능 변화에 대한 고려사항

### A. API 서버에서 응답되는 JSON 데이터의 구조가 변경되거나 데이터 포멧이 XML 등으로 변경될 가능성이 있다. 
### - RetrofitManager의 addConverterFactory() 변경 필요.

### B. 거의 동일한 형태의 목록화면이지만, N사의 이미지검색API를 호출해서 이미지를 동일하게 출력하는 추가 기능 개발 
### - 이미지 표시 시, 가로/세로 길이, url등을 사용하므로 수정 적음. 단, 추가 API연동 시 현재는 ID를 사용하므로 확인 필요

### C. 상세화면을 다른 곳에서 재활용
### Fragment로 분류하여 개발함. 재활용 가능

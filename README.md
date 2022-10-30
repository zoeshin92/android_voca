# part1-chapter7
단어장 앱 구현

# Chapter 0. 개요

### 구현기능

- 단어장 UI 구현
- 단어 추가
- 단어 수정
- 단어 삭제

---

# Chapter 1. 학습 목표

- 데이터를 추가, 수정, 삭제 하고, UI 에 변경된 내용을 보여줄 수 있다
    - Room 을 이용해, 데이터 추가, 수정, 삭제
    - RecyclerView 와 RecyclerViewAdapter 를 이용해 리스트 그리기
    - 변경된 데이터에 따른 내용을 UI 업데이트
- UI
    - RecyclerView, Adapter
    - TextInputLayout, TextInputEditText
    - ChipGroup, Chip
- Kotlin
    - data class
- Android
    - Room
    - registerForActivityResult
    - Parcelize
    
---

### RecyclerView
![image](https://user-images.githubusercontent.com/24618293/198893552-43289ea1-f225-4cf0-9758-d2674e26926d.png)

### RecyclerViewAdapter
![image](https://user-images.githubusercontent.com/24618293/198893559-5277ea32-7f67-4663-8d2d-522d93733162.png)

# 한걸음 더 
- 강의에서 구현한 방법과 다르게 RecyclerView 아이템 클릭 리스너 구현해보기
- 단어 추가 화면에서, 추가 버튼 클릭 시, 유효성 체크 해보기

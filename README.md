# 테트리스 자바 프로젝트

![Tetris Menu](https://github.com/hje1072/Tetris/assets/70854950/a92880e7-20d6-4f3b-80db-73f0be2d68d4)

## 프로젝트 소개
- 테트리스 자바 프로젝트는 일반적으로 알려진 테트리스 게임을 자바로 구현한 것입니다.
- 뿐만 아니라 다른 테트리스 게임에서는 볼 수 없는 `아이템 모드`가 있습니다.
- 혼자 하기 지겹다면 둘이서 즐길 수 있는 `대전모드`를 플레이 할 수 있습니다.
- 다양한 유저들을 위하여 `색맹모드`를 구현하여 모든 이용자가 불편함이 없도록 하였습니다.

## 사용된 기술

- Java Swing: GUI를 구현하기 위해 사용되었습니다.
- Java 기본 문법

## 프로젝트 설치 및 실행법

1. `추후 exe 파일 완성 후 수정될 예정...`

## 주요 기능 및 기타 설명

### [Basic Mode]
- 블록이 아래로 떨어지면서 테트리스 보드에 쌓입니다.
- 키보드 입력을 통해 블록을 회전시키고 이동시킵니다.
- 블록이 가득 찬 줄이 생기면 해당 줄이 사라집니다.
### [Item Mode]
- 10개의 라인을 사라질 때 마다, 다음 블럭을 아이템으로 설정합니다.
    - 다음과 같은 아이템이 있습니다.
        - `L블록` [라인지우기] L이 포함된 라인이 지워짐.
        - `무게추` 무게추 미노가 지나간 구역의 블록들을 삭제. 이후, 무게추 미노는 퇴장함.
        - `고스트` 모든 블록 무시. SKILL 사용시 현재위치에 블록이 정지.
        - `스왑` 한칸짜리 블록. SKILL 사용시 최대 3까지 다음블록과 교체가능. (유연한 플레이 가능)
        - `S블록` S블록 파괴시, 다음미노가 바로 아이템블록중 하나로 결정. 또한 라인삭제점수가 5배증가.
### [Battle Mode]
- 메인 메뉴에서 Battle! 을 선택하여 플레이 할 수 있습니다.
    - 다음과 같은 모드가 있습니다.
        - `Basic Battle!` Basic Mode로 2명의 플레이어가 대전합니다.
        - `Item Battle!` Item Mode로 2명의 플레이어가 대전합니다.
        - `Time Limit Battle!` 시간제한을 두고 2명의 플레이어가 대전합니다. (시간제한을 1분에서 5분까지 선택할 수 있습니다.)

### [점수 획득 방식]
- 한칸 내려갈시마다 레벨만큼 점수획득
- 각 줄 제거 시 100점 획득 (레벨증가마다 획득점수량 증가)
- 여러줄 한꺼번에 제거 시, 줄 개수 의 제곱 * 100만큼 점수획득(레벨증가마다 획득점수량 증가)
- 아이템 Score 블록 파괴시, 점수 500 * 라인 수만큼 증가.
- 점수판에는 상위 랭크 10등까지만 기록

### [기본 Key 설정 (사설키로 변경 가능)]
- 방향키 조절: 방향키
- 게임 퍼즈 : P키
- 게임 강제종료 : Q키
- 확인버튼 : 엔터키 (스코어보드 에서 메뉴갈때도 누르면 나가짐)
- 메뉴버튼 : ESC키 (게임도중 누를 시 메뉴로 감 게임은 종료됨 돌아가기 불가능)
- SKILL버튼 : SPACE BAR
- TURN버튼 : 윗 방향키

### [Skill]
- 기본적으로 스페이스바는 Skill 키로서, Basic Mode의 경우 Skill 사용시, 바로 땅으로 박힘.
- 특정 아이템블록의 경우, Skill 사용시 아이템에 맞는 특색적인 스킬 발동.


## 프로젝트 구조

```
├── README.md
└── src
     ├── data
     │     ├── colorBlind.csv
     │     ├── difficulty.csv
     │     ├── keySetting_battle.csv
     │     ├── scoreBoard.csv
     │     └── size.csv
     ├── main
     │     ├── Calc.java
     │     ├── GamePanel.java
     │     ├── KeyHandler.java
     │     ├── Main.java
     │     ├── Menu.java
     │     ├── PlayManager.java
     │     ├── ScoreBoard.java
     │     └── Setting.java
     ├── main_battle
     │     ├── KeyHandler_2.java
     │     └── PlayManager_Battle.java
     ├── mino
     │     ├── Block.java
     │     ├── Mino_Bar.java
     │     ├── Mino_L1.java
     │     ├── Mino_L2.java
     │     ├── Mino_Square.java
     │     ├── Mino_T.java
     │     ├── Mino_Z1.java
     │     ├── Mino_Z2.java
     │     └── Mino.java
     └── mino_item
           ├── Mino_Item_Ghost.java
           ├── Mino_Item_Swap.java
           └── Mino_Item_Weight.java
```

## 각 메뉴별 기능

### [메뉴화면]
- Tetris 실행 초기화면으로 Menu 화면이 나타납니다.

| 메뉴화면 |
|----------|
![2024-05-09-20-37-32](https://github.com/hje1072/Tetris/assets/70854950/d0f56768-109e-4d0f-b149-834b9d00db23)

<br>

### [Basic Mode]

| Basic Mode |
|----------|
![베이직 모드](https://github.com/hje1072/Tetris/assets/70854950/e8ab7c3c-b586-4ac7-a2b5-a815af957011)

<br>

### [Item Mode]

| L 블록 |
|----------|
- 스킬 사용법 : L블록이 있는 줄을 삭제합니다.

![L아이템](https://github.com/hje1072/Tetris/assets/70854950/508d2d5b-e13a-4782-a8b1-c958964e2fea)


| 무게추 아이템 |
|----------|

- 스킬 사용법 : 무게추 블록과 닿은 블록을 제거시킵니다.

![무게추-아이템](https://github.com/hje1072/Tetris/assets/70854950/bf1d120b-f738-4dfb-a78c-1b3f70b148d2)


| 고스트 아이템 |
|----------|

- 스킬 사용법 : 자신이 원하는 모든 곳에 space바 키를 이용하여 위치 시킬 수 있습니다. 현재 존재하는 블록들을 무시합니다.

![고스트아이템](https://github.com/hje1072/Tetris/assets/70854950/54779073-5a29-418b-be6a-b443cd3b7266)


| SWAP 아이템 |
|----------|

- 스킬 사용법 : 최대 3번까지 space바 키를 이용하여 다음 블록과 변경가능한 블록입니다.

![SWAP-아이템](https://github.com/hje1072/Tetris/assets/70854950/4a8fdfb3-d1e9-41b7-a502-dcd30c33aabb)



| S블럭 아이템 |
|----------|

- 스킬 사용법 : S블록을 제거하면 다음 블록으로 아이템블록이 생성됩니다.

![S블럭-아이템](https://github.com/hje1072/Tetris/assets/70854950/30516b36-61b6-4143-88da-d2096c7da361)

<br>

### [Battle Mode]

| Basic Battle Mode |
|----------|
- 추가 규칙 : 한 줄을 삭제 할 때마다 상대방에게 공격할 줄을 생성합니다.

![Battle-mode](https://github.com/hje1072/Tetris/assets/70854950/7bff5f6f-becf-4d9b-ac36-656523e39ede)

> 참고사항
- 공격할 줄이 10줄이 넘어갔을 경우 맨 윗줄 삭제   
(시연을 위해 1줄만 완성해도 공격하는 것으로 표현)

![10줄-초과](https://github.com/hje1072/Tetris/assets/70854950/9b2edaee-147c-4cff-9f12-7bb9af34e3cc)


| Item Battle Mode |
|----------|
- 추가 규칙 : Basic Battle Mode에 Item Mode를 추가합니다.   
(시연을 위해 1줄만 완성해도 Item이 생성되는 것으로 구현)

![아이템-배틀-모드](https://github.com/hje1072/Tetris/assets/70854950/10a22fa9-a3e2-47c2-8870-1086089463b4)


| Time Limit Battle Mode |
|----------|
- 추가 규칙 : Basic Battle Mode에 시간 제한을 추가합니다.   
(제한시간을 몇 분으로 할 지 게임 시작 전에 선택할 수 있습니다.)

![Time-Limit-Battle](https://github.com/hje1072/Tetris/assets/70854950/fbca7040-2d2e-4628-a814-9712670af874)   

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

## 주요 기능

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


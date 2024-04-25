## 자바로 구현한 테트리스 게임
![Main](https://github.com/hje1072/Tetris/assets/120327265/ca286a79-8741-405b-a988-ecbeccdc52c4)

### 기능
- 일반 모드
- 아이템 모드
- 설정에는 색맹모드, 화면설정, 키설정, 설정초기화 등 다양한 기능이 있습니다

### 점수
- 한칸 내려갈시마다 레벨만큼 점수획득
- 각 줄 제거 시 100점 획득 (레벨증가마다 획득점수량 증가)
- 여러줄 한꺼번에 제거 시, 줄 개수 의 제곱 * 100만큼 점수획득(레벨증가마다 획득점수량 증가)
- 아이템Score 블록 파괴시, 점수 500 * 라인 수만큼 증가.
- 점수판에는 상위 랭크 10등까지만 기록

### 기본키(본 상태는 기본값을 기준으로 설명. 설정에서 사설키로 변경가능)
- 방향키 조절: 방향키
- 게임 퍼즈 : P키
- 게임 강제종료 : Q키
- 확인버튼 : 엔터키 (스코어보드 에서 메뉴갈때도 누르면 나가짐)
- 메뉴버튼 : ESC키 (게임도중 누를 시 메뉴로 감 게임은 종료됨 돌아가기 불가능)
- SKILL버튼 : 스페이스바
- TURN버튼 : 윗방향키

### 스킬키
- 기본적으로 스페이스바는 SKILL 키로서, 노말블록의 경우 SKILL 사용시, 바로 땅으로 박힘.
- 특정 아이템블록의 경우, SKILL 사용시 아이템에 맞는 특색적인 스킬 발동.

### 규칙(기본모드)
- 일반적인 테트리스 규칙을 따릅니다

### 규칙(아이템모드)
- 10개의라인을 없앨 때 마다, 다음미노를 아이템으로 설정.

### 아이템
 1. L블록[라인지우기]) L이 포함된 라인이 지워짐.
 2. 무게추 ) 무게추 미노가 지나간 구역의 블록들을 삭제. 이후, 무게추 미노는 퇴장함.
 3. 고스트 ) 모든 블록 무시. SKILL 사용시 현재위치에 블록이 정지.
 4. 스왑 ) 한칸짜리 블록. SKILL 사용시 최대 3까지 다음블록과 교체가능. (유연한 플레이 가능)
 5. S블록 ) S블록 파괴시, 다음미노가 바로 아이템블록중 하나로 결정. 또한 라인삭제점수가 5배증가.

### 게임 플레이 사진

![PlayingGame](https://github.com/hje1072/Tetris/assets/120327265/883ef5ae-99f1-468d-a67c-26efa421a9df)


### 게임 플레이 동영상


https://github.com/hje1072/Tetris/assets/120327265/707f7f31-5df3-4451-8fc1-7f4137ee0359


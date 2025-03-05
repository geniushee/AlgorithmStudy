package org.ll.backjun.samsung;

/**
 * 무한루프에 빠지는지 검사만 하면 된다.
 * 0.초기값 설정
 * 1.전역변수 선언
 * 2.검사기 구현
 **/
public class SamsungA6Interpreter {
    // 배열 크기
    private int size;
    // 명령어
    private final String[] command;
    // 커맨드 포인터
    private int comPointer;
    // 현재 포인터 위치
    private int posPointer;
    // 루프 여부
    private boolean isLoop;
    // 루프 카운트
    private int commandCount;

    private final int maxCount = 50000000;


    public SamsungA6Interpreter(String arraySize, String command){
        this.size = Integer.parseInt(arraySize.split(" ")[0]);
        this.command = command.split("");
        this.comPointer = 0;
        this.posPointer = 0;
        this.isLoop = false;
        this.commandCount = 0;
    }

    /*
    검사기 조건
    1.반복 내 포인터 이동이 없다.
    2.포인터가 0으로 시작하지 않고 제자리를 왔다갔다 한다.
    3. 50,000,000번 이상 프로그램이 실행
     */

    /*
    1,2번 검사기 및 카운트 수 측정기
     */
    public boolean condition1n2(int startPointer, int endPointer){
        int left = 0;
        int right = 0;
        for(int i = startPointer; i <= endPointer;i++){
            if(command[i].equals("<")){
                left++;
            } else if (command[i].equals(">")) {
                right++;
            }
        }
        if(left == 0 && right == 0){
            return true;
        }else return left == right && this.posPointer != 0;
    }

    /*루프 카운터
    루프에서 몇번의 카운트가 발생하는가?
     */

}

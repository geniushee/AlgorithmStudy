package org.ll.algorithm.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 모티콘
 * 영선이는 매우 기쁜 마음에 효빈이에게 S개의 스마일 이모티콘을 보내려고 합니다. 처음에는 화면에 이모티콘 1개가 입력되어 있습니다.
 * 영선이가 사용할 수 있는 연산은 다음 3가지입니다:<br>
 * 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.<br>
 * 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.<br>
 * 화면에 있는 이모티콘 중 하나를 삭제한다.<br>
 * 각 연산의 제약사항:<br>
 * 모든 연산은 1초가 걸립니다.<br>
 * 클립보드에 복사하면 이전 내용은 덮어써집니다.<br>
 * 빈 클립보드에서는 붙여넣기를 할 수 없습니다.<br>
 * 이모티콘을 부분적으로 복사하거나 붙여넣을 수 없습니다.<br>
 * 입력 형식<br>
 * 첫째 줄에 정수 S (2 ≤ S ≤ 1000)가 주어집니다.<br>
 * S: 만들어야 할 이모티콘의 개수<br>
 * 출력 형식<br>
 * 첫째 줄에 이모티콘을 S개 만들기 위해 필요한 시간의 최솟값을 출력합니다.<br>
 * 예제 입력 1<br>
 * 2<br>
 * 예제 출력 1<br>
 * 2<br>
 * 예제 입력 2<br>
 * 4<br>
 * 예제 출력 2<br>
 * 4<br>
 * 예제 입력 3<br>
 * 6<br>
 * 예제 출력 3<br>
 * 5<br>
 */
public class Emoticon {

    public static int bfs(int target){
        HashSet<String> visited = new HashSet<>();
        Queue<State> queue = new LinkedList<>();

        State object = new State(1, 0,0);
        visited.add(object.screen+","+ object.clipboard);
        queue.add(object);

        while((object = queue.poll()) != null){
//            System.out.println(object);
            if(object.screen == target){
                return object.time;
            }

            if(!visited.contains(object.screen+","+object.screen)){
                visited.add(object.screen+","+ object.screen);
                queue.add(new State(object.screen, object.screen, object.time + 1));
            }

            if(object.clipboard > 0 && !visited.contains(object.paste()+","+ object.clipboard)){
                visited.add(object.paste()+","+ object.screen);
                queue.add(new State(object.paste(), object.clipboard, object.time + 1));
            }

            if(object.screen > 0 && !visited.contains(object.remove()+","+ object.clipboard)){
                visited.add(object.remove()+","+ object.screen);
                queue.add(new State(object.remove(), object.clipboard, object.time + 1));
            }
        }

        return -1;
    }

    static class State{
        private int screen;
        private int clipboard;
        private int time;

        private State(int screen, int clipboard, int time) {
            this.screen = screen;
            this.clipboard = clipboard;
            this.time  = time;
        }

        private int paste(){
            return screen + clipboard;
        }

        private int remove(){
            return screen -1;
        }
//
//        @Override
//        public String toString() {
//            return this.screen + "/"+this.clipboard+"/"+this.time;
//        }
    }
}


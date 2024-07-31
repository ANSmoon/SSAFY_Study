package jewel_1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    // 변수 N(보석 개수), K(가방 개수), result 선언
    static int N, K;
    static long result;
    // 보석 정보를 담는 배열 => sort해서 사용
    static Jewel[] jewels;
    // 가방 정보를 담는 배열 => sort해서 사용
    static int[] bag;
    // 가방에 담았는지 확인하는 배열
    static boolean[] visited;

    // 보석 무게, 가치 정보를 저장하는 클래스
    static class Jewel implements Comparable<Jewel> {
        // 무게
        int M;
        // 가치
        int V;

        Jewel(int M, int V) {
            this.M = M;
            this.V = V;
        }

        @Override
        public String toString() {
            return "Jewel{" +
                    "M=" + M +
                    ", V=" + V +
                    '}';
        }

        @Override
        // 무게 순서로 정렬
        // 2순위는 가치
        public int compareTo(Jewel o) {
            if (this.M == o.M) {
                return o.V - this.V;
            }
            return this.M - o.M;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 보석 담는 배열
        jewels = new Jewel[N];
        visited = new boolean[N];

        // 보석 객체 생성해서 배열에 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 보석 정보 배열 정렬
        Arrays.sort(jewels);
//        System.out.println(Arrays.toString(jewels));

        bag = new int[K];

        // 가방 정보 입력받음
        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }

        // 가방 무게 오름차순으로 정렬
        Arrays.sort(bag);
//        System.out.println(Arrays.toString(bag));

        // 보석의 가치를 담는 queue
        Queue<Integer> jewelQueue = new PriorityQueue<>(Comparator.reverseOrder());
        result = 0;
//        int idx = 0;

        // 가방을 순차적으로 탐색
        for (int i = 0, j = 0; i < K; i++) {
            // 보석 배열을 돌면서, 현재 탐색중인 가방에 들어갈 수 있는 보석의 가치 추가
            while (j < N) {
                if (bag[i] < jewels[j].M)    //다음 보석부터 가방의 무게보다 클 때
                    break;
                jewelQueue.add(jewels[j++].V);    //가방에 보석을 넣을 수 있을 때
            }
//            for (int j = idx; j < N; j++) {
//                if (jewels[j].M <= bag[i]) {
//                    jewelQueue.offer(jewels[j].V);
//                    idx = j + 1;
//                } else if (jewels[j].M > bag[i]) {
//                    continue next;
//                }
//            }

            System.out.println(jewelQueue);
            if (!jewelQueue.isEmpty()) {
                result += jewelQueue.poll();
            }
        }

        // 최종 결과 출력
        System.out.println(result);
    }


}

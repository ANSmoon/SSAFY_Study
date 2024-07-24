import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Product {
    int w;
    int v;

    public Product(int w, int v) {
        this.w = w;
        this.v = v;
    }


}

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long sum = 0;

        // 낮은 무게 순 pq
        PriorityQueue<Product> pq = new PriorityQueue<>((p1, p2) -> {
            return p1.w-p2.w;
        });

        // 가치가 높은 순의 pq
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((v1, v2) -> {
            return v2-v1;
        });

        // 가방의 pq
        PriorityQueue<Integer> pq3 = new PriorityQueue<>((b1, b2) -> {
            return b1-b2;
        });

        // 입력
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Product(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 가방 값 입력
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            pq3.offer(Integer.parseInt(st.nextToken()));
        }

        // pq 에서 무게가 적은 것부터 빼고 높은 가치 순으로 정렬된 pq2 에 넣는다.
        int idx = 0;
        for (int i=0; i<K; i++) {
            int nowB = pq3.poll();
            while (!pq.isEmpty() && nowB >= pq.peek().w) {
                pq2.offer(pq.poll().v);
            }
            if (!pq2.isEmpty()) {
                sum += pq2.poll();
            }

        }

        System.out.println(sum);

    }

}

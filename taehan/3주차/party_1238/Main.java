package party_1238;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, X;
    static int[][] minRoute;
    static List<List<Route>> routeList;
    static Queue<Route> queue;

    static class Route implements Comparable<Route>{
        int from;
        int to;
        int time;

        Route(){}

        public Route(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Route [from=" + from + ", to=" + to + ", time=" + time + "]";
        }

        @Override
        public int compareTo(Route o) {
            return this.time - o.time;
        };

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        minRoute = new int[N+1][N+1];

        for(int i = 0; i <= N; i++) {
            Arrays.fill(minRoute[i], 10000000);
            minRoute[i][i] = 0;
        }

        routeList = new ArrayList<>();
        for(int i = 0; i <= N+1; i++) {
            routeList.add(new ArrayList<>());
        }

        queue = new PriorityQueue<>();
        int from;
        int to;
        int time;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());

            minRoute[from][to] = time;
            routeList.get(from).add(new Route(from, to, time));
            queue.offer(new Route(from, to, time));
        }

        Route check;
        while(!queue.isEmpty()) {
            check = queue.poll();

            for(Route now : routeList.get(check.to)) {
                if(minRoute[check.from][now.from] + now.time < minRoute[check.from][now.to]) {
                    minRoute[check.from][now.to] = minRoute[check.from][now.from] + now.time;
                    queue.offer(new Route(check.from, now.to, minRoute[check.from][now.to]));
                }
            }

        }

//        for(int i = 0; i <= N; i++) {
//            System.out.println(Arrays.toString(minRoute[i]));
//        }

        int result = 0;
        for(int i = 1; i <= N; i++) {
            if(minRoute[i][X] + minRoute[X][i] > result) {
                result = minRoute[i][X] + minRoute[X][i];
            }
        }

        sb.append(result);
        System.out.println(sb);
    }
}

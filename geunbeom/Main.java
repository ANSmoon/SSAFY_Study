import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node {
    int n; // 정보, 벽인지 아닌지
    int down = 0; // 아래로 온 파이프 갯수
    int rgt = 0; // 오른쪽으로 온 파이프 갯수
    int cro = 0; // 대각선으로 온 파이프 갯수

    public Node(int n) {
        this.n = n;
    }

}

public class Main {

    static ArrayList<Integer> list;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        Node[][] arr = new Node[N][N];
        
        // 입력
        for (int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0; c<N; c++) {
                arr[r][c] = new Node(Integer.parseInt(st.nextToken()));
            }
        }
        arr[0][1].rgt = 1;

        // 시작 두줄은 절대로 못간다 (c 가 2부터 시작)
        for (int r=0; r<N; r++) {
            for (int c=2; c<N; c++) {
                if (arr[r][c].n == 1) continue;
                if (r>0) {
                    arr[r][c].down += arr[r-1][c].down;
                    arr[r][c].down += arr[r-1][c].cro;

                    if (arr[r-1][c].n != 1 && arr[r][c-1].n != 1) {
                        arr[r][c].cro += arr[r-1][c-1].cro;
                        arr[r][c].cro += arr[r-1][c-1].down;
                        arr[r][c].cro += arr[r-1][c-1].rgt;
                    }
                }
                arr[r][c].rgt += arr[r][c-1].rgt;
                arr[r][c].rgt += arr[r][c-1].cro;
            }
        }
        System.out.println(arr[N-1][N-1].rgt + arr[N-1][N-1].down +arr[N-1][N-1].cro);

    }


}
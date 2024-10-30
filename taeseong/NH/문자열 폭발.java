import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String checkString = br.readLine();
        String bomb = br.readLine();
        int bombLength = bomb.length();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < checkString.length(); i++) {
            result.append(checkString.charAt(i));

            // 폭탄 문자열 길이만큼 문자가 쌓였을 때 비교
            if (result.length() >= bombLength) {
                boolean isBomb = true;
                for (int j = 0; j < bombLength; j++) {
                    if (result.charAt(result.length() - bombLength + j) != bomb.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }

                // 폭탄 문자열을 발견하면 삭제
                if (isBomb) {
                    result.delete(result.length() - bombLength, result.length());
                }
            }
        }

        // 결과 출력
        if (result.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(result.toString());
        }
    }
}

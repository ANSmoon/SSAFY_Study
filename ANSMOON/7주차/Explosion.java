/*
 * Author : 문범수
 * Date : 2024-10-29
 * subject : Stack 활용
 * page : https://www.acmicpc.net/problem/9935
 * main : 문자열 탐색
 * issue : 인덱스 혼동 && 메모리 초과
 * name : 문자열 폭발
 * duration : 50m
 * no : 9935
 */

import java.util.*;
import java.io.*;

public class Explosion {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String original = reader.readLine();
        String bomb = reader.readLine();

        System.out.println(explodeString(original, bomb));
    }

    public static String explodeString(String original, String bomb) {
        StringBuilder builder = new StringBuilder();
        int bombLength = bomb.length();

        for (int i = 0; i < original.length(); i++) {
            builder.append(original.charAt(i));

            // 현재 추가된 부분이 폭탄 문자열과 일치하는지 확인
            if (builder.length() >= bombLength) {
                boolean isBomb = true;
                for (int j = 0; j < bombLength; j++) {
                    if (builder.charAt(builder.length() - bombLength + j) != bomb.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }

                // 폭탄 문자열을 발견하면 삭제
                if (isBomb) {
                    builder.delete(builder.length() - bombLength, builder.length());
                }
            }
        }

        return builder.length() > 0 ? builder.toString() : "FRULA";
    }
}
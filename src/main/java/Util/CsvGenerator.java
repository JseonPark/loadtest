package Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvGenerator {

    public static void main(String[] args) {
        long total = 100_000_000; // 1억 건
        String filePath = "/Users/js/dev/study/LoadTest2/Util"; // 저장 경로 (Linux는 "/tmp/members.csv" 등)

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (long i = 1; i <= total; i++) {
                String name = "user" + i;
                String phone = "010-1234-" + String.format("%04d", i % 10000);
                writer.write(name + "," + phone);
                writer.newLine();

                if (i % 1_000_000 == 0) {
                    System.out.println(i + " lines written...");
                }
            }
            System.out.println("✅ 파일 생성 완료: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
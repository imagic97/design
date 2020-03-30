package com.imagic97.ebook;

import java.util.Scanner;

public class ZipTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] numArr = new int[9];
        String s = sc.nextLine();
        String[] s11 = s.split(",");
        for (int i = 0; i < 9; i++) {
            numArr[i] = Integer.parseInt(s11[i]);
        }
        int s1 = numArr[0] + numArr[1] + numArr[2];
        int s2 = numArr[3] + numArr[4] + numArr[5];
        int s3 = numArr[6] + numArr[7] + numArr[8];

        if (s1 == s2 && s1 == s3) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}


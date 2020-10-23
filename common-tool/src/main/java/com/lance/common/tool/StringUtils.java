package com.lance.common.tool;

import java.util.Stack;

/**
 * @author Lance
 */
public class StringUtils {

    /**
     * Minimum Edit Distance. S(nm), T(nm)
     *
     * @link https://www.jianshu.com/p/a617d20162cf
     */
    public static int lev(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return str1 == null ? (str2 == null ? 0 : str2.length()) : str1.length();
        }

        int n = str1.length();
        int m = str2.length();
        if (java.lang.Math.min(n, m) == 0) {
            return java.lang.Math.max(n, m);
        }

        // If min(i,j)=0, lev(i,j)=max(i,j)
        int[][] d = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        // Otherwise, lev(i,j) = min(min(i-1,j)+1, min(i,j-1)+1, min(i-1,j-1)+1)
        // Or str1(i-1) == str2(j-1), lev(i,j) = min(min(i-1,j)+1, min(i,j-1)+1, min(i-1,j-1))
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int a = d[i - 1][j] + 1;
                int b = d[i][j - 1] + 1;
                int c = d[i - 1][j - 1];
                if (str1.charAt(i - 1) != str2.charAt(j - 1)) {
                    c += 1;
                }

                d[i][j] = java.lang.Math.min(java.lang.Math.min(a, b), c);
            }
        }
        return d[n][m];
    }

    /**
     * S(m), T(nm)
     */
    public static int lev2(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return str1 == null ? (str2 == null ? 0 : str2.length()) : str1.length();
        }

        int n = str1.length();
        int m = str2.length();
        if (java.lang.Math.min(n, m) == 0) {
            return java.lang.Math.max(n, m);
        }

        /*
            0 0 x y z
            0 0 1 2 3
            x 1 0 1 2
            x 2 1 1 2
            c 3 2 2 2
         */

        int[] d = new int[m + 1];
        for (int j = 0; j <= m; j++) {
            d[j] = j;
        }

        for (int i = 1; i <= n; i++) {
            int tmp = d[0];
            d[0] = i;
            for (int j = 1; j <= m; j++) {
                int a = d[j - 1] + 1;
                int b = d[j] + 1;
                int c = tmp;
                if (str1.charAt(i - 1) != str2.charAt(j - 1)) {
                    c += 1;
                }

                tmp = d[j];
                d[j] = java.lang.Math.min(java.lang.Math.min(a, b), c);
            }
        }
        return d[m];
    }

    public static void trackLev(String str1, String str2) {
        if (str1 == null) {
            str1 = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        System.out.println("From [" + str1 + "] to [" + str2 + "]");

        int n = str1.length();
        int m = str2.length();

        // If min(i,j)=0, lev(i,j)=max(i,j)
        int[][] d = new int[n + 1][m + 1];
        Position[][] p = new Position[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            d[i][0] = i;
            if (i != 0) {
                p[i][0] = new Position(i - 1, 0);
            }

        }
        for (int j = 0; j <= m; j++) {
            d[0][j] = j;
            if (j != 0) {
                p[0][j] = new Position(0, j - 1);
            }
        }


        // Otherwise, lev(i,j) = min(min(i-1,j)+1, min(i,j-1)+1, min(i-1,j-1)+1)
        // Or str1(i-1) == str2(j-1), lev(i,j) = min(min(i-1,j)+1, min(i,j-1)+1, min(i-1,j-1))
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int a = d[i - 1][j] + 1;
                int b = d[i][j - 1] + 1;
                int c = d[i - 1][j - 1];
                if (str1.charAt(i - 1) != str2.charAt(j - 1)) {
                    c += 1;
                }

                int currVal = java.lang.Math.min(java.lang.Math.min(a, b), c);
                if (currVal == c) {
                    p[i][j] = new Position(i - 1, j - 1);
                } else if (currVal == a) {
                    p[i][j] = new Position(i - 1, j);
                } else {
                    p[i][j] = new Position(i, j - 1);
                }

                d[i][j] = currVal;
            }
        }

        System.out.println();
        System.out.print("0 0 ");
        for (int j = 0; j < m; j++) {
            System.out.print(str2.charAt(j));
            System.out.print(' ');
        }
        System.out.println();


        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                System.out.print("0 ");
            } else {
                System.out.print(str1.charAt(i - 1));
                System.out.print(' ');
            }
            for (int j = 0; j <= m; j++) {
                System.out.print(d[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();

        Stack<Position> stack = new Stack<>();
        int currX = n;
        int currY = m;
        stack.push(new Position(currX, currY));

        while (currX != 0 || currY != 0) {
            Position position = p[currX][currY];
            stack.push(position);
            currX = position.getX();
            currY = position.getY();
        }

        int currVal = d[0][0];
        System.out.println(stack.pop());
        while (!stack.isEmpty()) {
            Position position = stack.pop();
            int newX = position.getX();
            int newY = position.getY();
            int newVal = d[position.getX()][position.getY()];

            String cmd = "";
            if (newX - 1 == currX && newY - 1 == currY) {
                if (currVal == newVal) {
                    cmd = "PASS";
                } else {
                    cmd = "REPLACE";
                }
            } else if (newX - 1 == currX) {
                cmd = "DELETE";
            } else {
                cmd = "INSERT";
            }
            currX = newX;
            currY = newY;
            currVal = newVal;
            System.out.println(position + " " + newVal + " " + cmd);
        }
    }

    public static class Position {

        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}

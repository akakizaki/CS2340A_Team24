package com.example.team24dungeoncrawler.model;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    static boolean isValid(int[][] map, boolean[][] visited, int x, int y) {
        int rows = map.length;
        int cols = map[1].length;

        boolean res = x >= 0 && x < rows && y >= 0 && y < cols && map[x][y] == 1 && !visited[x][y];
        return res;
    }

    static void algo(int[][] map, int begx, int begy, LinkedList<Point> list) {
        int[] xV = {-1, 0, 0, 1 };
        int[] yV = {0, -1, 1, 0 };

        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];

        Queue<Point> q = new LinkedList<>();
        visited[begx][begy] = true;
        Point p = new Point(begx, begy);
        list.add(p);
        q.add(p);

        while (!q.isEmpty()) {
            Point curr =  q.poll();
            int x = curr.x;
            int y = curr.y;
            for (int i = 0; i < 4; i++) {
                int nexX = x + xV[i];
                int nexY = y + yV[i];

                if (isValid(map, visited, nexX, nexY) && !isBlocked(map, begx, begy)) {
                    visited[nexX][nexY] = true;
                    q.add(new Point(nexX, nexY));
                }
            }
        }
    }
    private static boolean isBlocked(final int[][] map, final int x, final int y) {
        final int i = map[y][x];
        return i < 0 || i == 1 || i == 4;
    }
    static class Point {
        private int x;
        private int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public void setX(int x) {
            this.x = x;
        }
        public void setY(int y) {
            this.y = y;
        }
    }
}

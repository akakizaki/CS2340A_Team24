package com.example.team24dungeoncrawler.model;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    static class Point {
        int x,y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static boolean isValid(int[][] map, boolean[][] visited, int x, int y) {
        int rows = map.length;
        int cols = map[1].length;

        boolean res = x >= 0 && x < rows && y >= 0 && y < cols && map[x][y] == 1 && !visited[x][y];
        return res;
    }

    static void algo(int[][] map, int begx, int begy) {
        int[] xV = { -1, 0, 0, 1 };
        int[] yV = { 0, -1, 1, 0 };

        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];

        Queue<Point> q = new LinkedList<>();
        visited[begx][begy] = true;
        q.add(new Point(begx,begy));

        while(!q.isEmpty()) {
            Point curr =  q.poll();
            int x = curr.x;
            int y = curr.y;
            for(int i = 0; i < 4; i++) {
                int nexX = x + xV[i];
                int nexY = y + yV[i];

                if(isValid(map, visited, nexX, nexY)) {
                    visited[nexX][nexY] = true;
                    q.add(new Point(nexX, nexY));
                }
            }
        }
    }
}

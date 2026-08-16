package com.braillevision.vision;

import com.braillevision.model.Cell;
import com.braillevision.model.Dot;

import java.util.ArrayList;
import java.util.List;

public class BrailleCellDetector {
    
    public List<Cell> groupDotsIntoCells(List<Dot> dots) {
        List<Cell> cells = new ArrayList<>();
        if (dots.isEmpty()) return cells;
        
        // Simplified clustering: assume all detected dots belong to a single cell for now.
        // In a full implementation, use DBSCAN or K-Means clustering based on expected cell spacing.
        
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        
        for (Dot dot : dots) {
            if (dot.getX() < minX) minX = dot.getX();
            if (dot.getY() < minY) minY = dot.getY();
            if (dot.getX() > maxX) maxX = dot.getX();
            if (dot.getY() > maxY) maxY = dot.getY();
        }
        
        // Determine the 6-bit binary pattern for this cell based on dot positions
        // 1 4
        // 2 5
        // 3 6
        // This is a naive mapping assuming the bounding box is perfectly aligned.
        double midX = (minX + maxX) / 2.0;
        double thirdY1 = minY + (maxY - minY) / 3.0;
        double thirdY2 = minY + 2 * (maxY - minY) / 3.0;
        
        char[] pattern = {'0', '0', '0', '0', '0', '0'};
        
        for (Dot dot : dots) {
            boolean isLeft = dot.getX() < midX;
            boolean isTop = dot.getY() < thirdY1;
            boolean isBottom = dot.getY() > thirdY2;
            boolean isMiddle = !isTop && !isBottom;
            
            if (isLeft && isTop) pattern[0] = '1';
            else if (isLeft && isMiddle) pattern[1] = '1';
            else if (isLeft && isBottom) pattern[2] = '1';
            else if (!isLeft && isTop) pattern[3] = '1';
            else if (!isLeft && isMiddle) pattern[4] = '1';
            else if (!isLeft && isBottom) pattern[5] = '1';
        }
        
        cells.add(new Cell(dots, new String(pattern), minX, minY, maxX - minX, maxY - minY));
        return cells;
    }
}

package com.braillevision.vision;

import com.braillevision.model.Dot;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class DotDetector {

    public List<Dot> detectDots(Mat frame) {
        List<Dot> dots = new ArrayList<>();
        if (frame == null || frame.empty()) return dots;

        Mat gray = new Mat();
        Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);

        // Apply blur to reduce noise
        Imgproc.GaussianBlur(gray, gray, new Size(5, 5), 0);

        // Adaptive thresholding to highlight dots
        Mat thresh = new Mat();
        Imgproc.adaptiveThreshold(gray, thresh, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY_INV, 11, 2);

        // Find contours
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(thresh, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);
            if (area > 10 && area < 500) { // Filter out too small or too large areas
                float[] radius = new float[1];
                Point center = new Point();
                MatOfPoint2f contour2f = new MatOfPoint2f(contour.toArray());
                Imgproc.minEnclosingCircle(contour2f, center, radius);

                // Assuming circular dots
                if (radius[0] > 2 && radius[0] < 20) {
                    dots.add(new Dot(center.x, center.y, radius[0], 1.0));
                }
            }
        }

        gray.release();
        thresh.release();
        hierarchy.release();

        return dots;
    }
}

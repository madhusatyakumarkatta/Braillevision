package com.braillevision.vision;

import com.braillevision.model.Cell;
import com.braillevision.model.Dot;
import com.braillevision.translator.BrailleTranslator;
import org.opencv.core.Mat;

import java.util.List;

public class FrameProcessor {
    private final DotDetector dotDetector;
    private final BrailleCellDetector cellDetector;
    private final BrailleTranslator translator;

    public FrameProcessor() {
        this.dotDetector = new DotDetector();
        this.cellDetector = new BrailleCellDetector();
        this.translator = new BrailleTranslator();
    }

    public ProcessedFrame process(Mat frame) {
        List<Dot> dots = dotDetector.detectDots(frame);
        List<Cell> cells = cellDetector.groupDotsIntoCells(dots);
        String text = translator.translateCells(cells);
        return new ProcessedFrame(dots, cells, text);
    }

    public static class ProcessedFrame {
        public final List<Dot> dots;
        public final List<Cell> cells;
        public final String text;

        public ProcessedFrame(List<Dot> dots, List<Cell> cells, String text) {
            this.dots = dots;
            this.cells = cells;
            this.text = text;
        }
    }
}

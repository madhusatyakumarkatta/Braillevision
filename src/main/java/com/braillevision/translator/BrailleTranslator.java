package com.braillevision.translator;

import com.braillevision.model.Cell;

import java.util.List;

public class BrailleTranslator {
    private final BrailleDictionary dictionary;

    public BrailleTranslator() {
        this.dictionary = new BrailleDictionary();
    }

    public String translateCells(List<Cell> cells) {
        StringBuilder sb = new StringBuilder();
        for (Cell cell : cells) {
            sb.append(dictionary.translatePattern(cell.getBinaryPattern()));
        }
        return sb.toString();
    }
}

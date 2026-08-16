package com.braillevision.speech;

import java.io.IOException;

public class SpeechEngine {
    private String lastSpoken = "";

    public void speak(String text) {
        if (text == null || text.trim().isEmpty() || text.equals(lastSpoken)) {
            return;
        }

        lastSpoken = text;
        
        // Run in a separate thread so it doesn't block
        new Thread(() -> {
            try {
                // Using Windows native SpeechSynthesizer via PowerShell
                String script = String.format(
                    "Add-Type -AssemblyName System.Speech; " +
                    "$synth = New-Object System.Speech.Synthesis.SpeechSynthesizer; " +
                    "$synth.Speak('%s');", text.replace("'", "''"));
                
                ProcessBuilder pb = new ProcessBuilder("powershell.exe", "-Command", script);
                pb.start();
            } catch (IOException e) {
                System.err.println("Speech synthesis failed: " + e.getMessage());
            }
        }).start();
    }
}

package util;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundPlayer {
	
	/**
	 * Funcion que reproduce un sonido (Solo funciona con formato .wav)
	 * Puedes convertir un video de youtube a wav desde aqui: https://ontiva.com/converter-1/
	 * @param name
	 * @param volumeMultiplier
	 */
	public void playSound(String name, float volumeMultiplier) {
		try {
            File filePath = new File("src/assets/media/" + name);

            if (filePath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(filePath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volumeMultiplier);
                clip.start();
            } else {
                System.out.println("Incorrect audio file path!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}

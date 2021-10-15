package util;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {
	
	/** 
	 * Funcion que reproduce un sonido (Solo funciona con formato .wav)
	 * Puedes convertir un video de youtube a wav desde aqui: https://ontiva.com/converter-1/
	 * @param path
	 */
	public void playSound(String name) {
	    try {
	    	File file = new File("src/assets/media/" + name);
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY); 
	        // If you want to stop the sound, then use clip.stop();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacialRecognitionTest;

import java.util.ArrayList;
import org.opencv.core.Mat;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;

/**
 *
 * @author micha
 */
public class LocalBinaryPattern {

    private int spacialSize;

    public LocalBinaryPattern(int spacialSize) {
        this.spacialSize = spacialSize;
    }

    public int[] LocalBinaryPattern(Mat img) {
        Mat imgGrey = img.clone();
        imgGrey.convertTo(img, CV_LOAD_IMAGE_GRAYSCALE);
        //x, y start at one so no edge detection is needed
        ArrayList<Integer> lbp = new ArrayList<>();
        for (int i = 0; i < spacialSize; i++) {
            for (int x = 1 + (i * spacialSize); x < imgGrey.width() - 1; x++) {
                for (int y = 1 + (i * spacialSize); y < imgGrey.height() - 1; y++) {
                        if(imgGrey.get(x, y)[0] < imgGrey.get(x + (spacialSize / 2), y + (spacialSize / 2))[0]){
                            
                        }
                }
            }
        }
        
        return null;
    }
}

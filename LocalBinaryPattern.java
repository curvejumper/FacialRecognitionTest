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

    private final int patternSize = 9;
    private final int patternMultiple = 3;

    public LocalBinaryPattern() {
        
    }

    public int[] LocalBinaryPattern(Mat img) {
        Mat imgGrey = img.clone();
        imgGrey.convertTo(img, CV_LOAD_IMAGE_GRAYSCALE);
        int[] binaryPattern = new int[patternSize];
        int pixelByte = 0x0000;
        //x, y start at one so no edge detection is needed
        ArrayList<Integer> lbp = new ArrayList<>();
        for (int i = 0; i <= patternSize; i++) {
            int patternIndex = 0;
            for (int y = 0 + (i * patternMultiple); y < imgGrey.width(); y++) {
                for (int x = 0 + (i * patternMultiple); x < imgGrey.height(); x++) {
                    int xCenter = (i *patternMultiple) + 1;
                    int yCenter = (i * patternMultiple) + 1;
                    //check if pixel value is larger than center
                    //AND the pixel being checked isn't the center one
                    if (patternIndex != (patternSize / 2)) {
                        //give lower bit a 1 if value is larger than center
                        if (imgGrey.get(x, y)[0] >= imgGrey.get(i * (patternSize / 2), i * (patternSize / 2))[0]) {
                            pixelByte = 1 | (pixelByte << 1);
                        } else {
                            pixelByte = (pixelByte << 1);
                        }
                    }
                    patternIndex++;
                    
                    //TODO doublecheck if break just breaks out of current loop. 
                    if (x % (patternSize  - 1) == 0) {
                        break;
                    }
                }
                if (y % (patternSize - 1) == 0) {
                    break;
                }
            }
        }

        return null;
    }
}

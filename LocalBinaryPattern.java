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

    private final int numberOfBlocks = 9;
    private final int blockSize = 3;

    public LocalBinaryPattern() {   
    }

    public int[] LocalBinaryPattern(Mat img) {
        Mat imgGrey = img.clone();
        imgGrey.convertTo(img, CV_LOAD_IMAGE_GRAYSCALE);
        int[] binaryPattern = new int[numberOfBlocks];
        int pixelByte = 0x0000;
        //x, y start at one so no edge detection is needed
        ArrayList<Integer> lbp = new ArrayList<>();
        for (int i = 0; i <= numberOfBlocks; i++) {
            int patternIndex = 0;
            for (int y = 0 + (i * blockSize); y < imgGrey.width() && y < blockSize * (i + 1); y++) {
                for (int x = 0 + (i * blockSize); x < imgGrey.height() && x < blockSize * (i + 1); x++) {
                    int xCenter = ((x + 1) * blockSize) / 2;
                    int yCenter = ((y + 1) * blockSize) / 2;
                    //check if pixel value is larger than center
                    //AND the pixel being checked isn't the center one
                    if (patternIndex != (numberOfBlocks / 2)) {
                        //give lower bit a 1 if value is larger than center
                        if (imgGrey.get(x, y)[0] >= imgGrey.get(i * (numberOfBlocks / 2), i * (numberOfBlocks / 2))[0]) {
                            pixelByte = 1 | (pixelByte << 1);
                        } else {
                            pixelByte = (pixelByte << 1);
                        }
                    }
                    patternIndex++;
                    
                    //TODO doublecheck if break just breaks out of current loop. 
                    if (x % (numberOfBlocks  - 1) == 0) {
                        break;
                    }
                }
                if (y % (numberOfBlocks - 1) == 0) {
                    break;
                }
            }
        }

        return null;
    }
}

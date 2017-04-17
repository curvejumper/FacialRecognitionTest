/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacialRecognitionTest;

import java.io.File;
import java.util.ArrayList;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


/**
 *
 * @author micha
 */
public class FaceDetection{

    private final CascadeClassifier faceDetector;
    private MatOfRect faceDetections;

    private final String pathToFaceTrainerFile
            = "C:\\Users\\micha\\Documents\\Programming\\FacialRecognitionTest"
            + "\\src\\facialrecognitiontest\\haarcascade_frontalface_alt.xml";

    private int numFaces = 0;

    public FaceDetection() {
        faceDetector = new CascadeClassifier(
                new File(pathToFaceTrainerFile)
                .getPath());

        faceDetections = new MatOfRect();
    }

    public void findFaces(Mat frame) {
        try {
            if (!faceDetector.empty()) {
                faceDetector.detectMultiScale(frame, faceDetections);
                numFaces = faceDetections.toArray().length;
                if (faceDetections.toArray().length > 0) {
                    System.out.println(String.format("Detected %s faces", numFaces));

                    //faces detected
                }
                for (Rect rect : faceDetections.toArray()) {
                    Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                            new Scalar(0, 255, 0));
                }
            } else {
                System.err.println("Face Detector is empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCroppedFaces(Mat frameOfFace) {
        if (faceDetections != null && !faceDetections.empty()) {
            for (Rect rect : faceDetections.toArray()) {
                Mat cropImg = new Mat(frameOfFace, rect);
                Imgcodecs.imwrite("C:\\Users\\micha\\Desktop\\cropImg" + Math.random() + ".jpg", cropImg);
            }
        }
    }
    
    public ArrayList<String> nameTheFaces(Mat frameOfFace){
        ArrayList<String> names = new ArrayList<>();
        if (faceDetections != null && !faceDetections.empty()) {
            for (Rect rect : faceDetections.toArray()) {
                Mat cropImg = new Mat(frameOfFace, rect);
                
                //now that you have the cropped face,
                //find out if you know who's face it is!
                names.add(getName(cropImg));
            }
        }
        return names;
    }

    private String getName(Mat cropImg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void trainFace(){
        
    }
    
    private int[] LBP(Mat img){
        int kernelSize = 2;
        for(int i = 0; i < )
    }

}

package FacialRecognitionTest;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author SOHAM GANDHI
 */
public class CamCap extends javax.swing.JFrame {

    private videoBufferThread myThread = null;
    VideoCapture webSource = null;

    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();

    String File_path = "";

    private FaceDetection faceDetection;

    class videoBufferThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            //gets image from camera and puts it into a Mat
                            webSource.retrieve(frame);

                            faceDetection.findFaces(frame);

                            //Old way of encoding frame to format
                            //Highgui.imencode(".bmp", frame, mem);
                            Imgcodecs.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

                            BufferedImage buff = (BufferedImage) im;
                            Graphics g = jPanel1.getGraphics();

                            if (g.drawImage(buff, 0, 0, getWidth(), getHeight() - 150, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                if (runnable == false) {
                                    System.out.println("Going to wait()");
                                    this.wait();
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Error");
                        }
                    }
                }
            }
        }
    }

    /**
     * Creates new form CamCap
     */
    public CamCap() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startStreamButton = new javax.swing.JButton();
        stopStreamButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        getStreamSourceComboBox = new javax.swing.JComboBox();
        getStreamLocationButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        captureFaceButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Capture");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        startStreamButton.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        startStreamButton.setText("Start");
        startStreamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStreamButtonActionPerformed(evt);
            }
        });

        stopStreamButton.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        stopStreamButton.setText("Stop");
        stopStreamButton.setEnabled(false);
        stopStreamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopStreamButtonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(320, 240));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setAlignmentX(0.1F);
        jPanel2.setAlignmentY(0.1F);

        getStreamSourceComboBox.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        getStreamSourceComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "From WebCam", "From File" }));
        getStreamSourceComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getStreamSourceComboBoxActionPerformed(evt);
            }
        });

        getStreamLocationButton.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        getStreamLocationButton.setText("...");
        getStreamLocationButton.setEnabled(false);
        getStreamLocationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getStreamLocationButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel1.setText("Capture Method:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(getStreamSourceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(getStreamLocationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(getStreamSourceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getStreamLocationButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        captureFaceButton.setText("Capture Face");
        captureFaceButton.setEnabled(false);
        captureFaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                captureFaceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(captureFaceButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(startStreamButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopStreamButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startStreamButton)
                    .addComponent(stopStreamButton))
                .addGap(18, 18, 18)
                .addComponent(captureFaceButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startStreamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startStreamButtonActionPerformed

        if ((startStreamButton.getText()).equals("Start")) {
            if (getStreamSourceComboBox.getSelectedItem().equals("From WebCam")) {
                webSource = new VideoCapture(0);
            } else {
                webSource = new VideoCapture(File_path);
            }

            faceDetection = new FaceDetection();

            myThread = new videoBufferThread();
            Thread t = new Thread(myThread);
            t.setDaemon(true);
            myThread.runnable = true;
            t.start();

            startStreamButton.setEnabled(false);
            captureFaceButton.setEnabled(true);
            stopStreamButton.setEnabled(true);
            getStreamSourceComboBox.setEnabled(false);
        }
    }//GEN-LAST:event_startStreamButtonActionPerformed

    private void stopStreamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopStreamButtonActionPerformed

        if ((stopStreamButton.getText()).equals("Stop")) {
            myThread.runnable = false;
            stopStreamButton.setEnabled(false);
            startStreamButton.setEnabled(true);
            getStreamSourceComboBox.setEnabled(true);
            webSource.release();
        }
    }//GEN-LAST:event_stopStreamButtonActionPerformed

    private void getStreamLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getStreamLocationButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("AVI", "avi");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File_path = chooser.getSelectedFile().getPath();
            startStreamButton.setEnabled(true);
        } else {
            File_path = "";
        }
    }//GEN-LAST:event_getStreamLocationButtonActionPerformed

    private void getStreamSourceComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getStreamSourceComboBoxActionPerformed
        // TODO add your handling code here:
        if (getStreamSourceComboBox.getSelectedItem().equals("From File")) {
            getStreamLocationButton.setEnabled(true);
            startStreamButton.setEnabled(false);
        } else {
            getStreamLocationButton.setEnabled(false);
            startStreamButton.setEnabled(true);
        }
    }//GEN-LAST:event_getStreamSourceComboBoxActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:


    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (myThread == null) {
        } else if (myThread.runnable) {
            myThread.runnable = false;
            webSource.release();
        }
    }//GEN-LAST:event_formWindowClosing

    private void captureFaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_captureFaceButtonActionPerformed
        // TODO add your handling code here:
        if (captureFaceButton.getText().equals("Capture Face")) {
            faceDetection.getCroppedFaces(frame);
        }
    }//GEN-LAST:event_captureFaceButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //System.loadLibrary("OpenCV");

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                CamCap.setDefaultLookAndFeelDecorated(true);
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                } catch (Exception ex) {
                    System.out.println("Failed loading L&F: ");
                    System.out.println(ex);
                    System.out.println("Loading default Look & Feel Manager!");
                }

                new CamCap().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton captureFaceButton;
    private javax.swing.JButton getStreamLocationButton;
    private javax.swing.JComboBox getStreamSourceComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton startStreamButton;
    private javax.swing.JButton stopStreamButton;
    // End of variables declaration//GEN-END:variables

}

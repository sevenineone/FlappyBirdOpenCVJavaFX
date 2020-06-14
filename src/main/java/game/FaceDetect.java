package game;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

class FaceDetection extends Thread {
    private volatile boolean mFinish = false;
    public void finish()		//Инициирует завершение потока
    {
        mFinish = true;
    }
    @Override
    public void run() {
        nu.pattern.OpenCV.loadLocally();
        CascadeClassifier cascadeFaceClassifier = new CascadeClassifier("haar_face_detection.xml");
        VideoCapture videoDevice = new VideoCapture();
        videoDevice.open(0);
        for (; ; ) {
            if (videoDevice.isOpened()) {
                Mat frameCapture = new Mat();
                videoDevice.read(frameCapture);
                MatOfRect faces = new MatOfRect();
                cascadeFaceClassifier.detectMultiScale(frameCapture, faces);
                Rect maxRect = new Rect(0, 0, 1, 1);
                int area = 0;
                for (Rect rect : faces.toArray()) {
                    if (rect.height * rect.width > area) {
                        maxRect = rect;
                        area = rect.height * rect.width;
                    }
                }
                // System.out.print("Y- ");
                // System.out.println(maxRect.y);
                GameModel.Y = (maxRect.y - 50) * 3;

            } else {
                System.out.println("camera is not available.");
                return;
            }
            if(mFinish == true){
                videoDevice.release();
                return;
            }
        }
    }

}
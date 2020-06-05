package game;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;


public class Bird extends Pane {
    Rectangle rect;
    static volatile int Y = 300;
    static int Y2 = 300;
    static int Y3 = 300;
    static int Y4 = 300;


    public Bird() {
        rect = new Rectangle(20, 20, Color.RED);
        setTranslateX(100);
        setTranslateY(300);
        getChildren().addAll(rect);
    }

    public void moveX() {
        setTranslateX(getTranslateX() + 2);
        for (Wall w : FlappyBird.walls) {
            if (getBoundsInParent().intersects(w.getBoundsInParent())) {
                if (getTranslateX() + 20 == w.getTranslateX()) {
                    FlappyBird.gameOver = true;
                    setTranslateX(0);
                    //setTranslateX(getTranslateX() - 2);
                    return;
                }
            }
            if (getTranslateX() == w.getTranslateX()) {
                FlappyBird.score++;
                return;
            }
        }
    }

    public void moveY() {
        Double lastY = getTranslateY();
            setTranslateY((Bird.Y + Bird.Y2 + Bird.Y3 + Bird.Y4 + lastY) / 5);
        for (Wall w : FlappyBird.walls) {
            if (this.getBoundsInParent().intersects(w.getBoundsInParent())) {
                //setTranslateY(lastY);
                FlappyBird.gameOver = true;
                setTranslateX(0);
                return;
                }
            }

        if (getTranslateY() < 0) setTranslateY(0);
        if (getTranslateY() > 580) setTranslateY(580);
        Bird.Y4 = Bird.Y3;
        Bird.Y3 = Bird.Y2;
        Bird.Y2 = Bird.Y;

    }

    static class FaceDetection extends Thread {
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
                    Bird.Y = (maxRect.y - 50) * 3;

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
}


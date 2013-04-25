
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author krc
 */
public class PlotXY extends JFrame {

    myView view = new myView();
    int[] a = {10, 20, 30, 40, 50, 0, 22, 0, 10, 10, 11, 13, 15, 18, 21, 25, 30};

    public PlotXY() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                dispose();
                System.exit(0);
            }
        });
        setBounds(3, 10, 625, 350);
        view.setBounds(10, 10, 600, 301);
        getContentPane().add(view);
        getContentPane().setLayout(null);
        setVisible(true);
    }

    public class myView extends JPanel {

        BufferedImage I;
        Graphics2D G;

        public myView() {
        }

        public void paint(Graphics g) {
            if (I == null) {
                I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                G = I.createGraphics();
            }
            G.setColor(new Color(240, 240, 240));
            G.fillRect(0, 0, getWidth(), getHeight());
            G.setColor(Color.lightGray);

            for (int y = 0; y < 11; y++) {
                G.fillRect(0, y * 30, getWidth(), 1);
            }
            G.setColor(Color.darkGray);

            for (int x = 1; x < a.length; x++) {

                G.drawLine((x * 5), a[x - 1] + 150, ((1 + x) * 5), a[x] + 150);
                System.out.println(a[x]);
                            }
            g.drawImage(I, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        new PlotXY();
    }
}
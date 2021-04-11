import javax.swing.JFrame;

public class Main {
    public static void main (String[] args){
        JFrame frame = new JFrame("Corona Breaker");
        CoronaBreaker panel = new CoronaBreaker();
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(580,1080);
        frame.setResizable(false);

    }
}

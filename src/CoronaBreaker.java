
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


    public class CoronaBreaker extends JPanel implements KeyListener {
    ArrayList<Block> blocks = new ArrayList<Block>();
        ArrayList<Block> vaccine = new ArrayList<Block>();
    Block paddle;
    Thread thread;
    Animate animate;
        CoronaBreaker(){
            paddle = new Block(175,720,150,100, "src/assets/images/doctor.png");
            for (int i =0; i<8;i++){
                blocks.add(new Block((i*60+2),0,100,80,"src/assets/images/image_part_001.png"));
            }
            for (int i =0; i<8;i++){
                blocks.add(new Block((i*60+2),50,100,80,"src/assets/images/image_part_002.png"));
            }
            for (int i =0; i<8;i++){
                blocks.add(new Block((i*60+2),100,100,80,"src/assets/images/image_part_003.png"));
            }
            for (int i =0; i<8;i++){
                blocks.add(new Block((i*60+2),150,100,80,"src/assets/images/image_part_004.png"));
            }
            for (int i =0; i<8;i++){
                blocks.add(new Block((i*60+2),200,100,80,"src/assets/images/image_part_005.png"));
            }
            vaccine.add(new Block(220, 600, 50,100, "src/assets/images/vaccine.png"));
            addKeyListener(this);
            setFocusable(true);

        }
        public void paintComponent(Graphics g){
            //repaint screen
            super.paintComponent(g);
            for(Block b:blocks)
                b.draw(g,this);
            for(Block b:vaccine)
                b.draw(g,this);
                paddle.draw(g,this);
        }
        //update the image
        public void update(){
        repaint();
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_ENTER){
            animate = new Animate(this);
            thread=new Thread(animate);
            thread.start();
            }
            //moving left
        if(e.getKeyCode()== KeyEvent.VK_LEFT && paddle.x > 0){
            paddle.x-=15;
        } //moving right
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() -paddle.width)) {
            paddle.x+=15;
        }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }


import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class CoronaBreaker extends JPanel implements KeyListener {

    Random rand = new Random();
    List<String> givenList = Arrays.asList("src/assets/images/viruses/virus.png",
                "src/assets/images/viruses/image_part_001.png",
                "src/assets/images/viruses/image_part_002.png",
                "src/assets/images/viruses/image_part_003.png",
                "src/assets/images/viruses/image_part_004.png",
                "src/assets/images/viruses/image_part_005.png",
                "src/assets/images/viruses/image_part_006.png"
                );
        String randomElement ="";
        int randomIndex = 0;

    ArrayList<Block> blocks = new ArrayList<Block>();
    ArrayList<Block> vaccine = new ArrayList<Block>();
    ArrayList<Block> powerUp = new ArrayList<Block>();
    Block paddle;
    Thread thread;
    Animate animate;
    int size=25;
        CoronaBreaker() {
            paddle = new Block(175,720,150,100, "src/assets/images/doctor.png");
            drawVirus();
            Random powerRandom = new Random();
            blocks.get(powerRandom.nextInt(32)).powerup = true;
            blocks.get(powerRandom.nextInt(32)).powerup = true;
            blocks.get(powerRandom.nextInt(32)).powerup = true;
            blocks.get(powerRandom.nextInt(32)).powerup = true;
            blocks.get(powerRandom.nextInt(32)).powerup = true;
            vaccine.add(new Block(220, 600, 25,65, "src/assets/images/vaccine.png"));
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
            for(Block p:powerUp)
                p.draw(g,this);

            paddle.draw(g,this);
        }
        //update the image
        public void update(){
            for(Block p: powerUp){
                p.y+=1;
                if(p.intersects(paddle) && !p.destroyed){
                    p.destroyed = true;
                    vaccine.add(new Block(paddle.dx+75,437,25,65,"src/assets/images/vaccine.png" ));
                }
            }
            for(Block ba:vaccine) {
                ba.x+=ba.dx;
                if(ba.x>(getWidth()-size) && ba.dx>0 || ba.x<0)
                    ba.dx*=-1;


                if(ba.y<0 || ba.intersects(paddle))
                    ba.dy*=-1;

                ba.y+=ba.dy;
                for (Block b :blocks){
                    if(b.left.intersects(ba)||b.right.intersects(ba) && !b.destroyed){
                        ba.dx*=-1;
                        b.destroyed = true;
                        if(b.powerup){
                            powerUp.add(new Block(b.x,b.y,50,50, "src/assets/images/extra.png"));
                        }
                    }
                    if(ba.intersects(b) && !b.destroyed){
                    b.destroyed=true;
                    ba.dy*=-1;
                        if(b.powerup){
                            powerUp.add(new Block(b.x,b.y,50,50, "src/assets/images/extra.png"));
                        }
                    }
                }
            }
        repaint();
        }

        //draws enemies
        public void drawVirus(){
            for (int j =0; j<7;j++){
            for (int i =0; i<8;i++) {
                randomIndex = rand.nextInt(givenList.size());
                randomElement = givenList.get(randomIndex);
                blocks.add(new Block((i * 60 + 2), 50 * j, 55, 55, randomElement));
            }
            }
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

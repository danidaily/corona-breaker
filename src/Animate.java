public class Animate implements Runnable {
    CoronaBreaker bp;
   Animate(CoronaBreaker b){
        bp=b;
   }
    public void run() {
        while(true){
            bp.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

public class Print implements Runnable {
    @Override
    public void run() {
        while (true){
            System.out.println("1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

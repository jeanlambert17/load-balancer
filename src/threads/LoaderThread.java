package threads;

public class LoaderThread extends NotifyingThread {

    @Override
    public void doRun() {
        double random = Math.random()*10000;
        int milis = (int) random;
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

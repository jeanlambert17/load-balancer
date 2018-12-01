package threads;

import java.util.ArrayList;

public class LoaderManager implements ThreadCompleteListener {

    private int managerNumber;
    private boolean active = false;
    private LoaderThread thread = null;
    private ArrayList<String> users = new ArrayList<>();
    private long startedMilis;

    public LoaderManager(int managerNumber) {
        this.setManagerNumber(managerNumber);
    }

    public void go(String user) {
        this.users.add(user);
        if(!this.isActive()) {
            this.setActive(true);
            this.init();
        }
    }

    private void init() {
        thread = new LoaderThread();
        thread.addListener(this);
        this.setStartedMilis(System.currentTimeMillis());
        thread.start();
    }

    @Override
    public void notifyOfThreadComplete(Thread thread) {
        String user = this.users.get(0);
        this.users.remove(0);
        System.out.println(user + " has finished their load"
                + " with manager number " + this.getManagerNumber());

        if(this.users.size() > 0) {
            this.init();
        } else {
            this.setActive(false);
        }
    }

    public ArrayList<String> getUsers() { return this.users; }

    public int getManagerNumber() {
        return managerNumber;
    }

    public void setManagerNumber(int manageNumber) {
        this.managerNumber = manageNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getStartedMilis() {
        return startedMilis;
    }

    public void setStartedMilis(long startedMilis) {
        this.startedMilis = startedMilis;
    }
}

package threads;

import java.util.HashMap;

public class LoadBalancer {

    private HashMap<Integer, LoaderManager> loaderMap = new HashMap<>();
    private int loaderQty = 5;

    public LoadBalancer() {
        for(int i = 0; i < this.loaderQty; i++) {
            loaderMap.put(i, new LoaderManager(i));
        }
    }

    public void go() {
        boolean activeLoader = false;
        String randomUser = this.mapName((int) (Math.random()*5));

        for(int i = 0; i < this.loaderQty; i++) {
            LoaderManager loader = loaderMap.get(i);
            if(!loader.isActive()) {
                loader.go(randomUser);
                activeLoader = true;
                break;
            }
        }
        if(!activeLoader) {
            Integer users = -1;
            int manager = 0;
            for(int i = 0; i < loaderMap.size(); i ++) {
                int loaderUsers = loaderMap.get(i).getUsers().size();
                if(loaderUsers < users && users > -1) {
                    users = loaderUsers;
                    manager = i;
                }
            }
//            int random = (int) (Math.random()*5);
//            LoaderManager loader = loaderMap.get(random);
            LoaderManager loader = loaderMap.get(manager);
            loader.go(randomUser);
        }
    }

    private String mapName(int i) {
        switch (i) {
            case 0:
                return "Jean";
            case 1:
                return "Giovanny";
            case 2:
                return "Fabiola";
            case 3:
                return "Valeria";
            case 4:
                return "Carlos";
        }

        return "CompuTeam";
    }

}

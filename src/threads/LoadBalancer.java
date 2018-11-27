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

    public void go(String user) {
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
            // No hay ningun Loader activo, a quien le doy la carga?
            int random = (int) (Math.random()*5);
            LoaderManager loader = loaderMap.get(random);
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

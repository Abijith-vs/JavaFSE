public class logger{

    private static logger instance = new logger();

    private logger(){
    }
    public static logger getInstance() {
        if(instance==null){
            instance=new logger();
        }
        return instance;
    }
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
    }

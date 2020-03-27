//Ruslan Pashin 11-801
public class Main {

    public static void main(String[] args) {
        for (String str : args
        ) {
            ThreadDownloadFile tdf = new ThreadDownloadFile(str);
        }
    }

}

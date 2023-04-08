import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FunderReader {
    private List<Funder> funderList;

    public FunderReader() {
        funderList = new ArrayList<>();
    }

    public List<Funder> getFunderList() {
        return funderList;
    }

    public void loadFunders() {
        String line;
        String splitWhere = ",";
        try {
            File fundersFile = new File("/Users/oliviacrouch/Downloads/" +
                    "Landbay Coding Challenge/funded_products_by_funder.csv");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fundersFile));
            while ((line = bufferedReader.readLine()) != null) {
                String[] columns = line.split(splitWhere);
                Funder funder = new Funder(columns[0], columns[1]);
                funderList.add(funder);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

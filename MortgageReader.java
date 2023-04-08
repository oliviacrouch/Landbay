import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// the mortgage reader and funder reader classes alike, read data from the
// mortgages file, extracting the Mortgage name and the ProductID to a list
// this information can then be carried forward into the mortgage matcher class
// to manipulate and ensure that each funder has a very similar number of mortgages
// which they are funding.
// this way, when the data in the files is updated, the main method can be rerun and
// new matches can be made by the MortgageMatcher class.
public class MortgageReader {
    private List<Mortgage> mortgageList;

    public MortgageReader() {
        mortgageList = new ArrayList<>();
    }

    public List<Mortgage> getMortgageList() {
        return mortgageList;
    }

    public void loadMortgages() {
        String line;
        String splitWhere = ",";
        try {
            File mortgageFile = new File("/Users/oliviacrouch/Downloads/Landbay Coding Challenge/" +
                    "mortgages_to_be_funded.csv");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(mortgageFile));
            while ((line = bufferedReader.readLine()) != null) {
                String[] columns = line.split(splitWhere);
                Mortgage mortgage = new Mortgage(columns[0], columns[1], Integer.parseInt(columns[2]));
                mortgageList.add(mortgage);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

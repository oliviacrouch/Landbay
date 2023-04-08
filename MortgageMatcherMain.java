import java.util.List;
import java.util.Map;

public class MortgageMatcherMain {
    public static void main(String[] args) {
        // initialise a funderReader object
        FunderReader funderReader = new FunderReader();
        // load the funders from the csv file using the loadFunders() method in the
        // FunderReader class
        funderReader.loadFunders();
        // get the list of funders and productID preferences produced by the
        // funderReader class
        List<Funder> funderList = funderReader.getFunderList();

        // initialise a mortgageReader object
        MortgageReader mortgageReader = new MortgageReader();
        // load the mortgages from the CSV file using the method in the
        // MortgageReader class
        mortgageReader.loadMortgages();
        // get the list of mortgages and productIDs produced by the MortgageReader
        // class
        List<Mortgage> mortgageList = mortgageReader.getMortgageList();

        // initialise a mortgageMatcher object with the mortgageList and funderList as
        // arguments
        MortgageMatcher mortgageMatcher = new MortgageMatcher(mortgageList, funderList);
        // use this object to use the MortgageMatcher's matching method.
        mortgageMatcher.matchMortgages();

        // get the matched mortgages in the form of a hashmap
        Map<String, String> matchedMortgages = mortgageMatcher.getMatchedMortgages();
        // print the names of the mortgages matched and the funder code names
        // they've been allocated to
        System.out.println("Matched Mortgages: ");
        for (Map.Entry<String, String> entry : matchedMortgages.entrySet()){
            System.out.println("Mortgage Name: " + entry.getKey() + ", Funder Code Name: " + entry.getValue());
        }
    }
}




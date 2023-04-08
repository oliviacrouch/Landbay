import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// mortgage matcher takes two lists read from the CSV files
// in MortgageReader and FunderReader classes
class MortgageMatcher {
    private List<Mortgage> mortgages;
    private List<Funder> funders;
    private Map<String, Integer> funderMortgageCount;
    private Map<String, String> matchedMortgages;
    private Map<String, List<Funder>> productIDDesired = new HashMap<>();

    // constructor for mortgage matcher which initialises matchedMortgages and funderMortgageCount
    // hashmaps to be introduced in this class.
    public MortgageMatcher(List<Mortgage> mortgages, List<Funder> funders) {
        this.mortgages = mortgages;
        this.funders = funders;
        this.funderMortgageCount = new HashMap<>();
        this.matchedMortgages = new HashMap<>();

        // iterate through list of funder objects and for each create a key value pair
        // key is the funder's code name e.g. 'Eagle' initialised with zero.
        // this is used to keep track of the number of mortgages allocated to each funder object.
        // as the program runs, each mortgage which gets associated with the funder causes the value of the
        // corresponding key to increase relative to the number of mortgages associated with it - this is my
        // proposed 'fairness algorithm' to prevent any of the funders from dominating the market
        // by holding too many mortgages.
        for (Funder funder : funders) {
            // this adds the funder by code name to a tally which increases as the number of
            // mortgages they have been allocated increases
            funderMortgageCount.put(funder.getCodeName(), 0);

            // retrieves the desired productID property wanted by each Funder object
            String productIDPreference = funder.getDesiredProductID();
            // add each Funder object to an ArrayList in the productIDDesiredFundersMap hashmap.
            // this uses the productID as the key, if it doesn't already exist in the map (using Lambda),
            // it creates an ArrayList and adds the current Funder object to it.
            // k = key not already present in hashmap.
            productIDDesired.computeIfAbsent(productIDPreference, k -> new ArrayList<>()).add(funder);
        }
    }

    public void matchMortgages() {
        // loop through all the Mortgage objects in the mortgages list
        for (Mortgage mortgage : mortgages) {
            //get productID of each mortgage object
            String productID = mortgage.getProductID();
            // get list of Funder objects fom the productIDFundersMap which have the
            // same productID as the current Mortgage object - and are thus 'eligible Funders'
            List<Funder> eligibleFunders = productIDDesired.get(productID);
            // check if there are any eligible funders
            if (eligibleFunders != null && !eligibleFunders.isEmpty()) {
                // stream to find the Funder object in the eligibleFunders list which has the minimum
                // number of mortgages associated with it, based on the values in the funderMortgageCount map
                Funder lowestNumFunder = eligibleFunders.stream().min(Comparator.comparingInt(funder ->
                        funderMortgageCount.get(funder.getCodeName()))).orElse(null);
                // if there are no Funder objects in the preferredFunders list, set to null
                if (lowestNumFunder != null) {
                    // increment number of mortgages associated with funder object
                    funderMortgageCount.put(lowestNumFunder.getCodeName(),
                            funderMortgageCount.get(lowestNumFunder.getCodeName()) + 1);
                    // add the new match to the matchedMortgages hashMap
                    matchedMortgages.put(mortgage.getMortgageName(), lowestNumFunder.getCodeName());
                }
            }
        }
    }

    public Map<String, String> getMatchedMortgages() {
        return matchedMortgages;
    }
}

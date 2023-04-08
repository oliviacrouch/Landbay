public class Mortgage {
    // outline the attributes in the file which need to be accounted for in the Mortgages class.
    // list of mortgages will update with updates to files, so program should operate on this basis.
    // rerun the program to see updated allocations when changes to files are made.
    private String mortgageName;
    private String productID;
    private int amount;
    //public Map<String, String> mortgageNameProductIDMap;

    public Mortgage(String borrowerName, String productID, int amount){
        this.mortgageName = borrowerName;
        this.productID = productID;
        this.amount = amount;
    }

    // GETTERS AND SETTERS
    public String getMortgageName() {
        return mortgageName;
    }

    public void setMortgageName(String borrowerName) {
        this.mortgageName = borrowerName;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    //public Map<String, String> getMortgageNameProductIDMap(){
    //    return mortgageNameProductIDMap;
    //}
    // OVERRIDE TOSTRING TO DISPLAY PROPERLY
    //@Override
    //public String toString () {
     //   return mortgageName + ", " + productID + ", " + amount + "GBP";
}

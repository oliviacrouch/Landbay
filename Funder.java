public class Funder {
    private String codeName;
    private String productID;
    private int numFundedMortgages;

    //constructor
    public Funder(String codeName, String productID){
        this.codeName = codeName;
        this.productID = productID;
    }
        public String getCodeName() {
            return codeName;
        }

        public void setCodeName(String codeName) {
            this.codeName = codeName;
        }

        public String getDesiredProductID() {
            return productID;
        }

        public void setProductID(String productName) {
            this.productID = productName;
        }

        //public int getNumFundedMortgages(){
        //    return numFundedMortgages;
        //}
    }


# Landbay
Landbay coding challenge for interview 12.4.2023

Setup:
To use this application, download the CSV files ‘mortgages_to_be_funded’ and ‘funder_products_by_funder’ attached in the Landbay repository. The contents of these files should not be altered since both reader classes depend on the structures of the files to read them correctly to objects. Once downloaded, the pathnames referenced by the reader classes should be changed in accordance with those in your device. Once changed, run the application from the MortgageMatcherMain class in order to view the results of the matching in the terminal. 

Assumptions and understandings: 
This simple command line application reads from two CSV files – ‘mortgages_to_be_funded’ (‘mortgage file’ from hereon), and ‘funder_products_by_funder’ (‘funder file’ from hereon). I interpreted the mortgage file to contain a list of mortgages which needed funding, and the funder file to contain a list of funders and their preferences in mortgage type. From examining the ‘products’ CSV file, it was clear that the ‘productID’ is the most important piece of information. The ‘productID’ defines the type of mortgage, by distinguishing the interest rate; the type of rate; and the length of the term of had by mortgages of this particular productID. The funder file indicates which funder has preferences for which productID and thus their preferences for interest rate; type of rate; and the length of the term of the mortgage they are funding. 

With this in mind, I decided to make the ‘productID’ category in both the funder CSV file and the matching factor for funders and available mortgages. This guarantees that no funder is given a mortgage type that they do not want to fund. Also, given that the ‘productID’ is a common variable amongst classes this makes matching easier. 

Fairness algorithm:
I thought that implementing a fairness algorithm around the productID was most achievable and most relevant, given that the productID determines the interest rate, interest type and term length. The most important goals were that each funder had been allocated their preferred mortgage type, and that no single funder dominated the market by funding an inordinate number of mortgages. So, I decided to have a counter hash map which would count the number of mortgages which had been allocated to funders and allocate mortgages to the funder with the lowest number of mortgages on each iteration. This was implemented in the ‘MortgageMatcher’ class like so: 

In the MortgageMatcher constructor:
1.	A ‘funderMortgageCount’ hashmap is initialised to keep track of the number of mortgages to be funded by each Funder. 
2.	The ‘matchedMortgages’ hashmap is initialised to hold the mortgage name and funder code names which have been matched by the algorithm.
3.	A ‘productIDesired’ is initialised with each product ID (key), and an arraylist of all Funders who would be happy to fund this type of mortgage.

In the matchMortgages method:
4.	This loops around the mortgages objects, one product ID at a time, and matches it with a Funder object from the productIDDesired list with the same productID as the mortgage object – thus is an ‘eligibleFunder’
5.	The algorithm checks if there are already any eligibleFunders in the list.
6.	If there are, find the Funder with the lowest number of mortgages already associated with it – the lowestNumFunder (based on funderMortgageCount hashmap). This allocates the next mortgage each time to the funder with the least mortgages to ensure an even distribution. 
7.	If there are no eligibleFunders yet, and thus no lowestNumFunder, add the new Funder by codename to the funderMortgageCount and increment its count by one.
8.	Then, add this match to the matchedMortgages hashmap as a successfully matched pair. This matches the mortgage name and the funder’s code name to allow records to distinguish each funder and specific mortgage.

Efficiency: overall - O(n^2)

1.	MortgageMatcher class – O(n^2)
MortgageMatcher constructor: 
-	This initialises the variables at the beginning, with a time complexity of O(1). 
-	The mortgage matcher constructor loop around the funder objects to add them to a tally, as well as add each product ID and funder code name. 
-	This has a time complexity of O(n), as it depends on the number of funders which it loops around. 

matchMortgages method:
-	This method must iterate through each mortgage, and then iterate through each eligible funder for the mortgage. This is computationally expensive and would significantly slow the processing of larger datasets. O(n2) time complexity. 
-	To improve the efficiency of this method, the data could be ‘preprocessed’ in order to create a mapping of funders to mortgages which are eligible based on their product preference. This would reduce the number of funders to be iterated over for each mortgage.
-	Implementing a priority queue for storing eligible funders based on current load (number of mortgages already funded) could further improve efficiency – to allow faster retrieval of the funder with the minimum workload when matching mortgages. 

2.	MortgageMatcherMain class – O(n)
-	This method reads the data from CSV files, creates objects to store data, and displays matched mortgages to funders by iterating through the list of entires in the matchedMortgages hash map. 
-	The time complexity depends on the number of funders and mortgages in the CSV files being read, which are then implemented into a hashmap which is looped around. Hence the complexity is O(n^2).

3.	FunderReader / MortgageReader classes – O(n)
-	Both of these classes depend on the size of the input file being read.
-	Both take the file and create a list of funder/mortgage objects – thus the time complexity is O(n) where n is the size of the file. 
-	Split() method which splits columns in each line takes time proportional to the length of the string being split, but is only performed once. 

4.	Mortgage / Funder O(1)
-	Both Mortgage and Funder classes are O(1) since accessing and setting values of instance variables is a constant operation.


Improvements: 

Overall, the program works as I intended it to. It outputs a list of mortgage names paired with funder code names, which have been divided equally between the funders. All have either three or four mortgages assigned to them for funding. the CSV files could be expanded to include more mortgages and funders and would still retain a fairly equal spread of mortgages to funders. 
Matched Mortgages: 
Mortgage Name: Agecanonix, Funder Code Name: Finch
Mortgage Name: Obelix, Funder Code Name: Vulture
Mortgage Name: Manneken, Funder Code Name: Eagle
Mortgage Name: Antivirus, Funder Code Name: Vulture
Mortgage Name: Cetautomatix, Funder Code Name: Seagull
Mortgage Name: Bonemine, Funder Code Name: Finch
Mortgage Name: Ordralfabetix, Funder Code Name: Eagle
Mortgage Name: Plutoqueprevus, Funder Code Name: Eagle
Mortgage Name: Asterix, Funder Code Name: Eagle
Mortgage Name: Ielosubmarine, Funder Code Name: Vulture
Mortgage Name: Abraracourcix, Funder Code Name: Seagull
Mortgage Name: Falbala, Funder Code Name: Finch
Mortgage Name: Moulefix, Funder Code Name: Seagull

However, there are many ways in which this program could be improved both to: 
1.	Implement a more sophisticated fairness algorithm.
2.	Allow several funders to fund one mortgage where necessary.
3.	Optimise the time complexity.


1.	A more sophisticated fairness algorithm:
One way of implementing a more sophisticated fairness algorithm would be to attribute the various mortgage types with a weighting according to the attractiveness of their interest rates, interest rate types, and term lengths. This approach is not clear-cut, however, because although higher interest rates imply a higher return for the funders, they also imply that investing in this mortgage is more high-risk. Therefore, another attribute should be included in the mortgage product classification to better distinguish the desirability of the mortgages available. This attribute could be the LTV (loan-to-value) ratio – which is a metric designed specifically to assess the risk associated with a mortgage. The LTV value represents the proportion of the loan amount in relation to the appraised value of the property used as collateral. 

The way I propose the LTV ratio could be used is to order the properties in order of least-to-most desirable LTV ratio. An LTV ratio indicates that the proportion of the property’s value which the borrower made a down payment on and owns outright, is larger than the size of the loan. This means the borrower is less likely to default on the loan and the risk for the funder is relatively smaller. This should be considered in product classifications, and funder’s preferences should be updated accordingly. This way not only could the LTV ratio represent a more tangible indication of the funder’s appetite for risk, but it could also be used alongside interest rates, interest rate types, and term lengths to weight the mortgages available and give them a more accurate desirability ranking. The mortgages could then be sorted in descending order of their desirability rank (higher total weighting = less desirable), ranking them from least to most desirable. Then, they could be distributed to the funders in a similar manner to my program, keeping track of how many mortgages each funder has and allocating mortgages to those with the least. This way, each funder should have a relatively equal distribution of desirable and undesirable mortgages, which accord with their preferences. 


2.	Allow multiple funders to fund one mortgage where necessary:
It is more realistic for multiple funders to fund each mortgage. To implement this would require introducing a new entity which would hold the funding details of each mortgage being funded, as well as keeping track of each funder’s sum which they want to use. A list could be used to hold instances of this entity for each mortgage. Both the remaining mortgage amount for each mortgage, and the amount of funds each funder has would need be tracked using another variable. Priority queues for funders with the largest sum left first, and the mortgage with the largest sum first, would be used to match funders with mortgages until their sum diminishes and the mortgages have all been allocated. This would spread the risk between lenders and allow them to hold their funds in a greater variety of product types. 

3.	Optimisation:
The above proposition for an improved fairness algorithm should keep in mind the likelihood of a large dataset in its implementation. If sorting and weighting is done separately, then the above proposition should have a time complexity of (n log n). This is better than O(n2). An accurate analysis of the time complexity would depend on the actual implementation of the logic in code. 

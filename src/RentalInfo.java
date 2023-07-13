import java.util.HashMap;

public class RentalInfo {
    private static final String REGULAR_CODE = "regular";

    public String statement(Customer customer) {
        HashMap<String, Movie> movies = new HashMap<>();
        movies.put("F001", new Movie("You've Got Mail", REGULAR_CODE));
        movies.put("F002", new Movie("Matrix", REGULAR_CODE));
        movies.put("F003", new Movie("Cars", "childrens"));
        movies.put("F004", new Movie("Fast & Furious X", "new"));

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        String result = "Rental Record for " + customer.getName() + "\n";

        for (MovieRental r : customer.getRentals()) {
            double thisAmount = 0;
            // determine amount for each movie
            if (movies.get(r.getMovieId()).getCode().equals(REGULAR_CODE)) {
                thisAmount = 2;
                if (r.getDays() > 2) {
                    thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
                }
            }
            
            // Add rental details to the result
            result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
            
            // Increase the total amount
            totalAmount += thisAmount;
            
            // Increase frequent enter points
            frequentEnterPoints++;
        }

        // Add footer lines to the result
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentEnterPoints + " frequent renter points";
        
        return result;
    }
}

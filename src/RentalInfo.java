import java.util.HashMap;

public class RentalInfo {
    public String statement(Customer customer) {
        HashMap<String, Movie> movies = new HashMap<>();
        movies.put("F001", new Movie("You've Got Mail", "regular"));
        movies.put("F002", new Movie("Matrix", "regular"));
        movies.put("F003", new Movie("Cars", "childrens"));
        movies.put("F004", new Movie("Fast & Furious X", "new"));
        
        double totalAmount = 0;
        int frequentEnterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

        for (MovieRental r : customer.getRentals()) {
            double thisAmount = calculateAmount(movies.get(r.getMovieId()), r);
            
            frequentEnterPoints++;
            if (movies.get(r.getMovieId()).getCode().equals("new") && r.getDays() > 2) {
                frequentEnterPoints++;
            }

            result.append("\t")
                    .append(movies.get(r.getMovieId()).getTitle())
                    .append("\t")
                    .append(thisAmount)
                    .append("\n");

            totalAmount += thisAmount;
        }

        return result.toString();
    }
    
    private double calculateAmount(Movie movie, MovieRental rental) {
        double thisAmount = 0;
        
        if (movie.getCode().equals("regular")) {
            thisAmount = 2;
            if (rental.getDays() > 2) {
                thisAmount += (rental.getDays() - 2) * 1.5;
            }
        }
        
        if (movie.getCode().equals("new")) {
            thisAmount = rental.getDays() * 3;
        }
        
        if (movie.getCode().equals("childrens")) {
            thisAmount = 1.5;
            if (rental.getDays() > 3) {
                thisAmount += (rental.getDays() - 3) * 1.5;
            }
        }
        
        return thisAmount;
    }
}

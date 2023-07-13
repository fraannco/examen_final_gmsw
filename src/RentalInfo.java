public class RentalInfo {
  private static final String REGULAR_CODE = "regular";
  // ...

  public String statement(Customer customer) {
    HashMap<String, Movie> movies = createMoviesMap();
    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = calculateAmount(movies, r);

      frequentEnterPoints += calculateFrequentEnterPoints(movies, r);

      result.append("\t").append(movies.get(r.getMovieId()).getTitle()).append("\t").append(thisAmount).append("\n");
      totalAmount += thisAmount;
    }
    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return result.toString();
  }

  private double calculateAmount(HashMap<String, Movie> movies, MovieRental rental) {
    double amount = 0;
    Movie movie = movies.get(rental.getMovieId());
    String code = movie.getCode();
    int days = rental.getDays();

    if (code.equals(REGULAR_CODE)) {
      amount = 2;
      if (days > 2) {
        amount += (days - 2) * 1.5;
      }
    } else if (code.equals("new")) {
      amount = days * 3;
    } else if (code.equals("childrens")) {
      amount = 1.5;
      if (days > 3) {
        amount += (days - 3) * 1.5;
      }
    }

    return amount;
  }

  private int calculateFrequentEnterPoints(HashMap<String, Movie> movies, MovieRental rental) {
    int points = 1;
    Movie movie = movies.get(rental.getMovieId());
    String code = movie.getCode();
    int days = rental.getDays();

    if (code.equals("new") && days > 2) {
      points++;
    }

    return points;
  }

  private HashMap<String, Movie> createMoviesMap() {
    HashMap<String, Movie> movies = new HashMap<>();
    movies.put("F001", new Movie("You've Got Mail", REGULAR_CODE));
    movies.put("F002", new Movie("Matrix", REGULAR_CODE));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));
    return movies;
  }
}

/*
Author: Michael Ordaz
This class stores a language that was voted on with its number of first, second, third, fourth, fifth place votes.
 */
public class Language {

    private String name;
    int[] votes = new int[5];

    //Construct the new language
    public Language(String name, int vote) {
        this.name = name;
        castVote(vote);
    }

    //returns the name of the language
    public String getName() {
        return name;
    }

    //returns a string that contains the number of votes for each category
    public String getVotes() {
        int index = 0;
        String castVotes = "";
        String formatStr = "%-15s";
        for (Rank rank : Rank.values()) {
            castVotes = castVotes + String.format(formatStr, votes[index]);
            index++;
        }
        return castVotes;
    }

    public String getCategories() {
        int index = 0;
        String formatStr = "%-15s";
        String categories = String.format("%-20s", "Categories");
        for (Rank rank : Rank.values()) {
            categories = categories + String.format(formatStr, rank);
        }
        return categories;
    }

    public int[] getVoteArray() {
        return votes;
    }

    //Cast the vote
    public void castVote(int vote) {
        votes[vote-1]++;
    }

    //Contains the categories for each voting possibility
    public enum Rank {
        First (0),
        Second (1),
        Third (2),
        Fourth (3),
        Fifth (5);

        private final int theRank;

        Rank(int theRank) {
            this.theRank = theRank;
        }

        int getValue() {
            return theRank;
        }
    }
}

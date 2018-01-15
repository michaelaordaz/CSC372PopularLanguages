import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.io.FileWriter;

/*
Author: Michael Ordaz
This program takes in a file that consists of groups of tables which contains the votes of the students on what they
think are the most popular programming languages.

The output is two text files one showing each language the students voted for with the number of first, second, third,
fourth, and fifth place votes.
The other text file is a list of all the students that completed the assignment.
 */

public class ClassSurvey {
    public static void main(String[] args) throws IOException {
        //Read in the file and parse the contents.
        try (BufferedReader br = new BufferedReader(new FileReader("PopOLang.txt"))) {
            String line;
            ArrayList<String> panel = new ArrayList<>();
            ArrayList<Language> languages = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("Table")) {
                    line = br.readLine();
                    StringTokenizer tokens = new StringTokenizer(line, ","); //Separate the names of students
                    insertToList(panel, tokens);
                    line = br.readLine();
                    StringTokenizer langTok = new StringTokenizer(line, ","); //Separate the programming languages
                    for (int i = 0; langTok.hasMoreTokens(); i++) {
                        getLangs(langTok.nextToken(), languages); //input each of the languages and its number of votes
                    }
                }
            }
            Collections.sort(panel); //Sort the list of student into alphabetical order for readability
            createPanelFile(panel); //Write the students into a file
            createVoteFile(languages); //Write the languages into a file
        }
    }

    //This method tallys the number of votes for every language that is voted
    private static void getLangs(String sLang, ArrayList<Language> langs) {
        StringTokenizer lTok = new StringTokenizer(sLang); //separate the language from the vote
        String currLang = lTok.nextToken();
        int vote = Integer.parseInt(lTok.nextToken());
        //Check to see if the language has already been voted on
        for(int i = 0; i < langs.size(); i++) {
            //If it has cast the vote
            if(langs.get(i).getName().equals(currLang)) {
                langs.get(i).castVote(vote);
                return;
            }
        }
        //If it has not create a new language and cast the vote
        Language newLang = new Language(currLang, vote);
        langs.add(newLang);
    }

    private static void insertToList(ArrayList myList, StringTokenizer tokens) {
        String name;
        while(tokens.hasMoreTokens()) {
            name = tokens.nextToken().trim();
            myList.add(name);
        }
    }

    //This method creates the file that contains all the votes
    private static void createVoteFile(ArrayList<Language> langs){
        try{
            FileWriter fw = new FileWriter("PopularityOfLanguages.txt");
            for(int i = 0; i < langs.size(); i++) {
                fw.write(langs.get(i).getName() + "\n");
                fw.write(langs.get(i).getVotes() + "\n");
            }
            fw.close();
        }catch(Exception e){ System.out.println("There was an error in writing to the vote file");}
    }

    //This method create the file that contains the sutdent that completed the survey
    private static void createPanelFile(ArrayList<String> panel) {
        try {
            FileWriter fw = new FileWriter("Students.txt");
            for (int j = 0; j < panel.size(); j++) {
                fw.write(panel.get(j) + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("There was an error in writing to the panel file");
        }
    }
}

import java.util.*;

public class ElectionSystem {
    public static void main(String[] args) {
        // Create list candidates
        LinkedList<String> candidates = new LinkedList<>(Arrays.asList(
                "Marcus Fenix", "Dominic Santiago", "Damon Baird",
                "Cole Train", "Anya Stroud"
        ));

        Election election = new Election();

        election.initializeCandidates(candidates);
        election.setMaxVotes(5);

        // Cast votes
        election.castVote("Cole Train");
        election.castVote("Cole Train");
        election.castVote("Marcus Fenix");
        election.castVote("Anya Stroud");
        election.castVote("Anya Stroud");

        // Print top 3 candidates
        System.out.println("Top 3 candidates after 5 votes: " +
                election.getTopKCandidates(3));

        // Rig election
        election.rigElection("Marcus Fenix");

        // Print top 3 candidates after rigging
        System.out.println("Top 3 candidates after rigging the election: " +
                election.getTopKCandidates(3));

        // Audit election
        System.out.println("Election Audit:");
        election.auditElection();
    }
}

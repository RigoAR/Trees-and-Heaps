import java.util.*;

public class Election {
    private PriorityQueue<Candidate> candidateHeap;
    private Map<String, Candidate> candidateMap;
    private int totalVotes;
    private int maxVotes;

    public Election() {
        candidateHeap = new PriorityQueue<>((a, b) -> b.votes - a.votes); // -> creates a comparator function
        candidateMap = new HashMap<>();
    }

    public void initializeCandidates(LinkedList<String> candidates) {
        candidateHeap.clear();
        candidateMap.clear();
        totalVotes = 0;
        maxVotes = 0;

        for (String name : candidates) {
            Candidate candidate = new Candidate(name);
            candidateMap.put(name, candidate);
            candidateHeap.offer(candidate);
        }
    }

    public void castVote(String candidateName) {
        if (totalVotes >= maxVotes) {
            return;
        }

        Candidate candidate = candidateMap.get(candidateName);
        if (candidate == null) {
            return;
        }

        candidateHeap.remove(candidate);
        candidate.votes++;
        candidateHeap.offer(candidate);
        totalVotes++;
    }

    public void castRandomVote() {
        if (totalVotes >= maxVotes)
        {
            return;
        }

        List<String> candidates = new ArrayList<>(candidateMap.keySet());
        if (candidates.isEmpty()) {
            return;
        }

        String randomCandidate = candidates.get(new Random().nextInt(candidates.size()));
        castVote(randomCandidate);
    }

    public void rigElection(String candidateName) {
        Candidate candidate = candidateMap.get(candidateName);
        if (candidate == null) {
            return;
        }

        int requiredVotes = maxVotes - candidate.votes + 1;
        for (int i = 0; i < requiredVotes; i++) {
            castVote(candidateName);
        }
    }

    public List<String> getTopKCandidates(int k) {
        List<String> topCandidates = new ArrayList<>();
        PriorityQueue<Candidate> tempHeap = new PriorityQueue<>(candidateHeap);

        for (int i = 0; i < k && !tempHeap.isEmpty(); i++) {
            topCandidates.add(tempHeap.poll().name);
        }

        return topCandidates;
    }

    public void auditElection() {
        PriorityQueue<Candidate> tempHeap = new PriorityQueue<>(candidateHeap);
        while (!tempHeap.isEmpty()) {
            Candidate candidate = tempHeap.poll();
            System.out.println(candidate.name + " - " + candidate.votes);
        }
    }

    public void setMaxVotes(int maxVotes) {
        this.maxVotes = maxVotes;
    }

    private static class Candidate {
        String name;
        int votes;

        Candidate(String name) {
            this.name = name;
            this.votes = 0;
        }
    }
}

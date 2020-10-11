package leetcode.hard;

import java.util.*;

/**
 * 1125. Smallest Sufficient Team
 * In a project, you have a list of required skills req_skills, and a list of people.  The i-th person people[i] contains a list of skills that person has.
 * <p>
 * Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill.  We can represent these teams by the index of each person: for example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
 * <p>
 * Return any sufficient team of the smallest possible size, represented by the index of each person.
 * <p>
 * You may return the answer in any order.  It is guaranteed an answer exists.
 * <p>
 * Example 1:
 * <p>
 * Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
 * Output: [0,2]
 * Example 2:
 * <p>
 * Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
 * Output: [1,2]
 * <p>
 * Constraints:
 * <p>
 * 1 <= req_skills.length <= 16
 * 1 <= people.length <= 60
 * 1 <= people[i].length, req_skills[i].length, people[i][j].length <= 16
 * Elements of req_skills and people[i] are (respectively) distinct.
 * req_skills[i][j], people[i][j][k] are lowercase English letters.
 * Every skill in people[i] is a skill in req_skills.
 * It is guaranteed a sufficient team exists.
 */
public class SmallestSufficientTeam {

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> reqSkillsMap = getReqSkillMap(req_skills); // A map from req skill to index
        Map<Integer, Integer> personSkillsMap = getPersonSkillMap(people, reqSkillsMap); // A map from person to his skills
        List<Integer>[] dp = new ArrayList[1 << req_skills.length]; // Dynamic programming data structure
        dp[0] = new ArrayList<>(); // No member is needed for 0 skills
        for (int skillSet = 0; skillSet < dp.length; skillSet++) { // From 0 to all skills (dp.length-1)
            List<Integer> currTeam = dp[skillSet]; // Current team for skillSet
            if (currTeam == null) continue; // Nothing to compare
            for (int i = 0; i < people.size(); i++) { // For all people
                int mergedSkills = skillSet | personSkillsMap.get(i); // Or the skill of person with skillSet=merged
                if (mergedSkills == skillSet) continue; // No new skill
                if (dp[mergedSkills] == null || dp[mergedSkills].size() > currTeam.size() + 1) {
                    dp[mergedSkills] = new ArrayList<>(currTeam); // Init the dp cell
                    dp[mergedSkills].add(i); // Add person i to the team for merged skillSet
                }
            }
        }
        return dp[dp.length - 1].stream().mapToInt(i -> i).toArray(); // Simply convert to int[] and return
    }

    private Map<Integer, Integer> getPersonSkillMap(List<List<String>> people, Map<String, Integer> reqSkillsMap) {
        Map<Integer, Integer> personSkillsMap = new HashMap<>();
        for (int i = 0; i < people.size(); i++) { // Simply map each skill to a bit. Either the person has this bit or not.
            List<String> currSkills = people.get(i);
            int s = 0;
            for (String currSkill : currSkills) s = s | reqSkillsMap.get(currSkill); // Which bit should be set to 1.
            personSkillsMap.put(i, s);
        }
        return personSkillsMap;
    }

    private Map<String, Integer> getReqSkillMap(String[] req_skills) {
        Map<String, Integer> reqSkillsMap = new HashMap<>();
        for (int i = 0; i < req_skills.length; i++) reqSkillsMap.put(req_skills[i], (1 << i));
        return reqSkillsMap;
    }

    public static void main(String[] args) {
        SmallestSufficientTeam s = new SmallestSufficientTeam();
        System.out.println(Arrays.toString(s.smallestSufficientTeam(new String[]{"java", "nodejs", "reactjs"},
                List.of(List.of("java"), List.of("nodejs"), List.of("nodejs", "reactjs")))));
    }

}

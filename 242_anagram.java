class Solution {
  public boolean isAnagram(String s, String t) {
    // Time: O(n), Space: O(1)
    char[] c1 = s.toCharArray();
    char[] c2 = t.toCharArray();

    if (c1.length != c2.length) return false;
    Map<Character, Integer> charCount = new HashMap<>();

    for (char c : c2) {
      charCount.put(c, charCount.getOrDefault(c, 0) + 1);
    }

    for (char c : c1) {
      charCount.put(c, charCount.getOrDefault(c, 0) - 1);
      if (charCount.get(c) < 0) return false; // lacking 
    }

    return true;
  }
}
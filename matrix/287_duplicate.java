class Solution {
    public int findDuplicate(int[] nums) {
        // Fast-Slow Pointers Approach (Floyd's Cycle Detection)
        // Runtime: O(n); Space: O(1)
        int fast = nums[0], slow = nums[0];
        
        do { // starts with fast == slow, so do... while...
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}

/*
class Solution {
  public int findDuplicate(int[] nums) {
      // Runtime: O(n); Space: O(1)
      // Idea: Use O(n) to sort -> doesn't work for negative value & modify nums[]
      int idx = 0;
      while (idx < nums.length) {
          int tmp = nums[idx];
          if (nums[idx] == idx + 1) {
              idx++;
              continue;
          } else {
              if (nums[idx] == nums[Math.abs(tmp) - 1]) return tmp;
              else { // swap the order
                  nums[idx] = nums[tmp - 1];
                  nums[tmp - 1] = tmp;
              }
          }
      }
      return -1;
  }
}
*/
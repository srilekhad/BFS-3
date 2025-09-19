// Time Complexity: O(2^n ) – in the worst case BFS explores an exponential number of strings.
// Space Complexity: O(2^n * n) – the visited set/queue can hold up to O(2^n) strings, each of length O(n).

// Do BFS over strings by removing one parenthesis at every position per level; use a visited set to avoid repeats.
// As soon as any valid string is found at a level, record all valid strings from that level and stop expanding deeper.
// Validity check: scan with a counter (skip letters); increment on '(', decrement on ')', and ensure it never goes negative and ends at zero.

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        q.add(s);

        HashSet<String> set = new HashSet<>();
        set.add(s);
        boolean flag = false;

        while (!q.isEmpty()) {
            String curr = q.poll();

            if (isValid(curr)) {
                result.add(curr);
                flag = true;
            } else {
                if (!flag) {
                    for (int k = 0; k < curr.length(); k++) {
                        if (Character.isAlphabetic(curr.charAt(k))) continue;
                        String baby = curr.substring(0, k) + curr.substring(k + 1);
                        if (!set.contains(baby)) {
                            q.add(baby);
                            set.add(baby);
                        }
                    }
                }
            }
        }

        return result;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isAlphabetic(c)) continue;
            if (c == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) return false;
        }
        return count == 0;
    }
}

package com.example.demo.leetcode;

import java.util.*;

/**
 * @author ws
 * @Description:
 * @date 2023/01/03
 * @ClassName Solution
 */
class Solution {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aab"));
    }

    public static int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        int mid, val;
        while (left < right) {
            mid = (left + right) / 2;
            if (mid == left || mid == right) {
                break;
            }
            val = nums[mid];
            if (val == target) {
                return mid;
            }
            if (val > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return -1;
    }


    public static int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
            if (nums[i] < target) {
                if (i + 1 == nums.length) {
                    return nums.length;
                }
                if (nums[i + 1] > target) {
                    return i + 1;
                }
            } else {
                return i;
            }
        }
        return nums.length;
    }

    public int maxArea(int[] height) {
        int result = 0;
        int left = 0;
        int right = height.length - 1;
        while (right > left) {
            int leftVal = height[left];
            int rightVal = height[right];

            if (rightVal > leftVal) {
                int value = leftVal * (right - left);
                if (value > result) {
                    result = value;
                }
                left++;
            } else {
                int value = rightVal * (right - left);
                if (value > result) {
                    result = value;
                }
                right--;
            }
        }
        return result;
    }


    /**
     * You are given an array A of integers. Find the maximum number of nonintersecting segments of length 2 (two adjacent elements), such that segments have an equal sum.
     * For example, given A 10, 1, 3, 1, 2, 2, 1, 0, 4], there are three non-intersecting segments, each whose sum is equal to 4: (1, 3), (2, 2), (0, 4). Another three nonintersecting segments are: (3, 1), (2, 2), (0, 4).
     * Write a function:
     * class Solution { public int solution(int[] A);
     * that, given an array A of N integers, returns the maximum number of segments with equal sums.
     * Examples:
     * 1. Given A 10, 1, 3, 1, 2, 2, 1, 0, 4], the function should return 3, as explained above.
     * 2. Given A 5, 3, 1, 3, 2, 3], the function should return 1. Each sum of two adjacent elements is different from the others.
     * 3. Given A 9, 9, 9, 9, 9], the function should return 2.
     * 4. Given A 1, 5, 2, 4, 3, 3], the function should return 3. There are three segments: (1, 5), (2, 4), (3, 3) whose sums are equal to 6.
     * Write an efficient algorithm for the following assumptions:
     * N is an integer within the range [2..100,000];
     * each element of array A is an integer within the range
     * [0..1,000,000,000].
     **/
    public static int TTest(int[] array) {
        if (array.length < 4) {
            return 1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> site = new HashMap<>();
        for (int i = 0; i + 1 < array.length; i++) {
            int sum = array[i] + array[i + 1];
            if (map.containsKey(sum)) {
                if (site.get(sum) != i - 1) {
                    map.put(sum, map.get(sum) + 1);
                    site.put(sum, i);
                }
            } else {
                map.put(sum, 1);
                site.put(sum, i);
            }
        }
        List<Integer> value = new ArrayList(map.values());
        Collections.sort(value);
        if (value != null && value.size() > 0) {
            value.sort((num1, num2) -> {
                return num2.compareTo(num1);
            });
            return value.get(0);
        } else {
            return 0;
        }
    }


    public int[] sortedSquares(int[] nums) {
        List<Integer> result = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            int val = 0;
            if (nums[i] < 0) {
                val = -nums[i];
            } else {
                val = nums[i];
            }
            result.add(val * val);
        }
        Collections.sort(result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }


    /**
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右轮转 1 步: [7,1,2,3,4,5,6]
     * 向右轮转 2 步: [6,7,1,2,3,4,5]
     * 向右轮转 3 步: [5,6,7,1,2,3,4]
     **/
    public static void rotate(int[] nums, int k) {
        for (int val : nums) {
            System.out.print(val + ",");
        }
        int move = 0;
        if (k >= nums.length) {
            move = k % nums.length;
        } else {
            move = k;
        }
        int start = nums.length - move;
        int[] value = new int[nums.length];
        for (int i = 0; i < move; i++, start++) {
            value[i] = nums[start];
        }
        for (int i = move, j = 0; i < nums.length; i++, j++) {
            value[i] = nums[j];
        }
        System.arraycopy(value, 0, nums, 0, nums.length);

        System.out.println();
        for (int val : nums) {
            System.out.print(val + ",");
        }
    }


    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     * 示例 2：
     * <p>
     * 输入：head = [1], n = 1
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     * <p>
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    static class ListNode {

        int val;

        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        System.out.println("count:" + count);
        int del = count - n;
        System.out.println("del:" + del);
        ListNode dummy = new ListNode(0, head);
        node = dummy;
        for (int i = 0; i <= del; i++) {
            if (i == del) {
                node.next = node.next.next;
            }
            node = node.next;
        }
        return dummy.next;
    }


    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     **/
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        List<Character> list = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (list.contains(chars[i])) {
                int index = list.indexOf(chars[i]);
                while (index >= 0 && list.get(index) != null) {
                    list.remove(index);
                    index--;
                }
                list.add(chars[i]);
            } else {
                list.add(chars[i]);
            }

            if (list.size() > max) {
                max = list.size();
            }
        }
        return max;
    }


    /**
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * <p>
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [3,2,3]
     * 输出：3
     * 示例 2：
     * <p>
     * 输入：nums = [2,2,1,1,1,2,2]
     * 输出：2
     **/
    public synchronized int majorityElement(int[] nums) {

        int length = nums.length;
        if(length == 1){
            return nums[0];
        }
        int foot = length / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                int val = map.get(i) + 1;
                if (val > foot) {
                    return i;
                } else {
                    map.put(i, val);
                }
            } else {
                map.put(i, 1);
            }
        }
        return 0;
    }

    public int majorityElement2(int[] nums) {
        int length = nums.length;
        if(length == 1){
            return nums[0];
        }
        int foot = length / 2 ;
        Arrays.sort(nums);
        return nums[foot];
    }
}

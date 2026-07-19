/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy; // 일단 만들어 놓기

        int carry = 0;
        
        // 0보다 큰 carry가 있으면 끝나도 한번 더
        while (l1 != null || l2 != null || carry > 0) {
            int v1 = l1 != null ? l1.val : 0; // 9 9 9 9 9 9 9
            int v2 = l2 != null ? l2.val : 0; // 9 9 9 9 0 0 0
            int sum = v1 + v2 + carry; // 18 19 19 19 10 10 10

            int v3 = sum % 10; // 8 9 9 9 0 0 0 1

            current.next = new ListNode(v3); // 8 9 9 9 0 0 0 1

            // 다음에 쓸 것들
            carry = sum / 10; // 1 1 1 1 1 0
            if (l1 != null){
                l1 = l1.next;
            }
            
            if (l2 != null){
                l2 = l2.next;
            }
            
            current = current.next;
        }

        return dummy.next;
    }
}
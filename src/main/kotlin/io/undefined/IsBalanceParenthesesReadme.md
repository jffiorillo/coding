## Problem

Given a string with alphanumeric characters and parentheses, return a string with balanced parentheses by removing the fewest characters possible. You cannot add anything to the string.
Balanced parentheses means that each opening parenthesis has a corresponding closing parenthesis and the pairs of parentheses are properly nested. 


## Examples

```
balance("()") -> "()"
balance("a(b)c)") -> "a(b)c" or "a(bc)"
balance(")(") -> ""
balance("(()()(") -> "()()"
balance(")(())(") -> "(())"
```

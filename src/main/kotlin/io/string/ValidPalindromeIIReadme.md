
## Problem

Given a string S consisting of lowercase English characters determine if you can make it a palindrome by removing at most 1 character.

##  Examples

```
tacocats -> true # tacocats -> tacocat
acbd -> false
kbayak -> true  # kbayak -> kayak
acbccba -> true # acbccba -> abccba
```

## Examples to highlight the need of backtracking

If the candidate creates an iterative solution without backtracking, then consider giving the following examples. They can highlight the need for backtracking.

```
abbab -> true # abbab -> abba
babba -> true # babba -> abba
```
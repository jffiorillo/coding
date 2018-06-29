# https://www.geeksforgeeks.org/longest-increasing-subsequence/
import functools


def longest_increasing_sub_sequence(sequence, solution=[]):
	print("solution ", solution, "seq ", sequence)
	if len(sequence) == 0:
		return functools.reduce((lambda x, y: x if len(x) > len(y) else y), solution)
	item = sequence.pop(0)
	if len(solution) == 0:
		solution.append([item])
	else:
		found = False
		for x in solution:
			if x[-1] == item:
				continue
			elif x[-1] < item:
				x.append(item)
				found = True
		if not found:
			for x in solution:
				i = len(x) - 1
				while not found and i >= 0:
					if x[i] < item:
						found = True
						new_list = x[:i + 1]
						new_list.append(item)
						solution.append(new_list)
					i -= 1
		if not found:
			solution.append([item])

	return longest_increasing_sub_sequence(sequence)


if __name__ == '__main__':
	print(longest_increasing_sub_sequence([5, 60, 70, 8, 9, 10]))

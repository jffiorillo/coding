# https://www.geeksforgeeks.org/bell-numbers-number-of-ways-to-partition-a-set/


# noinspection PyDefaultArgument
def bell_number_or(n, previous=[1]):
	print n, previous
	if len(previous) == n:
		print "Bell number of ", n, " is ", previous[-1]
		return previous[-1]
	current = [previous[-1]]
	for x in previous:
		current.append(current[-1] + x)
	bell_number_or(n, current)


if __name__ == '__main__':
	bell_number_or(5)

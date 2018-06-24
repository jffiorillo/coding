# All the possible combinations of friends in a table

# noinspection PyDefaultArgument
def power_set(friends, table_size, pos=0, group=[], groups=[]):
	print group, pos
	if len(group) == table_size:
		groups.append(group)
	elif pos < len(friends):
		power_set(friends, table_size, pos + 1, group)
		new_group = list(group)
		new_group.append(friends[pos])
		power_set(friends, table_size, pos + 1, new_group)
	return groups


if __name__ == '__main__':
	friendsList = ["A", "B", "C", "D", "E"]
	tableSize = 3
	power_set = power_set(friendsList, tableSize)
	print "len ", len(power_set), "and should be len(friends)!/(tableSize!*(len(friends)-tableSize)!)"
	print power_set

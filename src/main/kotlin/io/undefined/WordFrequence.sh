#!/usr/bin/env bash


# https://leetcode.com/problems/word-frequency/


echo "WIP NOT WORKING RIGHT NOW"

echo "a     como estas \
\
     todo bien    y tu? como estas"> words.txt


declare -A wordsMap
fileString=$(<words.txt)
echo "$fileString"
for word in ${fileString}
  do
  if  ${word} in "${!wordsMap[@]}"
    then
      echo "Found $word";
      wordsMap[${word}]=1;
    else echo "Not found $word";
   fi
done

rm words.txt

echo "WIP NOT WORKING RIGHT NOW"
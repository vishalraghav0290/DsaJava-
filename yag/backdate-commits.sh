#!/bin/bash

# Array of commit dates
declare -a dates=(
    2024-01-10
2024-01-11
2024-01-14
2024-01-15
2024-01-16
2024-01-19
2024-01-20
2024-01-22
2024-01-25
2024-01-28
2024-01-29
2024-02-04
2024-02-05
2024-02-06
2024-02-13
2024-02-14
2024-02-17
2024-02-20
2024-02-21
2024-02-22
2024-02-23
2024-02-26
2024-02-27
2024-02-28
2024-02-29
2024-03-02
2024-03-04
2024-03-07
2024-03-08
2024-03-09
2024-03-10
2024-03-14
2024-03-15
2024-03-16
2024-03-18
2024-03-20
2024-03-23
2024-03-24
2024-03-27
2024-03-31
2024-04-01
2024-04-02
2024-04-06
2024-04-08
2024-04-09
2024-04-11
2024-04-15
2024-04-18
2024-04-19
2024-04-21
2024-04-23
2024-04-24
2024-04-26
2024-05-01
2024-05-02
2024-05-03
2024-05-04
2024-05-07
2024-05-10
2024-05-11
2024-05-15
2024-05-17
2024-05-18
2024-05-21
2024-05-22
)

# Directory where your DSA solutions and projects are stored
FILES_DIR="/Users/vishalraghav/Documents/Ceo/yag/Learning-DSA"

# Make sure the repo is clean
git checkout main

date_index=0
last_commit_date=""

# Get all files you want to commit
files=$(ls "$FILES_DIR")

for file in $files; do
    current_commit_date=${dates[$date_index]}
    
    if [[ "$current_commit_date" != "$last_commit_date" ]]; then
        # Set the commit date environment variables
        export GIT_AUTHOR_DATE="$current_commit_date 12:00:00"
        export GIT_COMMITTER_DATE="$current_commit_date 12:00:00"
        
        git add "$file"
        git commit --date="$current_commit_date 12:00:00" -m "Commit for $file on $current_commit_date"
        git push
        last_commit_date=$current_commit_date
    fi
    
    date_index=$((date_index + 1))
    
    if [[ $date_index -ge ${#dates[@]} ]]; then
        date_index=0
    fi
done

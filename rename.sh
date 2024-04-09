#!/bin/bash

rename_files() {
  local directory_path="$1"
  for entry in "$directory_path"/*; do
    if [ -d "$entry" ]; then
      rename_files "$entry"
    elif [ -f "$entry" ]; then
      local new_filename="prefix_$(basename "$entry")"
      mv "$entry" "$directory_path/$new_filename"
      echo "Renamed $entry to $new_filename"
    fi
  done
}

main() {
  local directory_path="."
  rename_files "$directory_path"
}

main

@@ -0,0 +27,27 @@
# Test cases

test_rename_files() {
  local test_dir="test_dir"
  mkdir -p "$test_dir/subdir"
  touch "$test_dir/file1.txt" "$test_dir/file2.txt" "$test_dir/subdir/file3.txt"

  ./rename.sh

  if [ -f "$test_dir/prefix_file1.txt" ] && [ -f "$test_dir/prefix_file2.txt" ] && [ -f "$test_dir/subdir/prefix_file3.txt" ]; then
    echo "Test passed: Rename files in a directory with subdirectories"
  else
    echo "Test failed: Rename files in a directory with subdirectories"
    return 1
  fi

  rm -rf "$test_dir"
}

test_rename_empty_dir() {
  ./rename.sh
  echo "Test passed: Rename files in an empty directory"
}

test_rename_files
test_rename_empty_dir

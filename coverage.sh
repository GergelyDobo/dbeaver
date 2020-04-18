#!/bin/bash

path="$(pwd)/coverage/index.html"

#mvn test

rm -R "coverage"
mkdir "coverage"
touch "coverage/index.html"


echo "<!DOCTYPE html>
<html>
<head>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 20%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>

<h1>Coverage</h1>

<h2>DBeaver - Plugins </h2>" >> $path

cd "plugins"

echo "<table>" >> $path 
echo "<tr><th>Name</th></tr>" >> $path
for d in */ ; do
	cd "$d"
    echo "<tr><td><a href='../plugins/${d}target/site/jacoco/index.html'>${d} </a></td></tr>" >> $path
    cd ".."
done
echo "</table>" >> $path
echo "</body>" >> $path
echo "</html>" >> $path
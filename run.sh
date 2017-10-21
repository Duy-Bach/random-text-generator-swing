#!/bin/bash
javac -d bin $(find ./src/* | grep .java)
java -cp bin duybach.example.swing.SwingFrame

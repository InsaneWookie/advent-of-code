const fs = require('fs');
const _ = require('lodash')

const values = fs.readFileSync('data.txt').toString().split("\n").map(b => b.split(' '));

console.log(values)

let notInf = true

let visitedLines = []
let acc = 0
let line = 0
while(notInf) {
    let instruction = values[line][0]
    let value = parseInt(values[line][1])
    console.log(values[line])
    if(instruction === 'acc'){
        acc += value
        line++
    } else if(instruction === 'jmp'){
        line += value
    } else {
        // acc++
        line++
    }

    if(visitedLines.indexOf(line) > -1){
        break
    } else {

        visitedLines.push(line)
    }



}

console.log(acc)

const fs = require('fs');
const _ = require('lodash')

const values = fs.readFileSync('data.txt').toString().split("\n").map(b => b.split(' '));

let finished = false

for(let i = 0; i < values.length; i++){
    if(values[i][0] === 'nop'){
        values[i][0] = 'jmp'
    } else if(values[i][0] === 'jmp'){
        values[i][0] = 'nop'
    }

    let visitedLines = []
    let acc = 0
    let line = 0

    while(true) {
        //moved off the end of the program so exit
        if(!values[line]){
            console.log('found end')
            console.log(acc)
            finished = true
            break;

        }

        let instruction = values[line][0]
        let value = parseInt(values[line][1])
      //  console.log(values[line])

        visitedLines.push(line)

        if(instruction === 'acc'){
            acc += value
            line++
        } else if(instruction === 'jmp'){
            line += value

        } else {
            line++
        }

        if(visitedLines.indexOf(line) > -1){
            break
        }
    }
    if(finished){
        break
    }

    if(values[i][0] === 'nop'){
        values[i][0] = 'jmp'
    } else if(values[i][0] === 'jmp'){
        values[i][0] = 'nop'
    }
}

